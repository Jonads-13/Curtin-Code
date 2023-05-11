package projectsearcher;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   30/03/2023
 * @Modified  11/04/2023
 * @Assertion Leaf node for composite pattern implementaion
 **/

public class FileEntry implements Entry
{
    // Warning suppressed under advice from David given in the form of a piazza reply.
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(FileEntry.class.getName());
    
    //Class fields
    private File file;
    private int numIncludedLines;
    private List<String> includedLines;
    
    public FileEntry(File file) // Constructor
    {
        this.file = file;
        numIncludedLines = 0;
    }

    
    

    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Accessor
     *
     * @param     void
     *
     * @Returns  String name of the file 
     **/

    @Override
    public String getName()
    {
        return file.getName();
    } // End getFile()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Accessor      
     *
     * @param     void
     *
     * @Returns   A File Object
     **/

    @Override
    public File getFile()
    {
        return file;
    } // End getFile()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Accessor
     *
     * @param     void
     *
     * @Returns   numIncludedLines
     **/

    @Override
    public int getNumIncludedLines()
    {        
        return numIncludedLines;
    } // End getnumIncludedLines()







    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Print numIncludedLinea to the terminal with appropriate formatting
     *
     * @param     indents Used to visulaise how far into the directory tree it is
     *
     * @Returns   void
     **/
    
    @Override
    public void displayCount(int indents)
    {
        String indentation = "  ".repeat(indents);
        System.out.println(indentation + file.getName() + ": " + numIncludedLines + " lines");
    } // End displayCount()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Print each included line to the terminal with appropriate formatting
     *
     * @param     indents Used to visualise how far into the directory tree it is
     *
     * @Returns   void
     **/

    @Override
    public void displayLines(int indents)
    {
        String indentation = "  ".repeat(indents);
        System.out.println(indentation + file.getName());

        for(String line : includedLines) 
        {
            System.out.println(indentation + line);    
        }
    } // End displayLines()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  11/04/2023
     *
     * @Assertion Reads the file and applies the given criteria to each line
     *
     * @param     criteria List of Criteria objects to match each line against
     *
     * @Returns   void
     **/

    @Override
    public void applyCriteria(List<Criteria> criteria)
    {
        String line;
        int lineNum = 0;

        // Reset the list and number of included lines each time the method is called
        includedLines = new LinkedList<>(); 
        numIncludedLines = 0;

        logger.info(() -> "Reading file: " + this.getName());

        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            line = br.readLine(); // Read line from the file
            while(line != null)
            {
                if(includeLine(line, criteria)) // Determine if line should be included
                {
                    // Add line with formatting
                    includedLines.add("  " + lineNum + " " + line);
                    numIncludedLines++; // increment total
                }

                line = br.readLine(); // Read next line
                lineNum++;
            }
        }
        catch(IOException e)
        {
            logger.severe(() -> "File reading failed: " + e.getMessage());
            System.out.println("File reading failed: " + e.getMessage());
        }
    } // End applyCriteria()






  
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Should never be called on a FileEntry Object
     *
     * @param     child Entry object to add as a child
     *
     * @Returns   void
     **/

     @Override
     public void addChild(Entry child)
     {
         logger.severe(() -> "Files can't have children: " + child.getName());
         throw new UnsupportedOperationException("Can't add a child to a file");
     } // End addChild()

    
    


     


    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   11/04/2023
     * @Modified  11/04/2023
     *
     * @Assertion Never called on a base file
     *
     * @param     format String telling which output format to use 
     *
     * @Returns   void
     **/
    
     @Override
     public void display(String format)
     {
         logger.severe(() -> "Impossible to get here");
         throw new UnsupportedOperationException("Can't use generic display for a base file");
     }

    
    



    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Checks line against exclusion criteria first, then inclusion criteria
     *
     * @param     line String read from the file to be pattern matched
     * @param     criteriaList List of Criteria objects to apply to the line
     *
     * @Returns   Whether or not to include the line
     **/

    @Override
    public boolean includeLine(String line, List<Criteria> criteriaList)
    {
        for (Criteria criteria : criteriaList) 
        {
            // Only apply exclusion criteria
            if(criteria.isExclude())
            {
                if(criteria.matches(line))
                {
                    return false;
                }
            }
        }        
        
        for (Criteria criteria : criteriaList) 
        {
            // Only apply inclusion criteria
            if(criteria.isInclude())
            {
                if(criteria.matches(line))
                {
                    return true;
                }
            }
        }        
        return false;
    }  // End includeLine()
}
