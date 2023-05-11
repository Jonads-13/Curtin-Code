/*
 * Title:     FileIO
 * Author:    Jacob Jonas, 18439731
 * Created:   08/09/2022
 * Modified:  05/10/2022
 * Assertion: Contains all file io functions for KeyMeUp
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileIO 
{
     /*
     * Title:     readGraphFile
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  07/09/2022
     * Import:    filename (String)
     * Export:    none
     * Assertion: Read a .al file and create a DSAGraph from the information
     */
    
    public static DSAGraph readGraphFile(String filename)
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
            System.out.println("Error in reading file: " + filename);
        }

        return graph;
    } // End readFile()
   
   
   
   
   
   /*
    * Title:     parseLine
    * Author:    Jacob Jonas, 18439731
    * Created:   07/09/2022
    * Modified:  05/10/2022
    * Import:    row (String), graph (DSAGraph)
    * Export:    DSAGraph
    * Assertion: Create a new edge between the two vertices identified in the row
    */
   
    public static DSAGraph parseLine(String row, DSAGraph graph)
    {
        String[] splitLine;
        splitLine = row.split(" ");

        // Add each key to the keyboard
        graph.addVertex(splitLine[0], 0);
        graph.addVertex(splitLine[1], 0);

        // Add the edge between them
        graph.addEdge(splitLine[0], splitLine[1], 0);

       return graph;
    }

    
    
    
    
    
    /*
     * Title:     readStringFile
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  08/09/2022
     * Import:    filename (String)
     * Export:    strings (DSALinkedList)
     * Assertion: Read a text file containg strings a return a DSALinkedList contain them
     */
    
     public static DSALinkedList readStringFile(String filename)
     {
         FileInputStream fileStream = null;
         InputStreamReader isr;
         BufferedReader bufRdr;
         int lineNum = 0;
         String line;
         DSALinkedList strings = new DSALinkedList();
    
         try
         {
             fileStream = new FileInputStream(filename);
             isr = new InputStreamReader(fileStream);
             bufRdr = new BufferedReader(isr);
    
             line = bufRdr.readLine();
             while(line != null)
             {
                 strings.insertLast(line);
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
             System.out.println("Error in reading file: " + filename);
         }

         return strings;
     } // End readStringFile()

    
    
    
    
   /*
    * Title:      writeFile
    * Author:     Jacob Jonas, 18439731
    * Created:    08/09/2022
    * Modified:   08/09/2022
    * Import:     paths (DSALinkedList), fliename (String)
    * Export:     none
    * Assertiorn: Write data to a file
    */

   public static void writeFile(String filename, DSALinkedList paths, DSALinkedList strings, int numPaths)
   {
       FileOutputStream fileStream;
       PrintWriter pw;

       String[] input = Display.createStringArray(strings);
       String from = "", to = "", totalAverage = "";
       double numNodes = 0;
       int i = 0; // indexing string array
       int j = 0; // Indexing each character in the string

       try
       {
            fileStream = new FileOutputStream(filename);
            pw = new PrintWriter(fileStream);

            for(Object list : paths)
            {
                pw.write("\tBest " + numPaths + " paths between characters for \"" + input[i] + "\":\n");

                DSALinkedList charList = (DSALinkedList)list;

                double[] average = new double[input[i].length()];

                for(Object curPathList : charList) 
                {
                    DSALinkedList pathList = (DSALinkedList)curPathList;

                    // This number should equal the number of paths
                    // Except in the case of double letters where it will only ever equal 1
                    double count = pathList.getCount();

                    from = input[i].charAt(j) + "";
                    to = input[i].charAt(j+1) + "";

                    pw.write("\n\tList of paths between \'" + from + "\' and \'" + to + "\'\n\n");

                    for(Object curPath : pathList) 
                    {
                        DSALinkedList path = (DSALinkedList)curPath;

                        for(Object node : path) 
                        {
                            pw.write(node + " ");
                            numNodes++; // Count the number of nodes
                        }
                        pw.write("\n");
                    }
                    // Using count instead of the numPaths parameter ensures the calculation is correct
                    average[j] = numNodes/count; // Calculate average, useful for scenarios in the report

                    pw.write("\n\tAverage length of the path = " + average[j]);
                    pw.write("\n\n\n\n\n");

                    j++;
                    numNodes = 0; // Reset total number for each new character pairing
                }
                // Add all the averages for the current string
                totalAverage = sumArray(average);

                pw.write("Average number of nodes to form \"" + input[i] + "\" = " + totalAverage + "\n");
                pw.write("\n\n\n\n\n");

                i++; // Move to next string
                j = 0; // Reset character index to the beginning of the string
            }
            pw.close();
       }
       catch(IOException e)
       {
           System.out.println("Error in writing to file");
       }
   } // End writeFile()

   
   
   
   
   
   /*
    * Title:     sumArray
    * Author:    Jacob Jonas, 18439731
    * Created:   30/09/2022
    * Modified:  30/09/2022
    * Import:    averages (double[])
    * Export:    totalAverage (double)
    * Assertion: calculate the total of the average path lengths for a string
    */
   
    public static String sumArray(double[] averages) 
    {
        double totalAverage = 0.0;

        for(int i = 0; i< averages.length; i++)
        {
            totalAverage += averages[i];
        }

        String rounded = String.format("%.3f", totalAverage);

        return rounded;
    }

   
   
   
   
   /*
    * Title:      writeKeyboard
    * Author:     Jacob Jonas, 18439731
    * Created:    13/09/2022
    * Modified:   13/09/2022
    * Import:     keyboard (DSAGraph), fliename (String)
    * Export:     none
    * Assertiorn: Write a keyboard to a file
    */

    public static void writeKeyboard(DSAGraph keyboard, String filename)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;

        DSALinkedList edges = keyboard.getEdges().inOrder();

        try
        {
            fileStream = new FileOutputStream(filename);
            pw = new PrintWriter(fileStream);

            for (Object edge : edges) 
            {
                pw.write(edge + "\n");
            }

            pw.close();
        }
        catch(IOException e)
        {
            System.out.println("Error in writing to file");
        }
    } // End writeFile()
}
