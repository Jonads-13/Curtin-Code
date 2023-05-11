#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv)
{
    int *ptr1 = (int*)malloc(sizeof(int));
    int *ptr2 = ptr1;

    *ptr1 = 5;

    printf("%d %d\n", *ptr1, *ptr2);

    free(ptr1);
    ptr1 = NULL;

    *ptr2 = 8;

    printf("%d\n", *ptr2);

    return 0;
}
