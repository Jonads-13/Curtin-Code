package projectsearcher;

import java.util.*;
import java.util.logging.Logger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   10/04/2023
 * @Modified  11/04/2023
 * @Assertion Contains the main funcionality of the application
 **/

public class Interactive 
{
    // Warning suppressed under advice from David given in the form of a piazza reply.
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Interactive.class.getName());

    private static final Scanner SC = new Scanner(System.in);
    private static final Criteria DEFAULT_CRITERIA = new IncludeRegex(".*");
    private static final String DEFAULT_OUTPUT = "count";

    public Interactive() {}

    /**
     * @Author:      Jacob Jonas, 18439731
     * @Created:     30/03/2023
     * @Modified:    10/04/2023
     *
     * @Assertion:   Display the application menu to the user
     *
     * @param        root 
     * 
     * @Returns:     void
     **/

    public void mainMenu(Entry root)
    {
        List<Criteria> criteria = new LinkedList<>();
        boolean close = false;

        // Ensures if user skips to report, the program will still apply default values
        criteria.add(DEFAULT_CRITERIA); 
        String outputFormat = DEFAULT_OUTPUT;
        
        do
        {
            // Display Menu
            System.out.println("> 1 Set Criteria");
            System.out.println("> 2 Set Output Format");
            System.out.println("> 3 Report");
            System.out.println("> 0 Quit");
            
            int choice = getIntegerInput();

            switch(choice)
            {
                case 1: 
                    logger.info(() -> "User chose Set Criteria");
                    criteria = enterCriteria(); 
                    break;
                    
                case 2: 
                    logger.info(() -> "User chose Set Output Format");
                    outputFormat = chooseOutputFormat();
                break;

                case 3:
                    logger.info(() -> "User chose Report"); 
                    report(root, criteria, outputFormat);
                break;

                case 0: 
                    System.out.println("Goodbye");
                    close = true;
                    logger.info(() -> "Exiting program");
                break;

                default:
                    logger.warning(() -> "Option: " + choice + " not stipulated. Choose again.");
                    System.out.println("Option: " + choice + " not stipulated. Choose again.\n");
                break;
            }
        }while(!close); 
    } // End mainMenu()






    /**
     * @Author:    Jacob Jonas, 18439731
     * @Created:   06/04/2023
     * @Modified:  09/04/2023
     *
     * @Assertion: Gets user input for their selected search criteria and, if valid, adds to a list
     *
     * @param      void
     * 
     * @Returns    A List of Criteria Objects created from user entered criteria
     **/
    
     private List<Criteria> enterCriteria()
     {
         // Holds Filter objects made from user entered criteria
         List<Criteria> criteriaList = new LinkedList<>();
         Validate valid = new Validate();
         
         // Display menu and proper criteria format
         System.out.println("Enter search criteria in the form of:\n");
         System.out.println("x y criteria\n");
         System.out.println("x:        Either \"i\" or \"e\" for include/exclude");
         System.out.println("y:        Either \"t\" or \"r\" for text/regular expression");
         System.out.println("criteria: The pattern to match each line against");
         System.out.println("When finished enter a blank line");
         
         logger.info("Aquiring search criteria from the user");
         String criteria = SC.nextLine();
         
         while(criteria.trim().length() > 0) // End at a blank line
         {
             if(valid.isValidCriteria(criteria)) // Criteria format is correct
             {
                 criteriaList.add(parseCriteria(criteria)); // Create Criteria Object
                 logger.info("Criteria successfully added");
             }
             
             criteria = SC.nextLine();
         }
         
         // If user exits without entering anything, ensure default criteria is still used.
         if(criteriaList.size() == 0)
         {
             criteriaList.add(DEFAULT_CRITERIA);
             logger.warning("Default criteria had to used as user didn't enter anything");
         }
         
         return criteriaList;
     } // End enterCriteria()









    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   09/04/2023
     * @Modified  09/04/2023
     *
     * @Assertion Split the criteria string into useable parts to aquire relevant object
     *
     * @param    critieria
     * 
     * @Returns  A single Criteria object
     **/

    private Criteria parseCriteria(String criteria)
    {
        // Split criteria into its respective parts
        String[] split = criteria.split(" ", 3);

        String objectType = split[0] + split[1];
        String pattern = split[2];

        return getCriteriaObject(objectType, pattern);
    } // End parseCriteria()






    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   09/04/2023
     * @Modified  09/04/2023
     *
     * @Assertion Return a specified Criteria type object with pattern as parameter
     *
     * @param     ObjectType Specifes which object to get from the map
     * @param     pattern Specifies the parameter for the object constructor
     * 
     * @Returns   A single Criteria object
     **/
    
    private Criteria getCriteriaObject(String objectType, String pattern)
    {
        Map<String, Criteria> criteria = new HashMap<>();
            
        criteria.put("it", new IncludeText(pattern));
        criteria.put("ir", new IncludeRegex(pattern));
        criteria.put("et", new ExcludeText(pattern));
        criteria.put("er", new ExcludeRegex(pattern));

        logger.info(() -> "Returning an: " + objectType + " Criteria Object");

        return criteria.get(objectType);
    }






    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   06/04/2023
     * @Modified  06/04/2023
     *
     * @Assertion User chooses how to display the report information
     *
     * @param     void
     * 
     * @Returns   String representation of the chosen output format
     **/
    
    private String chooseOutputFormat()
    {
        while(true)
        {
            //Display menu
            System.out.println("> 1 Count (Default)");
            System.out.println("> 2 Show");
            
            int choice = getIntegerInput();
            
            switch(choice)
            {
                case 1: return "count";
                case 2: return "show";

                default: 
                    logger.warning(() -> "Option: " + choice + " not stipulated. Choose again.");
                    System.out.println("Option: " + choice + " not stipulated. Choose again."); 
                break;
            }
        }
    } // End chooseOutputFormat() 

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Displays information about the directory tree
     *
     * @param     root Global parent of the directory tree
     * @param     criteria List of Criteria objects to apply to each file
     * @param     format Specifes which display format to use
     * 
     * @Returns   void
     **/
    
    private void report(Entry root, List<Criteria> criteria, String format)
    {
        if(root == null || criteria == null || format == null || format.equals(""))
        {
            logger.severe("Parameters are null or blank");
            throw new IllegalArgumentException(String.format(
            "root and criteria can't be null. format: %s can't be null or blank", format));
        }

        root.applyCriteria(criteria);
        root.display(format);
    } // End report()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   30/03/2023
     * @Modified  06/04/2023
     *
     * @Assertion Process integer input from the user
     *
     * @param     void
     * 
     * @Returns:  A single integer input from the user
     **/
    
    private int getIntegerInput()
    {
        while(true) // Loop until valid input
        {
            try
            {
                logger.info("Aquiring user input");
                int choice = Integer.parseInt(SC.nextLine());
                return choice;
            }
            catch(NumberFormatException e)
            {
                logger.warning("User entered an invalid integer");
                System.out.println("Please enter a valid integer");
            }
        }
    } // End getIntergerInput()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Close the Scanner object used within the class
     *
     * @param     void  
     *
     * @Returns   void
     **/

     public void closeScanner()
     {
        SC.close();
     }
    
    
}
