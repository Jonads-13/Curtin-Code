/* * * * * * * * * * * * * * *
 * Title:    Menu            *
 * Author:   Jacob Jonas     *
 * Created:  18/03/2022      *
 * Modified: 01/04/2022      *
 * * * * * * * * * * * * * * */

import java.util.*;
public class MethodsMenu
{
     public static void main(String[] args)
     {

		//Introduce scanner and declare variables
        Scanner sc = new Scanner(System.in);
        int choice;
		String answer;
        boolean close = false;       

		while(!close)
		{		
	
		    try
		    {        

				do
				{
				//Present menu to user and receive input
				System.out.println("What would you like to do? ");
				System.out.println("        > 1. Sum of two integers");
				System.out.println("        > 2. Product of two integers");
				System.out.println("        > 3. Deterimine if two integers are divisible");
				System.out.println("        > 0. Exit");
		 
				choice = sc.nextInt();
		 		
				//Begin switch statement
				switch(choice)
				{

				//Begin code for addition option
				case 1:
						//Call method for sum
				        answer = sum2Ints();
						
						//Display calculated answer		                
						System.out.println("");
				        System.out.println(answer);
						System.out.println("");
		
				break;
		 
				//Begin code for multiplication option
				case 2:
						//Call method for product
				        answer = prod2Ints();
		 
						//Display calculated answer
						System.out.println("");
				        System.out.println(answer);
						System.out.println("");
				break;
		 
				//Begin code for division option
				case 3:
						//Call method for Divsion
						answer = div2Ints();
						
						//Display calculated answer
						System.out.println("");
				        System.out.println(answer);
						System.out.println("");
				break;
		 
				//Exit option
				case 0:
				    	System.out.println("Goodbye");
		 				close = true;
				break;

				//Input error contigency
				default:
				        System.out.println("Error. Invalid input");
						System.out.println("");
				        
				    }        
				    
					}

				while(!close);
		 
				}
		
			//Catch mismatch error
			catch(InputMismatchException error)
			{
				//Notify user of invalid input
				System.out.println("Error. Invalid input");
				System.out.println("");
				//Clear incorrect scanner input			
				sc.next();
			}

		}

    }

	/* * * * * * * * * * * * * * * * * * * * *
	 * Method:   Sum of 2 Integers           *
	 * Author:   Jacob Jonas                 *
	 * Created:  01/04/2022                  *
	 * Modified: 01/04/2022                  *
	 * Import:   none                        *
	 * Export:   message (string)            *
	 * Purpose:  Add two integers together   *
	 * * * * * * * * * * * * * * * * * * * * */
    public static String  sum2Ints()
    {
		//Introduce scanner and declare variables
        Scanner sc = new Scanner(System.in);
        int numOne, numTwo, ans;
		String message = "";
        
        //try
        //{

		//Get inputs from the user
        System.out.println("");
        System.out.println("Please input the first number: ");
        numOne = sc.nextInt();
 
        System.out.println("");
        System.out.println("Please enter the second number: ");
        numTwo = sc.nextInt();
        //}
        
        //catch(InputMismatchException error)
        //{
            //Notify user of invalid input
            //System.out.println("Error. Invalid input");
            //System.out.println("");
            //clear scanner input
            //sc.next();
        //}

		//Call method for sum calculation
        ans = sum2IntsCalc(numOne, numTwo);

		//Create a readable output of the answer for the user
		message = numOne + " + " + numTwo + " = " + ans;

        return message;
     }

	/* * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   Product of 2 Integers             *
	 * Author:   Jacob Jonas                       *
	 * Created:  01/04/2022                        *
	 * Modified: 01/04/2022                        *
	 * Import:   none                              *
	 * Export:   message (string)                  *
	 * Purpose:  Multiply two integers together    *
	 * * * * * * * * * * * * * * * * * * * * * * * */

