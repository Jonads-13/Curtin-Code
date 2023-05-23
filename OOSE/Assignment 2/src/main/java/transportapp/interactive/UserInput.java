package transportapp.interactive;

import java.util.Scanner;
import java.util.logging.Logger;

public class UserInput 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(UserInput.class.getName());
    
    private static final Scanner SC = new Scanner(System.in);

    public UserInput() {}
    
    public String getFilename(String type)
    {
        System.out.println("Please enter the name of the " + type + "file to use: ");
        System.out.println("Note: default is: " + type + "s.txt");
        return SC.nextLine();
    }

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
    
     public int getIntegerInput()
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

     public void closeScanner()
     {
        SC.close();
     }
}
