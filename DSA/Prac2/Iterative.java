/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     Iterative                                                  *
 * Author:    Jacob Jonas, 18439731                                      *
 * Created:   14/08/2022                                                 *
 * Modified:  16/08/2022                                                 *
 * Assertion: To implement iterative version of factorial and fibonacci  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class Iterative 
{

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    factItWrap                                                     *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   13/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer)                                                    *
 * Export:    fact (long)                                                    *
 * Assertion: Ensure parameters are valid before calling the proper function *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static long factItWrap(int n)
	{
		if(n < 0) // Ensure value is not negative
		{
			throw new IllegalArgumentException("Error: number cannot be lees than 0");
		}
		else
		{
			long fact = factIt(n); // Call method
			return fact;
		}
	} // End factItWrap()		

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    factIt                                                         *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   06/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer)                                                    *
 * Export:    fact (long)                                                    *
 * Assertion: Calculate the factorial of an imported parameter iteratively   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static long factIt(int n)
	{
		long fact = 1;

		if(n == 0)
		{
			fact = 1;
		}
		else
		{
			for(int i = n; i >= 2; i--) // Descend until multiplying by 2, as 1 means nothing
			{
				// Multiply the currently calcualted factorial by the next number in the descending sequence
				fact = fact * i; 
			}
		}
		return fact;
	} // End factIt()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    fibItWrap                                                      *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   13/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Import:    n (Integer)                                                    *
 * Export:    fib (Integer)                                                  *
 * Assertion: Ensure parameters are valid before calling the proper function *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int fibItWrap(int n)
	{
		if(n < 0) // Ensure number is positive
		{
			throw new IllegalArgumentException("Error: number cannot be lees than 0");
		}
		else
		{
			int fib = fibIt(n); // Call method
			return fib;
		}
	} // End fibItWrap()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    fibIt                                                            *
 * Author:    Jacob Jonas, 18439731                                            *
 * Created:   06/08/2022                                                       *
 * Modified:  16/08/2022                                                       *
 * Import:    n (Integer)                                                      *
 * Export:    fib (Integer)                                                    *
 * Assertion: Calculate the nth number of the fibonacci sequence iteratively   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	private static int fibIt(int n)
	{
		// Values to initialise staring position
		int fibVal = 0;
		int currVal = 1;
		int lastVal = 0;

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
			for(int i = 1; i < n; i++) // loop until desired fibonacci index
			{
				fibVal = currVal + lastVal; // Perform fibonacci calculation
				
				// Update values ready for next iteration
				lastVal = currVal;
				currVal = fibVal;
			}
		}
		return fibVal;
	} // End fibIt()
}
