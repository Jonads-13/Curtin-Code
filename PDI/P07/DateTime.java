import java.util.*;
public class DateTime extends Date
{
	private int hours;
	private int minutes;
	private int seconds;

	//constructor with parameters
	public DateTime(int pDay, int pMonth, int pYear, int pHours, int pMinutes, int pSeconds)
	{
		super(pDay, pMonth, pYear);
		hours = pHours;
		minutes = pMinutes;
		seconds = pSeconds;
	}
	
	//Copy constructor
	public DateTime(DateTime pDateTime)
	{
		super(pDateTime);
		hours = pDateTime.getHours();
		minutes = pDateTime.getMinutes();
		seconds = pDateTime.getHours();
	}

	//Default constructor
	public DateTime()
	{
		super();
		hours = 12;
		minutes = 34;
		seconds = 56;
	}

	public int getHours()
	{
    	return hours;
	}

	public int getMinutes()
	{
		return minutes;
	}

	public int getSeconds()
	{
		return seconds;
	}

	public void setHours (int pHours)
	{
        if((pHours >= 0) && (pHours <= 23))
        {
		    hours = pHours;
        }
        else
        {
            System.out.println("Invalid value");
        }
	}

	public void setMinutes (int pMinutes)
	{
		minutes = pMinutes;
	}

	public void setSeconds (int pSeconds)
	{
		seconds = pSeconds;
	}

	public String toString()
	{
		String tempString = super.toString();
		String timeString;
		
		timeString = tempString + " " + hours + ":" + minutes + ":" + seconds;
		
		return timeString;
	} 
}

		
	
