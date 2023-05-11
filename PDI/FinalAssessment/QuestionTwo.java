/* * * * * * * * * * * * * * * * * * * * *
 * Title:     QuestionTwo                *    
 * Author:    Jacob Jonas, 18439731      *
 * Created:   12/6/2022                  *
 * Modified:  13/6/2022                  *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;

public class QuestionTwo
{
    public static void main(String[] args)
    {
		Scanner sc = new Scanner(System.in); //Scanner object for user input
		double choice = 0.0; //Variable to hold increase/decerease factor user input
		
		//File holds the user input for what they want the file to be called and fileName adds the txt suffix
		String file = "", fileName = "";
		
		//Display a welcom message and menu to the user
		System.out.println("\n\nWelcome to the array modification program\n\n\n\nBy what factor would you like to modify the array?\n\nPlease enter a number between 0.75 and 1.25 (Both inclusive):");
		
		try //Check for invalid input ie String
		{
			choice = sc.nextDouble(); //Get user input
		}
		catch(InputMismatchException error) //If data type was incorrect
		{
			System.out.println("Error. Invalid data type input"); //Notify user they entered an invalid data type
		}

		if((choice < 0.75) || (choice > 1.25)) //Make sure input was within specified limitations
		{
			System.out.println("Error. User input was not between 0.75 and 1.25"); //Nofify user of their error
		}
		else
		{	
			int[][] theArray = arrayGenerator(); //Create an array using the provided method

			int[][] modifiedArray = modifyArray(theArray, choice); //Create a new array that will hold the modified results

			System.out.println("\nHere is the array modified by " + choice + " times:\n"); //Remind user of their choice
		
			for(int r = 0; r < modifiedArray.length; r++) //Run through each sub array
			{
				for(int c = 0; c < modifiedArray[0].length; c++) //Run through each element of the currect sub array
				{
					System.out.print(modifiedArray[r][c] + " "); //Display each element to the user
				}	
				System.out.println(""); //Each sub array is on a different
			}

			writeFile(modifiedArray, "temp.txt", choice); //Call writeFile method and send necessary arguments to it

			System.out.println("\nData written to a file call \"temp.txt\""); //Tell user where the data was written
		}
    } //End main

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     modifyArray                                                         *
 * Import:     pArray (int array), pChoice (double)                                *
 * Export:     finalArray (int array)                                              *
 * Assertion:  Modify all values in pArray by a factor equal to the user's choice  *
 * Created:    12/6/2022                                                           *
 * Modified:   13/6/2022                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int[][] modifyArray(int[][] pArray, double pChoice)
    {
		double[][] modifiedArray = new double[pArray.length][pArray[0].length]; //New array to hold modified values

		for(int r = 0; r < pArray.length; r++) //Run through each sub array
		{
			for(int c = 0; c < pArray[0].length; c++) //Run through each element of the currect sub array
			{
				modifiedArray[r][c] = pArray[r][c] * pChoice; //Calculate the modification
				modifiedArray[r][c] = checkNum(modifiedArray[r][c]); //Check the new value is within specified limits
			}	
		}

		int[][] finalArray = convertToIntArray(modifiedArray); //New int array to hold type casted values from the double array

		return finalArray;
    } //End modifyArray method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     checkNum                                                                    *
 * Import:     pNum (double)                                                               *
 * Export:     validNum (double)                                                           *
 * Assertion:  Import array value and round it, or make -1 if pNum is greater than 65536   *
 * Created:    12/6/2022                                                                   *
 * Modified:   13/6/2022                                                                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static double checkNum(double pNum)
    {
		double validNum = 0;

		if(pNum > 65536) //If parameter is above the specified limit
		{
			validNum = -1; //Initialise to -1
		}
		else
		{
			validNum = Math.round(pNum); //Round parameter
		}
		return validNum;
    } //End checkNum

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     convertToIntArray                                         *
 * Import:     pArray (double array)                                     *
 * Export:     newArray (double array)                                   *
 * Assertion:  Create an int array containing data from a double array   *
 * Created:    12/6/2022                                                 *
 * Modified:   13/6/2022                                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int[][] convertToIntArray(double[][] pArray)
    {
		int[][] newArray = new int[pArray.length][pArray[0].length]; //New array equal in size to imported array

		for(int r = 0; r < pArray.length; r++) //Run through each sub array
		{
			for(int c = 0; c < pArray[0].length; c++) //Run through each element of the currect sub array
			{
			newArray[r][c] = (int)pArray[r][c]; //Type cast the double value into an int for the newArray
			}
		}
		return newArray;
    } //End convertToIntArray	

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     writeFile                                               *
 * Import:     pArray (int array), fileName (String)                   *
 * Export:     newArray (double array)                                 *
 * Assertion:  Write the contents of an imported array to a new file   *
 * Created:    12/6/2022                                               *
 * Modified:   13/6/2022                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void writeFile(int[][] pArray, String pFileName, double pChoice)
    {
    	FileOutputStream fileStrm = null;
    	PrintWriter pw;

    	try
    	{
			//Create PrintWriter
    		fileStrm = new FileOutputStream(pFileName);
    		pw = new PrintWriter(fileStrm);

			pw.println("Array modified by " + pChoice + " times:\n"); //Message to remind user of what their input was when reading the file
			

    		for(int r = 0; r < pArray.length; r++) //Run through each sub array
    		{
				for(int c = 0; c < pArray[0].length; c++) //Run through each element of the currect sub array
				{
					pw.print(pArray[r][c] + " "); //Print results to file
				}
				pw.println(""); //Each array is printed on a new line	
    		}
    		pw.close(); //Close PrintWriter

    	}
		catch(IOException error)
    	{
    		System.out.println("Error in writing to file");
    	}
    } //End writeFile	

    

	//As I had nothing to do with the creation of this method, it felt wrong to enter a date

    /******************************************************
    * Purpose: To calculate provide a 2D array            *
    * Date:                                               *
    * IMPORTS:  Nothing                                   *
    * EXPORTS:  2D array of integers                      *
    *******************************************************/
    public static int[][] arrayGenerator()
    {
        int anArray [][] = {{10, 20, 30, 70, 80, 65536},
                            {40, 50, 60, 10, 20, 30 },
                            {70, 80, 190, 20, 30, 70 },
                            {60, 10, 20, 190, 20, 30},
                            {70, 80, 20, 30, 80, 190},
                            {1, 60, 10, 80, 20, 30}};

        return anArray;
    }
}

