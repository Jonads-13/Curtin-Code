/*
 * Title:     LinkedListTest
 * Author:    Jacob Jonas, 18439731
 * Created:   30/08/2022
 * Modified:  01/09/2022
 * Assertion: Create some test for the DSALinkedList class
 * Previously submitted for Practical 04 in COMP1002, Sem 2, 2022
 */


public class UnitTestDSALinkedList 
{
    public static void main(String[] args)
    {
        System.out.println("\nBeginning tests for linked list:\n");

        // Create a DSALinkedList to perform tests on
        DSALinkedList test = new DSALinkedList();
        

        System.out.println("\nTesting DSALinkedList is created empty:");
        assert true == test.isEmpty(); // Test list was created empty
        System.out.println("PASSED");
  
        System.out.println("\nTesting peekFirst() on an empty list:");
        try
        {
            // Attempt to view the first node in an empty list
            test.peekFirst();
            assert false; // Test fails if it makes it here
        }
        catch(NullPointerException e)
        {
            assert true; // Test was successful if an exception was thrown
        }
        System.out.println("PASSED");

        System.out.println("\nTesting peekLast() on an empty list:");
        try
        {
            // Attempt to view the last node in an empty list
            test.peekLast();
            assert false; // Test fails if it makes it here
        }
        catch(NullPointerException e)
        {
            assert true; // Test was successful if an exception was thrown
        }
        System.out.println("PASSED");

        System.out.println("\nTesting insertFirst() and peekFirst():");
        test.insertFirst("Hello"); // Place a new item in the list
        assert "Hello".equals(test.peekFirst()); // Test both insertFirst and peekFirst are working together
        System.out.println("PASSED"); // Notify of success

        System.out.println("\nTesting isEmpty on a non empty list");
        assert false == test.isEmpty(); // Test isEmpty on a non-empty list
        System.out.println("PASSED"); // Notify of success

        System.out.println("\nTesting insertLast() and peekLast():");
        test.insertLast("World");
        assert "World".equals(test.peekLast()); // Test both insertLast and peekLast are working together
        System.out.println("PASSED"); //Nofify of success
        System.out.println("\nTesting removeFirst():");
        test.removeFirst();
        assert "World".equals(test.peekFirst()); // Test removeFirst()
        System.out.println("PASSED"); // Notify of success

        test.insertFirst("Hello"); // re-insert the first value

        System.out.println("\nTesting removeLast():");
        test.removeLast();
        assert "Hello".equals(test.peekLast()); // Test removeLast()
        System.out.println("PASSED\n"); // Notify of success
            
    }
    
}
