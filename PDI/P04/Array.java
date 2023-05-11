import java.util.*;
public class Array
{
public static void main(String[] args)
{

		//Declare variables
        int userNum, randomNum, maxValue = 20, element = 0;
        double[] theArray;
        
		//Introduce scanner and random number generator
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        
		//Display prompt to user for an input
        System.out.println("Please enter an integer between 0 and 20: ");

		//Receive input and declare array
        userNum = sc.nextInt();
        theArray = new double[userNum];

		int theArrayLength = theArray.length;	

		//Begin for loop for the length equivalent to the user's input
        for(element = 0; element < theArrayLength; element++)
        {
            theArray[element] = rand.nextInt(maxValue);

        }

		//Display results to the user
		for(element = 0; element < theArrayLength; element++)
        {
            System.out.println("The value of element "+ (element+1) + " of the Array is: " + theArray[element]);

        }

}
}
        
        
