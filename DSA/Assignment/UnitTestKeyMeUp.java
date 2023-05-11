/*
 * Title:     UnitTestKeyMeUp.java
 * Author:    Jacob Jonas, 18439731
 * Created:   18/09/2022
 * Modified:  19/09/2022
 * Assertion: Test various functions throughout the program
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

public class UnitTestKeyMeUp
{
    public static void main(String[] args)
    {
        testUsage();
        testSilent();
        testMenu();
        testGeneratePaths();
    }

    
    
    
    
    
    /*
     * Title:     testUsage
     * Author:    Jacob Jonas, 18439731
     * Created:   18/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test that usage is called when needed
     */
    
    public static void testUsage()
    {
        // Notice that a test is being performed
        System.out.println("\nTesting empty args:");

        PrintStream originalOut = System.out; // Setup

        // String to hold expected console output
        String exp = "\033[H\033[2J" // Clearing terminal
                    + "Usage: java KeyMeUp -x\n"
                    + "\nWhere -x is either:\n"
                    + "\t-i: Interactive Testing Environment\n"
                    + "\t-s: silent mode\n"
                    + "\nIf silent mode is selected then usage:\n"
                    + "\njava KeyMeUp -s keyfile strfile pathfile numPaths\n"
                    + "\n\tkeyfile:    The file representing the keyboard\n"
                    + "\tstrfile:    The file containing one or more strings to generate paths for\n"
                    + "\tpathfile:   The file to which the generated paths will be saved\n\n"
                    + "numPaths is an optional argument specifying how many paths between letters to generate\n"
                    + "\nFor example:\n\n"
                    + "\tIf the string is Data, and numPaths = 10\n\n"
                    + "Then,\n"
                    + "\t10 paths will be generated between \'D\' and \'a\',\n"
                    + "\t10 paths will be generated between \'a\' and \'t\',\n"
                    + "Finally,\n"
                    + "\t10 paths will be generated between \'t\' and \'a\',\n"
                    + "\nIf left blank, the default is 1,000\n\n";

        String[] nullArgs = new String[0]; // Create dummy args as parameter
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        KeyMeUp.main(nullArgs); // Function call
        assert exp.equals(capOut.toString());

        System.setOut(originalOut); // Tear down
        System.out.println("PASSED"); // Notify of success


        // Notice that a test is being performed
        System.out.println("\nTesting invalid args");
        
        String[] invalidArgs = {"hello"};
        ByteArrayOutputStream capOut2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut2)); // Capture console output
        KeyMeUp.main(invalidArgs); // Function call
        assert exp.equals(capOut2.toString());

        System.setOut(originalOut); // Tear down

        System.out.println("PASSED"); // Notify of success


        // Notice that a test is being performed
        System.out.println("\nTesting incorrect amount of arguments for Silent mode:");
        
        String[] invalidSilentArgs = {"-s"};
        ByteArrayOutputStream capOut3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut3)); // Capture console output
        KeyMeUp.main(invalidSilentArgs); // Function call
        assert exp.equals(capOut3.toString());

        System.setOut(originalOut); // Tear down
        System.out.println("PASSED"); // Notify of success

    } // End testUsage()

    
    
    
    
    
    /*
     * Title:     testSilent
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Testing the implementation of silent mode
     */
    
    public static void testSilent()
    {
        PrintStream originalOut = System.out; // Setup

        String success = "\033[H\033[2J\033[H\033[2J1000 paths between characters found for hello\n"
                       + "1000 paths between characters found for there\n"
                       + "1000 paths between characters found for general\n"
                       + "1000 paths between characters found for kenobi\n"
                       + "\nPaths for all compatible strings found\n"
                       + "\n1000 paths for each character pairing generated\n"
                       + "\n1000 paths between characters saved to file called: testoutput.txt\n";

        String[] exp = {
                        success,
                        "\033[H\033[2JError in reading file: netflix\nKeyboard was not created from file. Ensure the file name is correct and is not empty\n\n",
                        "\033[H\033[2JKeyboard was not created from file. Ensure the file name is correct and is not empty\n\n",
                        "\033[H\033[2JError in reading file: testinput\nString list was not created from file. Ensure the file name is correct and that it contains strings\n\n",
                        "\033[H\033[2JString list was not created from file. Ensure the file name is correct and that it contains strings\n\n" 
                       };

        String[][] args = {
                           {"-s", "netflix.al", "testinput.txt", "testoutput.txt"}, 
                           {"-s", "netflix", "testinput.txt", "testoutput.txt"}, 
                           {"-s", "empty.al", "testinput.txt", "testoutput.txt"}, 
                           {"-s", "netflix.al", "testinput", "testoutput.txt"},
                           {"-s", "netflix.al", "empty.txt", "testoutput.txt"}
                          }; 

        String[] msg = {
                        "Arguments are valid", 
                        "Keyboard file doesn't exist", 
                        "Keyboard file is empty", 
                        "String file doesn't exist", 
                        "String file is empty"
                       };
                          
        
        for(int i = 0; i < exp.length; i++)
        {
            System.out.println("\nTesting " + msg[i]);

            ByteArrayOutputStream capOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(capOut)); // Capture console output
            KeyMeUp.silentMode(args[i]); // Function call
            assert exp[i].equals(capOut.toString());

            System.setOut(originalOut); // Tear down

            System.out.println("PASSED");
        }


        System.out.println("\nTesting adding numpaths to command line");

        String numPathsExp = "\033[H\033[2J\033[H\033[2J10 paths between characters found for hello\n"
                           + "10 paths between characters found for there\n"
                           + "10 paths between characters found for general\n"
                           + "10 paths between characters found for kenobi\n"
                           + "\nPaths for all compatible strings found\n"
                           + "\n10 paths for each character pairing generated\n"
                           + "\n10 paths between characters saved to file called: testoutput.txt\n";

        String[] numPathsArgs = {"-s", "netflix.al", "testinput.txt", "testoutput.txt", "10"};

        ByteArrayOutputStream capOut2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut2)); // Capture console output
        KeyMeUp.silentMode(numPathsArgs); // Function call
        assert numPathsExp.equals(capOut2.toString());

        System.setOut(originalOut); // Tear down

        // Checking that the file to save was created
        File f = new File("testoutput.txt");
        assert true == f.exists();

        f.delete(); // Delete test output file

        System.out.println("PASSED");
        
    }

    
    
    
    
    
    /*
     * Title:     testMenu
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test menu is displayed with proper command line argument
     */
    
    public static void testMenu()
    {
        System.out.println("\nTesting menu is displayed after proper command line argument");

        // Setup
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        String[] args = {"-i"};

        String exp = "\033[H\033[2J"
                   + "Choose an option:\n\n"
                   + ">1 Load keyboard from file\n"
                   + ">2 Node Operations (find, add, delete)\n"
                   + ">3 Edge operations (find, add, delete)\n"
                   + ">4 Display graph\n"
                   + ">5 Display graph information\n"
                   + ">6 Create a list of strings or delete the current list\n"
                   + ">7 Generate paths\n"
                   + ">8 Display paths\n"
                   + ">9 Save keyboard\n"
                   + ">0 Exit\n"
                   + "\033[H\033[2JGoodbye\n";
        
        String input = "0\n"; // Needed to exit the function


        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Simulate user input
        KeyMeUp.main(args); // Function call
        assert exp.equals(capOut.toString());

        // Tear down
        System.setOut(originalOut);
        System.setIn(originalIn);

        System.out.println("PASSED");
        
    }

    
    
    
    
    
    /*
     * Title:     testGeneratePaths
     * Author:    Jacob Jonas, 18439731
     * Created:   19/09/2022
     * Modified:  19/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Test generatePaths()
     */
    
    public static void testGeneratePaths() 
    {
        PrintStream originalOut = System.out; // Setup

        System.out.println("\nTesting paths are generated");
        
        // Create keyboard
        DSAGraph g = FileIO.readGraphFile("netflix.al"); 

        DSALinkedList strings = new DSALinkedList(), paths = new DSALinkedList();


        // Populate list
        strings.insertLast("hello");
        strings.insertLast("there");
        ByteArrayOutputStream capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut)); // Capture console output

        paths = KeyMeUp.generatePaths(g, strings, 5);

        assert false == paths.isEmpty();

        System.setOut(originalOut); // Tear down

        System.out.println("PASSED");
    }
}
