/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     Prac3TestHarness                                 *
 * Author:    Jacob Jonas, 18439731                            *
 * Created:   16/08/2022                                       *
 * Modified:  25/08/2022                                       *
 * Assertion: Test the implementation of Various DSA classes   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import  java.util.*;

public class Prac3TestHarness 
{
    public static void main(String[] args)
    {
        // Test DSAStack class implementation
        testDSAStack();
        System.out.println("\nDSAStack tests were successful"); // Message confirming success

        // Test DSAQueue class implementation
        testDSAQueue();
        System.out.println("\nDSAQueue tests were successful"); // Message confirming success

        // Test DSAShufflingQueue class implementation
        testDSAShufflingQueue();
        System.out.println("\nDSAShufflingQueue tests were successful"); // Message confirming success

        // Test DSACircularQueue class implementation
        testDSACircularQueue();
        System.out.println("\nDSACircularQueue tests were successful"); // Message confirming success

    } // End main()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    testDSAStack                                                                     *
 * Author:    Jacob Jonas, 18439731                                                            *
 * Created:   16/08/2022                                                                       *
 * Modified:  17/08/2022                                                                       *
 * Import:    none                                                                             *
 * Export:    none                                                                             *
 * Assertion: Test the immplentation of the DSAStack class to ensure it is working as intended *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static void testDSAStack()
    {
        DSAStack testStack = new DSAStack(20); // Create new DSAStack to run tests on

        assert true == testStack.isEmpty(); // Test isEmpty() on an empty DSAStack
        assert false == testStack.isFull(); // Test isFull() on an empty DSAStack

        try
        {
            testStack.top(); // Attempt to view the top value on an empty DSAStack
            assert false; // If the test gets here then it failed
        }
        catch(NullPointerException e) // Catch exception thrown by the class
        {
            assert true; // Test succeeded if an exception was thrown
        }

        for(int i = 1; i <= 10; i++) // Partially fill DSAStack
        {
            testStack.push(i*2);
            assert testStack.getCount() == i; // Test count is incrementing as expected
        }

        assert false == testStack.isEmpty(); // Test isEmpty() on a partially filled DSAStack
        assert false == testStack.isFull(); // Test isFull() on a partially filled DSAStack

        Object[] exp = {20,18,16,14,12,10,8,6,4,2}; // Array to hold expected values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count value

        for(int i = 0; i < exp.length; i++)
        {
            assert exp[i] == testStack.pop(); // Test the values are popping off as expected
            assert expCount[i] == testStack.getCount(); // Test count is decrementing as expected
        }

        for(int i = 1; i <= 20; i++) // Completely fill DSAStack
        {
            testStack.push(i * i);
        }
        
        assert false == testStack.isEmpty(); // Test isEmpty() on a full DSAStack
        assert true == testStack.isFull(); // Test isFull() on a full DSAStack

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
        DSAQueue testQueue = new DSAQueue(20); // Create new DSAQueue to run tests on

        assert true == testQueue.isEmpty(); // Test isEmpty() on an empty DSAQueue
        assert false == testQueue.isFull(); // Test isFull() on an empty DSAQueue

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
        assert false == testQueue.isFull(); // Test isFull() in a partially filled DSAQueue

        Object[] exp = {2,4,6,8,10,12,14,16,18,20}; // Array to hold expceted values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count
        int [] expIdx = {0,1,2,3,4,5,6,7,8,9}; // Array to hold the expected index of the first value

        for(int i = 0; i < exp.length; i++)
        {
            assert expIdx[i] == testQueue.getHead(); // Test the first index moves properly
            assert exp[i] == testQueue.dequeue(); // Test values were correct
            assert expCount[i] == testQueue.getCount(); // Test count decrements as expected
        }

        for(int i = 11; i <= 20; i++) // Fill the object to the end from the new index
        {
            testQueue.enqueue(i * i);
        }

        assert false == testQueue.isEmpty(); // Test isEmpty() on a DSAQueue filled to the end but from the middle
        assert false == testQueue.isFull(); // Test isFull() on a DSAQueue filled to the end but from the middle

        assert 121 == (int)testQueue.peek(); // Test the value at the new first index

        DSAQueue full = new DSAQueue(); // Create a new DSAQueue to be filled completely

        for(int i = 0; i < 100; i++) // Completely fill DSAQueue
        {
            full.enqueue(i+i);
        }

        assert false == full.isEmpty(); // Test isEmpty() on a full DSAQueue
        assert true == full.isFull(); // Test isFUll() on a full DSAQueue

        assert 0 == (int)full.peek(); // Test first value 
    } // End testDSAQueue()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    testDSAShufflingQueue                                                                      *
 * Author:    Jacob Jonas, 18439731                                                                      *
 * Created:   16/08/2022                                                                                 *
 * Modified:  17/08/2022                                                                                 *
 * Import:    none                                                                                       *
 * Export:    none                                                                                       *
 * Assertion: Test the immplentation of the DSAShufflingQueue class to ensure it is working as intended  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static void testDSAShufflingQueue()
    {
        DSAShufflingQueue testQueue = new DSAShufflingQueue(20); // Create new DSAShufflingQueue to test on

        assert true == testQueue.isEmpty(); // Test isEmpty() on an empty DSAShufflingQueue
        assert false == testQueue.isFull(); // Test isFull() on an empty DSAShufflingQueue

        try
        {
            testQueue.peek(); // Attempt to view the first value in an empty DSAShufflingQueue
            assert false; // Test faisl if it gets this far
        }
        catch(NullPointerException e) // Catch exception thrown by the class
        {
            assert true; // Test suceeded if an exception was thrown
        }
        for(int i = 1; i <= 10; i++) // Partially fill DSAShufllingQueue
        {
            testQueue.enqueue(i*2);
            assert testQueue.getCount() == i; // Test count is incrementing as expected
        }
        
        assert false == testQueue.isEmpty(); // Test isEmpty() on a partially filled DSAShufflingQueue
        assert false == testQueue.isFull(); // Test isFull() on a partially filled DSAShufflingQueue
        
        Object[] exp = {2,4,6,8,10,12,14,16,18,20}; // Array to hold the expected values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count

        for(int i = 0; i < exp.length; i++)
        {
            assert exp[i] == testQueue.dequeue(); // Test values are as expected
            assert expCount[i] == testQueue.getCount(); // Test count is decrementing as expected
            assert 0 == testQueue.getHead(); // Test that the first index stays the same
        }

        for(int i = 1; i <= 20; i++) // Completely fill DSAShufflingQueue
        {
            testQueue.enqueue(i * i);
        }
        
        assert false == testQueue.isEmpty(); // Test isEmpty on a full DSAShufflingQueue
        assert true == testQueue.isFull(); // Test isFull() on a full DSAShufflingQueue
       
        assert 1 == (int)testQueue.peek(); // Test the first value is as expected
    } // End testDSAShufflingQueue()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    testDSACircularQueue                                                                       *
 * Author:    Jacob Jonas, 18439731                                                                      *
 * Created:   16/08/2022                                                                                 *
 * Modified:  17/08/2022                                                                                 *
 * Import:    none                                                                                       *
 * Export:    none                                                                                       *
 * Assertion: Test the immplentation of the DSACircularQueue class to ensure it is working as intended   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static void testDSACircularQueue()
    {
        DSACircularQueue testQueue = new DSACircularQueue(20);

        assert true == testQueue.isEmpty();
        assert false == testQueue.isFull();

        try
        {
            testQueue.peek();
            assert false;
        }
        catch(NullPointerException e)
        {
            assert true;
        }

        for(int i = 1; i <= 10; i++)
        {
            testQueue.enqueue(i*2);
            assert i == testQueue.getCount();
        }

        assert false == testQueue.isFull();
        assert false == testQueue.isEmpty();

        Object[] exp = {2,4,6,8,10,12,14,16,18,20};
        int [] expCount = {9,8,7,6,5,4,3,2,1,0};
        int[] expHead = {0,1,2,3,4,5,6,7,8,9};

        for(int i = 0; i < exp.length; i++)
        {
            assert expHead[i] == testQueue.getHead();
            assert exp[i] == testQueue.dequeue();
            assert expCount[i] == testQueue.getCount();
            assert 10 == testQueue.getTail();
        }

        DSACircularQueue full = new DSACircularQueue(20);

        for(int i = 1; i <= 20; i++)
        {
            full.enqueue(i * i);
        }

        assert true == full.isFull();
        assert false == full.isEmpty();

        assert 1 == (int)full.peek();

        for(int i = 0; i < 5; i++)
        {
            full.dequeue();
        }

        int[] expTail = {0,1,2,3,4};

        for(int i = 0; i < 5; i++)
        {
            assert expTail[i] == full.getTail();
            full.enqueue(i*3);
        }
    } // End testDSACircularQueue()
}
