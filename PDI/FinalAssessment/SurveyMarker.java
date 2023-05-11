/* * * * * * * * * * * * * * * * * * * * *
 * Title:     SurveyMarker               *    
 * Author:    Jacob Jonas, 18439731      *
 * Created:   12/6/2022                  *
 * Modified:  13/6/2022                  *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class SurveyMarker extends Station //Invoking inheritance from Station class
{
    //Class fields
    private String horizDatum;
    private double easting;
    private double northing;

    //Constructor with parameters
    public SurveyMarker(String pId_Code, double pLat, double pLon, String pHorizDatum, double pEasting, double pNorthing)
    {
		//Updating class fields with values of the imported parameters
		super(pId_Code,pLat,pLon); //Applying inheritance
		horizDatum = pHorizDatum;
		easting = pEasting;
		northing = pNorthing;
    }

    //Copy Constructor
    public SurveyMarker(SurveyMarker pSurveyMarker)
    {
		//Updating class fields with the values from the copied object
		super(pSurveyMarker); //Applying inheritacne
		horizDatum = pSurveyMarker.getHorizDatum();
		easting = pSurveyMarker.getEasting();
		northing = pSurveyMarker.getNorthing();
    }

    //Default Constructor
    public SurveyMarker()
    {
		//Class fields assigned default values
		super(); //Applying inheritance
		horizDatum = "LAS";
		easting = 599;
		northing = 599;
    }

/* * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getHorizDatum                   *
 * Import:     none                            *
 * Export:     horizDatum (String)             *
 * Assertion:  Returned the horizDatum value   *
 * Created:    12/6/2022                       *
 * Modified:   12/6/2022                       *
 * * * * * * * * * * * * * * * * * * * * * * * */

    public String getHorizDatum()
    {
		return horizDatum;
    }

/* * * * * * * * * * * * * * * * * * * * * *
 * Method:     getEasting	          	   *
 * Import:     none		           		   *
 * Export:     easting (double)            *
 * Assertion:  Returned the easting value  *
 * Created:    12/6/2022                   *
 * Modified:   12/6/2022                   *
 * * * * * * * * * * * * * * * * * * * * * */

    public double getEasting()
    {
		return easting;
    }

/* * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getNorthing	             	 *
 * Import:     none		             		 *
 * Export:     northing (double)             *
 * Assertion:  Returned the northing value   *
 * Created:    12/6/2022                     *
 * Modified:   12/6/2022                     *
 * * * * * * * * * * * * * * * * * * * * * * */

    public double getNorthing()
    {
		return northing;
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setHorizDatum				             			 *
* Import:      pHorizatum (String)                                   *
 * Export:     none                                                  *
 * Assertion:  State of horizDatum is updated to pHorizDatum value   *
 * Created:    12/6/2022                                             *
 * Modified:   12/6/2022                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    public void setHorizDatum(String pHorizDatum)
    {
		horizDatum = pHorizDatum;
    }
    
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setEasting                                      *
 * Import:     pEasting (double)                               *
 * Export:     none                                            *
 * Assertion:  State of easting is updated to pEasting value   *
 * Created:    12/6/2022                                       *
 * Modified:   12/6/2022                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public void setEasting(double pEasting)
    {
		if(pEasting < 0)
		{
			throw new IllegalArgumentException("Easting was invalid");
		}
		else
		{ 
			easting = pEasting;
		}
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     setNorthing                                     *
 * Import:     pNorthing (double)                              *
 * Export:     none                                            *
 * Assertion:  State of northing is updated to pNorthing value *
 * Created:    12/6/2022                                       *
 * Modified:   12/6/2022                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public void setNorthing(double pNorthing)
    {
		if(pNorthing < 0)
		{
			throw new IllegalArgumentException("Northing was invalid");
		}
		else
		{
			northing = pNorthing;
		}
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     toString                                      *
 * Import:     none                                          *
 * Export:     surveyMarkerString (String)		     		 *
 * Assertion:  Returns a string representaion of the object  *
 * Created:    12/6/2022                                     *
 * modified:   12/6/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public String toString()
    {
		String tempString = super.toString();
		String surveyMarkerString = horizDatum + easting + northing + tempString;
		
		return surveyMarkerString;
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
		SurveyMarker surveyMarker = null;

		if(pObject instanceof SurveyMarker)
		{
			surveyMarker = (SurveyMarker)pObject;
			if(super.equals(surveyMarker))
			{
				if(horizDatum.equals(surveyMarker.getHorizDatum()))
					if(easting == surveyMarker.getEasting())
						if(northing == surveyMarker.getNorthing())
							isEqual = true;
			}
		}
		return isEqual;
    }
}


