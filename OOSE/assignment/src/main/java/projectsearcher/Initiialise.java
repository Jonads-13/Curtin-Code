package projectsearcher;

import java.io.*;
import java.util.logging.Logger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   30/03/2023
 * @Modified  11/04/2023
 * @Assert    Contains functions for creating the composite pattern tree structure
 **/

public class Initiialise 
{
    // Warning suppressed under advice from David given in the form of a piazza reply.
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Initiialise.class.getName());

    public Initiialise() {}

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   11/04/2023
     * @Modified  11/04/2023
     *
     * @Assertion Create Entry Object to begin recursion on
     *
     * @param     directory String name of directory to begin recursive search
     *
     * @Returns   An Entry object
     **/
    
    public Entry directorySearch(String directory)
    {
        if(directory == null || directory.equals(""))
        {
            logger.severe("Parameter: directory, was null or blank");
            throw new IllegalArgumentException(String.format("directory: %s, can't be null or blank", directory));
        }
        File temp = new File(directory);
        logger.info("Beginning recursion");
        return directorySearchRec(new Directory(temp));

    }

    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   30/03/2023
     * @Modified  11/04/2023
     * 
     * @Assertion Recursively search a directory tree
     * 
     * @param     current the current Entry object in the recursion
     * 
     * @Returns   The root of the directory tree
     **/

    public Entry directorySearchRec(Entry current)
    {
        logger.info(() -> "Current: " + current.getName());
        if(current.getFile().isDirectory())
        {
            // Get all current directories children
            File [] fileList = current.getFile().listFiles();
            
            // Add each child to the current directory's child list
            for(File file : fileList) 
            {
                Entry child = fileOrDirectory(file);
                logger.info(() -> "Current child: " + child.getName());
                current.addChild(child);
                directorySearchRec(child); // Recurse
            }
        }

        // Due to recusrion it will return the root directory
        return current; 
    } // End directorySearch()

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   06/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Determines if given file is a directory or not
     *
     * @param     file File object to be tested
     *
     * @Returns   A new Entry Object of FileEntry or Directory depending on outcome
     **/

    public Entry fileOrDirectory(File file)
    {
        if(file.isDirectory())
        {
            return new Directory(file);
        }
        else
        {
            return new FileEntry(file);
        }
    } // End fileOrDirectory()
}