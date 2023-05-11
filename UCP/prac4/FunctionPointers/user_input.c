#include <stdio.h>

void readInts(int *ptr1, int *ptr2, int *ptr3, char *ptrC)
{
    printf("\nEnter the first of three integers:\n");
    scanf("%d", ptr1);

    printf("\nEnter the second integer:\n");
    scanf("%d", ptr2);

    printf("\nEnter the third integer:\n");
    scanf("%d", ptr3);

    printf("Enter either A or D:\n");
    scanf(" %c", ptrC);
}
    
