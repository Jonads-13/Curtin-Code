/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     TowersofHanoi                                                  *
 * Author:    Jacob Jonas, 18439731                                          *
 * Created:   06/08/2022                                                     *
 * Modified:  16/08/2022                                                     *
 * Assertion: To implement a function to solve the Towers of Hanoi problem   *  
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;

public class TowersofHanoi
{
	public static void main(String[] args)
	{
		int n; // Variable to hold user choice for number of disks
		Scanner sc = new Scanner(System.in); // Scanner object foir user input

		// Prompt user for how many disks they would like
		System.out.println("Enter how many disks will be used");
		n = sc.nextInt(); // Get user unput

		towers(n, 1, 3, 1); // Send parameters to method

		sc.close(); // Close scanner object
	} // End main()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    towers                                                       *
 * Author:    Jacob Jonas, 18439731                                        *
 * Created:   06/08/2022                                                   *
 * Modified:  16/08/2022                                                   *
 * Import:    n (Integer), src (Integer), dest (Integer), rLvl (Integer)   *
 * Export:    none                                                         *
 * Assertion: Perform the Towers of Hanoi problem                          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void towers(int n, int src, int dest, int rLvl)
	{
		if( n == 1)
		{
			moveDisk(n, src, dest, rLvl); // Move a disk
		}
		else
		{
			int temp =  6 - src - dest; // temp is the non target peg
			
			towers(n-1, src, temp, rLvl+1); // Recursive call increasing recursion level each time it is called
			
			moveDisk(n, src, dest, rLvl); // Move a disk
			towers(n-1, temp, dest, rLvl+1); // Recursive call increasing recursion level each time it is called
		}
	} // End towers()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    moveDisk                                                     *
 * Author:    Jacob Jonas, 18439731                                        *
 * Created:   06/08/2022                                                   *
 * Modified:  16/08/2022                                                   *
 * Import:    n (Integer), src (Integer), dest (Integer), rLvl (Integer)   *
 * Export:    none                                                         *
 * Assertion: Notify the user once a disk has been moved                   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void moveDisk(int n, int src, int dest, int lvl)
	{
		String blank;
		blank = "	".repeat(lvl); // To visualise recursion level using tabs
		// Tell user what disk is being moved to and from peg what peg
		System.out.println(blank + "Recursion Level: " + lvl + "\n" + blank + "Moving disk " + n + " from peg " + src + " to peg " + dest + "\n");
	} // End moveDisk()
}
