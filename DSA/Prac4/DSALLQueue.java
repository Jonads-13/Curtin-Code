/*
 * Title:    DSAQueue Class
 * Author:   Jacob Jonas, 18439731
 * Created:  12/08/2022
 * Modified: 25/08/2022
 */

import java.util.*;

public class DSALLQueue
{
    // Class fields
    private DSALinkedList queue;
    private int count;

    // Default constructor
    public DSALLQueue()
    {
        // Default values
        queue = new DSALinkedList();
        count = 0;
    }

/*
 * Method:    getCount
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  12/08/2022
 * Import:    none
 * Export:    count (Integer)
 * Assertion: Return the count value
 */

    public int getCount()
    {
        return count;
    }

/*
 * Method:    isEmpty
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  12/08/2022
 * Import:    none
 * Export:    isEmpty (Boolean)
 * Assertion: Determine if the queue is empty or not
 */

    public boolean isEmpty()
    {
        boolean isEmpty = true;

        if(count == 0)
        {
            isEmpty = true;
        }
        else
        {
            isEmpty = false;
        }
        return isEmpty;
    }


/*
 * Method:    enqueue
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    value (Object)
 * Export:    none
 * Assertion: Place a new value at the end of the queue
 */

    public void enqueue(Object value)
    {
        queue.insertLast(value); // place the new value at the end of the queue
        count++; // Increase count
    }

/*
 * Method:    dequeue
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  29/08/2022
 * Import:    none
 * Export:    fstVal (Object)
 * Assertion: Remove the first value from the queue
 */

    public Object dequeue()
    {
        Object value = queue.removeFirst(); // Get first value
        count--;
        return value;
    }

/*
 * Method:    peek
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  29/08/2022
 * Import:    none
 * Export:    fstVal (Object)
 * Assertion: View the item at the front of the queue
 */

    public Object peek()
    {
        Object value;

        if(isEmpty()) // Check if the queue is empty
        {
            throw new NullPointerException("Error");
        }
        else
        {
            value = queue.peekFirst(); // Get the first value
        }
        return value;
    }
}