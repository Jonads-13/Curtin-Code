/*
 * Title:     KeyMeUp
 * Author:    Jacob Jonas, 18439731
 * Created:   07/09/2022
 * Modified:  15/09/2022
 * Assertion: Main program for assignment
 */

import java.util.NoSuchElementException;

public class KeyMeUp
{
    public static void main(String[] args)
    {
        if(args.length > 0) // There are command line argumenats
        {
            if(args[0].equals("-i")) // User chose interactive mode
            {
                Interactive.menu();   
            }
            else if((args[0].equals("-s")) && (args.length >= 4)) // User chose silent mode
            {
                silentMode(args);
            }
            else // There are command line arguments but, they are invalid or too few
            {
                usage();
            }
        }
        else // No command line arguments
        {
            usage();
        }
    } // End main()

    
    
    
    
    
    /*
     * Title:     usage
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  14/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Notify the user of how to begin the program
     */

    public static void usage()
    {
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Usage: java KeyMeUp -x");
        System.out.println("\nWhere -x is either:");
        System.out.println("\t-i: Interactive Testing Environment");
        System.out.println("\t-s: silent mode"); 
        System.out.println("\nIf silent mode is selected then usage:");
        System.out.println("\njava KeyMeUp -s keyfile strfile pathfile numPaths");
        System.out.println("\n\tkeyfile:    The file representing the keyboard");
        System.out.println("\tstrfile:    The file containing one or more strings to generate paths for");
        System.out.println("\tpathfile:   The file to which the generated paths will be saved\n");
        System.out.println("numPaths is an optional argument specifying how many paths between letters to generate");
        System.out.println("\nFor example:\n");
        System.out.println("\tIf the string is Data, and numPaths = 10\n");
        System.out.println("Then,");
        System.out.println("\t10 paths will be generated between \'D\' and \'a\',");
        System.out.println("\t10 paths will be generated between \'a\' and \'t\',");
        System.out.println("Finally,");
        System.out.println("\t10 paths will be generated between \'t\' and \'a\',");
        System.out.println("\nIf left blank, the default is 1,000\n");
    } // End usage()





    /*
     * Title:     silentMode
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  15/09/2022
     * Import:    args (String[])
     * Export:    none
     * Assertion: Performs tasks without user input
     */

    public static void silentMode(String[] args)
    {
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        int numPaths = 1000; // Default value


        if(args.length == 5) // User specified the number of paths
        {
            // Change to user specified value
            numPaths = Integer.parseInt(args[4]);
        }

        DSAGraph g = FileIO.readGraphFile(args[1]);// Create keyboard
        DSALinkedList strings = FileIO.readStringFile(args[2]); // Create string list

        // String file is not empty, keyboard file was not empty and/or file reading was successful
        if((!strings.isEmpty()) && (!g.isEmpty()))
        {
            DSALinkedList paths = generatePaths(g, strings, numPaths);

            FileIO.writeFile(args[3], paths, strings, numPaths);

            System.out.println(numPaths + " paths between characters saved to file called: " + args[3]);
        }
        else if((strings.isEmpty())) // String list file was empty or didn't exist
        {
            System.out.println("String list was not created from file. Ensure the file name is correct and that it contains strings\n");
        }
        else if(g.isEmpty()) // Keyboard file was empty or didn't exist
        {
            System.out.println("Keyboard was not created from file. Ensure the file name is correct and is not empty\n");
        }
    } // End silentMode()

    
    
    
    
    
    /*
     * Title:     generatePaths
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  26/09/2022
     * Import:    strings (DSALinkedList), g (DSAGraph), numPaths (int)
     * Export:    paths (DSALinkedList)
     * Assertion: generate oaths for each string in the imported list
     */

    public static DSALinkedList generatePaths(DSAGraph g, DSALinkedList strings, int numPaths)
    {

        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // DSALinkedList to hold ALL paths generated for ALL strings
        DSALinkedList paths = new DSALinkedList();
        String string = "";

        // For each string; insert the list of paths generated into the overall paths list
        for(Object o : strings) 
        {
            try
            {
                string = o + ""; // Convert Object to a String
                paths.insertLast(findPaths(string ,g, numPaths));
                // Notice each time all paths for a string has been found
                System.out.println(numPaths + " paths between characters found for " + string); 
            }
            // Abort path finding if a character in the string is not in the keyboard
            catch(NoSuchElementException e)
            {
                // Notice of the missing key on the keyboard
                System.out.println("Paths for " + string + " failed: " + e.getMessage() + " in the keyboard");
            }
   
        }

        if(!paths.isEmpty())
        {
            System.out.println("\nPaths for all compatible strings found\n");
            System.out.println(numPaths + " paths for each character pairing generated\n");
        }
   

        return paths;
    } // End generatePaths()

    
    
    
    
    
    /*
     * Title:     findPath
     * Author:    Jacob Jonas, 18439731
     * Created:   13/09/2022
     * Modified:  15/09/2022
     * Import:    strings (DSALinkedList), g (DSAGraph), numPaths (int)
     * Export:    stringPaths (DSALinkedList)
     * Assertion: find all paths on a keyboard for a given string
     */

    public static DSALinkedList findPaths(String string, DSAGraph g, int numPaths)
    {
        DSALinkedList stringPaths = new DSALinkedList();

        String from = "", to = "";

        // For every charatcer in the string, bar the last;
        // Find paths inbetween it and the next character
        for(int i = 0; i < string.length() - 1; i++)
        {
            from = string.charAt(i) + "";
            to = string.charAt(i + 1) + "";

            // Converting space characters to the comparable key in the keyboard
            if(from.equals(" "))
            {
                from = "SPACE"; 
            }

            if(to.equals(" "))
            {
                to = "SPACE";
            }

            if(i < string.length() - 1) // After possibly incrementing i, ensure it is still within bounds
            {
                // Insert returned list of paths into overall list for each charcter in the string 
                stringPaths.insertLast(g.findPaths(from, to, numPaths));
            }
        }

        return stringPaths;
    } // End findPaths()
} // End KeyMeUp class
