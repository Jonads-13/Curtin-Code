/*
 * Title:     EquationSolver
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  25/08/2022
 * Assertion: Convert equations from infix to postfix and then evaluate the equation
 */

import java.util.*;
import java.io.*;

public class EquationSolver
{
    // Throw IOException to account for BufferedReader
    public static void main(String[] args) throws IOException
    {
        // Buffered reader to ensure user entered equations are read correctly
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

        // Scanner for choice input
        Scanner sc = new Scanner(System.in);

        // Variabke declarations
        int choice;
        double result1, result2, resultUser;
        String equation = "";

        // Display small menu to the user
        System.out.println("\nChoose an option:\n\n>1 Enter your own equation\n>2 Use preloaded equations as parameters");
        choice = sc.nextInt(); // get user input

        // Stipulated options from user choice
        if(choice == 1)
        {
            // Prompt user to enter the equation they wish to solve
            System.out.println("Enter your equation ensuring spaces between each character: ");
            equation = br.readLine(); // Read the equation using br to remove the "\n" character
            resultUser = solve(equation);

            // Display 6the answer to the user
            System.out.println("\nAnswer to your equation is: " + resultUser); 
        }
        else if(choice == 2)
        {
            // Prewrtiien equations to prove functionality
            String equation1 = "( ( 2 - 3 ) / 4 * ( 1 + 9 ) ) * 2";
            String equation2 = "( 10.3 * ( 14 + 3.2 ) ) / ( 5 + 2 - 4 * 3 )";

            // Show the user what the inbuilt equations are
            System.out.println("\nEquation 1: " + equation1 + "\n\nEquation 2: " + equation2);
            
            result1 = solve(equation1);
            result2 = solve(equation2);

            // Display the answers for each of the equations
            System.out.println("\nAnswer for equation 1  = " + result1);
            System.out.println("\nAnswer for eqaution 2 = " + result2 + "\n");   
        }

        sc.close(); // Close scanner object
    } // End main()

/*
 * Method:    solve
 * Author:    Jacob Jonas, 18439731
 * Created:   17/08/2022
 * Modified:  17/08/2022
 * Import:    equation (String)
 * Export:    ans (Double)
 * Assertion: Send equation to convert to postfix, then send postfix to be evaluated
 */

    public static double solve(String equation)
    {
        DSAQueue postfix = parseInfixToPostfix(equation);
        double ans = evaluatePostfix(postfix);
        return ans;
    } // End solve()

/*
 * Method:    parseInfixToPostfix
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    equation (String)
 * Export:    postfix (DSAQueue)
 * Assertion: Convert equation into a postfix expression
 */

    private static DSAQueue parseInfixToPostfix(String equation)
    {
        DSAQueue postfix = new DSAQueue(); // Queue to hold the postfix expression
        DSAStack opStack = new DSAStack(); // stack to hold the operators

        // Ensure there are spaces between all characters
        try 
        {
            String[] infix = equation.split(" "); // Split the equation using the spaces    

            // Run through each character in the equation
            for(int i = 0; i < infix.length; i++)
            {
                // new variable is the current charcter in equation
                String term = infix[i];  
                
                if(term.equals("("))
                {
                    opStack.push(term); // Place opening bracket onto the stack
                }
                else if(term.equals(")")) // When a closing bracket is found
                {
                    while(!opStack.top().equals("("))
                    {
                        postfix.enqueue(opStack.pop()); // Pop all operators between the two bracks off of the stack
                    }
                    opStack.pop(); // Remove the opening bracket
                }
                // If the next character is an operator
                else if((term.equals("+")) || (term.equals("-")) || (term.equals("*")) || (term.equals("/")))
                {
                    // Check whether the current operator has a higher BIMDAS precedence or not
                    while((!opStack.isEmpty()) && (!opStack.top().equals("(")) && (precedenceOf(opStack.top().toString())) >= precedenceOf(term)) 
                    {
                        postfix.enqueue(opStack.pop()); // Remove operator from the stack
                    }
                    opStack.push(term); // Place the current operator on the stack
                }
                else // Character is a number
                {
                    // Convert the character to a double and place it in the postfix expression
                    postfix.enqueue(Double.valueOf(term));
                }  
            }
            
            while(!opStack.isEmpty())
            {
                // Place any operators left on the stack into the postfix expression
                postfix.enqueue(opStack.pop()); 
            }
        }
        catch(IllegalArgumentException e) // Catch the error thrown by not having spaces between each character
        {
            // Notify user of the error
           System.out.println("Error: Please ensure there is a space between each character"); 
        }

        return postfix;
    } // End parseInfixToPostifx()

/*
 * Method:    precedenceOf
 * Author:    Jacob Jonas, 18439731
 * Created:   17/08/2022
 * Modified:  17/08/2022
 * Import:    theOp (String)
 * Export:    precedence (Integer)
 * Assertion: Determine the precedence value of an operator
 */

    private static int precedenceOf(String theOp)
    {
        int precedence = 0;

        // Plus and minus have the same precedence
        if((theOp.equals("+")) || (theOp.equals("-")))
        {
            precedence = 1;
        }
        // Divide and multiply have the same precedence, whihc is higher than plus and minus
        else if((theOp.equals("*")) || (theOp.equals("/")))
        {
            precedence = 2;
        }

        return precedence;
    } // End precedenceOf()

/*
 * Method:    evaluatePostfix
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    postfix (DSAQueue)
 * Export:    ans (Double)
 * Assertion: Evaluate the postfix expression
 */

    private static double evaluatePostfix(DSAQueue postfix)
    {
        DSAStack opStack = new DSAStack(); // Stack to hold the operands
        double numOne, numTwo, ans = 0;

        // While there are still charactes left
        while(!postfix.isEmpty())
        {
            if(postfix.peek() instanceof Double) // Character is a number
            {
                opStack.push(postfix.dequeue()); // Place it onto the operand stack
            }
            else // Current character is an operator
            {
                // Remove the operands off the stack 
                numOne = (double)opStack.pop(); 
                numTwo = (double)opStack.pop();

                // Perform the simple equation
                ans = executeOperation(postfix.dequeue().toString(), numOne, numTwo);
                opStack.push(ans); // Ans becomes the first operand
            }
        }
        return ans;
    } // End evaluatePostfix()

/*
 * Method:    executeOperation
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    op (String), op1 (Double), op2 (Double)
 * Export:    ans (Double)
 * Assertion: Perform a single calculation
 */

    private static double executeOperation(String op, double op1, double op2)
    {
        double ans;

        // Perform calculation depending on the operator
        // Ensuring the correct order of the numbers.
        switch(op)
        {
            case "+":
                ans = op2 + op1;
            break;

            case "-":
                ans = op2 - op1;
            break;

            case "*":
                ans = op2 * op1;
            break;

            case "/":
                ans = op2/op1;
            break;

            default:
                ans = 0;  
        }
        return ans;
    } // End executeOperation()
}
