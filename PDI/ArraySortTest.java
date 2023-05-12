import java.util.*;

public class ArraySortTest
{
	public static void main(String[] args)
	{
		String[] names = {"31/5/2021", "31/1/2021", "31/12/2021", "30/6/2021", "28/2/2021"};
		String[] dates = new String[100];
		int j = 0, k = 0;

		for(int i = 0; i < names.length; i++)
		{
			System.out.println(names[i]);
		}
		
		Arrays.sort(names);//, Collections.reverseOrder());
		/*
		for(int p = 0; p < names.length; p++)
		{
			for(int i = 0; i < names.length-1; i++)
			{
				if(names[i].equals("31/12/2021"))
				{
					dates[i] = names[i];
					k++;
				}
				else
				{
					j++;
				}
			}

			j = 0;

			for(int i = 0; i < names.length-1; i++)
			{
				if(names[i].equals("30/6/2021"))
				{
					dates[i+k] = names[i];
					k++;
				}
				else
				{
					j++;
				}
			}
			j = 0;
			for(int i = 0; i < names.length-1; i++)
			{
				if(names[i].equals("31/5/2021"))
				{
					dates[i+k] = names[i];
					k++;
				}
				else
				{
					j++;
				}
			}
			j = 0;
			for(int i = 0; i < names.length-1; i++)
			{
				if(names[i].equals("28/2/2021"))
				{
					dates[i+k] = names[i];
					k++;
				}
				else
				{
					j++;
				}
			}
			j = 0;
			for(int i = 0; i < names.length-1; i++)
			{
				if(names[i].equals("31/1/2021"))
				{
					dates[i+k] = names[i];
					k++;
				}
				else
				{
					j++;
				}
			}
		}*/



		System.out.println("\nsorted array\n");

		for(int i = 0; i < names.length; i++)
		{
			System.out.println(names[i]);
		}
	}
}

