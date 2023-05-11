/*
 * Title:    DSAStack Class
 * Author:   Jacob Jonas, 18439731
 * Created:  12/08/2022
 * Modified: 25/08/2022
 */

import java.util.*;

public class DSAStack
{
    // Class fields 
    private Object[] stack;
    private int count;
    private static final int DEFAULT_CAPACITY = 100;


    // Default constructor
    public DSAStack()
    {
        // Default values
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    // Constructor with parameters
    public DSAStack(int maxCap)
    {
        // stack length is equal to imported parameter
        stack = new Object[maxCap];
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
 * Assertion: Determine if the stack is empty or not
 */

    public boolean isEmpty()
    {
        boolean isEmpty = false;

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
 * Modified:  12/08/2022
 * Import:    none
 * Export:    isFull (Boolean)
 * Assertion: Determine if the stack is full or not
 */

    public boolean isFull()
    {
        boolean full = true;

        if(count == stack.length) 
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
 * Method:    push
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    value (Object)
 * Export:    none
 * Assertion: Place a new value on top of the stack
 */

    public void push(Object value)
    {
        if(isFull())
        {
            throw new IndexOutOfBoundsException("Error");
        }
        else
        {
            stack[count] = value;
            count++;
        }
    }

/*
 * Method:    pop
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  17/08/2022
 * Import:    none
 * Export:    topVal (Object)
 * Assertion: Remove the top value off of the stack
 */

    public Object pop()
    {
        Object topVal = top(); 
        stack[count-1] = null; // Removing the top value
        count--; // Decrease count 

        return topVal;
    }

/*
 * Method:    top
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  12/08/2022
 * Import:    none
 * Export:    topVal (Object)
 * Assertion: View the item of top of the stack
 */

    public Object top()
    {
        Object topVal;

        if(isEmpty())
        {
            throw new NullPointerException("Error");
        }
        else
        {
            topVal = stack[count-1]; // Getting the top value
        }
        return topVal;
    }
}
