package projectsearcher;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   11/04/2023
 * @Modified  11/04/2023
 * @Assert    Abstract class for Strategy/Template pattern
 **/

public abstract class Criteria 
{
    // Class field
    protected String pattern;

    public Criteria(String pattern) // Constructor
    {
        this.pattern = pattern;
    }
    
    // Abstract methods for child classes to define
    public abstract boolean matches(String line);
    public abstract boolean isInclude();
    public abstract boolean isExclude();
}
