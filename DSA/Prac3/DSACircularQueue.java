/*
 * Title:    DSACircularQueue Class
 * Author:   Jacob Jonas, 18439731
 * Created:  17/08/2022
 * Modified: 25/08/2022
 */

import java.util.*;

public class DSACircularQueue extends DSAQueue // Inherits from DSAQueue class
{
    // New class field
    private int tail;

    // Default Constructor
    public DSACircularQueue()
    {
        super(); // Inherit from parent class
        tail = 0;
    }

    //Constructor with parameters
    public DSACircularQueue(int maxCap)
    {
        super(maxCap); // Inherit from parent class
        tail = 0;
    } 

/*
 * Method:    getTail
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  12/08/2022
 * Import:    none
 * Export:    tail (Integer)
 * Assertion: Return the tail value
 */

    public int getTail()
    {
        return tail;
    }

/*
 * Method:    isFull
 * Author:    Jacob Jonas, 18439731
 * Created:   17/08/2022
 * Modified:  25/08/2022
 * Import:    none
 * Export:    isFull (Boolean)
 * Assertion: Determine if the queue is full or not
 */

    public boolean isFull()
    {
        boolean isFull = false;

        // New values are placed at tail. If it is null then the wueue isn't full
        if(queue[tail] == null)
        {
            isFull = false;
        }
        else
        {
            isFull = true;
        }

        return isFull;
    }

/*
 * Method:    enqueue
 * Author:    Jacob Jonas, 18439731
 * Created:   17/08/2022
 * Modified:  25/08/2022
 * Import:    value (Object)
 * Export:    none
 * Assertion: Place a new value at the end of the queue
 */

    public void enqueue(Object value)
    {
        if(isFull()) // Ensure queue is not full
        {
            throw new IllegalAccessError("Queue is full");
        }
        else
        {
            queue[tail] = value; // Place value at the tail
            count++; // Increas count
            tail++; // Increase position of the tail
        }

        // If the tail values moves out of bounds
        if(tail == queue.length) 
        {
            tail = 0; // Revert it to the first element
        }
    }

/*
 * Method:    dequeue
 * Author:    Jacob Jonas, 18439731
 * Created:   17/08/2022
 * Modified:  25/08/2022
 * Import:    none
 * Export:    fstVal (Object)
 * Assertion: Remove the first value from the queue
 */

    public Object dequeue()
    {
        Object fstVal = queue[head]; // Get first value
        queue[head] = null; // remove the first value
        head++; // Moved position of the head
        count--; // Decrease count

        return fstVal;
    }

}
