package projectsearcher;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   10/04/2023
 * @Modified  10/04/2023
 * @Assertion Contains Validation methods
 **/

public class Validate 
{
    // Warning suppressed under advice from David given in the form of a piazza reply.
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Validate.class.getName());

    public Validate() {} // Constructor




    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   06/04/2023
     * @Modified  06/04/2023
     *
     * @Assertion Determines if an entered criteria is valid
     *
     * @param     tempCriteria
     * 
     * @Returns   Whether or not the user entered criteria is valid or not   
     **/

     public boolean isValidCriteria(String tempCriteria)
     {
         // Removing spaces, there should be at least 3 characters
         if(tempCriteria.trim().length() < 3) 
         {
             logger.warning(() -> "Not enough characters: " + tempCriteria);
             System.out.println("Not enough characters: " + tempCriteria);

             return false;
         }
         
         // There must be spaces to separate each clause
         if(tempCriteria.charAt(1) != ' ' || tempCriteria.charAt(3) != ' ')
         {
             logger.warning(() -> "Missing proper spacing: " + tempCriteria);
             System.out.println("Missing proper spacing: " + tempCriteria);
             return false;
         }
 
         // First character specifies either including or excluding
         if((tempCriteria.charAt(0) != 'i') && (tempCriteria.charAt(0) != 'e'))
         {
             logger.warning(() -> "No include or exculde choice: " + tempCriteria.charAt(0));
             System.out.println("No include or exculde choice: " + tempCriteria.charAt(0));
             return false;
         }
         
         // Second character specifies text or regex
         if(tempCriteria.charAt(2) != 't' && tempCriteria.charAt(2) != 'r')
         {
             logger.warning(() -> "No text or regex choice: " + tempCriteria);
             System.out.println("No text or regex choice: " + tempCriteria);
             return false;
         }
 
         // Regex must be valid
         if((tempCriteria.charAt(2) == 'r') && !isValidRegex(tempCriteria))
         {
             return false;
         }
 
         return true;
     } // End isValidCriteria()





     /**
     * @Author    Jacob Jonas, 18439731
     * @Created   06/04/2023
     * @Modified  06/04/2023
     *
     * @Assertion Determines if an entered regex is valid
     *
     * @param     expression regex to be checked if valid
     * 
     * @Returns   Whether the expression is valid or not
     **/

    public boolean isValidRegex(String expression)
    {
        try
        {
            Pattern.compile(expression);
            return true;
        }
        catch(PatternSyntaxException e)
        {
            logger.severe(() -> "Invalid regex: " + expression);
            System.out.println("Invalid regex: " + expression);
            return false;
        }
    } // End isValidRegex()
}
