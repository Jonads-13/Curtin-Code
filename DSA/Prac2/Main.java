/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     Main                                     *
 * Author:    Jacob Jonas, 18439731                    *
 * Created:   06/08/2022                               *
 * Modified:  16/08/2022                               *
 * Assertion: To implement various recusive functions  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in); // Create Scanner for user input
		
		// Variables to hold user inputs
		int input = 0, choice = 0;
		boolean close = false; // Variable to conrol the do-while loop

		do // Begin do-while loop
		{
			// Present a menu to the user
			System.out.println("\nWhich function to use?\n\n>1 Factorial\n>2 Fibonacci\n>3 Greatest Common Denominator\n>4 Convert base 10 number to another base\n>0 Exit program");
			choice = sc.nextInt(); // Get user input
			
			// If statement stipulating choices
			if(choice == 1) // Factorial choice
			{
				// Prompt user to enter a number
				System.out.println("Enter an integer:");
				input = sc.nextInt(); // Get user input
			
				// Variables to hold the return values from the functions
				long factI = 0, factR = 0;			

				try
				{
					// Call both factorial methods throught their wrappers
					factI = Iterative.factItWrap(input);
					factR = Recursive.factRecWrap(input);
				
					// Dsiplay th results to the user
					System.out.println("\nFrom iterative = " + factI + ", From recursive = " + factR);
				}
				catch(IllegalArgumentException e) // Catch exception thrown by wrapper
				{
					System.out.println(e.getMessage());
				}

			}
			else if(choice == 2) // Fibonacci choice
			{
				// Prompt user to enter a number
				System.out.println("Enter an integer:");
				input = sc.nextInt(); // Get user input
			
				// Variables to hold the return values from the functions
				int fibI = 0, fibR = 0;
			
				try
				{
					// Call both fibonacci methods throught their wrappers
					fibI = Iterative.fibItWrap(input);
					fibR = Recursive.fibRecWrap(input);

					// Display results to the user
					System.out.println("\nFrom iterative = " + fibI + ", From recursive = " + fibR);
				}
				catch(IllegalArgumentException e) // Catch exception thrown by wrapper
				{
					System.out.println(e.getMessage());
				}
			}
			else if(choice == 3) // Greatest common denomitato choice
			{
				// Prompt user to enter a number
				System.out.println("Enter the first integer:");
				int numOne = sc.nextInt(); // Get user input

				// Prompt user to enter a number
				System.out.println("Enter the second integer:");
				int numTwo = sc.nextInt(); // Get user input
				
				// Variable to hold gcd value
				int gcd;
				
				try
				{
					gcd = Recursive.gcdWrap(numOne, numTwo); // Call method to determoine the gcd
				}
				catch(IllegalArgumentException e) // Catch exception thrown by wrapper
				{
					System.out.println(e.getMessage());

					if(numOne > numTwo) // Basically return which ever number isn't 0
					{
						gcd = numOne;
					}
					else
					{
						gcd = numTwo;
					}
				}

				// Display result to the user
				System.out.println("\nThe Greatest Common Denominator is: " + gcd);
			}
			else if(choice == 4) // Convert base 10 to another base choice
			{
				// Prompt user to enter a number
				System.out.println("Enter a base 10 number");
				int num = sc.nextInt(); // Get user input
			
				// Prompt user to enter a number to be the base to convert to
				System.out.println("Enter the new base to convert to:");
				int base = sc.nextInt(); // Get user input

				// Use a string variable to hold the converted number
				String newNum = "";

				try
				{
					newNum = Recursive.decConWrap(num, base); 

					// Display the converted number to the user
					System.out.println("\n" + num + " converted to base " + base + " is: " + newNum);
				}
				catch(IllegalArgumentException e) // Catch exception thrown by wrapper
				{
					System.out.println(e.getMessage());
				}
			}
			else if(choice == 0) // Exit program choice
			{
				close = true; // Exit do - while loop
			}
			else // User choice not stipulated
			{
				System.out.println("Invlaid choice"); // Notify user their choice is not stipulated.
			}
		}while(!close); // End of do-while loop

		sc.close(); // Close scanner object
	} // End main()
}
			
