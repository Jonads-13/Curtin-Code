import java.util.*;

public class quixkSorttest 
{
    public static void main(String[] args)
    {
        int[] A = {1,8,4,3,9,7,3,1};

        quickSort(A);

        System.out.println(Arrays.toString(A));

    }

    public static void quickSort(int[] A)
    {
        quickSortRecurse(A, 0, (A.length - 1), 0);
    }//quickSort()

    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx, int rLvl)
    {
        System.out.println(leftIdx + " " + rightIdx + " depth:" + rLvl);

        if(rightIdx > leftIdx)
        {
            System.out.println("Inside if: " + rLvl);
            int pivotIdx = rightIdx;
            System.out.println("before partitioning: " + leftIdx + " " + rightIdx + " " + pivotIdx + " depth:" + rLvl);
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

            quickSortRecurse(A, leftIdx, (newPivotIdx - 1), rLvl + 1);
            quickSortRecurse(A, (newPivotIdx + 1), rightIdx, rLvl + 1);
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

    
}
