/*
 * Title:     MainGraph
 * Author:    Jacob Jonas, 18439731
 * Created:   07/09/2022
 * Modified:  07/09/2022
 * Assertion: Create graphs from files
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class MainGraph 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String filename;

        // Clear terminal
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("Enter the file to read from:");
        filename = sc.next() + "";
        
        DSAGraph graph = readFile(filename);

        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Graph created from file " + filename);
        
        boolean close = false;
        int choice;

        do
        {
            System.out.println("\nChoose how you would like to view the graph:");
            System.out.println(">1 List of vertices");
            System.out.println(">2 List of edges");
            System.out.println(">3 Adjacency matrix");
            System.out.println(">4 Breadth first search");
            System.out.println(">5 Depth first search");
            System.out.println(">0 Exit");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    displayList(graph.getVertices());
                break;

                case 2:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    displayList(graph.getEdges());
                break;

                case 3:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    graph.displayAsMatrix();
                break;

                case 4:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    
                    graph.breadthFirstSearch();
                break;

                case 5:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    graph.depthFirstSearch();
                break;

                case 0:
                    close = true;
                    System.out.println("Goodbye");
                break;

                default:
                    System.out.println("Choice not stipulated. Choose again");
                
            }
            
        }while(!close);
        sc.close();
    }

    
    
    
    
    
    /*
     * Title:     displayList
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  07/09/2022
     * Import:    list (DSALinkedList)
     * Export:    none
     * Assertion: Display all items in a list
     */

     public static void displayList(DSALinkedList list)
     {
        for (Object o : list) 
        {
            System.out.println(o);
        }
     }

    
    
    
    
    
    /*
     * Title:     readFile
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  07/09/2022
     * Import:    filename (String)
     * Export:    none
     * Assertion: Read a csv file
     */
    
     public static DSAGraph readFile(String filename)
     {
         FileInputStream fileStream = null;
         InputStreamReader isr;
         BufferedReader bufRdr;
         int lineNum = 0;
         String line;
         DSAGraph graph = new DSAGraph();
    
         try
         {
             fileStream = new FileInputStream(filename);
             isr = new InputStreamReader(fileStream);
             bufRdr = new BufferedReader(isr);
    
             line = bufRdr.readLine();
             while(line != null)
             {
                 graph = parseLine(line,graph);
                 lineNum++;
                 line = bufRdr.readLine();
             }
             fileStream.close();
         }
         catch(IOException e)
         {
             if(fileStream != null)
             {
                 try
                 {
                     fileStream.close();
                 }
                 catch(IOException e2) { }
             }
             System.out.println("Error in file processing");
         }

         return graph;
     } // End readFile()
    
    
    
    
    
    /*
     * Title:     parseLine
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  07/09/2022
     * Import:    csvRow (String)
     * Export:    none
     * Assertion: Parse a csv row
     */
    
     public static DSAGraph parseLine(String row, DSAGraph graph)
     {
         String[] splitLine;
         splitLine = row.split(" ");
         int value = 0;

         // add both vertexs to the graph
         graph.addVertex(splitLine[0], value);
         graph.addVertex(splitLine[1], value);

         // add the edge between them to the graph
         graph.addEdge(splitLine[0], splitLine[1], value);

        return graph;

     }
}
