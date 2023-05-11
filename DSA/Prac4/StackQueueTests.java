/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     StackQueueTests                                 *
 * Author:    Jacob Jonas, 18439731                            *
 * Created:   01/09/2022                                     *
 * Modified:  01/09/2022                                      *
 * Assertion: Test the implementation of stack and qeueue using DSALinkedList  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import  java.util.*;

public class StackQueueTests
{
    public static void main(String[] args)
    {
        // Test DSALLStack class implementation
        testDSAStack();
        System.out.println("\nDSAStack tests were successful"); // Message confirming success

        // Test DSALLQueue class implementation
        testDSAQueue();
        System.out.println("\nDSAQueue tests were successful\n"); // Message confirming success

    } // End main()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    testDSAStack                                                                     *
 * Author:    Jacob Jonas, 18439731                                                            *
 * Created:   16/08/2022                                                                       *
 * Modified:  01/09/2022                                                                       *
 * Import:    none                                                                             *
 * Export:    none                                                                             *
 * Assertion: Test the immplentation of the DSAStack class to ensure it is working as intended *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static void testDSAStack()
    {
        DSALLStack testStack = new DSALLStack(); // Create new DSALLStack to run tests on

        assert true == testStack.isEmpty(); // Test isEmpty() on an empty DSALLStack

        try
        {
            testStack.top(); // Attempt to view the top value on an empty DSAStack
            assert false; // If the test gets here then it failed
        }
        catch(NullPointerException e) // Catch exception thrown by the class
        {
            assert true; // Test succeeded if an exception was thrown
        }

        for(int i = 1; i <= 10; i++) // Partially fill DSALLStack
        {
            testStack.push(i*2);
            assert testStack.getCount() == i; // Test count is incrementing as expected
        }

        assert false == testStack.isEmpty(); // Test isEmpty() on a partially filled DSLLAStack

        Object[] exp = {20,18,16,14,12,10,8,6,4,2}; // Array to hold expected values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count value

        for(int i = 0; i < exp.length; i++)
        {
            assert exp[i] == testStack.pop(); // Test the values are popping off as expected
            assert expCount[i] == testStack.getCount(); // Test count is decrementing as expected
        }

        for(int i = 1; i <= 20; i++) // Fill DSAStack
        {
            testStack.push(i * i);
        }

        assert 400 == (int)testStack.top(); // Test the top value is correct
    } // End testDSAStack()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    testDSAQueue                                                                     *
 * Author:    Jacob Jonas, 18439731                                                            *
 * Created:   16/08/2022                                                                       *
 * Modified:  17/08/2022                                                                       *
 * Import:    none                                                                             *
 * Export:    none                                                                             *
 * Assertion: Test the immplentation of the DSAQueue class to ensure it is working as intended *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static void testDSAQueue()
    {
        DSALLQueue testQueue = new DSALLQueue(); // Create new DSAQueue to run tests on

        assert true == testQueue.isEmpty(); // Test isEmpty() on an empty DSAQueue

        try
        {
            testQueue.peek(); // Attempt to view the first value in an empty DSAQueue
            assert false; // Test fails if it gets this far
        }
        catch(NullPointerException e) // Catch exception thrown by the class
        {
            assert true; // Test succeeded if an exception was thrown
        }

        for(int i = 1; i <= 10; i++) // Partially fill DSAQueue
        {
            testQueue.enqueue(i*2);
            assert testQueue.getCount() == i; // Test count is incrementing properly
        }

        assert false == testQueue.isEmpty(); // Test isEmpty() on a partially filled DSAQueue

        Object[] exp = {2,4,6,8,10,12,14,16,18,20}; // Array to hold expceted values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count

        for(int i = 0; i < exp.length; i++)
        {
            assert exp[i] == testQueue.dequeue(); // Test values were correct
            assert expCount[i] == testQueue.getCount(); // Test count decrements as expected
        }

        for(int i = 11; i <= 20; i++) // Fill the object to the end from the new index
        {
            testQueue.enqueue(i * i);
        }

        assert 121 == (int)testQueue.peek(); // Test the value at the new first index
    } // End testDSAQueue()
}