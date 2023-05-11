package projectsearcher;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   30/03/2023
 * @Modified  11/04/2023
 * @Assertion Container node for composite pattern implementaion
 **/

public class Directory implements Entry
{
    // Warning suppressed under advice from David given in the form of a piazza reply.
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Directory.class.getName());

    // Class fields
    private File file;
    private int numIncludedLines;
    private List<Entry> children;

    public Directory(File file) // Constructor
    {
        this.file = file;
        numIncludedLines = 0;
        children = new LinkedList<>();
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
    } // End getName()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Accessor      
     *
     * @param     void
     *
     * @Returns   File Object stored as identity
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
     * @Returns   The sum total of its children's numIncludedLines
     **/

    @Override
    public int getNumIncludedLines()
    {
        numIncludedLines = 0;
        for(Entry child : children) 
        {
            numIncludedLines += child.getNumIncludedLines();
        }

        return numIncludedLines;
    } // End getNumIncludedLines()

    
    
    
    
    

    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  11/04/2023
     *
     * @Assertion Recursively display all children's numIncludedLies
     *
     * @param     indents Used to visulaise how far into the tree the current Directory is
     *
     * @Returns   void
     **/
    
    

    @Override
    public void displayCount(int indents)
    {
        String indentation = "  ".repeat(indents);
        System.out.println(indentation + this.getName() + ": " + getNumIncludedLines());

        for(Entry child : children) 
        {
            // Only include relevant children
            if(child.getNumIncludedLines() > 0)
            {
                child.displayCount(indents + 1);
            }
        }
    } // End displayCount()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Recursively display all children's included lines
     *
     * @param     indents Used to visualise how far into the tree the current Directory is
     *
     * @Returns   void
     **/

    @Override
    public void displayLines(int indents)
    {
        String indentation = "  ".repeat(indents);
        System.out.println(indentation + this.getName());

        for (Entry child : children) 
        {
            // Only include relevant children
            if(child.getNumIncludedLines() > 0)
            {
                child.displayLines(indents + 1);
            }    
        }
    } // End displayLines()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Recursively applies given criteria to each child
     *
     * @param     criteria List of Criteria objects
     *
     * @Returns   void
     **/

    @Override
    public void applyCriteria(List<Criteria> criteria)
    {
        for(Entry child : children) 
        {
            child.applyCriteria(criteria);    
        }
    } // End applyCriteria()

    






    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Mutator
     *
     * @param     child Entry object to add as a child
     *
     * @Returns   void
     **/

     @Override
     public void addChild(Entry child)
     {
         children.add(child);
     } // End addChild()
 
     
     
     
     
     
     
     /**
      * @Author    Jacob Jonas, 18439731
      * @Created   11/04/2023
      * @Modified  11/04/2023
      *
      * @Assertion Determines which output method to call
      *
      * @param     format String telling which output format to use 
      *
      * @Returns   void
      **/
     
     @Override
     public void display(String format)
     {
         if(format.equals("count"))
         {
             logger.info("Display option is Count");
             displayCount(0);
         }
         else if(format.equals("show"))
         {
             logger.info("Display option is Show");
             displayLines(0);
         }
     }
    
    




    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Should never be called on a Directory Object
     *
     * @param     line Line from file to check against all criteria
     * @param     criteria List of Criteria objects to apply to each line
     *
     * @Returns   void  
     **/

    @Override
    public boolean includeLine(String line, List<Criteria> criteria)
    {
        logger.severe(() -> "Impossible to get here");
        throw new UnsupportedOperationException("Directories don't have any lines to check");
    } // End includeLine()
}
