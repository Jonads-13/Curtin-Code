/*
 * Title:     GraphTest
 * Author:    Jacob Jonas, 18439731
 * Created:   05/09/2022
 * Modified:  05/09/2022
 * Assertion: Test th implementation of DSAGraph
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class GraphTest 
{
    public static void main(String[] args)
    {
        DSAGraph test = new DSAGraph();

        // Notice that a test is being performed
        System.out.println("\nTesting isEmpty() on an empty Graph:");
        assert true == test.isEmpty();
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting addEdge() on an empty Graph:");
        try
        {
            test.addEdge("hello", "world", 0);
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting getEdge() on an empty Graph:");
        try
        {
            test.getEdge("Hello World");
            assert false;
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting hasEdge() on an empty Graph:");
        assert false == test.hasEdge("Hello");
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting hasVertex() on an empty Graph:");
        assert false == test.hasVertex("Hello");
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting getVertex() on an empty Graph:");
        try
        {
            test.getVertex("Hello");
        }
        catch(NoSuchElementException e)
        {
            assert true;
        }
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting addVertex() on an empty Graph:");
        test.addVertex("Hello",8);
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting isEmpty() on a non-empty Graph:");
        assert false == test.isEmpty();
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting getVertex() on a non-empty Graph:");
        test.getVertex("Hello");
        System.out.println("PASSED"); // Notify of success

        test.addVertex("World", 20); // Add a second vertex to test edge creation

        // Notice that a test is being performed
        System.out.println("\nTesting getVertex() on a Graph with two vertices:");
        test.getVertex("World");
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting hasVertex() on a Graph with two vertices:");
        assert true == test.hasVertex("World");
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting addEdge():");
        test.addEdge("Hello", "World", 100);
        System.out.println("PASSED"); // Notify of success

        // Notice that a test is being performed
        System.out.println("\nTesting getEdge():");
        test.getEdge("HelloWorld");
        System.out.println("PASSED"); // Notify of success

        test.addVertex("There", 4);

        test.addEdge("World", "There", 0);
        test.addEdge("World", "Hello", 0);
        test.addEdge("Hello", "There", 0);
        test.addEdge("There", "Hello", 0);

        // Notice that a test is being performed
        System.out.println("\nTesting createMatrix():");
        int[][] matrix = test.createMatrix();
        int[][] expMatrix = {{0,0,1,2},{0,0,1,1},{1,1,0,1},{2,1,1,0}};

        for(int r = 0; r < matrix.length; r++)
        {
            for(int c = 0; c < matrix.length; c++)
            {
                assert expMatrix[r][c] == matrix[r][c];
            }
        }
        test.displayAsMatrix();
        System.out.println("PASSED"); // Notify of success


        // Notice that a test is being performed
        System.out.println("\nTesting breadthFirstSearch():");
        PrintStream originalOut = System.out; // Setup

        String exp = "HelloThere\nHelloWorld\n"; // Expected output
        
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        test.breadthFirstSearch(); // Method call
        assert exp.equals(capOut.toString());

        System.setOut(originalOut); // Tear down

        test.breadthFirstSearch();
        System.out.println("PASSED"); // Notify of success
       
        // Notice that a test is being performed
        System.out.println("\nTesting depthFirstSearch():");
        exp = "HelloThere\nThereWorld\n"; // Expected output
        ByteArrayOutputStream capOut2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut2)); // Capture console output
        test.depthFirstSearch(); // Method call
        assert exp.equals(capOut2.toString());

        System.setOut(originalOut); // Tear down
        test.depthFirstSearch();
        System.out.println("PASSED"); // Notify of success


        // Notice that a test is being performed
        System.out.println("\nTesting hasEdge():");
        assert true == test.hasEdge("HelloWorld");
        System.out.println("PASSED\n"); // Notify of success

        DSAGraph lecture = new DSAGraph();

        // Creating the graph from the lecture slides
        lecture.addVertex("A", 0);
        lecture.addVertex("B", 0);
        lecture.addVertex("C", 0);
        lecture.addVertex("D", 0);
        lecture.addVertex("E", 0);

        lecture.addEdge("A", "B", 0);
        lecture.addEdge("A", "D", 0);
        lecture.addEdge("A", "E", 0);
        lecture.addEdge("B", "C", 0);
        lecture.addEdge("B", "E", 0);
        lecture.addEdge("C", "D", 0);
        lecture.addEdge("C", "E", 0);


        System.out.println("\nTesting breadthFirstSearch() on lecture graph:");

        exp = "AB\nAD\nAE\nBC\n"; // Expected output
        
        ByteArrayOutputStream capOut3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut3)); // Capture console output
        lecture.breadthFirstSearch(); // Method call
        assert exp.equals(capOut3.toString());

        System.setOut(originalOut); // Tear down
        lecture.breadthFirstSearch();
        System.out.println("PASSED"); // Notify of success
       
        // Notice that a test is being performed
        System.out.println("\nTesting depthFirstSearch() on lecture graph:");

        exp = "AB\nBC\nCD\nCE\n"; // Expected output

        ByteArrayOutputStream capOut4 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut4)); // Capture console output
        lecture.depthFirstSearch(); // Method call
        assert exp.equals(capOut4.toString());

        System.setOut(originalOut); // Tear down
        lecture.depthFirstSearch();
        System.out.println("PASSED"); // Notify of success

    }
}
