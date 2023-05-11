/*
 * Title:     UnitTestInteractive
 * Author:    Jacob Jonas, 18439731
 * Created:   03/10/2022
 * Modified:  04/10/2022
 * Assertion: Test Methods implemented in Interactive.java
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UnitTestInteractive 
{
    public static void main(String[] args)
    {
        testEnterStrings();
        testGetNumPaths();
        testDisplayGeneratedPaths();
        testOptionToSave();
        testSaveKeyboard();
        testOperationsUpdate();
    }






     /*
     * Title:     testEnterStrings
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test enterStrings()
     */
    
    public static void testEnterStrings() 
    {
        // Setup
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        System.out.println("\nTesting that a list of strings is created using enterStrings()");

        DSALinkedList test = new DSALinkedList();
        String input = "hello\nthere\n0\n"; // Simulated user input

        // Hods expected console output
        String exp = "\033[H\033[2JEnter a string, 0 to exit or -1 to erase the list:\n"
                   + "\033[H\033[2Jhello added to list of strings\n\n"
                   + "Enter a string, 0 to exit or -1 to erase the list:\n"
                   + "\033[H\033[2Jthere added to list of strings\n\n"
                   + "Enter a string, 0 to exit or -1 to erase the list:\n"
                   + "\033[H\033[2J";
        

        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Simulate user input
        System.setOut(new PrintStream(capOut)); // Capture console output
        test = Interactive.enterStrings(test);
        assert exp.equals(capOut.toString());

        // Returned list's state is as expected
        assert false == test.isEmpty();
        assert 2 == test.getCount();

        // Returned list has expected items
        assert "hello".equals(test.peekFirst() + "");
        assert "there".equals(test.peekLast() + "");

        // Tear down
        System.setOut(originalOut);
        System.setIn(originalIn);

        System.out.println("PASSED");

        System.out.println("\nTesting that list of strings is cleared if user wishes:");

        String clearList = "-1\n0\n"; // Simulated user input

        // Holds expeced console output
        String clearExp = "\033[H\033[2JEnter a string, 0 to exit or -1 to erase the list:\n"
                        + "\033[H\033[2JList has been erased and is now blank\n\n"
                        + "Enter a string, 0 to exit or -1 to erase the list:\n"
                        + "\033[H\033[2J";

        ByteArrayOutputStream capOut2 = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(clearList.getBytes())); // Simulate user input
        System.setOut(new PrintStream(capOut2)); // Capture console output
        test = Interactive.enterStrings(test); // Method call
        assert clearExp.equals(capOut2.toString()); // Console output is as expected

        // Returned list's state is as expected
        assert true == test.isEmpty();
        assert 0 == test.getCount();

        // Tear down
        System.setOut(originalOut);
        System.setIn(originalIn);

        System.out.println("PASSED");
    }

    
    
    
    
    
    /*
     * Title:     testGetNumPaths
     * Author:    Jacob Jonas, 18439731
     * Created:   03/10/2022
     * Modified:  03/10/2022
     * Import:    none
     * Export:    none
     * Assertion: unittest method
     */
    
    public static void testGetNumPaths() 
    {
        // Setup
        PrintStream originalOut = System.out;

        System.out.println("\nTesting valid input for numPaths");

        // Used to simulate user input
        Scanner valid = new Scanner("5\n");

        // holds expected console output
        String expValid = "\033[H\033[2J"
                        + "Enter the amount of paths to generate:\n"
                        + "NOTE: Entering 1 will find the shortest path\n";

        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        assert 5 == Interactive.getNumPaths(valid); // Method call
        assert expValid.equals(capOut.toString()); // Console output is as expected

        // Tear down
        System.setOut(originalOut);
        valid.close();

        System.out.println("PASSED");

        System.out.println("\nTesting invaid input for numpaths:");

        // Used to simulate user input
        Scanner invalid = new Scanner("0\n5\n");

        // holds expected console output
        String expInvalid = "\033[H\033[2J"
                          + "Enter the amount of paths to generate:\n"
                          + "NOTE: Entering 1 will find the shortest path\n"
                          + "Enter the amount of paths to generate:\n"
                          + "NOTE: Entering 1 will find the shortest path\n";

        ByteArrayOutputStream capOut1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut1)); // Capture console output
        assert 5 == Interactive.getNumPaths(invalid); // Method call
        assert expInvalid.equals(capOut1.toString()); // Console out is as expected

        // Tear down
        System.setOut(originalOut);
        invalid.close();

        System.out.println("PASSED");

        System.out.println("\nTesting mismatch error input for numPaths");

        // Used to simulate user input
        Scanner mismatch = new Scanner("a\n5\n");

        // Holds expected console output
        String expMismatch = "\033[H\033[2J"
                           + "Enter the amount of paths to generate:\n"
                           + "NOTE: Entering 1 will find the shortest path\n"
                           + "\033[H\033[2J"
                           + "Error: Incorrect character type. Enter an integer greater than 0\n\n"
                           + "Enter the amount of paths to generate:\n"
                           + "NOTE: Entering 1 will find the shortest path\n";

        ByteArrayOutputStream capOut2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut2)); // Capture console output
        assert 5 == Interactive.getNumPaths(mismatch); // Method call
        assert expMismatch.equals(capOut2.toString()); // Console output is as expected

        // Tear down
        System.setOut(originalOut);
        mismatch.close();

        System.out.println("PASSED");
    }

    
    
    
    
    
    /*
     * Title:     testDisplayGeneratedPaths
     * Author:    Jacob Jonas, 18439731
     * Created:   06/10/2022
     * Modified:  06/10/2022
     * Import:    none
     * Export:    none
     * Assertion: Method unittest
     */
    
    public static void testDisplayGeneratedPaths() 
    {
        // Setup
        PrintStream originalOut = System.out;

        // Create keyboard
        DSAGraph keyboard = FileIO.readGraphFile("switch.al");

        // Create list of strings
        DSALinkedList strings = new DSALinkedList();
        strings.insertFirst("as");
        DSALinkedList paths = new DSALinkedList();

        Scanner sc = new Scanner("n\n");

        String invalidExp = "\033[H\033[2J"
                          + "You have not yet generated any paths. Please select option 7 to do so\n\n";


        System.out.println("\nTesting invalid paths list for displayGeneratedPaths():");

        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        Interactive.displayGeneratedPaths(paths, strings, 1, sc);
        assert invalidExp.equals(capOut.toString());


        //Tear down
        System.setOut(originalOut);

        System.out.println("PASSED");


        System.out.println("\nTesting valid paths list for displayGenteratedPaths():");

        String validExp = "\033[H\033[2J"
                        + "\tBest 1 paths between characters for: \"as\"\n\n"
                        + "\n\tList of paths between 'a' and 's'\n\n"
                        + "a s "
                        + "\n\n\n\n\n\n\n"
                        + "Would you like to save the list of paths?\n"
                        + "(Y)es\n"
                        + "(N)o\n"
                        + "\033[H\033[2J";

        ByteArrayOutputStream capOut1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut1)); // Capture output to remove this mthod call from unittest display
        paths = KeyMeUp.generatePaths(keyboard, strings, 1);
        System.setOut(originalOut);

        ByteArrayOutputStream capOut2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut2));
        Interactive.displayGeneratedPaths(paths, strings, 1, sc);
        assert validExp.equals(capOut2.toString());


        //Tear down
        System.setOut(originalOut);
        sc.close();

        System.out.println("PASSED");

    }

    
    
    
    
    
    /*
     * Title:     testOptionToSave
     * Author:    Jacob Jonas, 18439731
     * Created:   03/10/2022
     * Modified:  03/10/2022
     * Import:    none
     * Export:    none
     * Assertion: unittest method
     */
    
    public static void testOptionToSave() 
    {
        // Setup
        PrintStream originalOut = System.out;

        System.out.println("\nTesting optionToSave():");

        // Capture output to clean up unittest display
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));

        DSAGraph keyboard = FileIO.readGraphFile("netflix.al"); // Create keyboard
        DSALinkedList strings = FileIO.readStringFile("strings.txt"); // Create list of strings
        DSALinkedList paths = KeyMeUp.generatePaths(keyboard, strings, 10);  // Create list of paths
        Scanner sc = new Scanner("y\ntestsave.txt\n"); // Used to simulate user input

        Interactive.optionToSave(paths, strings, 10, sc); // Method call

        File f = new File("testsave.txt"); // Create File object from output file's name
        assert true == f.exists(); // Test the output file was created

        // Tear down
        System.setOut(originalOut);
        f.delete();
        sc.close();

        System.out.println("PASSED");
    }

    
    
    
    
    
    /*
     * Title:     testSaveKeyboard
     * Author:    Jacob Jonas, 18439731
     * Created:   03/10/2022
     * Modified:  03/10/2022
     * Import:    none
     * Export:    none
     * Assertion: unittest method
     */
    
    public static void testSaveKeyboard() 
    {
        // Setup
        PrintStream originalOut = System.out;

        System.out.println("\nTesting saveKeyboard():");

        // Capture output to clean up unittest display
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));

        DSAGraph keyboard = FileIO.readGraphFile("netflix.al"); // Create keyboard
        Scanner sc = new Scanner("testkeyboard.al"); // Used to simulate user input
        Interactive.saveKeyboard(keyboard, sc); // Method call

        File f = new File("testkeyboard.al"); // Create File object from output file's name
        assert true == f.exists(); // Test the output file was created

        // Tear down
        System.setOut(originalOut);
        f.delete();
        sc.close();

        System.out.println("PASSED");
    }

    
    
    
    
    
    /*
     * Title:     testOperationsUpdate
     * Author:    Jacob Jonas, 18439731
     * Created:   03/10/2022
     * Modified:  03/10/2022
     * Import:    none
     * Export:    none
     * Assertion: unittest method
     */
    
    public static void testOperationsUpdate() 
    {
        // Setup
        PrintStream originalOut = System.out;

        System.out.println("\nTesting the update function in Operations:");

        // Capture output to clean up unittest display
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));

        // Create keyboard
        DSAGraph keyboard = FileIO.readGraphFile("netflix.al"); 

        // Prove that the node to be added later isn't already in the keyboard
        assert false == keyboard.hasVertex("A");
        
        // Used to simulate user input of adding a new node but
        // not updating the keyboard to keep the change
        Scanner nonUpdate = new Scanner("2\nA\n0\n");

        // Method call to the test method
        DSAGraph returned = Operations.nodeOperations(keyboard, nonUpdate);

        assert false == returned.hasVertex("A"); // returned keyboard doesn't have the added vertex

        // Used to simulate user input of adding a new node and
        // updating the keyboard to keep the change
        Scanner update = new Scanner("2\nA\n4\n0\n");

        // Method call to the test method
        returned = Operations.nodeOperations(keyboard, update);

        assert true == returned.hasVertex("A"); // Returned keyboard now has the added node

        // Tear down
        System.setOut(originalOut);
        nonUpdate.close();
        update.close();

        System.out.println("PASSED");
    }
    
}
