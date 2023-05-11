/*
 * Title:     Display
 * Author:    Jacob Jonas, 18439731
 * Created:   16/09/2022
 * Modified:  16/09/2022
 * Assertion: Conatins functions relating to display data
 */

import java.util.Iterator;

public class Display 
{

     /*
    * Title:     matrix
    * Author:    Jacob Jonas, 18439731
    * Created:   08/09/2022
    * Modified:  12/09/2022
    * Import:    keyboard (DSAGraph)
    * Export:    none
    * Assertion: Dsiplay an imported graph as a matrix
    */

    public static void matrix(DSAGraph keyboard)
    {
        keyboard.displayAsMatrix();
    } // End matrix()

    
    
    
    
    
    /*
     * Title:     list
     * Author:    Jacob Jonas, 18439731
     * Created:   07/10/2022
     * Modified:  07/10/2022
     * Import:    keyboard (DSAGraph)
     * Export:    none
     * Assertion: display an imported graph as an adjacency list
     */
    
    public static void list(DSAGraph keyboard) 
    {
        keyboard.adjacencyList();    
    } // End list()





    /*
     * Title:     linkedList
     * Author:    Jacob Jonas, 18439731
     * Created:   13/09/2022
     * Modified:  13/09/2022
     * Import:    list (DSALinkedList)
     * Export:    none
     * Assertion: print contents of a DSALinkedList
     */

    public static void linkedList(DSALinkedList list)
    {
        for (Object object : list) 
        {
            System.out.println(object);
        }
    } // End linkedList()





    /*
     * Title:     paths
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  15/09/2022
     * Import:    stringPathList (DSALinkedList), strings (DSAlinkedList), numPaths (int)
     * Export:    none
     * Assertion: Display all the generated paths
     */

    public static void paths(DSALinkedList stringPathList, DSALinkedList strings, int numPaths)
    {
        String[] input = createStringArray(strings);
        String from = "", to = "";
        int i = 0; // indexing string array
        int j = 0; // Indexing each character in the string
        int count = 0; // Keeps the amount of paths displayed to only 5
        

        for(Object list : stringPathList)
        {
            System.out.println("\tBest " + numPaths + " paths between characters for: \"" + input[i] + "\"\n");

            DSALinkedList letterList = (DSALinkedList)list;

            for (Object curPathList : letterList) 
            {

                DSALinkedList pathList = (DSALinkedList)curPathList;
                
                from = input[i].charAt(j) + "";
                to = input[i].charAt(j+1) + "";

                System.out.println("\n\tList of paths between \'" + from + "\' and \'" + to + "\'\n");

                if(numPaths > 5) // Notice if the list has been truncated
                {
                    System.out.println("\tNote: This list has been truncated to only 5 paths");
                    System.out.println("\tThe full " + numPaths + " is availble through file saving\n");
                }

                Iterator iter = pathList.iterator();

                while((iter.hasNext()) && (count < 5)) // Truncating list to only 5 oaths
                {
                    count++;

                    DSALinkedList path = (DSALinkedList)iter.next();

                    for (Object node : path) 
                    {
                        System.out.print(node + " ");
                    }
                    System.out.print("\n");
                }
                System.out.println("\n\n\n\n\n"); // Free space between each new string
                j++; // Move to the next character
                count = 0; // Reset the number of paths displayed
            }
            i++; // Move to the next string to display
            j = 0; // Reset back to the first chacracter
        }
    } // End paths()

    
    
    
    
    
    /*
     * Title:     createStringArray
     * Author:    Jacob Jonas, 18439731
     * Created:   13/09/2022
     * Modified:  15/09/2022
     * Import:    strings (DSALinkedList)
     * Export:    arr (String[])
     * Assertion: Create an array containing the strings used in the program for display purposes
     */

    public static String[] createStringArray(DSALinkedList strings) 
    {
        // Array size = to DSALinkedList size
        String[] arr = new String[strings.getCount()]; 
        int i = 0; // Indexing for arr

        // Place each object from the list into the string array
        for (Object string : strings) 
        {
            arr[i] = string + "";
            i++;
        }

        return arr;
    } // End createStringArray()
    
}
