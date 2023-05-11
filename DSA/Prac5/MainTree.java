/*
 * Title:     TreeMain
 * Author:    Jacob Jonas, 18439731
 * Created:   01/09/2022
 * Modified:  01/09/2022
 * Assertion: Pratical uses of tree methods with file reading and serialisation
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;



public class MainTree
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean close = false;
        BinarySearchTree tree = new BinarySearchTree();
        DSALinkedList order = null;
        String filename = "";

         // Clear terminal
         System.out.print("\033[H\033[2J");  
         System.out.flush(); 

        do
        {
            System.out.println("Choose an action to perform:\n");
            System.out.println("\n>1 Create a tree from scratch");
            System.out.println(">2 Read from csv file");
            System.out.println(">3 Read from a serialised file");
            System.out.println(">4 Write to a csv file");
            System.out.println(">5 Write a serialised file");
            System.out.println(">6 View the tree");
            System.out.println(">0 Exit");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    tree = createTree(sc);

                    System.out.println("Tree sucessfully created\n");
                break;

                case 2:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    System.out.println("Enter the name of the file to read from: ");
                    filename = sc.next();
                    filename += "";
                    tree = readFile(filename);
                    System.out.println("Tree sucessfully created from " + filename + "\n");
                break;

                case 3:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    tree = load("Tree.ser");
                    System.out.println("Tree successfully loaded from \"Tree.ser\"\n");
                break;

                case 4:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    order = chooseOrder(tree, sc);
                    System.out.println("Enter the name of the file to write to:");
                    filename = sc.next();
                    filename += "";
                    writeFile(order, filename);
                    System.out.println("Tree successfully saved to csv file called: \"" + filename + "\"\n");
                break;

                case 5:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    save(tree, "Tree.ser");
                    System.out.println("Tree successfully serialised to file called: \"Tree.ser\"\n");
                break;

                case 6:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    display(tree, sc);
                break;

                case 0:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 

                    System.out.println("Goodbye");
                    close = true;
                break;

                default:
                    // Clear terminal
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 
                    
                    System.out.println("Choice not stipulated\n");                
            }

        }while(!close);

        sc.close();
    }






   /*
    * Title:     createTree
    * Author:    Jacob Jonas, 18439731
    * Created:   01/09/2022
    * Modified:  01/09/2022
    * Import:    tree (BinarySearchTree)
    * Export:    newTree (BinarySearchTree)
    * Assertion: Create a new tree fro scratch
    */

    public static BinarySearchTree createTree(Scanner sc)
    {
        String key = "0";
        Object value;
        boolean close = false;
        BinarySearchTree newTree = new BinarySearchTree();

        // Clear terminal
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        do
        {
            System.out.println("Press 0 to stop or enter the key for the next node: ");
            key = sc.next();
        
            if(key.equals("0")) // User wants to exit
            {
                close = true;

                // Clear terminal
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            else
            { 
                System.out.println("Enter the value of: " + key);
                value = sc.next();
                newTree.insert(key,value);

                // Clear terminal
                System.out.print("\033[H\033[2J");  
                System.out.flush(); 

                // Notify user of what they added to the tree
                System.out.println("Node added with key: " + key + ", and value: " + value + "\n");
            }

        }while(!close);
        
        return newTree;
    }

    
    
    
    
    
       /*
        * Title:     display
        * Author:    Jacob Jonas, 18439731
        * Created:   01/09/2022
        * Modified:  01/09/2022
        * Import:    none
        * Export:    none
        * Assertion: Get user choice of how to order a BinarySearchTree
        */

        public static void display(BinarySearchTree tree, Scanner sc) 
        {
            int choice;
            boolean close = false;
            DSALinkedList order = null;

            // Clear terminal
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            
            do
            {
                System.out.println("In which order would you like to view the tree:");
                System.out.println(">1 pre order");
                System.out.println(">2 in order");
                System.out.println(">3 post order");
                System.out.println(">0 Return to previous menu");
                choice = sc.nextInt();

                switch(choice)
                {
                    case 1:
                        order = tree.preOrder();
                        // Clear terminal
                        System.out.print("\033[H\033[2J");  
                        System.out.flush(); 

                        for(Object o : order)
                        {
                            System.out.println(o);
                        }
                    break;

                    case 2:
                        order = tree.inOrder();

                        // Clear terminal
                        System.out.print("\033[H\033[2J");  
                        System.out.flush(); 

                        for(Object o : order)
                        {
                            System.out.println(o);
                        }
                    break;

                    case 3:
                        order = tree.postOrder();

                        // Clear terminal
                        System.out.print("\033[H\033[2J");  
                        System.out.flush(); 

                        for(Object o : order)
                        {
                            System.out.println(o);
                        }
                    break;

                    case 0:
                        close = true;
                    break;                        

                    default:
                        System.out.println("Choice not stipulated");
                } 
                System.out.println("");

            }while(!close);

        } // End display()

        
        
        
        
        
       /*
        * Title:     chooseOrder
        * Author:    Jacob Jonas, 18439731
        * Created:   01/09/2022
        * Modified:  01/09/2022
        * Import:    tree (BinarySearchTree)
        * Export:    order (DSALinkedList)
        * Assertion: Let the user choose which order will be printed to the file
        */

        public static DSALinkedList chooseOrder(BinarySearchTree tree, Scanner sc)
        {
            int choice;
            DSALinkedList order = null;

            System.out.println("In which order would you like to choose:");
            System.out.println(">1 pre order");
            System.out.println(">2 in order");
            System.out.println(">3 post order");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    order = tree.preOrder();
                break;

                case 2:
                    order = tree.inOrder();
                break;

                case 3:
                    order = tree.postOrder();
                break;

                default:
                    System.out.println("Error");
                    
            }

            return order;
        }

    
    
    
    
    
       /*
        * Title:     readFile
        * Author:    Jacob Jonas, 18439731
        * Created:   01/09/2022
        * Modified:  01/09/2022
        * Import:    filename (String)
        * Export:    none
        * Assertion: Read a csv file
        */
    
        public static BinarySearchTree readFile(String filename)
        {
            FileInputStream fileStream = null;
            InputStreamReader isr;
            BufferedReader bufRdr;
            int lineNum = 0;
            String line;
            BinarySearchTree tree = new BinarySearchTree();
    
            try
            {
                fileStream = new FileInputStream(filename);
                isr = new InputStreamReader(fileStream);
                bufRdr = new BufferedReader(isr);
    
                line = bufRdr.readLine();
                while(line != null)
                {
                    tree = parseLine(tree, line);
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

            return tree;
        } // End readFile()
    
    
    
    
    
       /*
        * Title:     parseLine
        * Author:    Jacob Jonas, 18439731
        * Created:   01/09/2022
        * Modified:  01/09/2022
        * Import:    csvRow (String)
        * Export:    none
        * Assertion: Parse a csv row
        */
    
        public static BinarySearchTree parseLine(BinarySearchTree tree, String csvRow)
        {
            String[] splitLine;
            splitLine = csvRow.split(",");

            tree.insert(splitLine[0],splitLine[1]);

            return tree;
        }

        
        
        
        
       /*
        * Title:      writeFile
        * Author:     Jacob Jonas, 18439731
        * Created:    01/09/2022
        * Modified:   01/09/2022
        * Import:     order (DSALinkedList), fliename (String)
        * Export:     none
        * Assertiorn: Write data to a csv file
        */
    
        public static void writeFile(DSALinkedList order, String filename)
        {
            FileOutputStream fileStream = null;
            PrintWriter pw;
    
            try
            {
                fileStream = new FileOutputStream(filename);
                pw = new PrintWriter(fileStream);
    
                for(Object o : order)
                {
                    String value = "" + o;
                    pw.write(value + ", ");
                    pw.write("\n");
                }
                
                pw.close();
            }
            catch(IOException e)
            {
                System.out.println("Error in writing to file");
            }
        } // End writeFile()







   /*
    * Title:     save
    * Author:    Jacob Jonas, 18439731
    * Created:   01/09/2022
    * Modified:  01/09/2022
    * Import:    theTree (BinarySeachTree), filename (String)
    * Export:    none
    * Assertion: Write a serialised file containing a BinarySearchTree
    */

    private static void save(BinarySearchTree theTree, String filename)
    {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;

        try 
        {
            fileStrm = new FileOutputStream(filename);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(theTree); // Write the object to the file
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
    * TItle:      load
    * Author:     Jacob Jonas, 18439731
    * Created:    01/09/2022
    * Modified:   01/09/2022
    * Import:     fliename (String)
    * Export:     inObj (BinarySearchTree)
    * Assertiorn: Load an object from a serialised file
    */

    public static BinarySearchTree load(String filename)
    {
        FileInputStream fileStream;
        ObjectInputStream objStream;
        BinarySearchTree inObj = new BinarySearchTree();

        try
        {
            fileStream = new FileInputStream(filename);
            objStream = new ObjectInputStream(fileStream);
            inObj = (BinarySearchTree)objStream.readObject(); // Typecast the object to BinarySearchTree
            objStream.close();
        }
        catch(ClassNotFoundException e)
        {
            System.out.print("Class BinarySearchTree not found" + e.getMessage());
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Unable to load object from file");
        }

        return inObj;
    } // End load()
    
}
