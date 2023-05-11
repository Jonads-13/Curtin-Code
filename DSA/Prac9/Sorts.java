import java.util.Random;
import java.util.Arrays;

/* * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Title:     Sorts                                  *
 * Authour:   Jacob Jonas, 18439731                  *
 * Created:   29/07/2022                             *
 * Modified:  09/08/2022                             *
 * Assertion: Implement various sorting algorithms   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * */

class Sorts
{

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    bubbleSort                                 *
 * Author:    Jacob Jonas, 18439731                      *
 * Created:   29/07/2022                                 *
 * Modified:  09/08/2022                                 *
 * Import:    pArray (Integer [])                        *
 * Export:    pArray (Integer [])                        *
 * Assertion: Sort an array using the bubble algorithm   *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void bubbleSort(int[] pArray)
    {
        int p = 0; // Variable to hold the number of passes
        boolean sorted = true; // Determines when the sort is finished

        do // Begin do-while loop
        {
            sorted = true; // Reinitialise to true at the beginning of each pass
            
            for(int i = 0; i < (pArray.length-1-p); i++)
            {
                // Compare the current element to the next element
                if(pArray[i] > pArray[i+1])
                {
                    // Swap the elements if the current element is bigger
                    int temp = pArray[i];
                    pArray[i] = pArray[i+1];
                    pArray[i+1] = temp;
                    sorted = false;
                }
            }
            p++; // Increase the pass count

        }while(!sorted); // End loop once sorted

    } // End bubbleSort()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    selectioneSort                               *
 * Author:    Jacob Jonas, 18439731                        *
 * Created:   29/07/2022                                   *
 * Modified:  09/08/2022                                   *
 * Import:    pArray (Integer [])                          *
 * Export:    pArray (Integer [])                          *
 * Assertion: Sort an array using the selection algorithm  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void selectionSort(int[] pArray)
    {
        for(int i = 0; i < pArray.length; i++)
        {
            int minIdx = i; // Minimun index is initialised as the current element

            for(int j = i + 1; j < pArray.length; j++)
            {
                // Compare the current element to the lowest index
                if(pArray[j] < pArray[minIdx])
                {
                    minIdx = j; // Minimum index is now the current element's index
                }
            }
            
            // Swap the current element with the element at the minimum index
            int temp = pArray[minIdx];
            pArray[minIdx] = pArray[i];
            pArray[i] = temp;
        }
    } // End selectionSort()

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Method:    insertionSort                                *
 * Author:    Jacob Jonas, 18439731                        *
 * Created:   29/07/2022                                   *
 * Modified:  09/08/2022                                   *
 * Import:    pArray (Integer [])                          *
 * Export:    pArray (Integer [])                          *
 * Assertion: Sort an array using the insertion algorithm  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public static void insertionSort(int[] pArray)
    {
        for(int n = 1; n < pArray.length; n++)
        {
            int i = n;

            // Compare the current element with the next element
            while((i > 0) && (pArray[i-1] > pArray[i]))
            {
                // Keep swapping the current element untill it's in it's final position
                int temp = pArray[i];
                pArray[i] = pArray[i-1];
                pArray[i-1] = temp;

                i--;
            }
        }

    } // End insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
        mergeSortRecurse(A, 0, (A.length)-1);
    }//mergeSort()

    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if(leftIdx < rightIdx)
        {
            int midIdx = (leftIdx + rightIdx)/2;

            mergeSortRecurse(A, leftIdx, midIdx);
            mergeSortRecurse(A, midIdx + 1, rightIdx);

            merge(A, leftIdx, midIdx, rightIdx);
        }
    }//mergeSortRecurse()

    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
        int[] temp = new int[rightIdx - leftIdx + 1];
        int i = leftIdx, j = midIdx + 1, k = 0;

        while((i <= midIdx) && (j <= rightIdx))
        {
            if(A[i] <= A[j])
            {
                temp[k] = A[i];
                i++;
            }
            else
            {
                temp[k] = A[j];
                j++;
            }
            k++;
        }

        for(int ii = i; ii <= midIdx; ii++)
        {
            temp[k] = A[ii];
            k++;
        }

        for(int jj = j; jj <= rightIdx; jj++)
        {
            temp[k] = A[jj];
            k++;
        }

        for(int kk = leftIdx; kk <= rightIdx; kk++)
        {
            A[kk] = temp[kk - leftIdx];
        }
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
        quickSortRecurse(A, 0, (A.length - 1));
    }//quickSort()

    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if(rightIdx > leftIdx)
        {
            int pivotIdx = leftIdx;
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, (newPivotIdx - 1));
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx);
        }
    }//quickSortRecurse()

    public static void quickSortMedian3(int[] A)
    {
        quickSortMedian3Recurse(A, 0, (A.length - 1));
    }//quickSortMedian3()

    private static void quickSortMedian3Recurse(int[] A, int leftIdx, int rightIdx)
    {
        if(rightIdx > leftIdx)
        {
            int pivotIdx = acsending3(A, leftIdx, (leftIdx+rightIdx)/2, rightIdx);
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortMedian3Recurse(A, leftIdx, (newPivotIdx - 1));
            quickSortMedian3Recurse(A, (newPivotIdx + 1), rightIdx);
        }
    }//quickSortMedian3Recurse()

    private static int acsending3(int[] A, int left, int mid, int right) 
    {
        int median = mid;

        if(A[left] > A[mid])
        {
            swap(A, left, mid);
            median = left;
        }
        
        if(A[mid] > A[right])
        {
            swap(A, mid, right);
            median = right;
        }

        if(A[left] > A[mid])
        {
            swap(A, left, mid);
            median = mid;
        }

        return median;
    }

    private static void swap(int[] A, int left, int right)
    {
        int temp;

        temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }

    public static void quickSortRandom(int[] A)
    {
        quickSortRandomRecurse(A, 0, (A.length - 1));
    }//quickSort()

    private static void quickSortRandomRecurse(int[] A, int leftIdx, int rightIdx)
    {
        Random rand = new Random();

        if(rightIdx > leftIdx)
        {
            int pivotIdx = (rand.nextInt(rightIdx) % (rightIdx - leftIdx)) + leftIdx;
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRandomRecurse(A, leftIdx, (newPivotIdx - 1));
            quickSortRandomRecurse(A, (newPivotIdx + 1), rightIdx);
        }
    }//quickSortRecurse()

    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
        int newPivotIdx = 0;

        int pivotVal = A[pivotIdx];
        A[pivotIdx] = A[rightIdx];
        A[rightIdx] = pivotVal;

        int curIdx = leftIdx;

        for(int i = leftIdx; i <= rightIdx - 1; i++)
        {
            if(A[i] < pivotVal)
            {
                int temp = A[i];
                A[i] = A[curIdx];
                A[curIdx] = temp;
                curIdx++;
            }
        }

        newPivotIdx = curIdx;
        A[rightIdx] = A[newPivotIdx];
        A[newPivotIdx] = pivotVal;

		return newPivotIdx;
    }//doPartitioning


    /* Following code sourced from: https://www.geeksforgeeks.org/shellsort/ */
    public static void shellSort(int arr[])
    {
        int n = arr.length;
 
        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1)
            {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = arr[i];
 
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
 
                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
            }
        }
    }

    /* Following code sourced from: https://www.geeksforgeeks.org/counting-sort/ */
    public static void countingSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
  
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
  
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
  
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }


    /* Following code sourced from: https://www.geeksforgeeks.org/radix-sort/ */
    public static void radix(int[] arr) 
    {
        radixSort(arr, arr.length);    
    }
    // The main function to that sorts arr[] of
    // size n using Radix Sort
    private static void radixSort(int arr[], int n)
    {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);
 
        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    private static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
 
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    private static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
 
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;
 
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
 
        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

}//end Sorts calss
