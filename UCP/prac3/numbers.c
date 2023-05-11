#include <stdio.h>
#include <stdlib.h>
#include "orders.h"
#include "user_input.h"

int main(void)
{
    int a, b, c, *ptr1, *ptr2, *ptr3;
    char choice, *ptrC;
    
    ptr1 = &a;
    ptr2 = &b;
    ptr3 = &c;
    ptrC = &choice;

    readInts(ptr1,ptr2,ptr3,ptrC);

    if((choice == 'A') || (choice == 'a'))
    {   
        ascending3(ptr1, ptr2, ptr3);
    }
    else if((choice == 'D') || (choice == 'd'))
    {
        descending3(ptr1,ptr2,ptr3);
    }

    printf("\n%d\n%d\n%d\n",a,b,c);

    return 0;
}
