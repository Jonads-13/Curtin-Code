import java.util.*;
public class asciicon
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        char upperChar, lowerChar;
        int upperInt, lowerInt;

        System.out.print("Please enter an uppercase character: ");
        upperChar = sc.next().charAt(0);

        upperInt = (int)upperChar;
        lowerInt = upperInt + 32;
        lowerChar = (char)lowerInt;

        System.out.println("The ASCII value of " + upperChar + " is " + upperInt);
        System.out.println("The lower case value of " + upperChar + " is " + lowerChar);
    }
}
