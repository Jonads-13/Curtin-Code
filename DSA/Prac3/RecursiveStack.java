/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     RecursiveStack                           *
 * Author:    Jacob Jonas, 18439731                    *
 * Created:   22/08/2022                               *
 * Modified:  22/08/2022                               *
 * Assertion: To implement various recusive functions  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class RecursiveStack 
{

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    facRec                                                       *
 * Author:    Jacob Jonas, 18439731                                        *
 * Created:   06/08/2022                                                   *
 * Modified:  22/08/2022                                                   *
 * Import:    n (Integer), stack (String)                                  *
 * Export:    fact (long)                                                  *
 * Assertion: Calulate the factorial of an imported parameter recursively  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static long factRec(int n, String stack)
	{
		long fact = 1;

		if(n < 1) // Return 1 if n equals 0
		{
			fact = 1;
		}
		else
		{
			// Update stack
            stack = stack + "factRec: n = " + n + " ";
            System.out.println(stack); // Print current stack
			fact = n * factRec(n-1,stack); // Multiply n by n-1 recursively until factorial has been calculated
            System.out.println(stack); // Pop off of stack
		}
		return fact; // Return calculated value
	} // End factRec()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    fibRec                                                           *
 * Author:    Jacob Jonas, 18439731                                            *
 * Created:   06/08/2022                                                       *
 * Modified:  22/08/2022                                                       *
 * Import:    n (Integer), stack (String)                                      *
 * Export:    fib (Integer)                                                    *
 * Assertion: Calculate the nth number of the fibonacci sequence recursively   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static int fibRec(int n, String stack)
	{
		int fibVal = 0;

		if(n == 0)
		{
			fibVal = 0;
		}
		else if(n == 1)
		{
			fibVal = 1;
		}
		else
		{
			// Update stack
            stack = stack + "fibRec: n = " + n + " ";
            System.out.println(stack); // Print out current stack
            // Calculate value with two recursive calls
			fibVal = fibRec(n-1,stack) + fibRec(n-2,stack);
            System.out.println(stack); // Pop off of stack
		}
		return fibVal;
	} // End fibRec()

		
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    gcd                                                              *
 * Author:    Jacob Jonas, 18439731                                            *
 * Created:   06/08/2022                                                       *
 * Modified:  16/08/2022                                                       *
 * Import:    numOne (Integer), numTwo (Integer), stack (String)               *
 * Export:    gcd (Integer)                                                    *
 * Assertion: Calculate the greatest common denominator between two numbers    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static int gcd(int numOne, int numTwo, String stack)
	{
		int gcd;
        
		if(numTwo == 0)
        {
            gcd = numOne;
        }
        else
        {
			//Updae stack
            stack = stack + "gcd: numOne = " + numOne + ", numTwo = " + numTwo + " ";
            System.out.println(stack); // Print current stack
            gcd = gcd(numTwo, numOne%numTwo,stack); // Recursive call with numTwo becoming numOne and the remainder becoming numTwo
            System.out.println(stack); // Pop off of stack
        }
		
		return gcd;
	} // End gcd()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    decCon                                                         *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   06/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer), base (Integer), stack (String)                    *
 * Export:    convertedNum (String)                                          *
 * Assertion: Convert a base 10 number into another imported base parameter  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public static String decCon(int n, int base, String stack)
	{
		// String to hold the converted number as, depending on the base, there may be characters
		String convertedNum = ""; 

		if(n <= 0) 
		{
			convertedNum = "";
		}
		else
		{
			// Variable to hold the remainder
			int r = n%base;

			if(r >= 10) // Remainder being greater than 10 measn the next value will be a character
			{
				// Recursive call with adding the type casted remainder to the string
                stack = stack + "decCon: n = " + n + " base = " + base + " ";
                System.out.println(stack);
				convertedNum = decCon(n/base, base,stack) + (char)(r + 55); 
                System.out.println(stack);
			}
			else // remainder is less than 10
			{
				// updating stack
                stack = stack + "decCon: n = " + n + " base = " + base + " ";
                System.out.println(stack); // Print out current stack
				convertedNum = decCon(n/base, base,stack) + (n%base); // Recursive call adding the remainder to the string
                System.out.println(stack); // Pop off the stack
			}
		}
		return convertedNum;
	} // End decCon()
}