    public static String prod2Ints()
    {
		//Introduce scanner and declare variables
        Scanner sc = new Scanner(System.in);
        int numOne, numTwo, ans;
		String message;

		//Get inputs from the user
        System.out.println("");
        System.out.println("Please input the first number: ");
        numOne = sc.nextInt();

        System.out.println("");
        System.out.println("Please enter the second number: ");
        numTwo = sc.nextInt();
 
        //Call method for product calcualtion
        ans = prod2IntsCalc(numOne, numTwo);
		message = numOne + " x " + numTwo + " = " + ans;

        return message;
    }

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   Division of 2 Integers                    *
	 * Author:   Jacob Jonas                               *
	 * Created:  01/04/2022                                *
	 * Modified: 01/04/2022                                *
	 * Import:   none                                      *
	 * Export:   message (String)                          *
	 * Purpose:  Determine if two integers are divisible   *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static String div2Ints()
	{

		//Introduce scanner and declare variables
		Scanner sc = new Scanner(System.in);
		int numOne, numTwo, divAns;
		boolean divBool;
		String message;
		
		//Get inputs from the user
		System.out.println("");
		System.out.println("Please input the first number: ");
		numOne = sc.nextInt();

		System.out.println("");
		System.out.println("Please enter the second number: ");
		numTwo = sc.nextInt();

		//Determine if numbers are divisible
		divBool = div2IntsBool(numOne, numTwo);

		//If statement for division of integers
		if (divBool == true)
		{
			//Call method for division calculation
			divAns = div2IntsCalc(numOne, numTwo);

			message = "Yes, these integers are divisible. The answer is: " + divAns;
		}
		else
		{				
			message = "No, these integers are not divisible";
		}

		return message;

	}
        
	/* * * * * * * * * * * * * * * * * * * * * *
	 * Method:   Sum of 2 Integers calulation  *
	 * Author:   Jacob Jonas                   *
	 * Created:  01/04/2022                    *
	 * Modified: 01/04/2022                    *
	 * Import:   numOne (int), numTwo (int)    *
	 * Export:   ans (int)                     *
	 * Purpose:  Add two integers together     *
	 * * * * * * * * * * * * * * * * * * * * * */
	
	public static int sum2IntsCalc(int pNumOne, int pNumTwo)
	{
		//Perform sum calculation
	    int ans = pNumOne + pNumTwo;

		return ans;
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   Product of 2 Integers Calculation *
	 * Author:   Jacob Jonas                       *
	 * Created:  01/04/2022                        *
	 * Modified: 01/04/2022                        *
	 * Import:   numOne (int), numTwo (int)        *
	 * Export:   answer (int)                      *
	 * Purpose:  Multiply two integers together    *
	 * * * * * * * * * * * * * * * * * * * * * * * */
	
	public static int prod2IntsCalc(int pNumOne, int pNumTwo)
	{
		//Perform product caculation
		int ans = pNumOne * pNumTwo;

		return ans;
	}
   
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   Possible division of 2 Integers           *
	 * Author:   Jacob Jonas                               *
	 * Created:  01/04/2022                                *
	 * Modified: 01/04/2022                                *
	 * Import:   numOne (int), numTwo (int)                *
	 * Export:   answer (boolean)                          *
	 * Purpose:  Determine if two integers are divisible   *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public static boolean div2IntsBool(int pNumOne, int pNumTwo)
	{
        //Check integer division possibility
		boolean ans = (pNumOne%pNumTwo==0);

		return ans;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   Division of 2 Integers Calculation        *
	 * Author:   Jacob Jonas                               *
	 * Created:  01/04/2022                                *
	 * Modified: 01/04/2022                                *
	 * Import:   numOne (int), numTwo (int)                *
	 * Export:   ans (int)                                 *
	 * Purpose:  Divide two integers whose remainder is 0  *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static int div2IntsCalc(int pNumOne, int pNumTwo)
	{
		//Peform division calculation
		int ans = pNumOne/pNumTwo;

		return ans;

	}
	
 
 }

