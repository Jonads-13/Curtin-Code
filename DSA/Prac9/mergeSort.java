import java.util.Arrays;

public class mergeSort {
    public static void main(String[] args)
    {
        int [] A = {1,8,4,7,9,7,3,1};

        merge(A);

        System.out.println(Arrays.toString(A));
    }

    public static void merge(int[] A)
    {
        mergeSortRecurse(A, 0, (A.length)-1, 0);
    }//mergeSort()

    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx, int rLvl)
    {
        System.out.println(leftIdx + " " + rightIdx + " depth:" + rLvl);

        if(leftIdx < rightIdx)
        {
            System.out.println("Inside if statement " + rLvl);
            int midIdx = (leftIdx + rightIdx)/2;

            mergeSortRecurse(A, leftIdx, midIdx, rLvl + 1);
            mergeSortRecurse(A, midIdx + 1, rightIdx, rLvl + 1);

            System.out.println("Before merge: " + leftIdx + " " + midIdx + " " + rightIdx + " depth:" + rLvl);
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
}
