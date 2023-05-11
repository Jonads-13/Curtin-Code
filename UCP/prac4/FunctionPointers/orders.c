#include <stdio.h>
#include "orders.h"

FUNCPTR orders(char choice)
{
    void (*funcPtr)(int*,int*,int*);

    if((choice == 'A') || (choice == 'a'))
    {
        funcPtr = &ascending3;    
    }
    else if((choice == 'D') || (choice == 'd'))
    {
        funcPtr = &descending3;
    }
    else
    {
        funcPtr = NULL;
    }

    return funcPtr;
}

void ascending2(int *ptr1, int *ptr2)
{
    int temp = 0;

    if(*ptr1 > *ptr2)
    {
        temp = *ptr1;
        *ptr1 = *ptr2;
        *ptr2 = temp;
    }
}

void ascending3(int *ptr1, int *ptr2, int *ptr3)
{
    ascending2(ptr1,ptr2);
    ascending2(ptr2,ptr3);
    ascending2(ptr1,ptr2);

}

void descending2(int *ptr1, int *ptr2)
{
    int temp = 0;

    if(*ptr1 < *ptr2)
    {
        temp = *ptr1;
        *ptr1 = *ptr2;
        *ptr2 = temp;
    }
}

void descending3(int *ptr1, int *ptr2, int *ptr3)
{
    descending2(ptr1,ptr2);
    descending2(ptr2,ptr3);
    descending2(ptr1,ptr2);
}


