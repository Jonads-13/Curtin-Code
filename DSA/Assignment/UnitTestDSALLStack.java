/*
 * Title:     UnitTestDSALLStack
 * Author:    Jacob Jonas, 18439731
 * Created:   20/09/2022
 * Modified:  20/09/2022
 * Assertion: Test DSALLStack implementation
 * Previously submitted for Practical 04 in COMP1002, Sem 2, 2022
 */

public class UnitTestDSALLStack
{
    public static void main(String[] args)
    {
        DSALLStack testStack = new DSALLStack(); // Create new DSALLStack to run tests on


        System.out.println("\nTesting isEmpty() on an empty object:");

        assert true == testStack.isEmpty(); // Test isEmpty() on an empty DSALLStack

        System.out.println("PASSED");


        System.out.println("\nTesting top() on an empty object:");

        try
        {
            testStack.top(); // Attempt to view the top value on an empty DSAStack
            assert false; // If the test gets here then it failed
        }
        catch(NullPointerException e) // Catch exception thrown by the class
        {
            assert true; // Test succeeded if an exception was thrown
        }

        System.out.println("PASSED");


        for(int i = 1; i <= 10; i++) // Partially fill DSALLStack
        {
            testStack.push(i*2);
            assert testStack.getCount() == i; // Test count is incrementing as expected
        }


        System.out.println("\nTesing isEmpty() on a partially full object:");

        assert false == testStack.isEmpty(); // Test isEmpty() on a partially filled DSLLAStack

        System.out.println("PASSED");


        Object[] exp = {20,18,16,14,12,10,8,6,4,2}; // Array to hold expected values
        int [] expCount = {9,8,7,6,5,4,3,2,1,0}; // Array to hold the expected count value


        System.out.println("\nTesting pop() and getCount():");

        for(int i = 0; i < exp.length; i++)
        {
            assert exp[i] == testStack.pop(); // Test the values are popping off as expected
            assert expCount[i] == testStack.getCount(); // Test count is decrementing as expected
        }

        System.out.println("PASSED");


        for(int i = 1; i <= 20; i++) // Fill DSAStack
        {
            testStack.push(i * i);
        }


        System.out.println("\nTesting top() on a full object:");

        assert 400 == (int)testStack.top(); // Test the top value is correct

        System.out.println("PASSED");
    } // End main()
}