import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 * Title:     UnitTestHeapSort
 * Author:    Jacob Jonas, 18439731
 * Created:   27/09/2022
 * Modified:  27/09/2022
 * Assertion: Implement a heap sort algorithm
 */

public class UnitTestHeapSort 
{
    public static void main(String[] args)
    {
        DSAHeapEntry[] arr = new DSAHeapEntry[7000];

        readFile("RandomNames7000.csv", arr);

        heapSort(arr, arr.length);

        writeFile("SortedHeap.csv", arr);
    }


    
    
    
    
    
    /*
     * Title:     heapSort
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    array (DSAHeapEntry[]), numItems (int)
     * Export:    none
     * Assertion: convert an array into a heap
     */
    
    
    public static void heapSort(DSAHeapEntry[] array, int numItems)
    {
        heapify(array, array.length);

        for(int i = numItems - 1; i > 0; i--)
        {
            swap(array, 0, i);
            trickleDown(array, 0, i);
        }
    }

    
    
    
    
    
    /*
     * Title:     heapify
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    heap (DSAHeapEntry[]), numItems (int)
     * Export:    none
     * Assertion: convert an array into a heap
     */
    
    public static void heapify(DSAHeapEntry[] heap, int numItems)
    {
        for(int i = (numItems/2)-1; i >= 0; i--)
        {
            trickleDown(heap, i, numItems);
        }
    }







    /*
     * Title:     trickleDown
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    curIdx (int)
     * Export:    none
     * Assertion: Move the new root down to its rightful place in the heap
     */
    
    private static void trickleDown(DSAHeapEntry[] heap, int curIdx, int numItems) 
    {
        int lChildIdx = (curIdx * 2) + 1;
        int rChildIdx = (curIdx * 2) + 2; 
        int largeIdx;

        if(lChildIdx < numItems)
        {
            largeIdx = lChildIdx;

                if(rChildIdx < numItems)
                {   
                    if(heap[lChildIdx].getPriority() < heap[rChildIdx].getPriority())
                    {
                        largeIdx = rChildIdx;
                    }
                }
                if(heap[curIdx].getPriority() < heap[largeIdx].getPriority())
                {
                    swap(heap, curIdx, largeIdx);
                    trickleDown(heap, largeIdx, numItems);
                }
        }
    }






    /*
     * Title:     swap
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    curIdx (int), parentIdx (int)
     * Export:    NONE
     * Assertion: swao two elements in the heap
     */
    
    private static void swap(DSAHeapEntry[] heap, int curIdx, int parentIdx) 
    {
        DSAHeapEntry temp = heap[parentIdx];
        heap[parentIdx] = heap[curIdx];
        heap[curIdx] = temp;   
    }

    
    
    
    
    
    /*
     * Title:     readFile
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    filename (String), arr (DSAheapEntry[])
     * Export:    none
     * Assertion: Read a csv file and create a DSAHeapEntry array from the data
     */
    
    public static void readFile(String filename, DSAHeapEntry[] arr)
    {
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        int lineNum = 0;
        String line;
    
        try
        {
            fileStream = new FileInputStream(filename);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
    
            line = bufRdr.readLine();
            while(line != null)
            {
                parseLine(line, arr, lineNum);
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
            System.out.println("Error in reading file " + filename);
        }
    } // End readFile()
    
    
    
    
    
    /*
     * Title:     parseLine
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    csvRow (String), arr (DSAHeapEntry[]), num (int)
     * Export:    none
     * Assertion: Parse a csv row
     */
    
    public static void parseLine(String csvRow, DSAHeapEntry[] arr, int num)
    {
        String[] splitLine;
        splitLine = csvRow.split(",");

        int p = Integer.parseInt(splitLine[0]);

        DSAHeapEntry temp = new DSAHeapEntry(p, splitLine[1]);
        arr[num] = temp;
    }

    
    
    
    
    /*
     * Title:      writeFile
     * Author:     Jacob Jonas, 18439731
     * Created:    27/09/2022
     * Modified:   27/09/2022
     * Import:     fliename (String), arr (DSAHeapEntry[])
     * Export:     none
     * Assertiorn: Write data to a csv file
     */
    
    public static void writeFile(String filename, DSAHeapEntry[] arr)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;
    
        try
        {
            fileStream = new FileOutputStream(filename);
            pw = new PrintWriter(fileStream);
            
            for(int i = 0; i < arr.length; i++)
            {
                pw.write(arr[i] + "\n");
            }
            
            pw.close();
        }
        catch(IOException e)
        {
                System.out.println("Error in writing to file");
        }
    } // End writeFile()
}
