/* * * * * * * * * * * * * * * * * * * * *
 * Title:     Country class              *    
 * Author:    Jacob Jonas, 18439731      *
 * Created:   13/5/2022                  *
 * Modified:  18/5/2022                  *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*; 

public class Country
{
	//Class fields
	private String iso3;
	private String continent;
	private String countryName;
	private String nuts; //Side note: what is the point of this value?
	private double latitude;
	private double longitude;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     Constructor with Parameters                                                 *
 * Import:     pIso3 (String), pContinent (String), pCountryName (String), pNuts (String), *
 * 	       pLatitude (Real Number), pLongitude (Real Number)                           *
 * Export:     none                                                                        *
 * Assertion:  Create an object with imported values                                       *
 * Created:    13/5/2022                                                                   *
 * Modified:   13/5/2022                                                                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public Country(String pIso3, String pContinent, String pCountryName, String pNuts, double pLatitude, double pLongitude)
	{
		//Updating class fields with values of the imported parameters
		iso3 = pIso3;
		continent = pContinent;
		countryName = pCountryName;
		nuts = pNuts;
		latitude = pLatitude;
		longitude = pLongitude;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     Copy Constructor                      *
 * Import:     pCountry (Country)                    *
 * Export:     none                                  *
 * Assertion:  Create a Copy of the imported object  *
 * Created:    13/5/2022                             *
 * Modified:   13/5/2022                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public Country(Country pCountry)
	{
		//Updating class fields with the values from the copied object
		iso3 = pCountry.getIso3();
		continent = pCountry.getContinent();
		countryName = pCountry.getCountryName();
		nuts = pCountry.getNuts();
		latitude = pCountry.getLatitude();
		longitude = pCountry.getLongitude();
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     Default Constructor                       *
 * Import:     none                                      *
 * Export:     none                                      *
 * Assertion:  Create an object with the default values  *
 * Created:    13/5/2022                                 *
 * Modified:   13/5/2022                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public Country()
	{
		//Class fields assigned default values
		iso3 = "LAS"; //I'm surprised this isn't already used for an existing country
		continent = "GI"; 
		countryName = "Lastation"; //coughnoireandunibestgirlscough
		nuts = "LS";
		latitude = -16.574834;
		longitude = 100.490285;
	}

/* * * * * * * * * * * * * * * * * * * * *
 * Method:     getIso3                   *
 * Import:     none                      *
 * Export:     iso3 (String)             *
 * Assertion:  Returned the iso3 value   *
 * Created:    13/5/2022                 *
 * Modified:   13/5/2022                 *
 * * * * * * * * * * * * * * * * * * * * */

	public String getIso3()
	{
    	return iso3;
	}

/* * * * * * * * * * * * * * * * * * * * * * * 
 * Method:     getContinent                  *
 * Import:     none                          *
 * Export:     continent (String)            *
 * Assertion:  Returned the continent value  *
 * Created:    13/5/2022                     *
 * Modified:   13/5/2022                     *
 * * * * * * * * * * * * * * * * * * * * * * */

	public String getContinent()
	{
		return continent;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * 
 * Method:     getCountryName                  *
 * Import:     none                            *
 * Export:     countryName (String)            *
 * Assertion:  Returned the countryName value  *
 * Created:    13/5/2022                       *
 * Modified:   13/5/2022                       *
 * * * * * * * * * * * * * * * * * * * * * * * */

	public String getCountryName()
	{
		return countryName;
	}

/* * * * * * * * * * * * * * * * * * * * * 
 * Method:     getNuts                   *
 * Import:     none                      *
 * Export:     nuts (String)             *
 * Assertion:  Returned the nuts value   *
 * Created:    13/5/2022                 *
 * Modified:   13/5/2022                 *
 * * * * * * * * * * * * * * * * * * * * */

	public String getNuts()
	{
		return nuts;
	}

/* * * * * * * * * * * * * * * * * * * * * * * 
 * Method:     getLatitude                   *
 * Import:     none                          *
 * Export:     latitude (Double)             *
 * Assertion:  Returned the latitude value   *
 * Created:    13/5/2022                     *
 * Modified:   13/5/2022                     *
 * * * * * * * * * * * * * * * * * * * * * * */

	public double getLatitude()
	{
		return latitude;
	}

/* * * * * * * * * * * * * * * * * * * * * * * 
 * Method:     getLongitude                  *
 * Import:     none                          *
 * Export:     longitude (Double)            *
 * Assertion:  Returned the longitude value  *
 * Created:    13/5/2022                     *
 * Modified:   13/5/2022                     *
 * * * * * * * * * * * * * * * * * * * * * * */
	
	public double getLongitude()
	{
		return longitude;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setIso3                                   *
 * Import:     pIso3 (String)                            *
 * Export:     none                                      *
 * Assertion:  State of is03 is updated to pIso3 value   *
 * Created:    13/5/2022                                 *
 * Modified:   13/5/2022                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setIso3(String pIso3)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		iso3 = pIso3;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setContinent                                        *
 * Import:     pContinent (String)                                 *
 * Export:     none                                                *
 * Assertion:  State of continent is updated to pContinent value   *
 * Created:    13/5/2022                                           *
 * Modified:   13/5/2022                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setContinent(String pContinent)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		continent = pContinent;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setCountryName                                          *
 * Import:     pCountryName (String)                                   *
 * Export:     none                                                    *
 * Assertion:  State of countryName is updated to pCountryName value   *
 * Created:    13/5/2022                                               *
 * Modified:   13/5/2022                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setCountryName(String pCountryName)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		countryName = pCountryName;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setNuts                                   *
 * Import:     pNuts (String)                            *
 * Export:     none                                      *
 * Assertion:  State of nuts is updated to pNuts value   *
 * Created:    13/5/2022                                 *
 * Modified:   13/5/2022                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setNuts(String pNuts)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		nuts = pNuts;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setLatitude                                       *
 * Import:     pLatitude (String)                                *
 * Export:     none                                              *
 * Assertion:  State of latitude is updated to pLatitude value   *
 * Created:    13/5/2022                                         *
 * Modified:   13/5/2022                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public void setLatitude(double pLatitude)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		latitude = pLatitude;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setLongitude                                        *
 * Import:     pLongitude (String)                                 *
 * Export:     none                                                *
 * Assertion:  State of longitude is updated to pLongitude value   *
 * Created:    13/5/2022                                           *
 * Modified:   13/5/2022                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public void setLongitude(double pLongitude)
	{
		//Because the file beong read from is official, I'm assuming all data is correct and hence no validation
		longitude = pLongitude;
	}

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     toString                                      *
 * Import:     none                                          *
 * Export:     countryString (String)                        *
 * Assertion:  Returns a string representaion of the object  *
 * Created:    13/5/2022                                     *
 * modified:   17/5/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public String toString()
	{
		String countryString = "";
		countryString = iso3 + " " + continent + " " + countryName + " " + nuts + " " + latitude + " " + longitude;
		
		return countryString;	
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
		Country inCountry = null;
		if(inObject instanceof Country)
		{
			inCountry = (Country)inObject;
				if(iso3.equals(inCountry.getIso3()))
					if(continent.equals(inCountry.getContinent()))
						if(countryName.equals(inCountry.getCountryName()))
							if(nuts.equals(inCountry.getNuts()))
								if(latitude == inCountry.getLatitude())
									if(longitude == inCountry.getLongitude())
										isEqual = true;
		}
		return isEqual;
	}
}

	

