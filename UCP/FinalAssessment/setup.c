/*
 * Title:     setup
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  02/11/2022
 * Assertion: All functions related to the program before runtime
 */

#include <stdio.h>
#include <stdlib.h>
#include "setup.h"
#include "map.h"
#include "runtime.h"
#include "free.h"







/*
 * Title:     extract
 * Author:    Jacob Jonas, 18439731
 * Created:   06/09/2022
 * Modified:  31/10/2022
 * Import:    argv (char**)
 * Export:    none
 * Assertion: Extract the values from argv
 */

void extract(char** argv)
{
    /* Open file given in the command line for reading */
    FILE* fp = fopen(argv[1], "r");
    int tempMax, tempSleep;
    State* state;

    
    if(fp == NULL) /* File reading failed */
    {
        printf("\n");
        perror(argv[1]); /* Print out the error */
        printf("\n");
    }
    else /* File reading succeeded */
    {
        /* Extract remaining values from the command line */
        tempMax = atoi(argv[2]);
        tempSleep = atof(argv[3]);

        if((tempMax < 0) || (tempSleep < 0)) /* Either value is negative*/
        {
            printf("\nYou entered %d and %d", tempMax, tempSleep);
            printf("\nNeither of these values can be negative. Please enter positive numbers.\n\n");
        }
        else /* Both values are valid */
        {
            /* Allocate memory for main structs */
            state = (State*)malloc(sizeof(State));
            state->maps = (MapData*)malloc(sizeof(MapData));

            state->max = tempMax;
            state->sleep = tempSleep;

            setup(state, fp);
            fclose(fp);  /* close the file */
        }
    }
}






/* 
 * Method:    setup
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  01/11/2022
 * Import:    fp (FILE*), argv (char**)
 * Export:    none
 * Assertion: Get everything ready to begin the simulation
 */

void setup(State* state, FILE* fp)
{
    /* Used to hold values read from the file */
    int* tempRow = (int*)malloc(sizeof(int));
    int* tempCol = (int*)malloc(sizeof(int));
    char* tempChar = (char*)malloc(sizeof(char));
    
    /* Read data for map size */
    fscanf(fp, "%d %d", tempRow, tempCol);

    /* +2 to account for the border */
    state->maps->rows = *tempRow + 2;
    state->maps->cols = *tempCol + 2;

    /* Call function to allocate memory for the 2-D arrays */
    state->maps->display = createDisplay(state->maps->display, state->maps->rows, state->maps->cols);
    state->maps->colorState = createColorState(state->maps->colorState, state->maps->rows, state->maps->cols);

    /* Read data related to the Langton's Ant and initialise it */
    fscanf(fp, "%d %d %c", tempRow, tempCol, tempChar);
    state->langAnt = initAnt(state->langAnt, state->maps->display, tempRow, tempCol, tempChar);

    /* Read data related to the random ant  and initialise it */
    fscanf(fp, "%d %d %c", tempRow, tempCol, tempChar);
    state->randAnt = initAnt(state->randAnt, state->maps->display, tempRow, tempCol, tempChar);

    /* Read the data realted to the starting colours */
    readColorState(state, fp);

    /* Free temp pointers as they are only needed for the file reading */
    freeTempPointers(tempRow, tempCol, tempChar);

    begin(state); 
}






/*
 * Title:     initAnt
 * Author:    Jacob Jonas, 18439731
 * Created:   31/10/2022
 * Modified:  01/11/2022
 * Import:    amt (Properties*), display (char**), row (int*), col (int), dir (char*)
 * Export:    ant (Properties*)
 * Assertion: Malloc memory for an ant and return the pointer to that memory
 */

Ant* initAnt(Ant* ant, char** display, int* row, int* col, char* dir)
{
    ant = (Ant*)malloc(sizeof(Ant));

    /* plus 1 to account for the border */
    ant->row = *row + 1;
    ant->col = *col + 1;
    ant->direction = *dir;

    /* Place the ant at the specified location */
    display[ant->row][ant->col ] = ant->direction;

    return ant;
}







/*
 * Title:     readColorState
 * Author:    Jacob Jonas, 18439731
 * Created:   31/10/2022
 * Modified:  01/11/2022
 * Import:    state (State*), fp (FILE*)
 * Export:    none
 * Assertion: read the data from the file about the background colours
 */

void readColorState(State *state, FILE *fp)
{
    /* Cols is multiplied by 2 because the file line has spaces inbetween each number */
    int r, c, doubledCols = state->maps->cols << 1;

    /* Used to store the data read using fgets */
    char* fileString = (char*)calloc((doubledCols), sizeof(char));
    
    /* For some reason the first time I use fgets, fileString is empty
     * This call outside of anything is to consume that blank line
     * Otherwise all areas that should be green are pushed down by 1 
     */
    fgets(fileString, doubledCols, fp); 

    /* The amount of line in the file remaing should equal the amount of rows in the map */
    for(r = 1; r < state->maps->rows - 1; r++)
    {
        fgets(fileString, doubledCols, fp);

        /* increment by 2 to skip over the spaces in the file line */
        for(c = 0; c < doubledCols - 2; c += 2)
        {
            /* "(c >> 1) + 1" ensures that even though th index value is 
             * incremented by 2 each time, the colorState array 
             * is populated in increments of 1 starting with 1 
             * to skip the area that would be the border
             */
            state->maps->colorState[r][(c >> 1) + 1] = atoi(&fileString[c]);
        }
    }

    /* Free the string used in fgets() */
    free(fileString);
    fileString = NULL;
}
