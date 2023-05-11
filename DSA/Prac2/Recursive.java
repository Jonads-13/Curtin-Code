/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     Recursive                                *
 * Author:    Jacob Jonas, 18439731                    *
 * Created:   14/08/2022                               *
 * Modified:  16/08/2022                               *
 * Assertion: To implement various recusive functions  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class Recursive 
{

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    facRectWrap                                                    *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   13/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer)                                                    *
 * Export:    fact (long)                                                    *
 * Assertion: Ensure parameters are valid before calling the proper function *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static long factRecWrap(int n)
	{
		if(n < 0) // Ensure number is larger than 0
		{
			throw new IllegalArgumentException("Error: number cannot be lees than 0");
		}
		else
		{
			long fact = factRec(n); // Send value to the actual method
			return fact;
		}
	} // End factRecWrap()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    facRec                                                       *
 * Author:    Jacob Jonas, 18439731                                        *
 * Created:   06/08/2022                                                   *
 * Modified:  16/08/2022                                                   *
 * Import:    n (Integer)                                                  *
 * Export:    fact (long)                                                  *
 * Assertion: Calulate the factorial of an imported parameter recursively  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static long factRec(int n)
	{
		long fact = 1;

		if(n < 1) // Return 1 if n equals 0
		{
			fact = 1;
		}
		else
		{
			// Multiply n by n-1 recursively until factorial has been calculated
			fact = n * factRec(n-1); 
		}
		return fact; // Return calculated value
	} // End factRec()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    fibRecWrap                                                     *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   13/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer)                                                    *
 * Export:    fib (Integer)                                                  *
 * Assertion: Ensure parameters are valid before calling the proper function *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int fibRecWrap(int n)
	{
		
		if(n < 0) // Ensure value is not negative
		{
			throw new IllegalArgumentException("Error: number cannot be less than 0");
		}
		else
		{
			int fib = fibRec(n); // Call method
			return fib; 
		}
	} // End fibRecWrap()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    fibRec                                                           *
 * Author:    Jacob Jonas, 18439731                                            *
 * Created:   06/08/2022                                                       *
 * Modified:  16/08/2022                                                       *
 * Import:    n (Integer)                                                      *
 * Export:    fib (Integer)                                                    *
 * Assertion: Calculate the nth number of the fibonacci sequence recursively   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static int fibRec(int n)
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
			// Calculate value with two recursive calls
			fibVal = fibRec(n-1) + fibRec(n-2);
		}
		return fibVal;
	} // End fibRec()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    gcdWrap                                                        *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   13/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    numOne (Integer), numTwo (Integer)                             *
 * Export:    gcd (Integer)                                                  *
 * Assertion: Ensure parameters are valid before calling the proper function *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int gcdWrap(int numOne, int numTwo)
	{
		if((numTwo == 0) || (numOne == 0)) // Ensure neither value is 0
		{
			throw new IllegalArgumentException("Error: Cannot divide by 0");
		}
		else
		{
			int gcd = gcd(numOne, numTwo); // Call method
			return gcd;	
		}
	} // End gcdWrap()
		
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    gcd                                                              *
 * Author:    Jacob Jonas, 18439731                                            *
 * Created:   06/08/2022                                                       *
 * Modified:  16/08/2022                                                       *
 * Import:    numOne (Integer), numTwo (Integer)                               *
 * Export:    gcd (Integer)                                                    *
 * Assertion: Calculate the greatest common denominator between two numbers    *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static int gcd(int numOne, int numTwo)
	{
		int gcd;
        
		if(numTwo == 0)
        {
            gcd = numOne;
        }
        else
        {
			// Recursive call with numTwo becoming numOne and the remainder becoming numTwo
            gcd = gcd(numTwo, numOne%numTwo);
        }
		
		return gcd;
	} // End gcd()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    decConWrap                                                     *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   13/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer), base (Integer)                                    *
 * Export:    convertedum (String)                                           *
 * Assertion: Ensure parameters are valid before calling the proper function *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static String decConWrap(int n, int base)
	{
		if(base <= 0) // Ensure base is greater than 0
		{
			throw new IllegalArgumentException("Base cannot be 0 or negative");
		}
		else
		{
			String convertedNum = decCon(n, base); // Call method
			return convertedNum;
		}
	} // End decConWrap()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    decCon                                                         *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   06/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer), base (Integer)                                    *
 * Export:    convertedNum (String)                                          *
 * Assertion: Convert a base 10 number into another imported base parameter  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	private static String decCon(int n, int base)
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
				convertedNum = decCon(n/base, base) + (char)(r + 55); 
			}
			else // remainder is less than 10
			{
				// Recursive call adding the remainder to the string
				convertedNum = decCon(n/base, base) + (n%base);
			}
		}
		return convertedNum;
	} // End decCon()
}
