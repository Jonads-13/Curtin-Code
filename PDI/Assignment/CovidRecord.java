/* * * * * * * * * * * * * * * * * * * * *
 * Title:     CovidRecord class          *    
 * Author:    Jacob Jonas, 18439731      *
 * Created:   13/5/2022                  *
 * Modified:  18/5/2022                  *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;

public class CovidRecord
{
	//Class fields
	private Country country;
	private String date;
	private int cumulativePositive;
	private int cumulativeDeceased;
	private int cumulativeRecovered;
	private int currentlyPositive;
	private int hospitalised;
	private int intensiveCare;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     Constructor with Parameters                                                                                                       *
 * Import:     pCountry (Country), pDate (String), pCumulativePositive (Integer), pCumulativeDeceased (Integer), pCumulativeRecovered (Integer), *
 *             pCurrentlyPositive (Integer), pHospitalised (Integer), pIntensiveCare (Integer)                                                   *
 * Export:     none                                                                                                                              *
 * Assertion:  Create an object with imported values                                                                                             *
 * Created:    13/5/2022                                                                                                                         *
 * Modified:   13/5/2022                                                                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public CovidRecord(Country pCountry, String pDate, int pCumulativePositive, int pCumulativeDeceased, int pCumulativeRecovered, int pCurrentlyPostive, int pHospitalised, int pIntensiveCare)
	{
		country = pCountry;
		date = pDate;
		cumulativePositive = pCumulativePositive;
		cumulativeDeceased = pCumulativeDeceased;
		cumulativeRecovered = pCumulativeRecovered;
		currentlyPositive = pCurrentlyPostive;
		hospitalised = pHospitalised;
		intensiveCare =  pIntensiveCare;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     Copy Constructor                      *
 * Import:     pCountry (Country)                    *
 * Export:     none                                  *
 * Assertion:  Create a Copy of the imported object  *
 * Created:    13/5/2022                             *
 * Modified:   13/5/2022                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public CovidRecord(CovidRecord pCovidRecord)
	{
		country = pCovidRecord.getCountry();
		date = pCovidRecord.getDate();
		cumulativePositive = pCovidRecord.getCumulativePositive();
		cumulativeDeceased = pCovidRecord.getCumulativeDeceased();
		cumulativeRecovered = pCovidRecord.getCumulativeRecovered();
		currentlyPositive = pCovidRecord.getCurrentlyPositive();
		hospitalised = pCovidRecord.getHospitalised();
		intensiveCare =  pCovidRecord.getIntensiveCare();
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     Default Constructor                       *
 * Import:     none                                      *
 * Export:     none                                      *
 * Assertion:  Create an object with the default values  *
 * Created:    13/5/2022                                 *
 * Modified:   13/5/2022                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	public CovidRecord()
	{
		country = new Country("LAS", "GI", "Lastation", "LS", -16.574834, 100.490285); //Using the same values as the default constructor in the Country class
		date = "17/11/2006"; //using a date nowhere near the dates in the csv files to prevent any confusion in data filtering
		//Initialise all these values to 0 to make them unavailable for any calulations in the main program
		cumulativePositive = 0;
		cumulativeDeceased = 0;
		cumulativeRecovered = 0;
		currentlyPositive = 0;
		hospitalised = 0;
		intensiveCare = 0;
	}

/* * * * * * * * * * * * * * * * * * * * * *
 * Method:     getCountry                  *
 * Import:     none                        *
 * Export:     country (Country)           *
 * Assertion:  Returned the country value  *
 * Created:    13/5/2022                   *
 * Modified:   13/5/2022                   *
 * * * * * * * * * * * * * * * * * * * * * */

	public Country getCountry()
	{
		return country;
	}

