/* * * * * * * * * * * * * * * * * * * * *
 * Title:     QuestionFour               *    
 * Author:    Jacob Jonas, 18439731      *
 * Created:   12/6/2022                  *
 * Modified:  14/6/2022                  *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;

public class QuestionFour
{
    public static void main(String[] args)
    {
		//Present welcome message to the user
		System.out.println("\n\nWelcome to the SurveyMarker Application\n");	

		//Create a new SurveyMarker object with the default constructor
		SurveyMarker surveyMarkerOne = new SurveyMarker();
		//Send new object to the method to get user input for the object's class fields
		getSurveyMarkerData(surveyMarkerOne,"first");

		//Create a new SurveyMarker object with the default constructor
		SurveyMarker surveyMarkerTwo = new SurveyMarker();
		//Send new object to the method to get user input for the object's class fields
		getSurveyMarkerData(surveyMarkerTwo, "second");

		//Display both object's data to the user
		System.out.println("\nSurveyMarker One:\n> idCode: " + surveyMarkerOne.getIdCode() + "\n> lat: " + surveyMarkerOne.getLat() + "\n> lon: " + surveyMarkerOne.getLon() + "\n> horizDatum: " + surveyMarkerOne.getHorizDatum() + "\n> easting: " + surveyMarkerOne.getEasting() + "\n> northing: " + surveyMarkerOne.getNorthing());

		System.out.println("\nSurveyMarker Two:\n> idCode: " + surveyMarkerTwo.getIdCode() + "\n> lat: " + surveyMarkerTwo.getLat() + "\n> lon: " + surveyMarkerTwo.getLon() + "\n> horizDatum: " + surveyMarkerTwo.getHorizDatum() + "\n> easting: " + surveyMarkerTwo.getEasting() + "\n> northing: " + surveyMarkerTwo.getNorthing()); 
	
	   
		double latOne = surveyMarkerOne.getLat(); //Apply the lat value of the first object to latOne variable
        double lonOne = surveyMarkerOne.getLon(); //Apply the lon value of the first object to lonOne variable
        double latTwo = surveyMarkerTwo.getLat(); //Apply the lat value of the second object to latTwo variable
        double lonTwo = surveyMarkerTwo.getLon(); //Apply the lon value of the second object to lonTwo variable

        double theDistance = 0.0;   //Variable to store the calculated distance value

        //Call method to calculate the distance between the 2 objects
        theDistance = distanceBetweenPoints(latOne, lonOne, latTwo, lonTwo);

		//Display the distance to the user
        System.out.println("The distance between the two SurveyMarkers is: " + theDistance + "degrees");
    }

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     getSurveyMarkerData                           *
 * Import:     pObject (SurveyMarker), pObjectNum (String)   *
 * Export:     none                                          *
 * Assertion:  Get data for an object from the user          *
 * Created:    12/6/2022                                     *
 * Modified:   14/6/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void getSurveyMarkerData(SurveyMarker pObject, String pObjectNum)
    {
		Scanner sc = new Scanner(System.in); //Scanner object for user input
		//String and double variables to hold user input
		String idCode = "", horizDatum = "";
		//Initialise these values as invalid to ensure that a caught InputMismatchException won't exit the loop
		double lat = 200, lon = 200, northing = -1, easting = -1;
		boolean close = false; //Variable to handle the do-while loop

		do //Begin do-while loop
		{
			try
			{
			//Prompt user for input
			System.out.println("\nEnter the details of the " + pObjectNum +" Survey Marker:\n");
			System.out.println("Enter the id code:");
			idCode = sc.next(); //Get user input
			System.out.println("\nEnter the latitude:");
			lat = sc.nextDouble(); //Get user input
			System.out.println("\nEnter the longitude:");
			lon = sc.nextDouble(); //Get user input
			System.out.println("\nEnter the horizon datum:");
			horizDatum = sc.next(); //Get user input
			System.out.println("\nEnter the easting:");
			easting = sc.nextDouble(); //Get user input
			System.out.println("\nEnter the northing:");
			northing = sc.nextDouble(); //Get user input
			}
			catch(InputMismatchException error)
			{
				System.out.println("Error. Invalid data type input"); //Notify user of their error
				sc.next(); //Clear scanner buffer to prevent infinite looping
			}
			
			//Send data to method to modify the object using user data and exit loop if all data was valid
			close = modifyObject(pObject,idCode,lat,lon,horizDatum,easting,northing);

		}while(!close); //End do-while loop
    } //End getSurveyMarkerData

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     modifyObject                                                                                                                          *
 * Import:     pObject (SurveyMarker), pIdCode (String), pLat (double), pLon (double), pHorizDatum (String), pEasting (double), pNorthing (double)   *
 * Export:     close (boolean)															    														 *
 * Assertion:  Using mutator methods, apply user data to the object. If all values are valid, exit the loop in the getSurveyMarkerData               *
 * Created:    12/6/2022															    															 *
 * Modified:   13/6/2022															     															 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static boolean modifyObject(SurveyMarker pObject, String pIdCode, double pLat, double pLon, String pHorizDatum, double pEasting, double pNorthing)
    {
		boolean close = false; 
		try
		{
			//Attempt to modify all class fields
			pObject.setIdCode(pIdCode);
			pObject.setLat(pLat);
			pObject.setLon(pLon);
			pObject.setHorizDatum(pHorizDatum);
			pObject.setEasting(pEasting);
			pObject.setNorthing(pNorthing);
			close = true; //If all modifications succeed update boolean value to exit loop in the getSurveyMarkerData method
		}
		catch(IllegalArgumentException error) //Catch error thrown from setter methods in the object's class file
		{
			System.out.println(error.getMessage()); //Notify user of which value was invalid
		}
		return close; //return boolean value which will determine whether to exit the loop or not
    } //End modifyObject



	//As I had nothing to do with the creation of this method, it felt wrong to enter a date

    /******************************************************
    * Purpose: To calculate the distance between 2 points *
    * Date:
    * IMPORTS:  latOne   (Real)                           *
    *           lonOne   (Real)                           *
    *           latTwo   (Real)                           *
    *           lonTwo   (Real)                           *
    * EXPORTS:  distance (Real)                           *
    *******************************************************/

    private static double distanceBetweenPoints(double latOne, double lonOne, double latTwo, double lonTwo)
    {
        double distance = 0.0;
        distance = Math.sqrt((lonTwo - lonOne)*(lonTwo - lonOne) + (latTwo - latOne)*(latTwo - latOne));
        return distance;
    }
}

