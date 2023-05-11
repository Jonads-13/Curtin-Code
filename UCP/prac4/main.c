#include <stdio.h>
#include "arrays.h"
#include <string.h>


int main(int argc, char **argv)
{
    int array[4], ans;
    ans = 0;

    if(argc < 3)
    {
        printf("Error. Need more than 2 command line arguments");
    }
    else
    {
        stringConversion(argv, array, argc);

        if(strcmp(argv[1],"sum") == 0)
        {
            ans = sum(array);
            printf("Sum = %d\n",ans);
        }
        else if(strcmp(argv[1], "max") == 0)
        {
            ans = max(array);
            printf("max index = %d\n",ans);
        }
        else if(strcmp(argv[1],"reverse") == 0)
        {
            reverse(array);
            printArray(array);
        }
    }
        
    return 0;

} 
