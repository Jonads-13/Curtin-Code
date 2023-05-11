/*
 * Title:     Interactive
 * Author:    Jacob Jonas, 18439731
 * Created:   16/09/2022
 * Modified:  30/09/2022
 * Assertion: Contains all methods relating to user interaction
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interactive 
{

    /*
     * Title:     menu
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  30/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Interactive menu for the program
     */

    public static void menu()
    {
        Scanner sc = new Scanner(System.in);

        // A number highly unlikely to be entered by the user 
        //to differentiate between incorrect choice and InputMismatchExceptions 
        int choice = -18439731;

        int numPaths = 0;
        boolean close = false; // Control loop

        // Empty objects that will be updated later through returns of other methods
        DSALinkedList strings = new DSALinkedList(), paths = new DSALinkedList();
        DSAGraph keyboard = new DSAGraph();

        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        do
        {
            // Display menu to user
            System.out.println("Choose an option:\n");
            System.out.println(">1 Load keyboard from file");
            System.out.println(">2 Node Operations (find, add, delete)");
            System.out.println(">3 Edge operations (find, add, delete)");
            System.out.println(">4 Display graph");
            System.out.println(">5 Display graph information");
            System.out.println(">6 Create a list of strings or delete the current list");
            System.out.println(">7 Generate paths");
            System.out.println(">8 Display paths");
            System.out.println(">9 Save keyboard");
            System.out.println(">0 Exit");

            try
            {
                choice = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Error: Incorrect character type. Enter an integer\n");
                sc.next(); // Clear Scanner buffer
            }

            switch(choice)
            {
                case 1:
                    keyboard = loadKeyboard(keyboard, sc);
                break;

                case 2:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    keyboard = Operations.nodeOperations(keyboard, sc);
                break;

                case 3:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    keyboard = Operations.edgeOperations(keyboard, sc);
                break;

                case 4:
                    displayKeyboard(keyboard, sc);
                break;

                case 5:
                    displayInformation(keyboard, sc);
                break;

                case 6:
                    strings = createStringList(strings, sc);
                break;

                case 7:
                    numPaths = getNumPaths(sc);
                    paths = getPaths(keyboard, paths, strings, numPaths);
                break;

                case 8:
                    displayGeneratedPaths(paths, strings, numPaths, sc);
                break;

                case 9:
                    saveKeyboard(keyboard, sc);
                break;

                case 0:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("Goodbye");
                    close = true;
                break;

                default:
                    if(choice != -18439731) // Only print this if choice was never updated
                    {
                        // Clear terminal
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("Choice " + choice + " not stipulated. Choose again\n");
                    }   
            }
        }while(!close);
        sc.close();
    } // End interactive()

    
    
    
    
    
    /*
     * Title:     loadKeyboard
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    keyboard (DSAgraph), sc (Scanner)
     * Export:    keyboard (DSAGraph)
     * Assertion: menu option 1
     */
    
    public static DSAGraph loadKeyboard(DSAGraph keyboard, Scanner sc) 
    {
         // Clear terminal
         System.out.print("\033[H\033[2J");
         System.out.flush();

         System.out.println("Enter the file to read from:");
         System.out.println("WARNING: THIS WILL OVERWRITE ANY KEYBOARD ALREADY IN THE PROGRAM.");
         System.out.println("Enter an invalid filename to abort this process.");
         String filename = sc.next() + "";
         
         // Create DSAGraph from file
         DSAGraph temp = FileIO.readGraphFile(filename);

         if(!temp.isEmpty())
         {
             // Clear terminal
             System.out.print("\033[H\033[2J");
             System.out.flush();

             System.out.println("Keyboard created from file " + filename + "\n");
             keyboard = temp;
         }    

         return keyboard;
    } // End loadKeyboard()

    
    
    
    
    
    /*
     * Title:     displayKeyboard
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  07/10/2022
     * Import:    keyboard (DSAGraph) sc Scanner
     * Export:    none
     * Assertion: menu option 4
     */
    
    public static void displayKeyboard(DSAGraph keyboard, Scanner sc) 
    {
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        if(keyboard.isEmpty()) // User has not loaded in a keyboard
        {
            System.out.println("You have not provided a keyboard to display.");
            System.out.println("Please selected option 1 to load in a keyboard or options 2 and 3 to create one from scratch\n");
        }
        else
        {
            int choice = -18439731;
            boolean close = false;

            do
            {
                System.out.println("View Adjacency Matrix or List:");
                System.out.println(">1 Matrix");
                System.out.println(">2 List");
                try
                {
                    choice = sc.nextInt();
                }
                catch(InputMismatchException e)
                {
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("Error: Incorrect character type. Enter an integer\n");
                    sc.next(); // Clear Scanner buffer
                    choice = -18439731;

                }

                if(choice == 1)
                {
                    Display.matrix(keyboard);
                    close = true;
                }
                else if(choice == 2)
                {
                    Display.list(keyboard);
                    close = true;
                }
                else
                {
                    if(choice != -18439731) // Only print this if choice was never updated
                    {
                        // Clear terminal
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("Choice " + choice + " not stipulated. Choose again\n");
                    } 
                }
            }while(!close);
        }    
    } // End displayKeyboard

    
    
    
    
    
    /*
     * Title:     displayInformation
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    none
     * Assertion: menu option 5
     */
    
    public static void displayInformation(DSAGraph keyboard, Scanner sc) 
    {
         // Clear terminal
         System.out.print("\033[H\033[2J");
         System.out.flush();

         if(keyboard.isEmpty()) // User has not loaded in a keyboard
         {
             System.out.println("You have not provided a keyboard to display information for.");
             System.out.println("Please selected option 1 to load in a keyboard or options 2 and 3 to create one from scratch\n");
         }
         else
         {
             graphInformation(keyboard, sc);
         }    
    } // End displayInformation()

    
    
    
    
    
    /*
     * Title:     graphInformation
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  26/09/2022
     * Import:    keyboard (DSAGraph)
     * Export:    none
     * Assertion: Display the information about an imported graph
     */

    public static void graphInformation(DSAGraph keyboard, Scanner sc)
    {
        // A number highly unlikely to be entered by the user 
        //to differentiate between incorrect choice and InputMismatchExceptions
        int choice = -18439731;
        boolean close = false;

        do
        {
            System.out.println("Choose a piece of information to view:");
            System.out.println(">1 View all vertices");
            System.out.println(">2 View all edges");
            System.out.println(">3 View a breadth first search");
            System.out.println(">4 View a depth first search");
            System.out.println(">0 Exit");
            try
            {
                choice = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Error: Incorrect character type. Enter an integer\n");
                sc.next();
            }
            

            switch(choice)
            {
                case 1:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    DSALinkedList vertices = (keyboard.getVertices()).inOrder();

                    Display.linkedList(vertices);
                    System.out.println("Total number of vertices: " + vertices.getCount() + "\n");
                break;

                case 2:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    DSALinkedList edges = (keyboard.getEdges()).inOrder();

                    Display.linkedList(edges);
                    System.out.println("Total number of edges: " + edges.getCount() + "\n");
                break;

                case 3:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    keyboard.breadthFirstSearch();
                    System.out.print("\n");
                break;

                case 4: 
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    keyboard.depthFirstSearch();
                    System.out.print("\n");
                break;

                case 0:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    close = true;
                break;

                default:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("Option: " + choice + " not stipulated. Choose again\n");
            }
        }while(!close);
    } // End displayInformation

    
    
    
    
    
    /*
     * Title:     createStringList
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    strings (DSALinkedList), sc (Scanner)
     * Export:    strings (DSALinkedList)
     * Assertion: menu option 6
     */
    
    public static DSALinkedList createStringList(DSALinkedList strings, Scanner sc) 
    {
        // A number highly unlikely to be entered by the user 
        //to differentiate between incorrect choice and InputMismatchExceptions
        int choice = -18439731;
        boolean close = false;
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        do
        {
            System.out.println("Enter: ");
            System.out.println(">1 Manually");
            System.out.println(">2 Load from file");
            try 
            {
                choice = sc.nextInt();
            } 
            catch(InputMismatchException e) 
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Error: Incorrect character type. Enter an integer\n");
                sc.next();
            }
            
            if(choice == 1)
            {
                strings = enterStrings(strings);
                close = true;
            }
            else if(choice == 2)
            {
                System.out.println("Enter the name of the file to read from:");
                String filename = sc.next() + "";

                strings = FileIO.readStringFile(filename);

                if(!strings.isEmpty())
                {
                    System.out.println("List of strings created from file " + filename);
                }  
                close = true;
            } 
            else 
            {
                if(choice != -18439731)
                {
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("Option: " + choice + " not stipulated. Choose again\n");
                }
            } 
        }while(!close);  

        return strings;
    } // End createStringsList()


    
    
    
    
    
    /*
     * Title:     enterStrings
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  15/09/2022
     * Import:    strings (DSALinkedList), br (BufferedReader)
     * Export:    strings DSALinkedList
     * Assertion: Get a list of strings from the user
     */

    public static DSALinkedList enterStrings(DSALinkedList strings)
    {
        String input = "";
        boolean close = false;
        
        // Buffered Reader used to allow for spaces in the String input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        do 
        {
            System.out.println("Enter a string, 0 to exit or -1 to erase the list:");

            try
            {
                input = br.readLine();
            }
            catch(IOException e)
            {/*What would actually cause this from user input?*/}
            

            if(input.equals("0")) // User wants to exit
            {
                close = true; // Exit loop

                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            else if(input.equals("-1")) // User wants to erase the current list
            {
                strings = new DSALinkedList();

                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("List has been erased and is now blank\n");
            }
            else
            {
                strings.insertLast(input); // Insert user entered string

                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(input + " added to list of strings\n");
            }
           
        }while(!close);


        return strings;
    } // End enterStrings()

    
    
    
    
    
    /*
     * Title:     getNumPaths
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    sc (Scanner)
     * Export:    numPaths (int)
     * Assertion: get numPaths value from the user
     */
    
    public static int getNumPaths(Scanner sc) 
    {
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int numPaths = -1;

        do
        {
            System.out.println("Enter the amount of paths to generate:");
            System.out.println("NOTE: Entering 1 will find the shortest path");
            try
            {
                numPaths = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Error: Incorrect character type. Enter an integer greater than 0\n");
                sc.next(); // Clear Scanner buffer
            }
        }while(numPaths < 1);    

        return numPaths;
    } // getNumPaths()

    
    
    
    
    
    /*
     * Title:     getPaths
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    keyboard (DSAGraph), paths (DSALinkedList), strings (DSALinkedList), numPaths (int)
     * Export:    paths (DSALinkedList)
     * Assertion: menu option 7
     */
    
    public static DSALinkedList getPaths(DSAGraph keyboard, DSALinkedList paths, DSALinkedList strings, int numPaths) 
    {
        if(strings.isEmpty()) // User has not entered any strings
        {
            System.out.println("You have not provided any strings to find paths for. Please select option 6 to provide strings\n");
        }
        else if(keyboard.isEmpty()) // User has not loaded in a keyboard
        {
            System.out.println("You have not provided a keyboard to find paths on.");
            System.out.println("Please selected option 1 to load in a keyboard or options 2 and 3 to create one from scratch\n");
        }
        else
        {
            paths = KeyMeUp.generatePaths(keyboard, strings, numPaths);
        } 
        
        return paths;
    } // End getPaths()

    
    
    
    
    
    /*
     * Title:     displayGeneratedPaths
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    paths (DSALinkedList), strings (DSALinkedList), numPaths, (int), sc (Scanner)
     * Export:    none
     * Assertion: menu option 8
     */
    
    public static void displayGeneratedPaths(DSALinkedList paths, DSALinkedList strings, int numPaths, Scanner sc) 
    {
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        if(paths.isEmpty()) // User has not generated any paths
        {
            System.out.println("You have not yet generated any paths. Please select option 7 to do so\n");
        }
        else
        {
            Display.paths(paths, strings, numPaths);

            optionToSave(paths, strings, numPaths, sc);
        }    
    } // End displayGeneratedPaths()






    /*
     * Title:     optionToSave
     * Author:    Jacob Jonas, 18439731
     * Created:   13/09/2022
     * Modified:  15/09/2022
     * Import:    stringPathList (DSALinkedList), strings (DSALinkedList), numPaths (int), sc (Scanner)
     * Export:    none
     * Assertion: Ask the user whether they want to save the generated paths to a file.
     */

    public static void optionToSave(DSALinkedList paths, DSALinkedList strings, int numPaths, Scanner sc) 
    {
        boolean close = false; // Controls loop

        do
        {
            System.out.println("Would you like to save the list of paths?");
            System.out.println("(Y)es");
            System.out.println("(N)o");
            char choice = sc.next().charAt(0);

            if((choice == 'y') || (choice == 'Y'))
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Enter the name of the file to save the paths to:");
                String filename = sc.next() + "";

                FileIO.writeFile(filename, paths, strings, numPaths);

                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Paths written to file called \"" + filename + "\"\n");

                close = true; // Exit loop

            }
            else if((choice == 'n') || (choice == 'N'))
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                close = true; // Exit loop
            }
            else
            {
                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Option: " + choice + " not stipulated. Choose again.\n");
            }
        }while(!close);
    } // End optionToSave()

    
    
    
    
    
    /*
     * Title:     saveKeyboard
     * Author:    Jacob Jonas, 18439731
     * Created:   29/09/2022
     * Modified:  29/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    none
     * Assertion: menu option 9
     */
    
    public static void saveKeyboard(DSAGraph keyboard, Scanner sc) 
    {
        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Enter the name of the file to save the keyboard to:");
        String filename = sc.next() + "";

        if(keyboard.isEmpty()) // User hasn't loaded or created a keybpard
        {
            System.out.println("There is no keyboard to save. Please select options 2 and 3 to create one from scratch");
            System.out.println("Or option 1 to load a keyboard, then options 2 and 3 to modify it\n");
        }
        else
        {
            FileIO.writeKeyboard(keyboard, filename);
        }    
    } // End saveKeyboard()
}
