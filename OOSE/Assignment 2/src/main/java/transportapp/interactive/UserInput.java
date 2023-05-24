package transportapp.interactive;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Handles input from the user
 **/

import java.util.Scanner;
import java.util.logging.Logger;

public class UserInput 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(UserInput.class.getName());
    
    private static final Scanner SC = new Scanner(System.in);

    public UserInput() {} // Constructor
    

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Aquires a filename from the user
     *
     * @param     type The type of file that the filename represents
     *
     * @returns   filename entered by the user
     **/
    
    public String getFilename(String type)
    {
        logger.info(()-> "Aquiring " + type + " filename from the user");
        System.out.println("Please enter the name of the " + type + "file to use: ");
        System.out.println("Note: default is: " + type + "s.txt");
        return SC.nextLine();
    }





    

    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Process integer input from the user
     *
     * @param     void
     * 
     * @returns:  A single integer input from the user
     **/
    
     public int getIntegerInput()
     {
         while(true) // Loop until valid input
         {
             try
             {
                 logger.info("Aquiring user input for an Intger");
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
      * @author    Jacob Jonas, 18439731
      *
      * @assertion close scanner used in the class
      *
      * @param     void
      *
      * @returns   void
      **/

     public void closeScanner()
     {
        SC.close();
     }
}
