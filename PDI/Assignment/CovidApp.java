/* * * * * * * * * * * * * * * * * * *
 * Title:    CovidApp                *
 * Author:   Jacob Jonas, 18439731   *
 * Created:  13/5/2022               *
 * Modified: 15/5/2022               *
 * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;


/* I'm defining a "valid" value as a value that is greater than 0 becaause I don't how to differentiate between what was 0 in the record and 
 * what became 0 due to the default constructor
 *
 * I'm just going to put this disclaimer at the top so that whoever reads this knows what I mean throughout the rest of the code
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class CovidApp
{
    public static void main(String[] args)
    {
    	/* Delcare and initialise an array with the length equal to the total amount of records in the data file + 1. (The + 1 is because it 
		 * keeps reading past the final line of the file which was giving an out of bounds exception) This is the only way I can think of to 
		 * do it as you can't create an array without knowing how long it will need to be and we aren't allowed to use lists. 
		 *
		 * (Not that I know how they work but, google told me lists would solve this problem)
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

		CovidRecord[] covidArray = new CovidRecord[1778];

		readFile("CovidData.csv", covidArray); //Call method to read the file	

		/* As mentioned above, it keeps reading past the last line in the csv, causing the last element to be null, which messes up all calculations.
		 *
		 * This line of code is to initialise the final element using the default constructor
		 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		covidArray[1777] = new CovidRecord();
		
		//Welcome message outside of any methods as I only want it to be displayed once
		System.out.println("\n\nWelcome to the JRC COVID-19 Analysis Program. There are a total of '1777' records loaded.");

		//Call main menu method where the user interaction begins    
		mainMenu(covidArray);

    }//End main
		
		
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     readFile                                      *
 * Import:     pData01 (String), pArray (CovidRecord array)  *
 * Export:     none                                          *
 * Assertion:  Read each line of a file                      *
 * Created:    13/5/2022                                     *
 * Modified:   15/5/2022                                     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
    public static void readFile(String pData01, CovidRecord[] pArray)
    {
		FileInputStream fileStream = null;
		    InputStreamReader isr;
		BufferedReader bufRdr;
		int lineNum;
		String line;
		try
		{
			fileStream = new FileInputStream(pData01);
			isr = new InputStreamReader(fileStream);
			bufRdr = new BufferedReader(isr);

			//This vairable is also used as the index for the array in the PopulateArray method. Setting this to -1 allows the first pass through that method to use this variable for index 0 and up
			lineNum = -1;
			bufRdr.readLine(); //Used to ignore the headers at the beginning of the csv
			line = bufRdr.readLine();
			while(line != null)
			{
			lineNum++;
			populateArray(line, pArray, lineNum); //Call method to populate the array with the current line from the csv
			line = bufRdr.readLine();
			}
			fileStream.close();
			}
		catch(IOException errorDetails)
		{
			if(fileStream != null)
			{
			try
			{
				fileStream.close();
			}
			catch(IOException ex2)
			{ }
			}
			System.out.println("Error in file processing");
		}
    }//End RedFile method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     populateArray                                                                     *
 * Import:     csvRow (String), pArray (CovidRecord array), lineNum (Integer)                    *
 * Export:     none                                                                              *
 * Assertion:  Parse each line from the csv file and create objects in an array from that data   *
 * Created:    13/5/2022                                                                         *
 * Modified:   15/5/2022                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void populateArray(String csvRow, CovidRecord[] pArray, int pLineNum)
    {
		//Declare and initialise variables which the data from the csv will be allocated to. Initialising them here means I won't have to do it in the if statements further down
		String date = "", iso3 = "", continent = "", countryName = "", nuts = "";
		double latitude = 0.0, longitude = 0.0;
		int cumPos = 0, cumDec = 0, cumRec = 0, curPos = 0, hos = 0, care = 0;
		
		String[] splitLine;// Array for the csv data to initially occupy

		//Learnt the ,-1 from here: https://stackoverflow.com/questions/29440357/java-csv-file-parsing-does-not-parse-empty-columns-at-end
		splitLine = csvRow.split(",",-1); //Placing the csv data into the array. The ,-1 is to ensure that no matter if the row ends in blank values, the array will have the same length every iteration
		
		//A whole bunch of if statements for each "column" of the csv file stating that if it is blank, then don't update the variable, otherwise update the variable to the element value
		if(splitLine[0].equals(""))
		{}
		else
		{
			    date = splitLine[0];
		}

		if(splitLine[1].equals(""))
		{}
		else
		{
			iso3 = splitLine[1];
		}

		if(splitLine[2].equals(""))
		{}
		else
		{
			continent = splitLine[2];
		}

		if(splitLine[3].equals(""))
		{}
		else
		{
			countryName = splitLine[3];
		}

		if(splitLine[4].equals(""))
		{}
		else
		{
			latitude = Double.parseDouble(splitLine[4]); //Using Double.parseDouble to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[5].equals(""))
		{}
		else
		{
			longitude = Double.parseDouble(splitLine[5]); //Using Double.parseDouble to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[6].equals(""))
		{}
		else
		{
			cumPos = Integer.parseInt(splitLine[6]); //Using Integer.parseInt to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[7].equals(""))
		{}
		else
		{
			cumDec = Integer.parseInt(splitLine[7]); //Using Integer.parseInt to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[8].equals(""))
		{}
		else
		{
			cumRec = Integer.parseInt(splitLine[8]); //Using Integer.parseInt to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[9].equals(""))
		{}
		else
		{
			curPos = Integer.parseInt(splitLine[9]); //Using Integer.parseInt to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[10].equals(""))
		{}
		else
		{
			hos = Integer.parseInt(splitLine[10]); //Using Integer.parseInt to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[11].equals(""))
		{}
		else
		{
			care = Integer.parseInt(splitLine[11]); //Using Intger.parseInt to convert the string value read from the csv file into the data type required by the variable
		}

		if(splitLine[12].equals(""))
		{}
		else
		{
			nuts = splitLine[12];
		}
			
		Country temp = new Country(iso3, continent, countryName, nuts, latitude, longitude); //Creating a temporary Country object to use in the constructor for a CovidRecord object
		
		pArray[pLineNum] = new CovidRecord(); //Use the default constructor the populate the current element with an object to be edited in the next section of code

		//Editing the current elements' object using mutators
		pArray[pLineNum].setCountry(temp);
		pArray[pLineNum].setDate(date);
		pArray[pLineNum].setCumulativePositive(cumPos);
		pArray[pLineNum].setCumulativeDeceased(cumDec);
		pArray[pLineNum].setCumulativeRecovered(cumRec);
		pArray[pLineNum].setCurrentlyPositive(curPos);
		pArray[pLineNum].setHospitalised(hos);
		pArray[pLineNum].setIntensiveCare(care);

    }//End PopulateArray method

	
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     mainMenu                                                                                        *
 * Import:     pMainArray (CovidRecord array)                                                                  *
 * Export:     none                                                                                            *
 * Assertion:  Display a menu for the user to allow them to select what set of data they would like to view    *
 * Created:    13/5/2022                                                                                       *
 * Modified:   17/5/2022                                                                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void mainMenu(CovidRecord [] pMainArray)
	{
		Scanner sc = new Scanner(System.in); //Create scanner object for user input
	
		int choice = 0; //Variable used to get user input for switch statement further down
		boolean close = false; //Variable used for do-while loop

		do
		{
			//Display a menu to the user with the options to choose from. New line (\n) character used to have each option on a new line and save time writing System.out.println 10 times.
			System.out.println("\nPlease make a selection from the Menu below to choose which area (or date) to analyse:\n\n> 1 All countries\n> 2 Countries in South America\n> 3 Countries in North America\n> 4 Countries in Oceania\n> 5 Countries in Asia\n> 6 Countries in Africa\n> 7 Countries in Europe\n> 8 Enter a Country\n> 9 Enter a Date\n> 0 Exit\n\nEnter Selection:");
			try
			{
			choice = sc.nextInt();// Get user input

			switch(choice) //Switch statement for options depending on user input
			{
			case 1:
			    statisticsMenu(pMainArray, "in all countries"); //Send array containing all csv data to the StatisticsMenu method. String variable is used for display purposes to the user.
		        break;

			case 2:
			    southAmerica(pMainArray); //Send array containg all csv data to the SouthAmerica method
			break;

			case 3:
			    northAmerica(pMainArray); //Send array containg all csv data to the NorthAmerica method
			break;

			case 4:
			    oceania(pMainArray); //Send array containg all csv data to the Oceania method
			break;

			case 5:
			    asia(pMainArray); //Send array containg all csv data to the Asia method
			break;
		
			case 6:
			    africa(pMainArray); //Send array containg all csv data to the Africa method
			break;

			case 7:
			    europe(pMainArray); //Send array containg all csv data to the Europe method
			break;
		
			case 8:
			    countryChoice(pMainArray); //Send array containg all csv data to the CountryChoice method
			break;

			case 9:
			    dateChoice(pMainArray); //Send array containg all csv data to the DateChoice method
			break;

			case 0:
			    System.out.println("Thank you for using the JRC COVID-19 Analysis Program. Goodbye");
			    close = true; //update boolean value to allow the user to exit the loop
			break;

			default:
			    System.out.println("\nError. Invalid choice. Please enter the number next to the option you wish to choose"); //Message for a user input not stipulated in the switch statement
			}

			}
			catch(InputMismatchException error)
			{
				System.out.println("\nError. Invalid data type input. Please enter the number next to the option you wish to choose"); //Message for wrong data type input
				sc.next(); //Clear scanner buffer to prevent infinite looping
			}


		}while(!close); //End do-while loop around menu
	} //End MainMenu method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     statisticsMenu                                                                                  *
 * Import:     pStatArray (CovidRecord array), pDataSetName (String)       	                               *
 * Export:     none                                                                                            *
 * Assertion:  Display a menu for the user to allow them to select which statistic they would like to view     *
 * Created:    13/5/2022                                                                                       *
 * Modified:   17/5/2022                                                                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void statisticsMenu(CovidRecord[] pStatArray, String pDataSetName) //pDataSetName is an identifier used for display to remind the user which option they selected from the main menu 
	{
	    Scanner sc = new Scanner(System.in); //Create scanner object for user input

	    //Declare and initialise variables used to get user input display the answers 
	    int choice = 0, ans1 = 0, ans2 = 0 , ans3 = 0;
	    String  strAns1 = "", strAns2 = "", strAns3 = "", ans4 = "", ans5 = "", ans6 = "";
	    boolean close = false; //Variable used for the do-while loop

	    do
	    {
			//Display a Menu to the user with the options to select from. Again, "\n" used to save time writing System.out.println
			System.out.println("\nYou selected \"" + pDataSetName +"\". Please select from a statistic below:\n\n> 1 Total number of cumulatively positive cases\n> 2 Total number of cumulatively deceased cases\n> 3 Total number of cumulatively recovered cases\n> 4 Average daily number of currently positive cases\n> 5 Number and percentage of cumulatively positive cases recovered\n> 6 Number and percentage of cumulatively positive cases deceased\n> 7 All of the above statistics\n> 0 Return to Previous Menu\n\nEnter selection:");
			try
			{
			choice = sc.nextInt(); //Get user input

			switch(choice) //Switch statement for options depending on user input 
			{
			case 1:
				//Check if the imported array is an array populated with data from only one country by comparing the first two elements
				if(pStatArray[0].getCountry().getCountryName().equals(pStatArray[1].getCountry().getCountryName()))
				{
					ans1 = cumulativePositiveCountry(pStatArray); //Send array to country specific method
				}
				else
				{
					ans1 = cumulativePositive(pStatArray); //Send array to the standard method
				}
				strAns1 = createMessage(ans1); //Create a string answer for display
				System.out.println("\nCumulative number of positive cases " + pDataSetName + ": " + strAns1); //Display calculated answer to the user
			break;

			case 2:
				//Check if the imported array is an array populated with data from only one country by comparing the first two elements
				if(pStatArray[0].getCountry().getCountryName().equals(pStatArray[1].getCountry().getCountryName()))
				{
					ans2 = cumulativeDeceasedCountry(pStatArray); //Send array to country specific method
				}
				else
				{
					ans2 = cumulativeDeceased(pStatArray); //Send array to the standard method
				}
				strAns2 = createMessage(ans2); //Create a string answer for display
				System.out.println("\nCumulative number of deceased cases " +pDataSetName + ": " + strAns2); //Display calculated answer to the user
			break;
		
			case 3:
				//Check if the imported array is an array populated with data from only one country by comparing the first two elements
				if(pStatArray[0].getCountry().getCountryName().equals(pStatArray[1].getCountry().getCountryName()))
				{
					ans3 = cumulativeRecoveredCountry(pStatArray); //Send array to country specific method
				}
				else
				{
					ans3 = cumulativeRecovered(pStatArray); //Send array to the standard method
				}
				strAns3 = createMessage(ans3); //Create a string answer for display
				System.out.println("\nCumulative number of recovered cases " + pDataSetName + ": " + strAns3); //Display calculated answer to the user
			break;

			case 4:
				ans4 = currentlyPositiveAverage(pStatArray); //Assign variable the value returned from the CurrentlyPosotiveAverage method
				System.out.println("\nThe average number of daily cases " + pDataSetName + ": " + ans4); //Display calculated answer to the user
			break;

			case 5:
				ans5 = cumulativeRecoveredPercentage(pStatArray); //Assign variable the value returned from the CumulativeRecoveredPercentage method
				System.out.println("\n" + ans5 + " recovered cases " + pDataSetName + "."); //Display calculated answer to the user
			break;

			case 6:
				ans6 = cumulativeDeceasedPercentage(pStatArray); //Assign variable the value returned from the CumulativeDeceasedPercentage method
				System.out.println("\n" + ans6 + " deceased cases " + pDataSetName + "."); //Display calculated answer to the user
			break;

			case 7:
				//Assign variables to the returned value from the relevant method

				////Check if the imported array is an array populated with data from only one country by comparing the first two elements
				if(pStatArray[0].getCountry().getCountryName().equals(pStatArray[1].getCountry().getCountryName()))
				{
					//Send array to country specific methods
					ans1 = cumulativePositiveCountry(pStatArray);
					ans2 = cumulativeDeceasedCountry(pStatArray); 
					ans3 = cumulativeRecoveredCountry(pStatArray);
				}
				else
				{
					//Send the array to the standard methods
					ans1 = cumulativePositive(pStatArray);
					ans2 = cumulativeDeceased(pStatArray);
					ans3 = cumulativeRecovered(pStatArray);
				}
				//Create a string answers for display
				strAns1 = createMessage(ans1); 
				strAns2 = createMessage(ans2);
				strAns3 = createMessage(ans3);

				ans4 = currentlyPositiveAverage(pStatArray);
				ans5 = cumulativeRecoveredPercentage(pStatArray);
				ans6 = cumulativeDeceasedPercentage(pStatArray);

				//Display calculated answers to the user, once again using "\n" to save my fingers from rsi
				System.out.println("\nCumulative number of positive cases " + pDataSetName + ": " + strAns1 + "\n\nCumulative number of deceased cases " +pDataSetName + ": " + strAns2 + "\n\nCumulative number of recovered cases " + pDataSetName + ": " + strAns3 + "\n\nThe average number of daily cases " + pDataSetName + ": " + ans4 + "\n\n" + ans5 + "recovered cases " + pDataSetName + "."+ "\n\n" + ans6 + "deceased cases " + pDataSetName + ".");

			break;
		
			case 0:
				close = true; //Exit do-while loop, sending user back to the MainMenu method
			break;

			default:
				System.out.println("\nError. Invalid choice. Please enter the number next to the option you wish to choose"); //Message for user input not stipulated in the switch statement
			}

			}
			catch(InputMismatchException error)
			{
				System.out.println("\nError. Invalid data type input. Please enter the number next to the option you wish to choose"); //Message for wrong data type input
				sc.next(); //Clear scanner buffer to prevent inifite looping
			}


		}while(!close); //End do-while loop
    } //End statisticsMenu method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     southAmerica													       *
 * Import:     pMainArray (CovidRecord array)											       *
 * Export:     none														       *
 * Assertion:  Identify which data from the main array contains data from South America and populate a new array with ony that data.   *
 * Created:    14/5/2022                                                                                                               *
 * Modified:   15/5/2022										           	 	       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void southAmerica(CovidRecord[] pMainArray)
	{
		CovidRecord[] southArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into

		southArray = filterArrayContinent(southArray, pMainArray, "SA"); //Call method to filter array for only South America realted data

		southArray = fillArray(southArray); //Call method to fill null elements with default values

		statisticsMenu(southArray, "in South America"); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End SouthAmerica method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     northAmerica													       *
 * Import:     pMainArray (CovidRecord array)											       *
 * Export:     none														       *
 * Assertion:  Identify which data from the main array contains data from North America and populate a new array with ony that data.   *
 * Created:    14/5/2022                                                                                                               *
 * Modified:   15/5/2022													       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void northAmerica(CovidRecord[] pMainArray)
	{
		CovidRecord[] northArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into
		
		northArray = filterArrayContinent(northArray, pMainArray, "NA"); //Call method to filter array for only North America realted data

		northArray = fillArray(northArray); //Call method to fill null elements with default values

		statisticsMenu(northArray, "in North America"); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End NorthAmerica method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     oceania   													 *
 * Import:     pMainArray (CovidRecord array)											 *
 * Export:     none														 *
 * Assertion:  Identify which data from the main array contains data from Oceania and populate a new array with ony that data.   *
 * Created:    14/5/2022                                                                                                         *
 * Modified:   15/5/2022													 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void oceania(CovidRecord[] pMainArray)
	{
		CovidRecord[] oceaniaArray = new CovidRecord[pMainArray.length];  //Create new array for the filtered data to be copied into
		
		oceaniaArray = filterArrayContinent(oceaniaArray, pMainArray, "OC"); //Call method to filter array for only Oceania realted data

		oceaniaArray = fillArray(oceaniaArray); //Call method to fill null elements with default values

		statisticsMenu(oceaniaArray, "in Oceania"); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End oceania method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     asia									          			     *
 * Import:     pMainArray (CovidRecord array)										     *
 * Export:     none													     *
 * Assertion:  Identify which data from the main array contains data from Asia and populate a new array with ony that data.  *
 * Created:    14/5/2022                                                                                                     *
 * Modified:   15/5/2022												     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void asia(CovidRecord[] pMainArray)
	{
		CovidRecord[] asiaArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into

		asiaArray = filterArrayContinent(asiaArray, pMainArray, "AS"); //Call method to filter array for only Asia realted data

		asiaArray = fillArray(asiaArray); //Call method to fill null elements with default values

		statisticsMenu(asiaArray, "in Asia"); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End asia method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     africa    												       *
 * Import:     pMainArray (CovidRecord array)										       *
 * Export:     none													       *
 * Assertion:  Identify which data from the main array contains data from Africa and populate a new array with ony that data.  *
 * Created:    14/5/2022                                                                                                       *
 * Modified:   15/5/2022												       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void africa(CovidRecord[] pMainArray)
	{
		CovidRecord[] africaArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into

		africaArray = filterArrayContinent(africaArray, pMainArray, "AF"); //Call method to filter array for only Africa realted data

		africaArray = fillArray(africaArray); //Call method to fill null elements with default values

		statisticsMenu(africaArray, "in Africa"); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End africa method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     europe										        		       *
 * Import:     pMainArray (CovidRecord array)										       *
 * Export:     none													       *
 * Assertion:  Identify which data from the main array contains data from Europe and populate a new array with ony that data.  *
 * Created:    14/5/2022                                                                                                       *
 * Modified:   15/5/2022												       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void europe(CovidRecord[] pMainArray)
	{
		CovidRecord[] europeArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into

		europeArray = filterArrayContinent(europeArray, pMainArray, "EU"); //Call method to filter array for only Europe realted data

		europeArray = fillArray(europeArray); //Call method to fill null elements with default values

		statisticsMenu(europeArray, "in Europe"); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End europe method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     countryChoice														             *
 * Import:     pMainArray (CovidRecord array)												             *
 * Export:     none															             *
 * Assertion:  Identify which data from the main array contains data from the user's choice of country and populate a new array with ony that data.  *
 * Created:    14/5/2022                                                                                                                             *
 * Modified:   17/5/2022															     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void countryChoice(CovidRecord[] pMainArray)
	{
		Scanner sc = new Scanner(System.in); //Create scanner object for user input
		CovidRecord[] countryArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into
		boolean close = false; //Variable for do-while loop
		String choice = ""; //Variable to store user input
				
		do
		{
			System.out.println("\nWhich country would you like to view data from?"); //Ask the user to input their country of choice
			try
			{
			choice = sc.nextLine(); //Get user input
			}
			catch(InputMismatchException error)
			{
				System.out.println("Error. Invalid data type input");
			}

			countryArray = filterArrayCountry(countryArray, pMainArray, choice); //Call method to filter array with data only from the user's choice of country

			countryArray = fillArray(countryArray); //Call method to fill null elements with default values

			if(countryArray[0] == null) //Check if filtered arrays' element [0] is null. If it is, then that means the user entered an invalid country.
			{
				//Message telling to user they entered an invalid country and telling how to possibly fix it
				System.out.println("The country you entered was not found in the records. Please make sure you spelled it correctly using capital letters where appropriate");
			}
			else
			{
				close = true; //Exit do-while loop allowing data to be passed to the next method
			}

		}while(!close); //end do-while loop

	    	statisticsMenu(countryArray, "in " + choice); //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End countryCHoice method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     dateChoice														     *
 * Import:     pMainArray (CovidRecord array)												     *
 * Export:     none															     *
 * Assertion:  Identify which data from the main array contains data from user's choice of date and populate a new array with ony that data. *
 * Created:    14/5/2022                                                                                                                     *
 * Modified:   17/5/2022														     *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void dateChoice(CovidRecord[] pMainArray)
	{
		Scanner sc = new Scanner(System.in); //Create scanner object for user input
		CovidRecord[] dateArray = new CovidRecord[pMainArray.length]; //Create new array for the filtered data to be copied into
		boolean close = false; //Variable for do-while loop
		String choice = ""; //Variable to store user input
		int j = 0; //Variable used to make sure filtered array is populated in sequence

		do
		{
			System.out.println("\nWhich date would you like to view data from?");  //Ask the user to input their date of choice
			try
			{
				choice = sc.nextLine(); //Get user input
			}
			catch(InputMismatchException error)
			{
				System.out.println("Error. Invalid data type input");
			}

			dateArray = filterArrayDate(dateArray, pMainArray, choice); 

			dateArray = fillArray(dateArray);//Call method to fill null elements with default values

			if(dateArray[0] == null) //Check if filtered arrays' element [0] is null. If it is, then that means the user entered an invalid date.
			{
				//Message telling to user they entered an invalid date with help on how to fix it
				System.out.println("\nThe date you entered was not found in the records. Please enter a valid date. You need to enter your choice in dd/m/yyyy format. eg 1 is January and 12 is December. Note that the only dates available are the last day of each month for the year 2021\n");
			}
			else
			{
				close = true; //Exit do-while loop allowing data to be passed to the next method
			}
		}while(!close); //End do-while loop

		statisticsMenu(dateArray, "on " + choice);  //Send filtered array to StatisticsMenu method along with a String indentifying the dataset's origin.
	} //End dateChoice method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     filterArrayCountry                                                                      *
 * Import:     pFilteredArray (CovidRecord array), pMainArray (CovidRecord array), pCountry (String)   *
 * Export:     pFilteredArray (CovidRecord array)                                                      *
 * Assertion:  Filter an array with data only coming from the selected country                         *
 * Created:    17/5/2021                                                                               *    
 * Modified:   17/5/2021                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static CovidRecord[] filterArrayCountry(CovidRecord[] pFilteredArray, CovidRecord[] pMainArray, String pCountry)
	{
		int j = 0; //Variable used to make sure filtered array is populated in sequence

		/* I hope that comment made sense but just to make sure I'll explain it more here. Essentially I wanted the new array to be populated 
		 * starting from the first element ie [0]. Normally without this "j" variable then, due to the for loop, it would just be populated in
		 * whichever element the data was in in the main array. For example, if the first element to be copied into the array was found at [400] 
		 * of the main array, then all elements of the filtered array from [0] to [399] would all be null. Use of this "j" variable allows the 
		 * data from the example to be copied into the [0] element of the filtered array. Furthermore if the second and third elements to be copied
		 * were found at [401] and [743] respectively, then they would be placed in elements [1] and [2] with "j". Otherwise they would be placed 
		 * in elelements [401] and [743] with all elements inbetween being null. 
		 * 
		 * Populating the array in sequence ensures that if the required data exists it will be placed in element [0]. However, what if the user
		 * enters an invalid country? Making sure element [0] is always populated in the case of correct input means that I'm able to check if it 
		 * is empty in the case of incorrect input. 
		 *
		 * I hope that makes sense
		 * * * * * * * * * * * * * * */

		for(int i = 0; i < pMainArray.length; i++) //For loop to run through array checking for users' choice related data
		{
			if(pMainArray[i].getCountry().getCountryName().equals(pCountry)) //Check if the current element data comes from the user's choice
			{
				pFilteredArray[i-j] = pMainArray[i]; //Copy data from main array into the new, filtered array. i-j makes sure it's populated in sequence.
			}
			else
			{
			    j++; //Increment this variable when element is not copied, allowing the filtered array to be populated in sequence.
			}

		}

		return pFilteredArray;
	} //EndfilterArrayCountry method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     filterArrayDate                                                                     *
 * Import:     pFilteredArray (CovidRecord array), pMainArray (CovidRecord array), pDate (String)  *
 * Export:     pFilteredArray (CovidRecord array)                                                  *
 * Assertion:  Filter an array with data only coming from the selected date                        *
 * Created:    17/5/2021                                                                           *    
 * Modified:   17/5/2021                                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static CovidRecord[] filterArrayDate(CovidRecord[] pFilteredArray, CovidRecord[] pMainArray, String pDate)
	{
		int j = 0; //Variable used to make sure filtered array is populated in sequence

		for(int i = 0; i < pMainArray.length; i++) //For loop to run through array checking for users' choice related data
		{
			if(pMainArray[i].getDate().equals(pDate)) //Check if the current element data comes from the user's choice
			{
				pFilteredArray[i-j] = pMainArray[i]; //Copy data from main array into the new, filtered array. i-j makes sure it's populated in sequence.
			}
			else
			{
			    j++; //Increment this variable when element is not copied, allowing the filtered array to be populated in sequence.
			}

		}

		return pFilteredArray;
	} //End filterArrayDate method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     filterArrayContinent                                                                      *
 * Import:     pFilteredArray (CovidRecord array), pMainArray (CovidRecord array), pContinent (String)   *
 * Export:     pFilteredArray (CovidRecord array)                                                        *
 * Assertion:  Filter an array with data only coming from the selected continent                         *
 * Created:    17/5/2021                                                                                 *    
 * Modified:   17/5/2021                                                                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static CovidRecord[] filterArrayContinent(CovidRecord[] pFilteredArray, CovidRecord[] pMainArray, String pContinent)
	{
		int j = 0; //Variable used to make sure filtered array is populated in sequence

		for(int i = 0; i < pMainArray.length; i++)  //For loop to run through array checking for pContinent related data
		{
			if(pMainArray[i].getCountry().getContinent().equals(pContinent)) //Check if the current element data comes from pContinent
			{
				pFilteredArray[i-j] = pMainArray[i]; //Copy data from main array into the new, filtered array. i-j makes sure it's populated in sequence.
			}
			else
			{
			    j++; //Increment this variable when element is not copied, allowing the filtered array to be populated in sequence.
			}

		}

		return pFilteredArray; //Return the filtered array
	} //End filterArrayContinent method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     fillArray                                                 *
 * Import:     pArray (CovidRecord array)                                *
 * Export:     pArray (covidRecord array                                 *
 * Assertion:  Fill all null elements in an array with default values    *
 * Created:    17/5/2021                                                 *
 * Modified:   17/5/2021                                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 

	public static CovidRecord[] fillArray(CovidRecord[] pArray)
	{
		//For loop to run through the filtered array checking for null elements except the first as that should either be filled with proper data or remain null to check for incorrect input.
		for(int i = 1; i < pArray.length; i++) 
		{
			if(pArray[i] == null) //Check if the current element is null
			{
				pArray[i] = new CovidRecord(); //If it is null, then populate it using the default constructor
			}
		}

		return pArray; //Return filled array
	} //End fillArray method


/* For the cumulative methods I would really have liked to be able to only take the lastest record for each country which would have been fairly easy if there was any entry for each country 
 * on every date. Then I could just use the cumulative value from the "31/12/2021" date and then proceed with the calculation. But some countries last available record is from 30/11/2021 and some 
 * countries have records for both dates so I don't know how to stipulate only getting the lastest record and make sure there are no two values that come from the same country.
 *
 * This has resulted in the cumulative results just being the sum of ALL releveant data meaning the calculated answer is vastly higher than what it should actually be
 *
 * This basic method does work for date filtered arrays and I've also written methods that work for country filtered arrays, however I don't know to get what I want for continent filtered arrays.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativePositive                                                              *
 * Import:     pArray (CovidRecord array)                                                      *
 * Export:     sum (Integer)                                                                   *
 * Assertion:  Calculate the total cumulative positive amount of cases and return that value   *
 * Created:    14/5/2021                                                                       *
 * Modified:   17/5/2021                                                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 

    public static int cumulativePositive(CovidRecord[] pArray)
    {
		int sum = 0; //Variable to hold the information of the sum

		for(int i = 0; i < pArray.length; i++) //For loop to to run through array and grab valid values.
		{
			//Defining a "valid" value as greater than 0 becaause I don't how to differntiate between what was 0 in the record and what became 0 due the constructor
			if(pArray[i].getCumulativePositive() > 0) 
			{
			sum = sum + pArray[i].getCumulativePositive(); //Increase the value of sum by adding the current element's value to the previously calculated value.
			}
		}

		return sum; //Return calculated value
    } //End cumulativePositive method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativePositiveCountry                                                 *
 * Import:     pArray (CovidRecord array)                                                *
 * Export:     value (Integer)                                                           *
 * Assertion:  Find the total cumulative positive amount of cases and return that value  *
 * Created:    16/5/2021                                                                 *
 * Modified:   17/5/2021                                                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int cumulativePositiveCountry(CovidRecord[] pArray)
    {
	    int value = 0;

	    //What this for loop and bunch of if statements are doing is going through the array and figuring out which element has the latest or most current value
	    for(int i = 0; i < pArray.length; i++)
	    {
		    if(pArray[i].getDate().equals("31/12/2021")) //If the current element is from December
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from December
		    }
		    else if(pArray[i].getDate().equals("30/11/2021")) //If the current element is from November
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from Novemeber
		    }
		    else if(pArray[i].getDate().equals("31/10/2021")) //If the currentl element os from October
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from October
		    }
		    else if(pArray[i].getDate().equals("30/9/2021")) //If the current element is from September
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from September
		    }
		    else if(pArray[i].getDate().equals("31/8/2021")) //If the current element is from August
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from August
		    }
		    else if(pArray[i].getDate().equals("31/7/2021")) //If the current element is from July
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from July
		    }
		    else if(pArray[i].getDate().equals("30/6/2021")) //If the current element is from June
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from June
		    }
		    else if(pArray[i].getDate().equals("31/5/2021")) //If the current element is from May
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from May
		    }
		    else if(pArray[i].getDate().equals("30/4/2021")) //If the current element is from April
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from April
		    }
		    else if(pArray[i].getDate().equals("31/3/2021")) //If the current element is from March
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from March
		    }
		    else if(pArray[i].getDate().equals("28/2/2021")) //If the current element is from February
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from February
		    }
		    else if(pArray[i].getDate().equals("31/1/2021")) //If the current element is from January
		    {
			value = pArray[i].getCumulativePositive(); //Value is assigned the number from January
		    }
	    }

	    return value; //Return found value
    } //End cumulativePositiveCountry method    

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativeDeceased                                                              *
 * Import:     pArray (CovidRecord array)                                                      *
 * Export:     sum (Integer)                                                                   *
 * Assertion:  Calculate the total cumulative deceased amount of cases and return that value.  *
 * Created:    14/5/2021                                                                       *
 * Modified:   17/5/2021                                                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int cumulativeDeceased(CovidRecord[] pArray)
    {
		int sum = 0; //Variable to hold the information of the sum

		for(int i = 0; i < pArray.length; i++) //For loop to to run through array and grab valid values.
		{
			//Defining a "valid" value as greater than 0 becaause I don't how to differntiate between what was 0 in the record and what became 0 due the constructor
			if(pArray[i].getCumulativeDeceased() > 0)
			{
			sum = sum + pArray[i].getCumulativeDeceased(); //Increase the value of sum by adding the current element's value to the previously calculated value.
			}
		}

		return sum; //Return calculated value
    } //End cumulativeDeceased method	

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativeDeceasedCountry                                                 *
 * Import:     pArray (CovidRecord array)                                                *
 * Export:     value (Integer)                                                           *
 * Assertion:  Find the total cumulative deceased amount of cases and return that value  *
 * Created:    16/5/2021                                                                 *
 * Modified:   17/5/2021                                                                 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int cumulativeDeceasedCountry(CovidRecord[] pArray)
    {
	    int value = 0;

	    //What this for loop and bunch of if statements are doing is going through the array and figuring out which element has the latest or most current value
	    for(int i = 0; i < pArray.length; i++)
	    {
		    if(pArray[i].getDate().equals("31/12/2021")) //If the current element is from December
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from December
		    }
		    else if(pArray[i].getDate().equals("30/11/2021")) //If the current element is from November
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from Novemeber
		    }
		    else if(pArray[i].getDate().equals("31/10/2021")) //If the currentl element os from October
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from October
		    }
		    else if(pArray[i].getDate().equals("30/9/2021")) //If the current element is from September
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from September
		    }
		    else if(pArray[i].getDate().equals("31/8/2021")) //If the current element is from August
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from August
		    }
		    else if(pArray[i].getDate().equals("31/7/2021")) //If the current element is from July
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from July
		    }
		    else if(pArray[i].getDate().equals("30/6/2021")) //If the current element is from June
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from June
		    }
		    else if(pArray[i].getDate().equals("31/5/2021")) //If the current element is from May
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from May
		    }
		    else if(pArray[i].getDate().equals("30/4/2021")) //If the current element is from April
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from April
		    }
		    else if(pArray[i].getDate().equals("31/3/2021")) //If the current element is from March
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from March
		    }
		    else if(pArray[i].getDate().equals("28/2/2021")) //If the current element is from February
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from February
		    }
		    else if(pArray[i].getDate().equals("31/1/2021")) //If the current element is from January
		    {
			value = pArray[i].getCumulativeDeceased(); //Value is assigned the number from January
		    }
	    }

	    return value; //Return found value
    } //End cumulativeDeceasedCountry method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativeRecovered                                                               *
 * Import:     pArray (CovidRecord array)                                                        *
 * Export:     sum (Integer)                                                                     *
 * Assertion:  Calculate the total cumulative recovered amount of cases and return that value.   *
 * Created:    14/5/2021                                                                         *
 * Modified:   17/5/2021                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static int cumulativeRecovered(CovidRecord[] pArray)
    {
		int sum = 0; //Variable to hold the information of the sum

		for(int i = 0; i < pArray.length; i++) //For loop to to run through array and grab valid values.
		{
			 //Defining a "valid" value as greater than 0 becaause I don't how to differntiate between what was 0 in the record and what became 0 due the constructor
			if(pArray[i].getCumulativeRecovered() > 0)
			{
			sum = sum + pArray[i].getCumulativeRecovered(); //Increase the value of sum by adding the current element's value to the previously calculated value.
			}
		}
		
		return sum; //Return calculated value
    } //End cumulativeRecovered method	

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativeRecoveredCountry                                                  *
 * Import:     pArray (CovidRecord array)                                                  *
 * Export:     value (Integer)                                                             *
 * Assertion:  Find the total cumulative recovered amount of cases and return that value   *
 * Created:    16/5/2021                                                                   *
 * Modified:   17/5/2021                                                                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    public static int cumulativeRecoveredCountry(CovidRecord[] pArray)
    {
	    int value = 0; //Variable to store value to be returned

	    //What this for loop and bunch of if statements are doing is going through the array and figuring out which element has the latest or most current value
	    for(int i = 0; i < pArray.length; i++)
	    {
		    if(pArray[i].getDate().equals("31/12/2021")) //If the current element is from December
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from December
		    }
		    else if(pArray[i].getDate().equals("30/11/2021")) //If the current element is from November
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from Novemeber
		    }
		    else if(pArray[i].getDate().equals("31/10/2021")) //If the currentl element os from October
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from October
		    }
		    else if(pArray[i].getDate().equals("30/9/2021")) //If the current element is from September
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from September
		    }
		    else if(pArray[i].getDate().equals("31/8/2021")) //If the current element is from August
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from August
		    }
		    else if(pArray[i].getDate().equals("31/7/2021")) //If the current element is from July
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from July
		    }
		    else if(pArray[i].getDate().equals("30/6/2021")) //If the current element is from June
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from June
		    }
		    else if(pArray[i].getDate().equals("31/5/2021")) //If the current element is from May
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from May
		    }
		    else if(pArray[i].getDate().equals("30/4/2021")) //If the current element is from April
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from April
		    }
		    else if(pArray[i].getDate().equals("31/3/2021")) //If the current element is from March
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from March
		    }
		    else if(pArray[i].getDate().equals("28/2/2021")) //If the current element is from February
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from February
		    }
		    else if(pArray[i].getDate().equals("31/1/2021")) //If the current element is from January
		    {
			value = pArray[i].getCumulativeRecovered(); //Value is assigned the number from January
		    }
	    }

	    return value; //Return found value
    } //End cumulativeRecoveredCountry method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     currentlyPositiveAverage                                                                *
 * Import:     pArray (CovidRecord array)                                                              *
 * Export:     averageString (String)                                                                  *
 * Assertion:  Calculate the average daily currently positive amount of cases and return that value.   *
 * Created:    14/5/2021                                                                               *
 * Modified:   17/5/2021                                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static String currentlyPositiveAverage(CovidRecord[] pArray)
    {
		double total = 0, average = 0;
		int numToDivide = 0; //Variable to know how many values have been used to know what number to divide by for the average calculation
		String averageString = "";


	/* I wasn't entirely sure what was meant by "average daily number of currently positive cases" and after writing the first calculation, 
	 * it didn't really seem right so I wrote the second calculation. The second seems to be closer to what I think David meant by that option 
	 * so that's why I left that one "un-comment blocked" but I left the first calculation there just in case
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


		/*for(int i = 0; i < pArray.length; i++) //For loop to to run through array and grab valid values.
		{
			if(pArray[i].getCurrentlyPositive() > 0) //Check if the value is valid
			{
			total = total + pArray[i].getCurrentlyPositive(); //Increase the value of sum by adding the current element's value to the previously calculated value.
			numToDivide++; //Increment this variable to keep track of how many values have been used in the calculation
			}
		}*/

		//Or, which I thought was more correct...
		
			
		//Calculate the total number of currently positive cases by adding all the month values together
		total = calculateTotal(pArray);
		numToDivide = 12; //There are 12 months, hense divide the total by 12


		if(total > 0) //Check if the total is valid for calculation
		{
			average = total/numToDivide; //Calculate average value

			averageString = String.format("%.2f", average); //Round calculated answer to 2 decimal points
		}
		else if(total == 0) //Date is invalid
		{
			averageString = "No data available"; //If data is "invalid" then rather than just returning 0, create a more meaningful message
		}

		return averageString; //Return calulated answer
    } //End CurrentlyPositiveAverage method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     calculateTotal                                                        *
 * Import:     pArray (CovidRecord array)                                            *
 * Export:     total (double)                                                        *
 * Assertion:  Calculate the total currently positive cases and return that value.   *
 * Created:    17/5/2021                                                             *
 * Modified:   17/5/2021                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static double calculateTotal(CovidRecord[] pArray)
    {
		//Variables used to store each month's number of currently positive cases
		double total = 0, sumJan = 0, sumFeb = 0, sumMar = 0, sumApr = 0, sumMay = 0, sumJun = 0, sumJul = 0, sumAug = 0, sumSep = 0, sumOct = 0, sumNov = 0, sumDec = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/1/2021")) //Collect all vaules from January
			{
				sumJan = sumJan + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("28/2/2021")) //Collect all vaules from February
			{
				sumFeb = pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("31/3/2021")) //Collect all vaules from March
			{
				sumMar = sumMar + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("30/4/2021")) //Collect all vaules from April
			{
				sumApr = sumApr + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("31/5/2021")) //Collect all vaules from May
			{
				sumMay = sumMay + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("30/6/2021")) //Collect all vaules from June
			{
				sumJun = sumJun + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("31/7/2021")) //Collect all vaules from July
			{
				sumJul = sumJul + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("31/8/2021")) //Collect all vaules from August
			{
				sumAug = sumAug + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("30/9/2021")) //Collect all vaules from September
			{
				sumSep = sumSep + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("31/10/2021")) //Collect all vaules from October
			{
				sumOct = sumOct + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("30/11/2021")) //Collect all vaules from November
			{
				sumNov = sumNov + pArray[i].getCurrentlyPositive(); //And add them together
			}
			else if(pArray[i].getDate().equals("31/12/2021")) //Collect all vaules from December
			{
				sumDec = sumDec + pArray[i].getCurrentlyPositive(); //And add them together
			}
		}
		
		//Calculate the total by adding all the month values together
		total = sumJan + sumFeb + sumMar + sumApr + sumMay + sumJun + sumJul + sumAug + sumSep + sumOct + sumNov + sumDec;

		return total; // Return calculated value
    } //End calculateTotal method


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativeRecoveredPercentage                                       *
 * Import:     pArray (CovidRecord array)                                          *
 * Export:     ansString (String)                                                  *
 * Assertion:  Calculate the percentage of recovered cases and return that value.  *
 * Created:    14/5/2021                                                           *
 * Modified:   17/5/2021                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    public static String cumulativeRecoveredPercentage(CovidRecord[] pArray)
    {
		double sumPos = 0, sumRec = 0, ans = 0; //Variables to store values needed for later calculation
		String ansRound = "", ansString = ""; //String variables for better readability

		//Check if the imported array is an array populated with data from only one country by comparing the first two elements
		if(pArray[0].getCountry().getCountryName().equals(pArray[1].getCountry().getCountryName()))
		{
			//Send array to  country  specific methods
			sumPos = cumulativePositiveCountry(pArray);
			sumRec = cumulativeRecoveredCountry(pArray);
		}
		else //If the array is not country specific
		{
			//Then send array to standard methods
			sumPos = cumulativePositive(pArray);
			sumRec = cumulativeRecovered(pArray);
		}

		ansString = percentageMessage(sumRec, sumPos);
		
		return ansString; //return calculated answer
    } //End CumulativeRecoveredPercentage method	

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     cumulativeDecasedPercentage                                         *
 * Import:     pArray (CovidRecord array)                                          *
 * Export:     ansString (String)                                                  *
 * Assertion:  Calculate the percentage of deceased cases and return that value.   *
 * Created:    14/5/2021                                                           *
 * Modified:   17/5/2021                                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static String cumulativeDeceasedPercentage(CovidRecord[] pArray)
    {
		double sumPos = 0, sumDec = 0, ans = 0; //Variables to store values needed for later calculation
		String ansRound = "", ansString = ""; //String variables for better readability

		//Check if the imported array is an array populated with data from only one country by comparing the first two elements
		if(pArray[0].getCountry().getCountryName().equals(pArray[1].getCountry().getCountryName()))
		{
			//Send array to  country  specific methods
			sumPos = cumulativePositiveCountry(pArray);
			sumDec = cumulativeDeceasedCountry(pArray);
		}
		else //If the array is not country specific
		{
			//Then send array to standard methods
			sumPos = cumulativePositive(pArray);
			sumDec = cumulativeDeceased(pArray);
		}

		ansString = percentageMessage(sumDec, sumPos);

		return ansString; //Return calculated answer
    } //End CumulativeDeceasedPercentage method

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:     percentageMessage                                           *
 * Import:     pNum1 (Double), pNum2 (Double)                              *
 * Export:     ansString (String)                                          *
 * Assertion:  Calculate percentage and convert it to string for display   *
 * Created:    18/5/2021                                                   *
 * Modified:   18/5/2021                                                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static String percentageMessage(double pNum1, double pNum2)
    {
		double ans = 0;
		String ansRound = "", ansString = "";

		if((pNum1 > 0) && (pNum2 > 0)) //Make sure both calculateed values are valid
		{
			ans = (pNum1/pNum2) * 100; //Perfrom percentage calculation

			ansRound = String.format("%.2f", ans); //Round number to 2 decimal points for readbility

			ansString = ansRound + "% (" + pNum1 + "/" + pNum2 + ") of "; //Format answer as a string for more meaningful display
		}
		else if((pNum1 == 0) || (pNum2 == 0)) //If either value is invalid
		{
			ansString = "No data available on "; //Create a more meaningful message than just returning 0
		}

		return ansString;
    }


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method: createMessage							     *
 * Import: pNum (Integer)							     *
 * Export: ans (String)								     *
 * Assertion: Convert a integer into a string to display a more meaningful message   *
 * Created: 16/5/2021								     *
 * Modified: 17/5/2021                                                               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static String createMessage(int pNum)
    {
		String ans = "";
			if(pNum > 0) //Check if the imported value is valid
		{
			ans = "" + pNum; //"Convert" the integer to a string
		}
		else if(pNum == 0) //If the the imported value is invalid
		{
			ans = "No data available"; //Create a more meaningful message than just showing "0" to the user
		}

		return ans; //Return the created string message
	} //End CreateMessage method
	
