package projectsearcher;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   10/04/2023
 * @Modified  10/04/2023
 * @Assertion Parent abstract class for the text based child classes
 **/

public abstract class Text extends Criteria
{
    public Text(String pattern) // Super constructor
    {
        super(pattern);
    }

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Determines if the given line matches the pattern
     *
     * @param     line String read from a file
     *
     * @Returns   Whether the given line contains the text pattern
     **/
    
    
    @Override
    public boolean matches(String line)
    {
        return line.contains(pattern);
    } // End matches()

}
