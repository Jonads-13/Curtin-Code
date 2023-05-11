/*
 * Title:     Serialisation
 * Author:    Jacob Jonas, 18439731
 * Created:   30/08/2022
 * Modified:  30/08/2022
 * Assertion: Interactive program to demonstrate serialisation
 */

import java.util.*;
import java.io.*;

public class Serialisation 
{
    public static void main(String[] args)
    {
        // Scanner object for user input
        Scanner sc = new Scanner(System.in);
        int choice; // Holds user choice
        Object value; // To hold any values removed from the list
        boolean close = false; // Control loop

        // Create new list object
        DSALinkedList list = new DSALinkedList();
        // Clear the terminal
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        do // Begin do-while loop for menu
        {
            // Display menu
            System.out.println("Choose an action to perform:\n\n>1 Insert First:\n>2 Insert Last: \n>3 Remove First: \n>4 Remove Last: \n>5 Save: \n>6 Load: \n>0 Exit");
            choice = sc.nextInt(); // Get user choice

            switch(choice) // Stipulated options
            {
                case 1:
                    list.insertFirst(sc.next()); // Place user input at the beginning of the list

                    // Clear the terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    // Notify user of what they entered
                    System.out.println("Object added: " + list.peekFirst() + "\n");
                break;

                case 2:
                    list.insertLast(sc.next()); // Place user input at the end of the list

                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    // Notify user of what they added
                    System.out.println("Object added: " + list.peekLast() + "\n");
                break;

                case 3:
                    value = list.removeFirst(); // Remove the first value

                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    // Notify user what value was removed
                    System.out.println("Object removed: " + value + "\n");
                break;

                case 4:
                    value = list.removeLast(); // Remove the last value

                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    // Notify user what value was removed
                    System.out.println("Object removed: " + value + "\n");
                break;

                case 5:
                    save(list, "LinkedList.ser"); // Save the List to a serialised file

                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    // Notify user of what the saved file is called
                    System.out.println("List saved to file called: LinkedList.ser");
                break;

                case 6:
                    // New list is the list loaded from the serialised file
                    DSALinkedList loadedList = load("LinkedList.ser");

                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    // Print the list
                    iterateOverList(loadedList);
                break;

                case 0:
                    // Exit the program
                    System.out.println("Goodbye");
                    close = true; // Exit the loop
                break;

                default:
                    System.out.println("Error."); // input not stipulated
                break;
            }
        }while(!close);

        sc.close(); // Close scanner object

    } // End main()

    
    
    
    
    
    /*
     * Title:     save
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    theList (DSALinkedList), filename (String)
     * Export:    none
     * Assertion: Serialise a DSALinkedList and save it to a file
     */

    private static void save(DSALinkedList theList, String filename)
    {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;

        try 
        {
            fileStrm = new FileOutputStream(filename);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(theList); // Write the object to the file
            objStrm.close();
            fileStrm.close();
        }
        catch (Exception e) 
        {
            System.out.println("Error: " + e);
            throw new IllegalArgumentException("Unable to save object to file");
        } 
    } // End save()

    
    
    
    
    
    /*
     * Title:     load
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    filename (String)
     * Export:    inObj (DSALinkedList)
     * Assertion: Read a serialised file and return the object that was written in it
     */
    
    private static DSALinkedList load(String filename) throws IllegalArgumentException
    {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSALinkedList inObj = new DSALinkedList(); // New list to hold the loaded object

        try 
        {
            fileStrm = new FileInputStream(filename);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSALinkedList)objStrm.readObject(); // Typecast object to DSALinkedList
            objStrm.close();
        }
        catch (ClassNotFoundException e) 
        {
            System.out.println("Class DSALinkedList not found" + e.getMessage());
        }
        catch (Exception e) 
        {
            throw new IllegalArgumentException("Unable to load object from file");
        }

        return inObj;
    } // End load()

    
    
    
    
    
    /*
     * Title:     iterateOverList
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    theList (DSALinkedList)
     * Export:    none
     * Assertion: Print the contents of a list
     */

    public static void iterateOverList(DSALinkedList theList)
    {
        // New iterator
        Iterator iter = theList.iterator();
        Object value; // Hold each value in the list

        while(iter.hasNext()) // Iterate through list
        {
            value = iter.next();
            System.out.println(value); // Print list item
        }

        // Notify once the iterator is at the end of the list
        System.out.println("List has finshed printing\n");
    } // End itersteOverList()

}
