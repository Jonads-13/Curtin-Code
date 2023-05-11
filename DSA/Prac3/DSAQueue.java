/*
 * Title:    DSAQueue Class
 * Author:   Jacob Jonas, 18439731
 * Created:  12/08/2022
 * Modified: 25/08/2022
 */

import java.util.*;

public class DSAQueue
{
    // Class fields
    protected Object[] queue; //
    protected int count; //         protected so child classes can use them
    protected int head; // 
    private static final int DEFAULT_CAPACITY = 100;

    // Default constructor
    public DSAQueue()
    {
        // Default values
        queue = new Object[DEFAULT_CAPACITY];
        count = 0;
        head = 0;
    }

    // Constructor with parameters
    public DSAQueue(int maxCap)
    {
        // queue length is equal to imported parameter
        queue = new Object[maxCap];
        count = 0;
        head = 0;
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
 * Method:    getHead
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  12/08/2022
 * Import:    none
 * Export:    head (Integer)
 * Assertion: Return the head value
 */

    public int getHead()
    {
        return head;
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
 * Method:    isFull
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  25/08/2022
 * Import:    none
 * Export:    isFull (Boolean)
 * Assertion: Determine if the queue is full or not
 */

    public boolean isFull()
    {
        boolean full = false;

        if(count == queue.length)
        {
            full = true;
        }
        else
        {
            full = false;
        }
        return full;
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
        if(isFull())
        {
            throw new IndexOutOfBoundsException("Error");
        }
        else
        {
            // place the new value at the end of the queue
            queue[head + count] = value;
            count++; // Increase count
        }
    }

/*
 * Method:    dequeue
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    none
 * Export:    fstVal (Object)
 * Assertion: Remove the first value from the queue
 */

    public Object dequeue()
    {
        Object fstVal = peek(); // Get first value
        queue[head] = null; // Remove the first value
        count--; // Decrease count
        head++; // Move the head to the new first value
        return fstVal;
        
    }

/*
 * Method:    peek
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  12/08/2022
 * Import:    none
 * Export:    topVal (Object)
 * Assertion: View the item at the front of the queue
 */

    public Object peek()
    {
        Object fstVal;

        if(isEmpty()) // Check if the queue is empty
        {
            throw new NullPointerException("Error");
        }
        else
        {
            fstVal = queue[head]; // Get the first value
        }
        return fstVal;
    }
}