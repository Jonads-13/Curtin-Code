import java.util.*;
public class TwoDArray
{
	public static void main(String[] args)
	{

		//Declare variables
        int numOne, numTwo;
        int[][] theArray;
        
		//Introduce scanner
        Scanner sc = new Scanner(System.in);
        
		//Display prompt to user for row number and the receive input
        System.out.println("Please enter the amount of rows for the Array: ");
        numOne = sc.nextInt();

		//Display prompt to user for column number and receive the input
		System.out.println("Please enter the amount of columns for the Array: ");
		numTwo = sc.nextInt();

        theArray = new int[numOne][numTwo];

		int theArrayLength = theArray.length;	

		//Begin for loop for the length equivalent to the user's input
        for(int i = 0; i < numOne; i++)
        {
			for(int j = 0; j < numTwo; j++)
			{	
            	theArray[i][j] = i*j ;
			}

        }

		//Display results to the user
		 for(int i = 0; i < numOne; i++)
        {
			for(int j = 0; j < numTwo; j++)
			{	
            	System.out.print(theArray[i][j] + " ");
				
			}System.out.println();//print on new line

        }

	}
}
        
        
