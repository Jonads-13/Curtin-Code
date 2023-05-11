/*
 * Title:     Operations
 * Author:    Jacob Jonas, 18439731
 * Created:   08/09/2022
 * Modified:  08/09/2022
 * Assertion: Methods to modify the keyboard used in KeyMeUp
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Operations 
{
    /*
     * Title:     nodeOperations
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  26/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    keyboard (DSAGraph)
     * Assertion: Modify the nodes of the imported graph
     */

    public static DSAGraph nodeOperations(DSAGraph keyboard, Scanner sc)
    {
        // A number highly unlikely to be entered by the user 
        //to differentiate between incorrect choice and InputMismatchExceptions 
        int choice = -18439731;
        boolean close = false;

        // Temporary DSAGraph that alows for changes to be either saved or discarded.
        DSAGraph update = new DSAGraph(keyboard);

        do
        {
            System.out.println("Choose an operation to perform:\n");
            System.out.println(">1 Find a node");
            System.out.println(">2 Add a node");
            System.out.println(">3 Remove a node");
            System.out.println(">4 Save changes");
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
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    findNode(update, sc);
                break;

                case 2:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    update = addNode(update, sc);
                break;

                case 3:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    update = removeNode(update, sc);
                break;

                case 4:
                    keyboard = update; // Apply changes to return keyboard

                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("Changes saved\n");
                break;

                case 0:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    close = true;
                break;

                default:
                    if(choice != -18439731) // Only print this if choice was updated
                    {
                        // Clear terminal
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("Choice " + choice + " not stipulated. Choose again\n");
                    }   
            }

        }while(!close);

       return keyboard;
    } // End nodeOperations()

    
    
    
    
    
    /*
     * Title:     findNode
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  08/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    none
     * Assertion: determines of the node entered by hte user exists in the graph
     */

    public static void findNode(DSAGraph keyboard, Scanner sc)
    {
        System.out.println("Enter the label of the node you wish to find");
        boolean inGraph = keyboard.hasVertex(sc.next() + "");

        if(inGraph)
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Node exists in the keyboard\n");
        }
        else
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Node does not exist in the keyboard\n");
        }
    } // End findNode()

    
    
    
    
    
    /*
     * Title:     addNode
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  08/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    keyboard (DSAGraph)
     * Assertion: Add a node to the keyboard
     */

    public static DSAGraph addNode(DSAGraph keyboard, Scanner sc)
    {
        System.out.println("Enter the label of the node you wish to add");
        String label = sc.next() + "";

        boolean inGraph = keyboard.hasVertex(label);

        if(!inGraph)
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            keyboard.addVertex(label, 0);

            System.out.println("Node " + label + " was added to the keyboard\n");
        }
        else
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Node exists in the keyboard\n");
        }

        return keyboard;
    } // End addNode()

    
    
    
    
    
    /*
     * Title:     removeNode
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  08/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    keyboard (DSAGraph)
     * Assertion: remove a ndoe from the keyboard
     */

    public static DSAGraph removeNode(DSAGraph keyboard, Scanner sc)
    {
        System.out.println("Enter the label of the node you wish to remove:");
        String label = sc.next() + "";

        boolean inGraph = keyboard.hasVertex(label);

        if(inGraph)
        {
            keyboard.deleteVertex(label);
        }
        else
        {
            System.out.println("Node does not exist to remove");
        }

        return keyboard;
    } // End removenode()

    
    
    
    

   /*
    * Title:     edgeOperations
    * Author:    Jacob Jonas, 18439731
    * Created:   08/09/2022
    * Modified:  26/09/2022
    * Import:    keyboard (DSAGraph), sc (Scanner)
    * Export:    keyboard (DSAGraph)
    * Assertion: Modify the edges of an imported graph
    */

    public static DSAGraph edgeOperations(DSAGraph keyboard, Scanner sc)
    {
        // A number highly unlikely to be entered by the user 
        //to differentiate between incorrect choice and InputMismatchExceptions 
        int choice = -18439731;
        boolean close = false;
        
        // Temporary DSAGraph that alows for changes to be either saved or discarded.
        DSAGraph update = new DSAGraph(keyboard);

        do
        {
            System.out.println("Choose an operation to perform:\n");
            System.out.println(">1 Find an edge");
            System.out.println(">2 Add an edge");
            System.out.println(">3 Remove an edge");
            System.out.println(">4 Save changes");
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
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    findEdge(update, sc);
                break;

                case 2:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    update = addEdge(update, sc);
                break;

                case 3:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    update = removeEdge(update, sc);
                break;

                case 4:
                    keyboard = update;

                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("Changes saved");
                break;

                case 0:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    close = true;
                break;

                default:
                    if(choice != -18439731) // Only print this if choice was updated
                    {
                        // Clear terminal
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("Choice " + choice + " not stipulated. Choose again\n");
                    } 
            }

        }while(!close);

       return keyboard;
    } // End edgeOperations()

    
    
    
    
    
    /*
     * Title:     findEdge
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  26/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    none
     * Assertion: Determine for the user if an edge is in the graph
     */

    public static void findEdge(DSAGraph keyboard, Scanner sc)
    {
        System.out.println("Enter the label of the edge you wish to find");
        boolean inGraph = keyboard.hasEdge(sc.next() + "");

        if(inGraph)
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Edge exists in the keyboard\n");
        }
        else
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Edge does not exist in the keyboard\n");
        }
    } // End findEdge()

    
    
    
    
    
    /*
     * Title:     addEdge
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  26/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    keyboard (DSAGraph)
     * Assertion: add an edge given by the user to the graph
     */

    public static DSAGraph addEdge(DSAGraph keyboard, Scanner sc)
    {
        System.out.println("Enter the label of the node where the edge starts");
        String label1 = sc.next() + "";
        System.out.println("Enter the label of the node where the edge ends");
        String label2 = sc.next() + "";

        boolean inGraph1 = keyboard.hasVertex(label1);
        boolean inGraph2 = keyboard.hasVertex(label2);

        // Only try to add the edge of both nodes exist
        if((inGraph1) && (inGraph2))
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if(keyboard.hasEdge(label1 + label2))
            {
                System.out.println("Edge " + label1 + label2 + " already exists in the keyboard\n");
            }
            else // Edge doesn't already exist
            {
                keyboard.addEdge(label1, label2, 0);

                System.out.println("Edge " + label1 + label2 + " was added to the keyboard\n");
            }
        }
        else if(!inGraph1)
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Node " + label1 + " does not exist in the keyboard\n");
        }
        else if(!inGraph2)
        {
            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Node " + label2 + " does not exist in the keyboard\n");
        }

        return keyboard;
    } // End addEdge()

    
    
    
    
    
    /*
     * Title:     removeEdge
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  26/09/2022
     * Import:    keyboard (DSAGraph), sc (Scanner)
     * Export:    keyboard (DSAGraph)
     * Assertion: remove and edge from the keyboard
     */

    public static DSAGraph removeEdge(DSAGraph keyboard, Scanner sc)
    {
        System.out.println("Enter the label of the edge you wish to remove:");
        System.out.println("NOTE: edge names have no spaces.");
        String label = sc.next() + "";

        boolean inGraph = keyboard.hasEdge(label);

        if(inGraph)
        {
            keyboard.deleteVertex(label);

            // Clear terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(label + " removed from the keyboard\n");
        }
        else
        {
            System.out.println("Edge does not exist to remove\n");
        }

        // Clear the scanner buffer if the user entered a space character
        // Otherwise the program will crash in the parent menu at sc.nextInt()
        if(label.matches("\\S"))
        {
            sc.next();
        }

        return keyboard;
    } // End removeEdge()
}