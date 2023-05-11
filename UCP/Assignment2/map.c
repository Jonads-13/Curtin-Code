/*
 * Title:     map
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Assertion: Everything related to the map and it's creation
 */

#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "structs.h"
#include "map.h"
#include "macros.h"
#include "random.h"
#include "color.h"


/* 
 * Method:    createMap
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  26/08/2022
 * Import:    mapVals (int*), playerVals (int*),   
 * Export:    none  
 * Assertion: Get everything ready to create the map for the game 
 */

char** createMap(char** map, int row, int col)
{
    int r, c; /* Variables to control 2-D array looping */

    /* Dynamically allocote 2-D array to hold the map */
    map = (char**)malloc(sizeof(char*) * (row));

    for(r = 0; r < row; r++)
    {
        map[r] = (char*)malloc(sizeof(char*) * (col));
    }

    /* Fill the top row with '*' characters */
    for(c = 0; c < col; c++)
    {
        map[0][c] = '*';
    }

    /* For each row of the map, excluding the top and bottom */
    for(r = 1; r < ((row) - 1); r++)
    {
        map[r][0] = '*'; /* Fill the first column with a '*' character */

        for(c = 1; c < ((col) - 1); c++)
        {
            map[r][c] = BLANK;  /* Fill all inbetween charcters with BLANK characters */
        }   
        
        map[r][(col)-1] = '*'; /* Fill the last column with a '*' character */
    } 

    /* Fill the bottom row with '*' characters */
    for(c = 0; c < col; c++)
    {
        map[(row)-1][c] = '*';
    }

    return map;
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

void printMap(MapState* ms)
{
    /* printed determines if any of the conditional prints triggered */
    int r, c, printed = FALSE;
    system("clear"); /* Always print the map in the top left corner */

    /* Loop over each row of the map */
    for(r = 0; r < ms->mv->row; r++)
    {
        /* Loop over each column */
        for(c = 0; c < ms->mv->col; c++)
        {
            printed = FALSE; /* Reinitialised every iteration */

            /* Spaces are used when printing each element to make 
             * the map look closer to its actual dimensions
             * If there were no spaces, square maps look like thin rectangles */

            if(ms->map[r][c] == 'P') /* Current element is the player */
            {
                /* Change character colour */
                setForeground("blue");
                printf("%c ", ms->map[r][c]);
                setForeground("reset");
                printed = TRUE;
            }

            if(ms->map[r][c] == 'G') /* Current element is the goal */
            {
                /* Change character colour */
                setForeground("yellow");
                printf("%c ", ms->map[r][c]);
                setForeground("reset");
                printed = TRUE;
            }

            if(ms->playerMovement->head->next != NULL)  /*Player has moved from their start location */
            {
                /* Current element matches the location of the most recent collapsed floor */
                if((((Recent*)(ms->playerMovement->head->data))->floorRow == r) && 
                   (((Recent*)(ms->playerMovement->head->data))->floorCol == c))
                {
                    /* change background colour */
                    setBackground("red");
                    putchar(ms->map[r][c]);
                    setBackground("reset");
                    /* Separate the character and space to make the background
                     * change only affect the character being printed */
                    putchar(BLANK); 
                    printed = TRUE;
                }
            }

            if(!printed) /* None of the above statements triggererd */
            {
                /* Print current element */
                printf("%c ", ms->map[r][c]);
            }
        }
        printf("\n"); /* Print each row on a new line */
    }

    /* Print movement options to the user */
    printf("\n");
    printf("w to move up\n");
    printf("s to move down\n");
    printf("a to move left\n");
    printf("d to move right\n");
    printf("u to undo previous move\n");
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

void floorCollapse(MapState *ms)
{
    /* Variables to hold values for the location of the collapsed floor */
    int tempRow, tempCol;

    /* initialise variables to a place where a '*' character is guaranteed to be */
    tempRow = 0, tempCol = 0;

    /* Keep creating values while the generated location is already populated */
    while(ms->map[tempRow][tempCol] != BLANK)
    {
        /* Get random numbers */
        tempRow = randomNum(0, (ms->mv->row) - 1);
        tempCol = randomNum(0, (ms->mv->col) - 1); 
    } 
    /* Place a collapsed floor at the generated location */
    ms->map[tempRow][tempCol] = 'X';

    /* Store the location data used for the background cahnge in printMap() */
    ((Recent*)(ms->playerMovement->head->data))->floorRow = tempRow;
    ((Recent*)(ms->playerMovement->head->data))->floorCol = tempCol;
}
