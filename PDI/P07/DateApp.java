import java.util.*;
public class DateApp
{
    public static void main(String[] args)
    {
		Date[] dateArray = new Date[8];

        Date [0] = new Date(23, 12, 1996);
        Date [1] = new Date(5, 5, 1968);
        Date [2] = new Date(5, 12, 1969);    
        Date [3] = new Date(1, 1, 2020);
		Date [4] = new Date(2, 2, 2021);
		Date [5] = new Date();
		Date [6] = new Date(12, 1, 2020);
		Date [7] = new Date(2, 1, 2022);

		yearSort(dateArray);
		monthSort(dateArray);
		daySort(dateArray);

    }

	public static void yearSort(Date[] pArray)
	{
		for(int p = 0; p < pArray.length-1 ; p++)
		{
			for(int i = 0; i <pArray.length -1; i++)
			{
				if(pArray[i].getYear() > pArray[i+1].getYear())
				{ 
					Date temp = pArray[i];
					pArray[i] = pArray[i+1];
					pArray[i+1] = temp;
				}
			}
		}

		System.out.println("\nHere are the dates sorted by year:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}
	}

	public static void yearSort(Date[] pArray)
	{
		for(int p = 0; p < pArray.length-1 ; p++)
		{
			for(int i = 0; i <pArray.length -1; i++)
			{
				if(pArray[i].getMonth() > pArray[i+1].getMonth())
				{ 
					Date temp = pArray[i];
					pArray[i] = pArray[i+1];
					pArray[i+1] = temp;
				}
			}
		}

		System.out.println("\nHere are the dates sorted by month:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}
	}

	public static void yearSort(Date[] pArray)
	{
		for(int p = 0; p < pArray.length-1 ; p++)
		{
			for(int i = 0; i <pArray.length -1; i++)
			{
				if(pArray[i].getDay() > pArray[i+1].getDay())
				{ 
					Date temp = pArray[i];
					pArray[i] = pArray[i+1];
					pArray[i+1] = temp;
				}
			}
		}

		System.out.println("\nHere are the dates sorted by day:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}
	}
}
