/*
 * Title:     FileIOTests
 * Author:    Jacob Jonas, 18439731
 * Created:   19/09/2022
 * Modified:  19/09/2022
 * Assertion: Test functions in FileIO.java
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UnitTestFileIO 
{
    public static void main(String[] args)
    {
        testParseLine();
        testReadGraph();
        testReadString();
    }

    
    
    
    
    
    /*
     * Title:     testParseLine
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test parseLine function in FileIO.java
     */
    
    public static void testParseLine()
    {
        DSAGraph test = new DSAGraph();

        test = FileIO.parseLine("A B", test);

        System.out.println("\nTesting keyboard has nodes and edge from parsed line:");
        assert true == test.hasVertex("A");
        assert true == test.hasVertex("B");
        assert true == test.hasEdge("AB");

        System.out.println("PASSED");
    }

    
    
    
    
    
    /*
     * Title:     testReadGraph
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test that a graph is created from a file using readGraphFile()
     */
    
    public static void testReadGraph()
    {
        DSAGraph test = new DSAGraph();

        test = FileIO.readGraphFile("netflix.al");

        System.out.println("\nTesting graph was created properly from file:");

        assert true == test.hasVertex("e");
        assert false == test.hasVertex("E");
        assert true == test.hasEdge("ef");
        assert false == test.hasEdge("Ef");
        assert true == test.hasVertex("SPACE");
        assert true == test.hasEdge("SPACEDELETE");

        System.out.println("PASSED");


        System.out.println("\nTesting graph isn't created if file is invalid:");

        PrintStream originalOut = System.out; // Setup
        String exp = "Error in reading file: testkeyboard\n"; // Expected console output

        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        test = FileIO.readGraphFile("testkeyboard"); // Method call
        assert exp.equals(capOut.toString()); // Console output is as expected
        
        assert true == test.isEmpty(); // Object state is as expected

        System.setOut(originalOut); // Tear down

        System.out.println("PASSED");
    }

    
    
    
    
    
    /*
     * Title:     testReadString
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test DSALinked list is created from readStringFile
     */
    
    public static void testReadString() 
    {
        DSALinkedList test = new DSALinkedList();

        System.out.println("\nTesting list was created properly from file");
        test = FileIO.readStringFile("testinput.txt");

        assert false == test.isEmpty();
        assert "hello".equals(test.peekFirst() + "");
        assert "kenobi".equals(test.peekLast() + "");

        System.out.println("PASSED");


        System.out.println("\nTesting list is not created if file is invalid");

        PrintStream originalOut = System.out; // Setup
        String exp = "Error in reading file: testinput\n"; // Expected console output

        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        test = FileIO.readStringFile("testinput"); // Method call
        assert exp.equals(capOut.toString()); // Console output is as expected

        assert true == test.isEmpty();

        System.setOut(originalOut); // Tear down

        System.out.println("PASSED");
    }
}
