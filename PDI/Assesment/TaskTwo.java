/* * * * * * * * * * * * * * * * * * * * * 
 * Title:     TaskTwo                    *
 * Author:    Jacob Jonas, 18439731      *
 * Created:   05/04/2022                 *
 * Modified:  06/04/2022                 *
 * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
public class TaskTwo
{
	public static void main(String[] args)
	{
		//Introduce scanner to recieve user input
		Scanner sc = new Scanner(System.in);

		//Declare integer variables		
		int choice, choiceCorrect;
		//Declare averages as double variables for accuracy
		double averageDTS, averageAnna, averageBridgerton;
		//Declare long variables to avoid numbers being x.xxxxxxxE7 for example.
		long viewCount, sumDTS, sumAnna, sumBridgerton, finaleDTS, finaleAnna, finaleBridgerton;
		//Declare boolean variables used for entering and leaving loops
		boolean closeDTS = false, closeAnna = false, closeBridgerton = false, closeMenu = false;
		boolean closeDTSCorrect = false, closeAnnaCorrect = false, closeBridgertonCorrect = false;
		//Declare string variables for presenting results
		String episodeDTS, episodeAnna, episodeBridgerton, mostViewed, highestFinale;

		//Declare and create arrays
		long[] arrayDTS = new long[10];
		long[] arrayAnna = new long[9];
		long[] arrayBridgerton = new long[8];

		//Declare variables for each respective arrays length
		int arrayDTSLength = arrayDTS.length;
		int arrayAnnaLength = arrayAnna.length;
		int arrayBridgertonLength = arrayBridgerton.length;

		//Present program header
		System.out.println("             ************************************************************");
		System.out.println("             |  Welcome to the Netflix Season Episode Program Analyser  |");
		System.out.println("             |       This program tracks views per episode of the       |");
		System.out.println("             |                  selected series season                  |");
		System.out.println("             ************************************************************");
		System.out.println("");
		System.out.println("Data Entry: ");

		//Create a while loop around try expression because it's the only way to get it to work how I want it to. I'm sure there's a better way.
		while(!closeDTS)
		{
			//Begin try expression around whole code section that's being checked because I can't get it to work any other way
			try
			{
				//Start do-while loop for checking DTS episode input
				do
				{
					//Prompt user for input
					System.out.println("Please enter the view count for each episode of Drive to Survive, season 4: ");
					//Begin for loop to fill Drive to Survive array
					for(int i = 0; i < arrayDTSLength; i++)
					{
						System.out.println("Episode " + (i+1) + ":");
						//Get user input
						viewCount = sc.nextLong();
						//If statement assering, unless input is larger than 99999, it only counts as 1
						if(viewCount > 99999)
						{
							arrayDTS[i] = viewCount;
						}
						else
						{
							arrayDTS[i] = 1;
						}
					}
					//For loop to display entered values to the user
					for(int i = 0; i < arrayDTSLength; i++)
					{
						System.out.println("Episode " + (i+1) + " views: " + arrayDTS[i]);
					}
					//Do-while loop for input checking
					do
					{
						//Ask user if the numbers shown are correct
						System.out.println("");
						System.out.println("Are the values correct?");
						System.out.println("    > 1. Yes");
						System.out.println("    > 0. No");
						//Get user input
						choiceCorrect = sc.nextInt();

						//"Yes" option
						if(choiceCorrect == 1)
						{
							//Exit both loops
							closeDTS = true;
							closeDTSCorrect = true;
						}
						//"No" option
						else if(choiceCorrect == 0)
						{
							//Take user back to episode input process
							System.out.println("Okay, restarting input process...");
							System.out.println(" ");
							//Exit current nested loop
							closeDTSCorrect = true;
						}
						//Contingency for wrong input
						else
						{
							System.out.println("Error. Invalid input");
							//Reassert booleans as false for possible future loops
							closeDTS = false;
							closeDTSCorrect = false;
						}
					//End do-while loop around input checking
					}while(!closeDTSCorrect);
				//End do-while loop around episode input
				}while(!closeDTS);
			}
			//catch mismatch error
			catch(InputMismatchException error)
			{
				//Because I don't know how to resume in the middle of a for loop
				System.out.println("Input Mismatch error. Please start from the beginning");
				//Clear scanner buffer to prevent infinte looping
				sc.next();
			}
		}
		//Create a while loop around try expression		
		while(!closeAnna)
		{
			//Begin try expression
			try
			{
				//Start do-while loop for checking Inventing Anna episode input
				do
				{
					//Prompt user for input
					System.out.println("Please enter the view count for each episode of Inventing Anna: ");
					//Begin for loop to fill Inventing Anna array
					for(int i = 0; i < arrayAnnaLength; i++)
					{
						System.out.println("Episode " + (i+1) + ":");
						//Get user input
						viewCount = sc.nextLong();
						//If statement assering, unless input is larger than 99999, it only counts as 1
						if(viewCount > 99999)
						{
							arrayAnna[i] = viewCount;
						}
						else
						{
							arrayAnna[i] = 1;
						}
					}
					//For loop to display entered values to the user
					for(int i = 0; i < arrayAnnaLength; i++)
					{
						System.out.println("Episode " + (i+1) + " views: " + arrayAnna[i]);
					}
					//Do-while loop for input checking
					do
					{
						//Ask user if the numbers shown are correct
						System.out.println("");
						System.out.println("Are the values correct?");
						System.out.println("    > 1. Yes");
						System.out.println("    > 0. No");
						//Get user input
						choiceCorrect = sc.nextInt();
						//"Yes" option
						if(choiceCorrect == 1)
						{
							//Exit both loops
							closeAnna = true;
							closeAnnaCorrect = true;
						}
						//"No" option
						else if(choiceCorrect == 0)
						{
							//Take user back to episode input process
							System.out.println("Okay, restarting input process...");
							System.out.println(" ");
							//Exit current nested loop
							closeAnnaCorrect = true;
						}
						//Contingency for wrong input
						else
						{
							System.out.println("Error. Invalid input");
							//Reassert booleans as false for possible future loops
							closeAnna = false;
							closeAnnaCorrect = false;
						}
					//End do-while loop around input checking
					}while(!closeAnnaCorrect);
				//End do-while loop around episode input
				}while(!closeAnna);
			}
			//catch mismatch error
			catch(InputMismatchException error)
			{
				//Because I don't know how to resume in the middle of a for loop
				System.out.println("Input Mismatch error. Please start from the beginning");
				//Clear scanner buffer to prevent infinte looping				
				sc.next();
			}
		}
		//Create a while loop around try expression	
		while(!closeBridgerton)
		{
			//Begin try expression
			try
			{
				//Start do-while loop for checking Bridgerton episode input
				do
				{
					//Prompt user for input
					System.out.println("Please enter the view count for each episode of Bridgerton, season 2: ");
					//Begin for loop to fill Bridgerton array
					for(int i = 0; i < arrayBridgertonLength; i++)
					{
						System.out.println("Episode " + (i+1) + ":");
						//Get user input
						viewCount = sc.nextLong();
						//If statement assering, unless input is larger than 99999, it only counts as 1
						if(viewCount > 99999)
						{
							arrayBridgerton[i] = viewCount;
						}
						else
						{
							arrayBridgerton[i] = 1;
						}
					}
					//For loop to display entered values to user
					for(int i = 0; i < arrayBridgertonLength; i++)
					{
						System.out.println("Episode " + (i+1) + " views: " + arrayBridgerton[i]);
					}
					//Do-while loop for input checking
					do
					{
						//Ask user if the numbers shown are correct
						System.out.println("");
						System.out.println("Are the values correct?");
						System.out.println("    > 1. Yes");
						System.out.println("    > 0. No");
						//Get user input
						choiceCorrect = sc.nextInt();
						//Yes option
						if(choiceCorrect == 1)
						{
							//Exit both loops
							closeBridgerton = true;
							closeBridgertonCorrect = true;
						}
						else if(choiceCorrect == 0)
						{
							//Take user back to episode input process
							System.out.println("Okay, restarting input process...");
							System.out.println(" ");
							//Exit current nested loop
							closeBridgertonCorrect = true;
						}
						//Contigency for wrong input
						else
						{
							System.out.println("Error. Invalid input");
							//Reassert booleans as false for possible future loops
							closeBridgerton = false;
							closeBridgertonCorrect = false;
						}
					//End do-while loop around input checking
					}while(!closeBridgertonCorrect);
				//End do-while loop around episode input
				}while(!closeBridgerton);
			}
			//Catch mismatch error
			catch(InputMismatchException error)
			{
				//Because I don't know how to resume in the middle of a for loop
				System.out.println("Input Mismatch error. Please start from the beginning");
				//Clear scanner buffer to prevent infinite looping
				sc.next();
			}
		}
		//Calculate the sum of the values in each array in preperation for menu selection by calling a method
		sumDTS = SumCalculation(arrayDTS, arrayDTSLength);
		sumAnna = SumCalculation(arrayAnna, arrayAnnaLength);
		sumBridgerton = SumCalculation(arrayBridgerton, arrayBridgertonLength);
		//Calculate the average of the values in each array in preperation for menu selection by calling a method
		averageDTS = AverageCalculation(sumDTS, arrayDTSLength);
		averageAnna = AverageCalculation(sumAnna, arrayAnnaLength);
		averageBridgerton = AverageCalculation(sumBridgerton, arrayBridgertonLength);
		//Assert where to find episode finale values
		finaleDTS = arrayDTS[arrayDTSLength-1];
		finaleAnna = arrayAnna[arrayAnnaLength-1];
		finaleBridgerton = arrayBridgerton[arrayBridgertonLength-1];

		//Create a while loop around try expression
		while(!closeMenu)
		{
			//Begin try expression
			try
			{
				//Start do-while loop for checking Menu input
				do
				{
					//Present a menu to the user
					System.out.println("");
					System.out.println("What would you like to do?");	
					System.out.println("    > 1. Display average view count for each series:");
					System.out.println("    > 2. Display highest viewed episode from each series:");
					System.out.println("    > 3. Display the most watched series:");
					System.out.println("    > 4. Display the series with the highest viewed season finale:");
					System.out.println("    > 5. Exit the program");
					//Get user input
					choice = sc.nextInt();
					//Begin switch statement for menu choice
					switch(choice)
					{
					//Case statement for average choice
					case 1:
						//Display results to the user
						System.out.println("The average viewership for Drive to Survive, season 4 is: " + averageDTS + " views per episode");
						System.out.println("The average viewership for Inventing Anna is: " + averageAnna + " views per episode");
						System.out.println("The average viewership for Bridgerton, season 2 is: " + averageBridgerton + " views per episode");
					break;
					//Case statement for highest episode view count choice
					case 2:
						//Call function to dertmine the highest viewed episode for each series
						episodeDTS = HighestValue(arrayDTS, arrayDTSLength);
						episodeAnna = HighestValue(arrayAnna, arrayAnnaLength);
						episodeBridgerton = HighestValue(arrayBridgerton, arrayBridgertonLength);
						//Display results to the user
						System.out.println("Drive to Survive, season 4's " + episodeDTS);
						System.out.println("Inventing Anna's " + episodeAnna);
						System.out.println("Bridgerton, season 2's " + episodeBridgerton);
					break;
					//Case statement for most watched choice
					case 3:
						//Call method to determine which seriers had the highest total views
						mostViewed = HighestTotal(sumDTS, sumAnna, sumBridgerton);
						//Display result to user
						System.out.println(mostViewed);
					break;
					//Case statement for finale choice
					case 4:
						//Call method to determine which seriers had the highest viewed finale
						highestFinale = HighestFinale(finaleDTS, finaleAnna, finaleBridgerton);
						//Display result to the user
						System.out.println(highestFinale);
					break;
					//Case statement for exit choice
					case 5:
						System.out.println("Goodbye.");
						closeMenu = true;
					break;
					
					default:
						//Display error to user for wrong input
						System.out.println("Error. Invalid Input");
					}
				//End do-while loop around menu
				}while(!closeMenu);		
			}
			//Catch mismatch error
			catch(InputMismatchException error)
			{
				//Display error to the user
				System.out.println("Input Mismatch error. Please try again");
				//Clear scanner buffer to prevent infinte looping
				sc.next();
			}
		}
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   SumCalculation                            *
	 * Author:   Jacob Jonas, 18439731                     *
	 * Import:   array (long) array length (int)           *
	 * Export:   sum (long)                                *
	 * Created:  05/04/2022                                *
	 * Modified: 06/04/2022                                *
	 * Purpose:  Find the sum of the numbers in an array   *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 
	public static long SumCalculation(long[] pArray, int pLength)
	{
		long sum = 0;
		//For loop to calculate sum of array values
		for(int i = 0; i < pLength; i++)
		{
			sum = sum + pArray[i];
		}
		//Send result value back to main
		return sum;
	}			

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   AverageCalculation                              *
	 * Author:   Jacob Jonas, 18439731                           *
	 * Import:   sum (long) array length (int)                   *
	 * Export:   average (double)                                *
	 * Created:  05/04/2022                                      *
	 * Modified: 06/04/2022                                      *
	 * Purpose:  Find the average of the numbers in an array     *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 		
	public static double AverageCalculation(long pSum, int pLength)
	{	
		//Calculate average				
		double average = pSum/pLength;	
		//Send result back to main
		return average;
	}	

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   HighestValue                                        *
	 * Author:   Jacob Jonas, 18439731                               *
	 * Import:   array (double)                                      *
	 * Export:   message (string)                                    *
	 * Created:  06/04/2022                                          *
	 * Modified: 06/04/2022                                          *
	 * Purpose:  Find the highest value from the numbers in a array  *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 
	public static String HighestValue(long[] pArray, int pLength)
	{
		//Assert the first element as the largest
		long high = pArray[0];
		//Declare variable to help find where highest value is located
		int highLocate = 1;
		//For loop to determine which array element's value is the largest
		for(int i = 0; i < pLength; i++)
		{
			//If current value is bigger than the previously asserted highest value
			if(pArray[i] > high)
			{
				//Update highest value with the current elements value
				high = pArray[i];
				//Variable to show which episode the value belongs to
				highLocate = i + 1;
			}
		}
		String message = "peak viewership was " + high + " for episode " + highLocate;
	
		return message;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   HighestTotal                                                    *
	 * Author:   Jacob Jonas, 18439731                                           *
	 * Import:   sumDTS (long), sumAnna (long), sumBridgerton (long)             *
	 * Export:   message (string)                                                *
	 * Created:  05/04/2022                                                      *
	 * Modified: 06/04/2022                                                      *
	 * Purpose:  Find the highest total of the sum of the elements in an array   *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 
	public static String HighestTotal(long pD, long pA, long pB)
	{
		String message = "";
		//If Statement to check if DTS has most views
		if((pD > pA) && (pD > pB))
		{
			message = "Drive to Survive, season 4 had the highest viewership with " + pD + " total views";
		}
		//Check if Inventing Anna has the most views
		else if((pA > pD) && (pA > pB))
		{
			message = "Inventing Anna had the highest viewership with " + pA + " total views";
		}
		//If neither DTS nor Inventing Anna has the most views, then it must be Bridgerton
		else
		{
			message = "Bridgerton, season 2 had the highest viewership with " + pB + " total views";
		}
		//Send result back to main
		return message;
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Method:   HighestFinale                                                   *
	 * Author:   Jacob Jonas, 18439731                                           *
	 * Import:   finaleDTS (long), finaleAnna (long), finaleBridgerton (long)    *
	 * Export:   message (string)                                                *
	 * Created:  05/04/2022                                                      *
	 * Modified: 06/04/2022                                                      *
	 * Purpose:  Find the highest viewed finale                                  *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 	
	public static String HighestFinale(long pD, long pA, long pB)
	{
		String message = "";
		//If Statement to check if DTS had the most viewed finale	
		if((pD > pA) && (pD > pB))
		{
			message = "Drive to Survive, season 4 had the most viewed finale with " + pD + " views";
		}
		//Check if Inventing Anna has the most viewed finale
		else if((pA > pD) && (pA > pB))
		{
			message = "Inventing Anna had the most viewed finale with " + pA + " views";
		}
		//If neither DTS nor Inventing Anna has the most viewed finale, then it must be Bridgerton
		else
		{
			message = "Bridgerton season, 2 had the most viewed finale with " + pB + " views";
		}	
		//Send result back to main	
		return message;
	}
}	


						
					
