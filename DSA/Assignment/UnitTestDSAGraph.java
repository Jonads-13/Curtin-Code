/*
 * Title:     GraphTest
 * Author:    Jacob Jonas, 18439731
 * Created:   05/09/2022
 * Modified:  05/09/2022
 * Assertion: Test the implementation of DSAGraph
 * Previously submitted for Practical 06 in COMP1002, Sem 2, 2022
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class UnitTestDSAGraph 
{
    public static void main(String[] args)
    {
        DSAGraph test = new DSAGraph();

        PrintStream originalOut = System.out; // Setup

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
            test.getEdge("HelloWorld");
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
            assert false;
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
        test.addEdge("There", "World", 0);

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

        String exp = "Hello |\tThere World \n"
                       + "There |\tHello World \n"
                       + "World |\tHello There \n";
        System.out.println("\nTesting adjacencyList():");
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        test.adjacencyList();
        assert exp.equals(capOut.toString());

        System.setOut(originalOut); // Tear down
        test.adjacencyList();

        System.out.println("PASSED");



        // Notice that a test is being performed
        System.out.println("\nTesting breadthFirstSearch():");
        

        exp = "HelloThere\nHelloWorld\n"; // Expected output
        
        ByteArrayOutputStream capOut1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut1)); // Capture console output
        test.breadthFirstSearch(); // Method call
        assert exp.equals(capOut1.toString());

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


        // Create a DSAgraph representing the graph from the lecture
        DSAGraph lecture = new DSAGraph();

        lecture.addVertex("A", 0);
        lecture.addVertex("B", 0);
        lecture.addVertex("C", 0);
        lecture.addVertex("D", 0);
        lecture.addVertex("E", 0);

        lecture.addEdge("A", "B", 0);
        lecture.addEdge("A", "D", 0);
        lecture.addEdge("A", "E", 0);
        lecture.addEdge("B", "A", 0);
        lecture.addEdge("B", "C", 0);
        lecture.addEdge("B", "E", 0);
        lecture.addEdge("C", "B", 0);
        lecture.addEdge("C", "D", 0);
        lecture.addEdge("C", "E", 0);
        lecture.addEdge("D", "C", 0);
        lecture.addEdge("D", "A", 0);
        lecture.addEdge("E", "A", 0);
        lecture.addEdge("E", "B", 0);
        lecture.addEdge("E", "C", 0);


        System.out.println("\nTesting findPaths() between A and E on lecture graph:");

        DSALinkedList paths = lecture.findPaths("A","E", 10);

        for (Object p : paths) 
        {
            DSALinkedList path = (DSALinkedList)p;

            for (Object node : path) 
            {
                System.out.print(node + " ");
            }
            System.out.println(" ");
        }



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

        // Notice that a test is being performed
        System.out.println("\nTesting deleteVertex():");

        lecture.deleteVertex("C");

        // Try to get the newly delete vertex
        try
        {
            lecture.getVertex("C");
            assert false; // Test fails if it gets here
        }
        catch(NoSuchElementException e)
        {
            assert true; // Success if exception os thrown
        }
        
        assert false == lecture.hasVertex("C");

        assert false == lecture.hasEdge("BC"); // Ensure an edge the delete vertex was part of is also deleted
        System.out.println("PASSED"); // Notify of success

        // Create a DSAGraph that represents a number pad
        DSAGraph numPad = new DSAGraph();

        numPad.addVertex("1",0);
        numPad.addVertex("2",0);
        numPad.addVertex("3",0);
        numPad.addVertex("4",0);
        numPad.addVertex("5",0);
        numPad.addVertex("6",0);
        numPad.addVertex("7",0);
        numPad.addVertex("8",0);
        numPad.addVertex("9",0);

        numPad.addEdge("1","2",0);
        numPad.addEdge("1","4",0);
        numPad.addEdge("1","3",0);
        numPad.addEdge("1","7",0);
        numPad.addEdge("2","1",0);
        numPad.addEdge("2","3",0);
        numPad.addEdge("2","5",0);
        numPad.addEdge("2","8",0);
        numPad.addEdge("3","2",0);
        numPad.addEdge("3","6",0);
        numPad.addEdge("3","9",0);
        numPad.addEdge("3","1",0);
        numPad.addEdge("4","1",0);
        numPad.addEdge("4","7",0);
        numPad.addEdge("4","5",0);
        numPad.addEdge("4","6",0);
        numPad.addEdge("5","2",0);
        numPad.addEdge("5","4",0);
        numPad.addEdge("5","8",0);
        numPad.addEdge("5","6",0);
        numPad.addEdge("6","9",0);
        numPad.addEdge("6","5",0);
        numPad.addEdge("6","3",0);
        numPad.addEdge("6","4",0);
        numPad.addEdge("7","8",0);
        numPad.addEdge("7","1",0);
        numPad.addEdge("7","9",0);
        numPad.addEdge("7","4",0);
        numPad.addEdge("8","9",0);
        numPad.addEdge("8","7",0);
        numPad.addEdge("8","5",0);
        numPad.addEdge("8","2",0);
        numPad.addEdge("9","3",0);
        numPad.addEdge("9","8",0);
        numPad.addEdge("9","7",0);
        numPad.addEdge("9","6",0);
   
        // Notice that a test is being performed
        System.out.println("\nTesting findPaths() between 1 and 9 on a number pad:");

        DSALinkedList numPadPaths = numPad.findPaths("1","9", 1000);

        for (Object padPath : numPadPaths) 
        {
            DSALinkedList curPath = (DSALinkedList)padPath;

            for (Object vertice : curPath) 
            {
                System.out.print(vertice + " ");
            }
            System.out.println(" ");
        }
    }
}
