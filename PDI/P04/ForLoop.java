import java.util.*;

public class ForLoop
{
	public static void main(String[] args)
	{
		Scanner input= new Scanner(System.in);
		
		//Declaring variables
		int userNumber, i;		


		//Prompt user for input
		System.out.print("Enter an integer: ");
		userNumber = input.nextInt();
		
		//Begin for loop for user entered number of times
		for(i = 0; i < (userNumber); i++)
		{
    		
			//Display all integers between 1 and the entered integer (both inclusive)
			System.out.print(i+1);
			System.out.print(" ");

		}
			System.out.println("");
	}
}
