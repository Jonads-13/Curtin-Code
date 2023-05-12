import java.util.*;

public class TestingMethods
{

// Java production code for ISE worksheet 6B.
    /**
     * Calculates a grade, given a mark. For marks less than 50, the grade is 
     * "F". For marks from 50 to 59, the grade is "5". For 60 to 69, the grade
     * is "6", and so on up to "10". If the mark is invalid, calcGrade will 
     * export the empty string "".
     */
	

    public static String calcGrade(int mark)
    {
        String grade = "";
        if(mark < 50)
        {
            grade = "F";
        }
        else if(mark == 100)
        {
            grade = "10";
        }
        else
        {
            grade = "" + ((mark + 1) / 10);
        }
        return grade;
    }

    /**
     * Calculates the volume of a room, but only if the imported width, length 
     * and height are valid. To be valid, width must be at least 2 (metres), 
     * length 2.5, and height 3. For invalid imports, this submodule will 
     * return 0.
     */

    public static double roomVolume(double width, double length, double height)
    {
        double volume = 0.0;
        if(width >= 2.0 || length >= 2.5 || height >= 3.0)
        {
            volume = width * length * height;
        }
        return volume;
    }

    /**
     * Determines a rating for ultraviolet radiation risk, based on a real-
     * valued UV index. Ratings below zero are invalid, in which case the 
     * submodule returns "-". Otherwise, if the index is below 3, the rating 
     * is "low", then up to 6 for "moderate", up to 8 for "high", and up to 11 
     * for "very high". Any rating over 11 is "extreme".
     */

    public static String uvRating(double index)
    {
        String rating;
        if(index < 0.0)
        {
            rating = "-";
        }
        else if(index <= 3.0)
        {
            rating = "low";
        }
        else if(index <= 6.0)
        {
            rating = "medium";
        }
        else if(index <= 8.0)
        {
            rating = "high";
        }
        else if(index <= 11.0)
        {
            rating = "very high";
        }
        else
        {
            rating = "extreme";
        }
        return rating;
    }

    /** 
     * Checks whether or not ch is an upper/lowercase letter. If checkUpper is
     * true, the method checks whether ch is uppercase, and return 
     * true/false accordingly. If checkUpper is false, the method instead 
     * checks whether ch is lowercase. 
     * 
     * Note: from a modularity point of view (see lecture 7), the design of 
     * this method may be questionable...
     */

    public static boolean charCase(boolean checkUpper, char ch)
    {
        boolean upperCase = 'A' <= ch && ch <= 'z';
        boolean lowerCase = 'a' <= ch && ch <= 'z';
        return (checkUpper && upperCase) || (!checkUpper && lowerCase);
    }
    
    /**
     * Determines whether one string occurs inside the other. If it does, the 
     * method returns whichever string is shorter. If not, it returns the 
     * the empty string "". Note that the empty string is, by definition, 
     * contained inside every string (including itself).
     *
     * For instance, if str1 is "conscience" and str2 is "science", then this 
     * method returns "science". If both imported strings are "xyz", then 
     * the method returns "xyz".
     */

    public static String substr(String str1, String str2)
    {
        String result = str1;
        if(str1.contains(str2))
        {
            result = str2;
        }
        else if(str2.contains(str1))
        {
            result = str1;
        }
        return result;
    }
}

	
