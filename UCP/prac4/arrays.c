#include <stdio.h>
#include "arrays.h"
#include <stdlib.h>

/*int main(int argc, char **argv)
{

    int array[LENGTH], i, ans, maxIdx, intArray[NEWLENGTH];
    char stringArray[4][4] = {"7", "1", "14", "-5"};

    for(i = 0; i < LENGTH; i++)
    {
        array[i] = i*3;
    }

    ans = sum(array);

    maxIdx = max(array);

    reverse(array);

    stringConversion(stringArray, intArray, NEWLENGTH);

    printf("Sum = %d\n",ans);
    
    printf("Max index is : %d\n",maxIdx);

    for(i = 0; i < LENGTH; i++)
    {
        printf("%d  %d\n", i, array[i]);
    }

    maxIdx = max(array);

    printf("After reverse, max index is now: %d\n",maxIdx);  

    for(i = 0; i < NEWLENGTH; i++)
    {
        ans += intArray[i];
        printf("%d\n",ans);
    }  

    printArray(array);
    
    return 0;

} End main() */

int sum(int array[])
{
    int i, sum;
    sum = 0;

    for(i = 0; i < 3; i++)
    {
        sum += array[i];
    }

    return sum;
}

int max(int array[])
{
    int i, maxIdx;
    maxIdx = 0;

    for(i = 1; i < LENGTH; i++)
    {
        if(array[i] > array[maxIdx])
        {
            maxIdx = i;
        }
    }

    return maxIdx;
}

void reverse(int array[])
{
    int i, head, tail, temp;
    head = 0;
    tail = LENGTH-1;

    for(i = 0; i < LENGTH/2; i++)
    {
        temp = array[head];
        array[head] = array[tail];
        array[tail] = temp;
        head++;
        tail--;
    }
}

void stringConversion(char **stringArray, int array[], int length)
{
    int i;
    for(i = 2; i < length; i++)
    {
        array [i-2] = atol(stringArray[i]);
    }
}

void printArray(int array[])
{
    int i;

    printf("{%d, ",array[0]);
    for(i = 1; i < LENGTH-1; i++)
    {
        printf("%d, ",array[i]);
    }
    printf("%d}\n",array[LENGTH-1]);
}
