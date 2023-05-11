import java.util.*;

public class Bubble
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int[] valueArray = new int[8];
		
		System.out.println("Please enter the numbers to be sorted: ");

		for(int i = 0; i < valueArray.length; i++)
		{
			System.out.println("Number " + (i+1));
			valueArray[i] = sc.nextInt();
		}

		BubbleSort(valueArray);

	}

	public static void BubbleSort(int[] pArray)
	{
		for(int p = 0; p < pArray.length-1 ; p++)
		{
			for(int i = 0; i < pArray.length -1; i++)
			{
				if(pArray[i] > pArray[i+1])
				{ 
					int temp = pArray[i];
					pArray[i] = pArray[i+1];
					pArray[i+1] = temp;
				}
			}
		}

		System.out.println("\nHere are the numbers sorted smallest to largest");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}

	}
}


