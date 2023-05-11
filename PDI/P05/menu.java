import java.util.*;
 public class MethodsMenu
 {
     public static void main(String[] args)
     {

		//Introduce scanner and declare variables
        Scanner sc = new Scanner(System.in);
        int choice, numOne, numTwo, divAns, answer;
        boolean divBool, close = false;       

		while(!close)
		{		
	
        try
        {        

		do
		{
		//Present menu to user and receive choice
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
				System.out.println("");
                System.out.println("Please input the first number: ");
                numOne = sc.nextInt();
 
				System.out.println("");
                System.out.println("Please enter the second number: ");
                numTwo = sc.nextInt();
 
				//Perform addition calculation
                answer = numOne + numTwo;
 
				//Display calculated answer
				System.out.println("");
                System.out.println(numOne + " + " +  numTwo + " = " + answer);
				System.out.println("");
        break;
 
		//Begin code for multiplication option
		case 2:
				System.out.println("");
                System.out.println("Please input the first number: ");
                numOne = sc.nextInt();
 
				System.out.println("");
                System.out.println("Please enter the second number: ");
                numTwo = sc.nextInt();
 
				//Perform calcualtion
                answer = numOne * numTwo;
 
				//Display calculated answer
				System.out.println("");
                System.out.println(numOne + " x " + numTwo + " = " + answer);
				System.out.println("");
        break;
 
	    //Begin code for division option
        case 3:
				System.out.println("");
                System.out.println("Please input the first number: ");
                numOne = sc.nextInt();
 
				System.out.println("");
                System.out.println("Please enter the second number: ");
                numTwo = sc.nextInt();
 
				//Determine if numbers are divisible
                divBool = (numOne%numTwo == 0);

				if (divBool == true)
				{

					divAns = numOne/numTwo;

					System.out.println("");
					System.out.println("Yes, these integers are divisible. The answer is: " + divAns);
					System.out.println("");
				}
				else
				{				
					System.out.println("No, these integers are not divisible");
					System.out.println("");
				}

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

	public int sumTwoNum()
 
 }

