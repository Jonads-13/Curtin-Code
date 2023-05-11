/*
 * Title:     IteratorTest
 * Author:    Jacob Jonas, 18439731
 * Created:   30/08/2022
 * Modified:  30/08/2022
 * Assertion: Test the DSALinkedListIterator
 */

import java.util.*;

public class IteratorTest 
{
    public static void main(String[] args)
    {
        // New object to perform tests on
        DSALinkedList test = new DSALinkedList();

        for(int i = 0; i < 10; i++) // Fill list with 10 values
        {
            test.insertLast(i);
        }

        iterateOverList(test); // Send to iteration method
    }

    
    
    
    
    
    /*
     * Title:     iterateOverList
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  01/09/2022
     * Import:    theList (DSALinkedList)
     * Export:    none
     * Assertion: iterate over the imported list
     */

    public static void iterateOverList(DSALinkedList theList) 
    {
        int value, i = 0; // Used for assertion array
        int [] exp = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // expected values in the list

        Iterator iter = theList.iterator(); // Create new iterator object

        System.out.println("\nTesting Iterator:");
        while(iter.hasNext())
        {
            value = (int)iter.next(); 
            assert exp[i] == value; // Test that the value is as expected
            i++; // Increment to next value in exp array

        }
        System.out.println("PASSED"); // First iterator test successful

        i = 0; // Reinitialise to start test over

        // Test the for-each method only usable by an iterator
        System.out.println("\nTesting for-each method:");
        for (Object o : theList)
        {   
            value = (int)o;
            assert exp[i] == value;
            i++;
        } 
        System.out.println("PASSED\n"); // For-each test successful       
    } // End iterateOverList()
}
