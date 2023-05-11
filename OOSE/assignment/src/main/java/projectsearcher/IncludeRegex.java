package projectsearcher;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   10/04/2023
 * @Modified  10/04/2023
 * @Assertion Class to apply regex inclusion on lines from a file
 **/

public class IncludeRegex extends Regex
{
    public IncludeRegex(String pattern) // Super constructor
    {
        super(pattern);
    } 

    /* Methods used to determine what type of object it is
     * Allows exlusion criteria to be applied first 
     */
    @Override public boolean isInclude() { return true; }
    @Override public boolean isExclude() { return false; }
}
