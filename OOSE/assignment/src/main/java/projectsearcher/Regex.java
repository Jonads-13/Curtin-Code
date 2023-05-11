package projectsearcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   10/04/2023
 * @Modified  10/04/2023
 * @Assertion Parent abstract class for the regx based child classes
 **/

public abstract class Regex extends Criteria
{
    public Regex(String pattern) // Super constructor
    {
        super(pattern);
    }

    
    
    
    
    
    /**
     * @Author    Jacob Jonas, 18439731
     * @Created   10/04/2023
     * @Modified  10/04/2023
     *
     * @Assertion Creates Pattern and Matcher objects to determine if the given line
     *            matches the regex pattern
     *
     * @param     line String read from a file
     *
     * @Returns   Whether or not the given line matches the regex pattern
     **/
    
    @Override
    public boolean matches(String line)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        return m.find();
    } // End matches()
}
