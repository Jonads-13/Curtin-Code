import java.util.*;
public class menu
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int choice, numOne, numTwo, answer;
	boolean divAns;

        System.out.println("What would you like to do? ");
	System.out.println("	    > 1. Sum of two integers");
	System.out.println("	    > 2. Product of two integers");
	System.out.println("	    > 3. Deterimine of two integers are divisible");
	System.out.println("	    > 0. Exit"); 
        
	choice = sc.nextInt();

        switch(choice)
        {
            case 1: 
		System.out.println("Please input the first number: ");
		numOne = sc.nextInt();

		System.out.println("Please enter the second number: ");
		numTwo = sc.nextInt();

		answer = numOne + numTwo;

                System.out.println(numOne + " + " +  numTwo + " = " + answer);
       	break;

        case 2:
	    System.out.println("Please input the first number: ");
            numOne = sc.nextInt();

            System.out.println("Please enter the second number: ");
            numTwo = sc.nextInt();

            answer = numOne * numTwo;

            System.out.println(numOne + " x " + numTwo + " = " + answer);
	break;

	case 3: 
	    System.out.println("Please input the first number: ");
            numOne = sc.nextInt();

            System.out.println("Please enter the second number: ");
            numTwo = sc.nextInt();

            divAns = (numOne%numTwo == 0);

	    System.out.println(divAns);
	break;

	case 0:
	    System.out.println("Goodbye");
        
	break;
	default:
	    System.out.println("Error. Invalid input");

	}

    }

}
