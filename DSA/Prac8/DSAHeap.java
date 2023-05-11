/*
 * Title:     DSAHeap
 * Author:    Jacob Jonas, 18439731
 * Created:   27/09/2022
 * Modified:  27/09/2022
 * Assertion: Personal implementaion of a heap object
 */

public class DSAHeap 
{
    private DSAHeapEntry[] heap;
    private int count;

    public DSAHeap(int n)
    {
        heap = new DSAHeapEntry[n];
        count = 0;

        initialise();
    }

    
    
    
    
    
    /*
     * Title:     getCount
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    count (int)
     * Assertion: retunr the count value
     */
    
    public int getCount() 
    {
        return count;    
    }

    
    
    
    
    
    /*
     * Title:     initialise
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    none
     * Assertion: populate heap with DSAHeapEntrys
     */
    
    private void initialise() 
    {
        for(int i = 0; i < heap.length; i++)
        {
            heap[i] = new DSAHeapEntry();
        }    
    }

    
    
    
    
    
    /*
     * Title:     add
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    priority (int), value (Object)
     * Export:    none
     * Assertion: add an entry to the heap
     */
    
    public void add(int pPriority, Object pValue) 
    {       
        heap[count].setPriority(pPriority);
        heap[count].setValue(pValue);

        trickleUp(count);
        count++;
    }

    
    
    
    
    
    /*
     * Title:     trickleUp
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    curIdx (int)
     * Export:    none
     * Assertion: find the correct position for the newly added entry
     */
    
    private void trickleUp(int curIdx) 
    {
        int parentIdx = (curIdx - 1)/2;

        if(curIdx > 0)
        {
            if(heap[curIdx].getPriority() > heap[parentIdx].getPriority())
            {
                swap(curIdx, parentIdx);
                trickleUp(parentIdx);
            }
        }
    }

    
    
    
    
    
    /*
     * Title:     remove
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    (DSAHeapEntry)
     * Assertion: remove the highest priority entry from the heap
     */
    
    public DSAHeapEntry remove() 
    {
        DSAHeapEntry ret = heap[0];

        swap(0, count-1);

        count--;
        trickleDown(0);
        
        return ret;
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
    
    private void trickleDown(int curIdx) 
    {
        int lChildIdx = (curIdx * 2) + 1;
        int rChildIdx = (curIdx * 2) + 2; 
        int largeIdx;

        if(lChildIdx < count)
        {
            largeIdx = lChildIdx;

                if(rChildIdx < count)
                {   
                    if(heap[lChildIdx].getPriority() < heap[rChildIdx].getPriority())
                    {
                        largeIdx = rChildIdx;
                    }
                }
                if(heap[curIdx].getPriority() < heap[largeIdx].getPriority())
                {
                    swap(curIdx, largeIdx);
                    trickleDown(largeIdx);
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
    
    private void swap(int curIdx, int parentIdx) 
    {
        DSAHeapEntry temp = heap[parentIdx];
        heap[parentIdx] = heap[curIdx];
        heap[curIdx] = temp;   
    }

    
    
    
    
    
    /*
     * Title:     print
     * Author:    Jacob Jonas, 18439731
     * Created:   27/09/2022
     * Modified:  27/09/2022
     * Import:    none
     * Export:    none
     * Assertion: print the contents of the heap
     */
    
    public void print() 
    {
        for(int i = 0; i < count; i++)
        {
            System.out.println(heap[i]);
        }    
    }
}
