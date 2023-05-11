import java.util.Arrays;

public class set 
{

    public static void main(String[] args)
    {
        int[] arr = {5,7,2,2,6,8,3,5,6,1};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr) 
    {
        int n = arr.length;
        boolean swapped = true;
        while(swapped)
        {
            swapped = false;
            for(int i = 1; i < n-1; i++)
            {
                if(arr[i-1] > arr[i])
                {
                    swap(arr,i-1,i);
                    swapped = true;
                }
            }
            n = n-1;
        }

        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + ", ");
        }
    }

    public static void swap(int[] arr, int a, int b) 
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;    
    }
}
