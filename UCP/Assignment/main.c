#include <stdio.h>
#include "random.h"
#include "setup.h"

int main(int argc, char **argv)
{
    /* First and only call to method provided by Antoni */
    initRandom();

    if(argc != 7) /* Ensure the amount of command line arguments are correct */ 
    {
        /* Notify the user the necessary arguments if they make a mistake */
        printf("\nUsage: ./escape <map_row> <map_col> <player_row> <player_col> <goal_row> <goal_col>\n\n");

        /* More explanation of what each field means */
        printf("\n<map_row> is the amount of rows you want the map to contain. Range: 5 - 25 inclusive\n");
        printf("\n<map_col> is the amount of columns you want the map to contain. Range: 5 - 25 inclusive\n");
        printf("\n<player_row> is the row location of where you want to start from. Note * 0-based indexing\n");
        printf("\n<player_col> is the column location of where you want to start from. Note * 0-based indexing\n");
        printf("\n<goal_row> is the row location of where you want the goal located. Note * 0-based indexing\n");
        printf("\n<goal_col> is the column location of where you want the goal located. Note * 0-based indexing\n");

        /* Explain 0-based indexing */
        printf("\n\n\n* 0-based indexing means that the top left most location is indexed as (0, 0)\n");
        printf("\nThe bottom right-most location is (map_row-1, map_col-1)\n");
        printf("\neg. (10, 10) would not be valid location for a map of size 10 x 10.");
        printf("\n    (9, 9) is the furthest down and furthest right you could be\n\n");
    }
    else
    {
        /* Send command arguments to method that readies everything for the map creation */
        extract(argv);
    }

    return 0;
}
