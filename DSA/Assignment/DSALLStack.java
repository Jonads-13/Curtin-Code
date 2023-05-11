/*
 * Title:     DSALLStack Class
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  25/08/2022
 * Assertion: Personal implementaion of a stack object using a DSALinkedLkst
 * Previously submitted for Practical 04 in COMP1002, Sem 2, 2022
 */

public class DSALLStack
{
    // Class fields 
    private DSALinkedList stack;
    private int count;


    // Default constructor
    public DSALLStack()
    {
        // Default values
        stack = new DSALinkedList();
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
        stack.insertLast(value);
        count++;
    }

/*
 * Method:    pop
 * Author:    Jacob Jonas, 18439731
 * Created:   12/08/2022
 * Modified:  29/08/2022
 * Import:    none
 * Export:    topVal (Object)
 * Assertion: Remove the top value off of the stack
 */

    public Object pop()
    {
        Object topVal = stack.removeLast(); 
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
            topVal = stack.peekLast();
        }
        return topVal;
    }
}
