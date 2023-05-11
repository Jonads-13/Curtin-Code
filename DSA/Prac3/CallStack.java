/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     CallStack                                *
 * Author:    Jacob Jonas, 18439731                    *
 * Created:   22/08/2022                               *
 * Modified:  22/08/2022                               *
 * Assertion: To demonstrate the use of the call stack *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class CallStack 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String stack = "", convertedNum;
        int n, n2, base, fib, gcd;
        long fact;

        // Get some numbers to use as parameters
        System.out.println("Enter three numbers:");
        n = sc.nextInt(); //
        n2 = sc.nextInt(); //     Get user input
        base = sc.nextInt(); //

        // Go through each method sequentially to keep it simple
        fact = RecursiveStack.factRec(n, stack);
        fib = RecursiveStack.fibRec(n, stack);
        gcd = RecursiveStack.gcd(n, n2, stack);
        convertedNum = RecursiveStack.decCon(n, base, stack);

        // Print out results for fun
        System.out.println("\nfact = " + fact + " \nfib = " + fib + " \ngcd = " + gcd + " \nconvertedNum = " + convertedNum);

        sc.close();
    }
}
