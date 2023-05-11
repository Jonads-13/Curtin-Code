import java.util.*;

public class Date
{
    private int day;
    private int month;
    private int year;
	
	//Constructor with parameters
	public Date(int pDay, int pMonth, int pYear)
    	{
        day = pDay;
        month = pMonth;
        year = pYear;
   	}
	//Copy constructor
	public Date (Date pDate)
    	{
        day = pDate.getDay();
        month = pDate.getMonth();
        year = pDate.getYear();
    	}
	//Default constructor
	public Date()
    	{
        day = 5;
        month = 12;
        year = 1969;
    	}

	public int getDay()
	{
    	return day;
	}

	public int getMonth()
	{
		return month;
	}

	public int getYear()
	{
		return year;
	}

	public static String getSuffix(int pValue)
	{
		String suffix = "";
		switch (pValue)
		{
		case 1: case 21: case 31:
		    suffix = "st";
		break;

		case 2: case 22:
		    suffix = "nd";
		break;

		case 3: case 23:
		    suffix = "rd";
		break;

		default:
		    suffix = "th";
		break;
		}

		return suffix;
	}

	public void setDay (int pDay)
	{
		day = pDay;
	}

	public void setMonth (int pMonth)
	{
		month = pMonth;
	}

	public void setYear (int pYear)
	{
		year = pYear;
	}

	public String toString()
	{
		String daySuffix = getSuffix(day);
		String monthSuffix = getSuffix(month);
		String dateString;
		dateString =  day + daySuffix + " of the " + month + monthSuffix + ", " + year;
		
		return dateString;
	} 

}