/* * * * * * * * * * * * * * * * * * * * *
 * Method:     getdate                   *
 * Import:     none                      *
 * Export:     date (String)             *
 * Assertion:  Returned the date value   *
 * Created:    13/5/2022                 *
 * Modified:   13/5/2022                 *
 * * * * * * * * * * * * * * * * * * * * */


	public String getDate()
	{
		return date;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getCumulativePositive                   *
 * Import:     none                                    *
 * Export:     cumulativePositive (Intger)             *
 * Assertion:  Returned the cumulativePositive value   *
 * Created:    13/5/2022                               *
 * Modified:   13/5/2022                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	
	public int getCumulativePositive()
	{
		return cumulativePositive;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getCumulativeDeceased                   *
 * Import:     none                                    *
 * Export:     cumulativeDeceased (Intger)             *
 * Assertion:  Returned the cumulativeDeceased value   *
 * Created:    13/5/2022                               *
 * Modified:   13/5/2022                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public int getCumulativeDeceased()
	{
		return cumulativeDeceased;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getCumulativeRecovered                  *
 * Import:     none                                    *
 * Export:     cumulativeRecovered (Intger)            *
 * Assertion:  Returned the cumulativeRecovered value  *
 * Created:    13/5/2022                               *
 * Modified:   13/5/2022                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
	public int getCumulativeRecovered()
	{
		return cumulativeRecovered;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getCurrentlyPositive                    *
 * Import:     none                                    *
 * Export:     currentlyPositive (Intger)              *
 * Assertion:  Returned the currentlyPositive value    *
 * Created:    13/5/2022                               *
 * Modified:   13/5/2022                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public int getCurrentlyPositive()
	{
		return currentlyPositive;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getHospitalised                   *
 * Import:     none                              *
 * Export:     hospitalised (Intger)             *
 * Assertion:  Returned the hospitalised value   *
 * Created:    13/5/2022                         *
 * Modified:   13/5/2022                         *
 * * * * * * * * * * * * * * * * * * * * * * * * */

	public int getHospitalised()
	{
		return hospitalised;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getIntensiveCare                  *
 * Import:     none                              *
 * Export:     intensiveCare (Intger)            *
 * Assertion:  Returned the intensiveCare value  *
 * Created:    13/5/2022                         *
 * Modified:   13/5/2022                         *
 * * * * * * * * * * * * * * * * * * * * * * * * */

	public int getIntensiveCare()
	{
		return intensiveCare;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setCountry                                      *
 * Import:     pCountry (Country)                              *
 * Export:     none                                            *
 * Assertion:  State of country is updated to pCountry value   *
 * Created:    13/5/2022                                       *
 * Modified:   13/5/2022                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setCountry(Country pCountry)
	{
		country = pCountry;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setDate                                   *
 * Import:     pDate (String)                            *
 * Export:     none                                      *
 * Assertion:  State of date is updated to pDate value   *
 * Created:    13/5/2022                                 *
 * Modified:   13/5/2022                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setDate(String pDate)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		date = pDate; 
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setCumulativePositive                                                 *
 * Import:     pCumulativePositive (Integer)                                         *
 * Export:     none                                                                  *
 * Assertion:  State of cumulativePositive is updated to pCumulativePositive value   *
 * Created:    13/5/2022                                                             *
 * Modified:   13/5/2022                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public void setCumulativePositive(int pCumulativePositive)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		cumulativePositive = pCumulativePositive; 
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setCumulativeDeceased                                                 *
 * Import:     pCumulativeDeceased (Integer)                                         *
 * Export:     none                                                                  *
 * Assertion:  State of cumulativeDeceased is updated to pCumulativeDeceased value   *
 * Created:    13/5/2022                                                             *
 * Modified:   13/5/2022                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public void setCumulativeDeceased(int pCumulativeDeceased)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		cumulativeDeceased = pCumulativeDeceased;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setCumulativeRecovered                                                  *
 * Import:     pCumulativeRecovered (Integer)                                          *
 * Export:     none                                                                    *
 * Assertion:  State of cumulativeRecovered is updated to pCumulativeRecovered value   *
 * Created:    13/5/2022                                                               *
 * Modified:   13/5/2022                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		
	public void setCumulativeRecovered(int pCumulativeRecovered)
	{
		cumulativeRecovered = pCumulativeRecovered;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setCurrentlyPositive                                                *
 * Import:     pCurrentlyPositive (Integer)                                        *
 * Export:     none                                                                *
 * Assertion:  State of currentyPositive is updated to pCurrentlyPositive value    *
 * Created:    13/5/2022                                                           *
 * Modified:   13/5/2022                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setCurrentlyPositive(int pCurrentlyPositive)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		currentlyPositive = pCurrentlyPositive;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setHospitalised                                           *
 * Import:     pHospitalised (Integer)                                   *
 * Export:     none                                                      *
 * Assertion:  State of hospitalised is updated to pHospitalised value   *
 * Created:    13/5/2022                                                 *
 * Modified:   13/5/2022                                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setHospitalised(int pHospitalised)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		hospitalised = pHospitalised;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setIntensiveCare                                            *
 * Import:     pIntensiveCare (Integer)                                    *
 * Export:     none                                                        *
 * Assertion:  State of intensiveCare is updated to pIntensiveCare value   *
 * Created:    13/5/2022                                                   *
 * Modified:   13/5/2022                                                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setIntensiveCare(int pIntensiveCare)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		intensiveCare = pIntensiveCare;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     toString                                      *
 * Import:     none                                          *
 * Export:     covidRecordString (String)                    *
 * Assertion:  Returns a string representaion of the object  *
 * Created:    13/5/2022                                     *
 * modified:   15/5/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public String toString()
	{
		String covidRecordString = "";
		covidRecordString = country + " " + date + " " +  cumulativePositive + " " + cumulativeDeceased + " " + cumulativeRecovered + " " + currentlyPositive + " " + hospitalised + " " + intensiveCare;

		return covidRecordString;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method: equals                                              *
 * Import: inObject (Object)                                   *
 * Export: isEqual (Boolean)                                   *
 * Assertion: Returns true if the two objects are equivalent   *
 * Created: 17/5/2021                                          *
 * Modified: 17/5/2021                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public boolean equals(Object inObject)
	{
		boolean isEqual = false;
		CovidRecord inCovidRecord = null;
		if(inObject instanceof CovidRecord)
		{
			inCovidRecord = (CovidRecord)inObject;
				if(country == inCovidRecord.getCountry())
					if(date.equals(inCovidRecord.getDate()))
						if(cumulativePositive == inCovidRecord.getCumulativePositive())
							if(cumulativeDeceased == inCovidRecord.getCumulativeDeceased())
								if(cumulativeRecovered == inCovidRecord.getCumulativeRecovered())
									if(currentlyPositive == inCovidRecord.getCurrentlyPositive())
										if(hospitalised == inCovidRecord.getHospitalised())
											if(intensiveCare == inCovidRecord.getIntensiveCare())
												isEqual = true;
		}
		return isEqual;
	}
}


