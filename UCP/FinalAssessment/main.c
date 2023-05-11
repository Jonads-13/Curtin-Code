#include <stdio.h>
#include "random.h"
#include "setup.h"

int main(int argc, char** argv)
{
    /* First and only call to method provided by Antoni */
    initRandom();

    if(argc != 4) /* Ensure the amount of command line arguments are correct */ 
    {
        /* Notify the user the necessary arguments if they make a mistake */
        printf("\nUsage: ./ant <file_name> <steps_amount> <sleep_length>\n\n");
    }
    else
    {
        /* Send command arguments to method that readies everything for the map creation */
        extract(argv);
    }

    return 0;
}
