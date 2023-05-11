import java.util.*;

public class MyUtils
{
    public int max(int x, int y)
    {
        int result;
        if(x > y)
        {
            result = x;
        }
        else
        {
            result = y;
        }
        return result;
    }

    public static void printMax(MyUtils utils, int x, int y)
    {
        if (utils.max(x,y) == x)
        {
            System.out.println("x: " + x + " is max");
        }
        else
        {
            System.out.println("y: " + y + " is max");
        }
    }

}