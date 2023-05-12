// Java production code for ISE worksheet 8.

import java.io.*;
import java.util.*;

public class Utils
{
    public static void main(String[] args)
    {
	readChar("string");
    }

    public static void printCoordinates(double x, double y, double z)
    {
        System.out.printf("(%.2f,%.2f,%.2f)\n", x, y, z);
    }

    public static char readChar(String validChars)
    {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while(line.length() != 1 || !validChars.contains(line))
        {
            line = sc.nextLine();
        }
        return line.charAt(0);
    }

    public static void guessingGame(int number)
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter an integer: ");
        int guess = sc.nextInt();
        
        while(guess != number)
        {
            if(guess > number)
            {
                System.out.println("Too high.");
            }
            else
            {
                System.out.println("Too low.");
            }
            System.out.print("Enter an integer: ");
            guess = sc.nextInt();
        }
        System.out.println("Correct!");
    }

    public static double sumFile(String filename)
    {
        double sum = 0.0;
        try
        {
            Scanner sc = new Scanner(new File(filename)); // <-- Throws IOException
            while(sc.hasNextDouble())
            {
                sum += sc.nextDouble();
            }
        }
        catch(IOException e)
        {
            sum = -1.0;
        }
        return sum;
    }

    public static boolean saveData(String filename, String name, int health, int score)
    {
        boolean success = true;
        try(PrintWriter writer = new PrintWriter(filename)) // <-- Throws IOException
        {
            writer.println("name: " + name);
            writer.println("health: " + health);
            writer.println("score: " + score);
            if(health <= 0.0)
            {
                writer.println("status: dead");
            }
            else
            {
                if(score >= 100)
                {
                    writer.println("status: won");
                }
                else
                {
                    writer.println("status: alive");
                }
            }
        }
        catch(IOException e)
        {
            success = false;
        }
        return success;
    }
}


