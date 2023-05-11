package projectsearcher;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   10/04/2023
 * @Modified  10/04/2023
 * @Assertion Class to apply regex exclusion on lines from a file
 **/

public class ExcludeRegex extends Regex
{
    public ExcludeRegex(String pattern) // Super constructor
    {
        super(pattern);
    }

    /* Methods used to determine what type of object it is
     * Allows exlusion criteria to be applied first 
     */
    @Override public boolean isInclude() { return false; }
    @Override public boolean isExclude() { return true; }
}
