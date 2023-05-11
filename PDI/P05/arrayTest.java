import java.util.*;
public class arrayTest
{
	public static void main(String[] args)
	{

		
		int [][] myTwoDArray; 
		int numOne, numTwo;
		
		Scanner sc = new Scanner(System.in);

		System.out.println("enter rows");
		numOne = sc.nextInt();

		System.out.println("enter columns");
		numTwo = sc.nextInt();
		

		myTwoDArray = new int[numOne][numTwo];

		for(int i = 0; i < numOne; i++)
		{
			
			for(int j = 0; j < numTwo; j++)
			{
				myTwoDArray[i][j] = i*j;
			}
		}
		for(int i = 0; i < numOne; i++)
		{
			for(int j = 0; j < numTwo; j++)
			{
				System.out.print(myTwoDArray[i][j] + " ");
			}				
			System.out.println();//Print on the next line
		}
	}
}
