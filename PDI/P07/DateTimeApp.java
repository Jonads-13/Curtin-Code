import java.util.*;
public class DateTimeApp
{
    public static void main(String[] args)
    {
        DateTime dateTimeOne = new DateTime(23, 12, 1996, 23, 12, 9);
        System.out.println("Date 1 is " + dateTimeOne.toString());

        DateTime dateTimeTwo = new DateTime(5, 5, 1968, 7, 7, 45);
        System.out.println("Date 2 is " + dateTimeTwo.toString());

        DateTime dateTimeThree = new DateTime(dateTimeOne);
        System.out.println("Date 3 is " + dateTimeThree.toString());
    
        DateTime dateTimeFour = new DateTime();
        System.out.println("Date 4 is " + dateTimeFour.toString());
    }
}

