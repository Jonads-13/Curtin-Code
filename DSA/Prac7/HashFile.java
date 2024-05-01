import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 * Title:     HashFIle
 * Author:    Jacob Jonas, 18439731
 * Created:   23/09/2022
 * Modified:  23/09/2022
 * Assertion: Read data from a file and create a hash table from it
 */

public class HashFile 
{
    public static void main(String[] args)
    {
        DSAHashTable students = new DSAHashTable(7000);

        System.out.println("Length before reading: " + students.getLength());

        students = readFile("RandomNames7000.csv", students);

        System.out.println("Length after reading: " + students.getLength());

        System.out.println("\nNumber of entries: " + students.getCount());

        DSALinkedList dupes = students.dupeList();

        System.out.println("\nList of duplicates found when inserting the data into the hash table:\n");

        for (Object object : dupes) 
        {
            System.out.println(object);    
        }

        Object name = students.get("14176744");

        System.out.println("\nThe first entry of each duplicate key is the entry in the table. Any others were discarded");

        System.out.println("\nExample: When searching for 141767644, " + name + " is the returned value\n");

        writeFile("studentfile.csv", students);
    }

    
    
    
    
    
    /*
     * Title:     readFile
     * Author:    Jacob Jonas, 18439731
     * Created:   23/09/2022
     * Modified:  23/09/2022
     * Import:    filename (String)
     * Export:    none
     * Assertion: Read a csv file
     */
    
    public static DSAHashTable readFile(String filename, DSAHashTable t)
    {
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        int lineNum = 0;
        String line;
    
        try
        {
            fileStream = new FileInputStream(filename);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
    
            line = bufRdr.readLine();
            while(line != null)
            {
                parseLine(line, t);
                lineNum++;
                line = bufRdr.readLine();
            }
            fileStream.close();
        }
        catch(IOException e)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException e2) { }
            }
            System.out.println("Error in reading file " + filename);
        }

        return t;
    } // End readFile()
    
    
    
    
    
    /*
     * Title:     parseLine
     * Author:    Jacob Jonas, 18439731
     * Created:   23/09/2022
     * Modified:  23/09/2022
     * Import:    csvRow (String)
     * Export:    none
     * Assertion: Parse a csv row
     */
    
    public static DSAHashTable parseLine(String csvRow, DSAHashTable t)
    {
        String[] splitLine;
        splitLine = csvRow.split(",");

        t.put(splitLine[0], splitLine[1]);
        System.out.println(Arrays.toString(splitLine));

        return t;
    }

    
    
    
    
    /*
     * Title:      writeFile
     * Author:     Jacob Jonas, 18439731
     * Created:    23/09/2022
     * Modified:   23/09/2022
     * Import:     fliename (String), t (DSAHashTable)
     * Export:     none
     * Assertiorn: Write data to a csv file
     */
    
    public static void writeFile(String filename, DSAHashTable t)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
        
        PrintStream originalOut = System.out; // Setup

        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        t.print(); // Function call

        System.setOut(originalOut); // Tear down
    
        try
        {
            fileStream = new FileOutputStream(filename);
            pw = new PrintWriter(fileStream);
    
            pw.write(capOut.toString());

            pw.close();
        }
        catch(IOException e)
        {
                System.out.println("Error in writing to file");
        }

    } // End writeFile()
}
