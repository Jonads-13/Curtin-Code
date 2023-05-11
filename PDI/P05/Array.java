import java.util.*;
public class TwoDArray
{
	public static void main(String[] args)
	{

		//Declare variables
        int numOne, numTwo, i, j;
        double[][] theArray;
        
		//Introduce scanner and random number generator
        Scanner sc = new Scanner(System.in);
        
		//Display prompt to user for row number and the receive input
        System.out.println("Please enter the amount of rows you would like for the Array: ");
        numOne = sc.nextInt();

		//Display prompt to user for column number and receive the input
		System.out.println("Please enter the amount of columns you would like for the Array: ");
		numTwo = sc.nextInt();

        theArray = new int[numOne][numTwo];

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
        
        