/* Below is my failed attempt at writing a method that only adds up the values from the lastest date of each country within a continent
 *
 * What I wamted this code to do was to add all the values from 31/12/2021
 *
 * Then it would run through all the 30/11/2021 values and for each entry from 30/11/2021 it would check the if whatever country the entry 
 * belonged to had already had a value used in the previous iteration. If the country did have a value from 31/12/2021 then the 30/11/2021 entry
 * wouldn't be added to the total and the code would move on to the next entry. If there was a country who didn't have any data for 31/12/2021, 
 * it would add that value to the total.
 *
 * It would then do the same for October, September, August etc. until the total incorporated all the most recent values from each country on 
 * the selected continent with no two values being from the same country.
 *
 * Trying to figure this out broke me and, while I'm sure I've missed something super obvious I just can't get anything to work properly. 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */



	/*public static int cumulativePositiveContinent(CovidRecord[] pArray)
	{
		int sum = 0, j = 0, k = 0;
		String[] countryNamesArray = new String[1784];

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/12/2021"))
			{
				sum = sum + pArray[i].getCumulativePositive();
				countryNamesArray[i-j] = pArray[i].getCountry().getCountryName();
				//System.out.println(pArray[i].getCountry().getCountryName());
				k++;
			}
			else
			{
				j++;
			}
		}
		for(int i = 0; i < countryNamesArray.length; i++)
		{
			System.out.println("from the array: " + countryNamesArray[i]);
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("30/11/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().getCountryName().equals(countryNamesArray[e]))
					{j++; 
						//System.out.println("j= " + j);
					}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
						//j++;
						System.out.println(countryNamesArray[e-j+k]);
						System.out.println("\ne = " + e + "\nj = " + j + "\nk = " + k + "\ne-j+k = " + (e-j+k));
					}
				}
			}
			else
			{
				//j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/10/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("30/9/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/8/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/7/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("30/6/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/5/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("30/4/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/3/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("28/2/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		j = 0;

		for(int i = 0; i < pArray.length; i++)
		{
			if(pArray[i].getDate().equals("31/1/2021"))
			{
				for(int e = 0; e < pArray.length; e++)
				{
					if(pArray[e].getCountry().toString().equals(countryNamesArray[e]))
					{}
					else
					{
						sum = sum + pArray[e].getCumulativePositive();
						countryNamesArray[e-j+k] = pArray[e].getCountry().toString();
						k++;
					}
				}
			}
			else
			{
				j++;
			}
		}

		return sum;




	}*/
}


