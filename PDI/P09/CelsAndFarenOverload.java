import java.util.*;

public class CelsAndFarenOverload
{
    public static void main(String[] args)
   {
        Scanner sc = new Scanner(System.in);
       
        //delcaring variables
        double doubleCels, doubleFaren, answer;
		int intCels, intFaren;
        char tempChar, dataChar;

        //Creating menu for user
        System.out.println("Which temperature scale are you converting from?");
        System.out.println("        >(C)elsius");
        System.out.println("        >(F)arenheit");

        tempChar = sc.next().charAt(0);
        
        //Begining if statement for temperature selection
        if ((tempChar == 'C') || (tempChar == 'c'))
        {
			System.out.println("Are you entering an integer or double \n >(I)nteger \n >(D)ouble");
			dataChar = sc.next().charAt(0);

			if ((dataChar == 'I') || (dataChar == 'i'))
			{
				//Begin Celsius input section for integer input
            	System.out.println("Please enter the Celsius value: ");
				intCels = sc.nextInt();
		
				//Execute Celsius to farenheit calculation
				answer = CelsToFaren(intCels);
				
				//Display converted temperature
            	System.out.println("The temperature in Farenheit is: " + answer);
			}
			
			else if ((dataChar == 'D') || (dataChar == 'd'))
			{
				//Begin Celsius input section for double input
		        System.out.println("Please enter the Celsius value: ");
		        doubleCels = sc.nextDouble();

		        //Execute Celsius to farenheit calculation
		        answer = CelsToFaren(doubleCels);

		        //Display converted temperature
		        System.out.println("The temperature in Farenheit is: " + answer);
			}
			
			else 
        	{
            	System.out.println("Error. Invalid input"); 
        	}
        
        }

        else if ((tempChar == 'F') || (tempChar ==  'f'))
        {
			System.out.println("Are you entering an integer or double \n >(I)nteger \n >(D)ouble");
			dataChar = sc.next().charAt(0);

			if ((dataChar == 'I') || (dataChar == 'i'))
			{
				//Begin Farenheit input section for integer input
            	System.out.println("Please enter the Farenheit value: ");
				intFaren = sc.nextInt();
		
				//Execute Celsius to farenheit calculation
				answer = FarenToCels(intFaren);
				
				//Display converted temperature
            	System.out.println("The temperature in Celcius is: " + answer);
			}
			
			else if ((dataChar == 'D') || (dataChar == 'd'))
			{
				//Begin Farenheit input section for double input
		        System.out.println("Please enter the Farenheit value: ");
		        doubleFaren = sc.nextDouble();

		        //Execute farenheit to celcius calculation
		        answer = FarenToCels(doubleFaren);

		        //Display converted temperature
		        System.out.println("The temperature in Celcius is: " + answer);
			}

			else 
        	{
            	System.out.println("Error. Invalid input"); 
        	}
       
    }
}

	public static double CelsToFaren(int pCels)
	{
		double ans = (double)pCels * (9.0/5.0) + 32;

		return ans;
	}

	public static double CelsToFaren(double pCels)
	{
		double ans = pCels * (9.0/5.0) + 32;

		return ans;
	}

	public static double FarenToCels(int pFaren)
	{
		double ans = ((double)pFaren - 32) * (5.0/9.0);
	
		return ans;
	}

	public static double FarenToCels(double pFaren)
	{
		double ans = (pFaren - 32) * (5.0/9.0);
	
		return ans;
	}
}
