/*
 * Title:     map
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Assertion: Everything related to the map and it's creation
 */

#include <stdio.h>
#include <stdlib.h>
#include "map.h"
#include "game.h"
#include "macros.h"
#include "random.h"

/* 
 * Method:    createMap
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  26/08/2022
 * Import:    mapVals (int*), playerVals (int*),   
 * Export:    none  
 * Assertion: Get everything ready to create the map for the game 
 */

void createMap(int *mapVals, int *playerVals, int *goalVals)
{
    int r, c; /* Variables to control 2-D array looping */
    /* Dynamically allocote 2-D array to hold the map */
    char **map = (char**)malloc((mapVals[0]) * sizeof(char*));

    /* Dynamically allocate an array in each element of 2-D array */
    for(r = 0; r < mapVals[0]; r++)
    {
        map[r] = (char*)malloc((mapVals[1]) * sizeof(char*));
    }

    /* Fill the top row with '*' characters */
    for(c = 0; c < mapVals[1]; c++)
    {
        map[0][c] = '*';
    }

    /* For each row of the map, excluding the top and bottom */
    for(r = 1; r < (mapVals[0]) - 1; r++)
    {
        map[r][0] = '*'; /* Fill the first column with a '*' character */

        for(c = 1; c < (mapVals[1]) - 1; c++)
        {
            map[r][c] = BLANK;  /* Fill all inbetween charcters with BLANK characters */
        }   
        
        map[r][(mapVals[1])-1] = '*'; /* Fill the last column with a '*' character */
    } 

    /* Fill the bottom row with '*' characters */
    for(c = 0; c < mapVals[1]; c++)
    {
        map[(mapVals[0])-1][c] = '*';
    }

    /* Place the player and goal at the player's specified locations */
    map[playerVals[0]][playerVals[1]] = 'P';
    map[goalVals[0]][goalVals[1]] = 'G';

    /* Begin runtime of the game */
    game(map, mapVals, playerVals, goalVals);

    /* Free each sub array in reverse order */
    for (r = mapVals[0] - 1; r >= 0; r--) 
    {
        free(map[r]); /* Free the malloc'd sub array array */
        map[r] = NULL; /* Set the free'd array to NULL */
    } 

    free(map); /* free the main 2-D array */
    map = NULL; /* set the free'd array to null */
}






/* 
 * Method:    printMap
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    map (char**), row (int), col (int)
 * Export:    none 
 * Assertion: Print the map to the terminal 
 */

void printMap(char **map , int row, int col)
{
    int r, c;

    system("clear"); /* Always print the map in the top left corner */

    /* Loop over each row of the map */
    for(r = 0; r < row; r++)
    {
        /* Loop over each column */
        for(c = 0; c < col; c++)
        {
            /* Print current element */
            printf("%c ", map[r][c]);
        }
        printf("\n"); /* Print each row on a new line */
    }

    /* Print movement options to the user */
    printf("\nw to move up");
    printf("\ns to move down");
    printf("\na to move left");
    printf("\nd to move right\n");
}






/* 
 * Method:    floorCollapse
 * Author:    Jacob Jonas, 18439731
 * Created:   24/08/2022
 * Modified:  29/08/2022
 * Import:    map (char**), mv (int*)
 * Export:    none 
 * Assertion: Generate a location to place a "collapsed floor" 
 */

void floorCollapse(char **map, int *mv)
{
    /* Variables to hold values for the location of the collapsed floor */
    int floorRow, floorCol;

    /* initialise variables to a place where a '*' character is guaranteed to be */
    floorRow = 0, floorCol = 0;

    /* Keep creating values while the generated location is already populated */
    while(map[floorRow][floorCol] != ' ')
    {
        /* Get random numbers */
        floorRow = randomNum(0, mv[0] - 1);
        floorCol = randomNum(0, mv[1] - 1); 
    } 
    /* Place a collapsed floor at the generated location */
    map[floorRow][floorCol] = 'X';
}
