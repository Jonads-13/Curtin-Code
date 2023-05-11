/* * * * * * * * * * * * * * * * * * * * *
 * Title:     Station                    *    
 * Author:    Jacob Jonas, 18439731      *
 * Created:   12/6/2022                  *
 * Modified:  13/6/2022                  *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class Station
{
    //Class fields
    private String idCode;
    private double lat;
    private double lon;

    //Constructor with parameters
    public Station(String pIdCode, double pLat, double pLon)
    {
		//Updating class fields with values of the imported parameters
		idCode = pIdCode;
		lat = pLat;
		lon = pLon;
    }

    //Copy Constructor
    public Station(Station pStation)
    {
		//Updating class fields with the values from the copied object
		idCode = pStation.getIdCode();
		lat = pStation.getLat();
		lon = pStation.getLon();
    }

    //Default Constructor
    public Station()
    {
		//Class fields assigned default values
		idCode = "LAS599010";
		lat = 15.32145387;
		lon = 100.73928731;
    }

/* * * * * * * * * * * * * * * * * * * * *
 * Method:     getIdCode                 *
 * Import:     none                      *
 * Export:     idCode (String)           *
 * Assertion:  Returned the idCode value *
 * Created:    12/6/2022                 *
 * Modified:   12/6/2022                 *
 * * * * * * * * * * * * * * * * * * * * */

    public String getIdCode()
    {
		return idCode;
    }
   
/* * * * * * * * * * * * * * * * * * * * 
 * Method:     getLat                  *
 * Import:     none		       		   *
 * Export:     lat (Double)            *
 * Assertion:  Returned the lat value  *
 * Created:    12/6/2022               *
 * Modified:   12/6/2022               *
 * * * * * * * * * * * * * * * * * * * */
 
    public double getLat()
    {
		return lat;
    }

/* * * * * * * * * * * * * * * * * * * * 
 * Method:     getLon                  *
 * Import:     none		       		   *
 * Export:     lon (Double)            *
 * Assertion:  Returned the lon value  *
 * Created:    12/6/2022               *
 * Modified:   12/6/2022               *
 * * * * * * * * * * * * * * * * * * * */

    public double getLon()
    {
		return lon;
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setIdCode                                     *
 * Import:     pIdCode (double)                              *
 * Export:     none                                          *
 * Assertion:  State of idCode is updated to pIdCode value   *
 * Created:    12/6/2022                                     *
 * Modified:   12/6/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public void setIdCode(String pIdCode)
    {
		idCode = pIdCode;
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setLat                                  *
 * Import:     pLat (double)                           *
 * Export:     none                                    *
 * Assertion:  State of lat is updated to pLat value   *
 * Created:    12/6/2022                               *
 * Modified:   12/6/2022                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public void setLat(double pLat)
    {
		if((pLat < -90) || (pLat > 90)) //Check pLat is within natural limits
		{
			throw new IllegalArgumentException("Latitude was invalid"); //Manually throw exception if pLat is not valid
		}
		else
		{
			lat = pLat;
		}
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setLon                                  *
 * Import:     pLon (double)                           *
 * Export:     none                                    *
 * Assertion:  State of lon is updated to pLon value   *
 * Created:    12/6/2022                               *
 * Modified:   12/6/2022                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    public void setLon(double pLon)
    {
    	if((pLon < -180) || (pLon > 180)) //Check pLon is within natural limits
		{
			throw new IllegalArgumentException("Longitude was invalid"); //Manually throw exception if pLon is not valid
		}
		else
		{
			lon = pLon;
		}
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     toString                                      *
 * Import:     none                                          *
 * Export:     stationString (String)                        *
 * Assertion:  Returns a string representaion of the object  *
 * Created:    12/6/2022                                     *
 * modified:   12/6/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public String toString()
    {
		String stationString = "";
		stationString = idCode + lat + lon;

		return stationString;
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    equals                                         *
 * Import:    pObject (Object)                               *
 * Export:    isEqual (Boolean)                              *
 * Assertion: Returns true if the two objects are equivalent *
 * Created:   12/6/2021                                      *
 * Modified:  12/6/2021                                      *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public boolean equals(Object pObject)
    {
		boolean isEqual = false;
		Station inStation = null;

		if(pObject instanceof Station)
		{
			inStation = (Station)pObject;
			if(idCode.equals(inStation.getIdCode()))
				if(lat == inStation.getLat())
					if(lon == inStation.getLon())
						isEqual = true;
		}
		return isEqual;
    }
}


