/*
 * Title:     TreeTest
 * Author:    Jacob Jonas, 18439731
 * Created:   30/08/2022
 * Modified:  01/09/2022
 * Assertion: Test BinarySearchTree implementation
 * Previously submitted for Practical 05 in COMP1002, Sem 2, 2022
 */

import java.util.NoSuchElementException;

public class UnitTestBinarySearchTree
{
    public static void main(String[] args)
    {
        // Create new object to perform tests on
        BinarySearchTree test = new BinarySearchTree();

        System.out.println("\nTesting tree was created empty:");

        assert true == test.isEmpty(); // Test it was created empty

        System.out.println("PASSED");


        System.out.println("\nTesting getRoot() on an empty tree:");

        try
        {
            // Attempt to get root on a empty tree
            test.getRoot();
            assert false; // Test fails if it gets here
        }
        catch(NoSuchElementException e)
        {
            assert true; // Test succeeds of exceptio id thrown
        }

        System.out.println("PASSED");


        System.out.println("\nTesting find() on an empty tree:");

        try
        {
            // Attempt to find a node on a empty tree
            test.find("Hello");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }

        System.out.println("PASSED");


        System.out.println("\nTesting delete() on an empty tree:");        

        try
        {
            // Attempt to delete a node on a empty tree
            test.delete("Hello");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }

        System.out.println("PASSED");

        System.out.println("\nTesting height() on an empty tree:");
        assert -1 == test.height();
        System.out.println("PASSED");



        System.out.println("\nTesting key being blank:");

        try
        {
            // Attempt to insert a blank key
            test.insert("", 10);
            assert false;
        }
        catch(IllegalArgumentException e)
        {
            assert true;
        }

        System.out.println("PASSED");

        test.insert("Hello", 50); // Insert a node


        System.out.println("\nTesting find and insert:");
        assert 50 == (int)test.find("Hello");
        System.out.println("PASSED");


        System.out.println("\nTesting isEmpty() on a non empty tree:");
        assert false == test.isEmpty();
        System.out.println("PASSED");


        System.out.println("\nTesting getRoot() on a one node tree:");
        assert "Hello".equals(test.getRoot()); // Node was inserted at the root
        System.out.println("PASSED");


        System.out.println("\nTesting inserting the same key:");
        try
        {
            //attempt to insert a node with the same key
            test.insert("Hello", 20);
            assert false;
        }
        catch(IllegalArgumentException e)
        {
            assert true;
        }

        System.out.println("PASSED");


        System.out.println("\nTesting find() after multiple inserts:");

        // Insert a bunch more nodes
        test.insert("Cello", 30);
        test.insert("Mother", 54);
        test.insert("Arthur", 1);
        test.insert("Violin", 4);
        test.insert("List", 10);
        test.insert("Father", 55);


        // Search for each inserted node
        assert 55 == (int)test.find("Father");
        assert 10 == (int)test.find("List");
        assert 4 == (int)test.find("Violin");
        assert 1 == (int)test.find("Arthur");
        assert 54 == (int)test.find("Mother");
        assert 30 == (int)test.find("Cello");
        

        System.out.println("PASSED");


        System.out.println("\nTesting getRoot() after multiple inserts:");
        assert "Hello".equals(test.getRoot());
        System.out.println("PASSED");

        System.out.println("\nTesting min() and max():");
        assert "Arthur".equals(test.min());
        assert "Violin".equals(test.max());
        System.out.println("PASSED");

        // insert a few more values
        test.insert("Ishtar", 100);
        test.insert("Castoria", 90);
        test.insert("Jack", 13);

        // Notice that a test is being performed
        System.out.println("\nTesting height():");
        assert 4 == test.height();
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting balance():");
        assert 66.67 == test.balance();
        System.out.println("PASSED"); // Notify of success

        DSALinkedList pre = test.preOrder();

        System.out.println("\nTesting preOrder(): ");

        int i = 0; // indexing
        //expected pre order output
        String[] preExp = {"50", "30", "1", "90", "55", "54", "10", "100", "13", "4"};

        for(Object o : pre)
        {
            String value = "" + o; // convert obejct to string
            System.out.println(value); // Display
            assert preExp[i].equals(value);
            i++; 
        }
        System.out.println("\nPASSED");

        System.out.println("\nTesting inOrder(): ");

        DSALinkedList in = test.inOrder();
        String[] inExp = {"1", "90", "30", "55", "50", "100", "13", "10", "54", "4"};
        i = 0;

        for (Object o : in)
        {   String value = "" + o; // convert obejct to string
            System.out.println(value);
            assert inExp[i].equals(value);
            i++;
        }
        System.out.println("\nPASSED");

        System.out.println("\nTesting postOrder(): ");

        DSALinkedList post = test.postOrder();

        String[] postExp = {"90", "1", "55", "30", "13", "100", "10", "4", "54", "50"};

        i = 0;

        for(Object o : post)
        {
            String value = "" + o; // convert obejct to string
            System.out.println(value);
            assert postExp[i].equals(value);
            i++;
        }
        System.out.println("\nPASSED");


        System.out.println("\nTesting delete on the root:");
        test.delete("Hello");

        try
        {
            test.find("Hello");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }

        System.out.println("PASSED");


        System.out.println("\nTesting new root successor found:");
        assert "Ishtar".equals(test.getRoot()); // New node
        System.out.println("PASSED");


        System.out.println("\nTesting find() after deleting the root:");
        // Finding each node afeter deleting the node
        assert 55 == (int)test.find("Father");
        assert 10 == (int)test.find("List");
        assert 100 == (int)test.find("Ishtar");
        assert 4 == (int)test.find("Violin");
        assert 1 == (int)test.find("Arthur");
        assert 90 == (int)test.find("Castoria");
        assert 54 == (int)test.find("Mother");
        assert 30 == (int)test.find("Cello");
        assert 13 == (int)test.find("Jack");

        System.out.println("PASSED");

        // Deleting all nodes in the tree
        test.delete("Father");
        test.delete("List");
        test.delete("Violin");
        test.delete("Arthur");
        test.delete("Mother");
        test.delete("Cello");
        test.delete("Ishtar");
        test.delete("Castoria");
        test.delete("Jack");

        System.out.println("\nTesting isEmpty() on a newly empty tree:");
        assert true == test.isEmpty();
        System.out.println("PASSED");



        System.out.println("\nTesting if nodes were deleted:");
        // Attempt to find bunch of nodes that were recently deleted
        try
        {
            test.find("List");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }

        try
        {
            test.find("Father");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }        
        
        try
        {
            test.find("Arthur");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }        
        
        try
        {
            test.find("Violin");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }        
        
        try
        {
            test.find("Mother");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }        
        
        try
        {
            test.find("Cello");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }

        System.out.println("PASSED");


        System.out.println("\nTesting delete on a newly empty tree:");
        try
        {
            test.delete("Father");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }

        System.out.println("PASSED");
    }
}