/*
 * Title:     UnitTestDSALLQueue
 * Author:    Jacob Jonas, 18439731
 * Created:   20/09/2022
 * Modified:  20/09/2022
 * Assertion: Test DSALLQueue implementation
 * Previously submitted for Practical 04 in COMP1002, Sem 2, 2022
 */

public class UnitTestDSALLQueue 
{
    public static void main(String[] args)
    {
        DSALLQueue testQueue = new DSALLQueue(); // Create new DSAQueue to run tests on


        System.out.println("\nTesting isEmpty() on an empty object:");

        assert true == testQueue.isEmpty(); // Test isEmpty() on an empty DSAQueue

        System.out.println("PASSED");


        System.out.println("\nTesting peek() on an empty object:");

        try
        {
            testQueue.peek(); // Attempt to view the first value in an empty DSAQueue
            assert false; // Test fails if it gets this far
        }
        catch(NullPointerException e) // Catch exception thrown by the class
        {
            assert true; // Test succeeded if an exception was thrown
        }

        System.out.println("PASSED");


        for(int i = 1; i <= 10; i++) // Partially fill DSAQueue
        {
            testQueue.enqueue(i*2);
            assert testQueue.getCount() == i; // Test count is incrementing properly
        }


        System.out.println("\nTesting isEmpty() on a partially filled object:");

        assert false == testQueue.isEmpty(); // Test isEmpty() on a partially filled DSAQueue

        System.out.println("PASSED");


        Object[] exp = {2,4,6,8,10,12,14,16,18,20}; // Array to hold expceted values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count

        System.out.println("\nTesting dequeue() and getCount():");

        for(int i = 0; i < exp.length; i++)
        {
            assert exp[i] == testQueue.dequeue(); // Test values were correct
            assert expCount[i] == testQueue.getCount(); // Test count decrements as expected
        }

        System.out.println("PASSED");


        for(int i = 11; i <= 20; i++) // Fill the object to the end from the new index
        {
            testQueue.enqueue(i * i);
        }

        System.out.println("\nTesting peek() on a full object:");

        assert 121 == (int)testQueue.peek(); // Test the value at the new first index

        System.out.println("PASSED");
    } // End main()
}
