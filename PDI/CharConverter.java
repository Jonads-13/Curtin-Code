import java.util.*;
public class CharConverter
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        char upperChar, lowerChar, startChar;
        int upperInt, lowerInt;

        System.out.print("Please enter an upper or lower case letter: ");
        startChar = sc.next().charAt(0);

	if (((int)startChar >= 65) && ((int)startChar <=90))
        {    
	    upperChar = startChar;
            upperInt = (int)upperChar;
            lowerInt = upperInt + 32;
            lowerChar = (char)lowerInt;

            System.out.println("The ASCII value of " + upperChar + " is " + upperInt);
            System.out.println("The lower case value of " + upperChar + " is " + lowerChar);
        }

	else if (((int)startChar >= 97) && ((int)startChar <=122))
	{
	    lowerChar = startChar;
	    lowerInt = (int)lowerChar;
	    upperInt = lowerInt - 32;
	    upperChar = (char)upperInt;

	    System.out.println("The ASCII value of " + lowerChar + " is " + lowerInt);
	    System.out.println("The upper case value of " + lowerChar + " is " + upperChar);

	}

	else
	{
	    System.out.println("Error. Invalid input");
	}

    }

}
