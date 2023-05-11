import java.util.*;

public class CelsAndFaren
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
       
        //delcaring variables
        double numCels, numFaren, answer;
        char tempChar;

        //Creating menu for user
        System.out.println("Which temperature scale are you converting from?");
        System.out.println("        >(C)elsius");
        System.out.println("        >(F)arenheit");

        tempChar = sc.next().charAt(0);
        
        //Begining if statement for temperature selection
        if ((tempChar == 'C') || (tempChar== 'c'))
        {

            //Begin Celsius input section
            System.out.println("Please enter the Celsius value: ");
            numCels = sc.nextDouble();

            //Execute Celsius calculation
            answer = numCels * (9.0/5.0) + 32;

            //Display converterd temperature
            System.out.println("The temperature in Farenheit is: " + answer);
        
        }

        else if ((tempChar == 'F') || (tempChar ==  'f'))
        {

            //Begin Farenheit input section
            System.out.println("Please enter the Farenheit value: ");
            numFaren = sc.nextDouble();

            //Begin Farenheit calculation
            answer = (numFaren - 32) * (5.0/9.0);

            //Display converted temperature
            System.out.println("The temperature in Celsius is: " + answer);
        
        }

        //Display an error if user enters invalid character
        else 
        {

            System.out.println("Error. Invalid input"); 

        }
    }
}
