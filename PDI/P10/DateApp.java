import java.util.*;
public class DateApp
{
    public static void main(String[] args)
    {
		Date[] dateArray = new Date[8];

        dateArray [0] = new Date(23, 12, 1996);
        dateArray [1] = new Date(5, 5, 1968);
        dateArray [2] = new Date(5, 12, 1969);    
        dateArray [3] = new Date(1, 1, 2020);
		dateArray [4] = new Date(2, 2, 2021);
		dateArray [5] = new Date(1, 1, 2019);
		dateArray [6] = new Date(12, 1, 2020);
		dateArray [7] = new Date(2, 1, 2022);

		dateArray = daySort(dateArray);
		dateArray = monthSort(dateArray);
		dateArray = yearSort(dateArray);

		System.out.println("\nHere are the sorted dates:\n");

		for(int i = 0; i < dateArray.length; i++)
		{
			System.out.println(dateArray[i]);
		}

    }

	public static Date[] yearSort(Date[] pArray)
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

		/*System.out.println("\nHere are the dates sorted by year:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}*/

		return pArray;
	}

	public static Date[] monthSort(Date[] pArray)
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

		/*System.out.println("\nHere are the dates sorted by month:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}*/

		return pArray;
	}

	public static Date[] daySort(Date[] pArray)
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

		//System.out.println("\nHere are the dates sorted by day:\n");

		/*for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}*/

		return pArray;
	}
}
