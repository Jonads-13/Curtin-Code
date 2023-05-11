import java.util.*;

public class CelsToFaren
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        double numOne, answer;

        System.out.print("Please enter the Celsius value: ");
        numOne = sc.nextInt();

        answer = (numOne*9)/5 + 32;

        System.out.println("The temperature in Farenheit is: " + answer);
    }
}
