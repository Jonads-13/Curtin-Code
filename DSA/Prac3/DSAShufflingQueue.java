/*
 * Title:    DSAShufflingQueue Class
 * Author:   Jacob Jonas, 18439731
 * Created:  17/08/2022
 * Modified: 25/08/2022
 */

import java.util.*;

public class DSAShufflingQueue extends DSAQueue // Inherits from DSAQueue class
{
    // Default constructor
    public DSAShufflingQueue()
    {
        super(); // Inherit from parent class
    }

    // Constructor with parameters
    public DSAShufflingQueue(int maxCap)
    {
        super(maxCap); // Inherit from parent class
    }

/*
 * Method:    dequeue
 * Author:    Jacob Jonas, 18439731
 * Created:   17/08/2022
 * Modified:  17/08/2022
 * Import:    none
 * Export:    fstVal (Object)
 * Assertion: Remove the first value from the queue
 */

    public Object dequeue()
    {
        Object fstVal = queue[0]; // Get the first value
        count--; // Decrease count

        if(!isEmpty()) // If there are still values in the array
        {
            for(int i = 1; i < queue.length; i++)
            {
                queue[i-1] = queue[i]; // Shuffle values to the start
            }
        }
        return fstVal;
    }
}
