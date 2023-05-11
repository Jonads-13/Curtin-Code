/*
 * Title:     DSAHeapEntry
 * Author:    Jacob Jonas, 18439731
 * Created:   27/09/2022
 * Modified:  27/09/2022
 * Assertion: Personal implementation of a heap entry for use in DSAHeap
 */

public class DSAHeapEntry 
{
    private int priority;
    private Object value;

    public DSAHeapEntry()
    {
        priority = 0;
        value = null;
    }

    public DSAHeapEntry(int pPriority, Object pValue)
    {
        priority = pPriority;
        value = pValue;
    }

    
    
    
    
    
    /*
     * Title:     getPriority
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    priority (int)
     * Assertion: return the priority value
     */
    
    public int getPriority()
    {
        return priority;
    }

    
    
    
    
    
    /*
     * Title:     setPriority
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    pPriority (int)
     * Export:    none
     * Assertion: Update the value of priority
     */
    
    public void setPriority(int pPriority)
    {
        priority = pPriority;
    }

    
    
    
    
    
    /*
     * Title:     getValue
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    value (Object)
     * Assertion: return the value
     */
    
    public Object getValue() 
    {
        return value;    
    }

    
    
    
    
    
    /*
     * Title:     setValue
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    pValue (Object)
     * Export:    none
     * Assertion: update the value
     */
    
    public void setValue(Object pValue) 
    {
        value = pValue;    
    }

    
    
    
    
    
    /*
     * Title:     toString
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    (String)
     * Assertion: retunr a string representation of the object
     */
    
    public String toString() 
    {
        return "" + priority + "," + value;
    }
}
