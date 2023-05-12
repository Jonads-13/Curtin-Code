import java.util.*;

public class ArrayTest
{
	public static void main(String[] args)
	{
		/*int [] array = {1,2,3,4,5,6,7,8,9};

		int [] intArray = new int[8];
		

		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}

		for(int i = 0; i < intArray.length; i++)
		{
			if(array[i] < 5)
			{
				intArray[i] = array[i];
			}
			else
			{
				intArray[i] = null;
			}
		}
	
		for(int i = 0; i < intArray.length; i++)
		{
			System.out.println(intArray[i]);
		}*/

		String name = "";
		String match = "British Virgin Islands";
		boolean equals = false;
		Scanner sc = new Scanner(System.in);

		System.out.println("enter a name");
		name = sc.nextLine();

		if(name.equals(match))
		{
			equals = true;
		}
		System.out.println(name + " " + match + " " + equals);


	}
}
