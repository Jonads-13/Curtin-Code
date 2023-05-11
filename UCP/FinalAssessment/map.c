/*
 * Title:     map
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Assertion: Everything related to the maps and their creation
 */

#include <stdio.h>
#include <stdlib.h>
#include "map.h"
#include "macros.h"
#include "color.h"







/* 
 * Method:    createDisplay
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  26/08/2022
 * Import:    mapVals (int*), playerVals (int*),   
 * Export:    none  
 * Assertion: Create the display map 
 */

char** createDisplay(char** map, int row, int col)
{
    int r, c; /* Variables to control 2-D array looping */

    /* Dynamically allocote 2-D array to hold the map */
    map = (char**)malloc(sizeof(char*) * (row));

    for(r = 0; r < row; r++)
    {
        map[r] = (char*)malloc(sizeof(char) * (col));
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
 * Title:     creatColorState
 * Author:    Jacob Jonas, 18439731
 * Created:   31/10/2022
 * Modified:  31/10/2022
 * Import:    colorState (int**), row (int), col (int)
 * Export:    colorState (int**)
 * Assertion: create a 2-D int array to hold the color state information
 */

int** createColorState(int** colorState, int row, int col)
{
    int r;
    /* Dynamically allocote 2-D array to hold the color state information */
    colorState = (int**)calloc(row, sizeof(int*));

    for(r = 0; r < row; r++)
    {
        /* calloc each sub array */
        colorState[r] = (int*)calloc(col, sizeof(int));
    }

    return colorState;
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

void printMap(State* state)
{
    int r,c; /* used for array indexing */

    system("clear");  /* Always print the map in the top left corner */

    for (r = 0; r < state->maps->rows; r++)
    {
        for(c = 0; c < state->maps->cols; c++)
        {
            /* Determine what the background colour of each element should be
             * by checking the correspoiding value in the colorState array
             */

            if(state->maps->colorState[r][c] == DEFAULT)
            {
                putchar(state->maps->display[r][c]);
            }
            else if(state->maps->colorState[r][c] == GREEN)
            {
                setBackground("green");
                putchar(state->maps->display[r][c]);
                setBackground("reset");
            }
            else if(state->maps->colorState[r][c] == RED)
            {
                setBackground("red");
                putchar(state->maps->display[r][c]);
                setBackground("reset");
            }
            else if(state->maps->colorState[r][c] == BLUE)
            {
                setBackground("blue");
                putchar(state->maps->display[r][c]);
                setBackground("reset");
            }
        }
        /* Print each row on a new line */
        printf("\n");
    } 
}

