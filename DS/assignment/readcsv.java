import java.util.*;
import java.io.*;

public class readcsv
{
    public static void main(String[] args)
    {
        readFile("nobel.csv");
    }

    
    
    
    
    
    /*
     * Title:     readFile
     * Author:    Jacob Jonas, 18439731
     * Created:   16/10/2023
     * Modified:  16/10/2023
     * Import:    filename (String)
     * Export:    none
     * Assertion: Read a csv file
     */
    
    public static void readFile(String filename)
    {
        int lineNum = 0;
        String line;
    
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            bufRdr.readLine(); // ignore header row of column names
            line = bufRdr.readLine();
            while(line != null)
            {
                parseLine(line);
                lineNum++;
                line = bufRdr.readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Error in reading file " + filename);
        }
    } // End readFile()
    
    
    
    
    
    /*
     * Title:     parseLine
     * Author:    Jacob Jonas, 18439731
     * Created:   16/10/2023
     * Modified:  16/10/2023
     * Import:    csvRow (String)
     * Export:    none
     * Assertion: Parse a csv row
     */
    
    public static void parseLine(String csvRow)
    {
        String[] splitLine;
        splitLine = csvRow.split(";", -1);

        
    }
}