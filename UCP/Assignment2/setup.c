#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "structs.h"
#include "setup.h"
#include "map.h"
#include "game.h"







/*
 * Title:     extract
 * Author:    Jacob Jonas, 18439731
 * Created:   06/09/2022
 * Modified:  08/10/2022
 * Import:    argv (char**)
 * Export:    none
 * Assertion: Extract the values from argv
 */

void extract(char** argv)
{
    /* Open file given in the command line for reading */
    FILE *fp = fopen(argv[1], "r");

    /* File reading failed */
    if(fp == NULL)
    {
        perror(argv[1]);
    }
    else
    {
        setup(fp);
        fclose(fp);  /* close the file */
    }
}






/* 
 * Method:    setup
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  08/10/2022
 * Import:    fp (FILE*)
 * Export:    none
 * Assertion: Get everything ready to create the map for the game 
 */

void setup(FILE* fp)
{
    int nReads = 0, tempRow, tempCol;
    char tempChar;

    /* Allocate memory for main struct */
    MapState *ms = (MapState*)malloc(sizeof(MapState));


    /* Allocate memory for all sub structs */


    /* Holds information on map dimensions */
    ms->mv = (MapValues*)malloc(sizeof(MapValues));

    /* Holds information of the goal location */
    ms->gv = (GoalValues*)malloc(sizeof(GoalValues));

    /* Holds the values that determine won and lost conditions */
    ms->end = (EndConditions*)malloc(sizeof(EndConditions));

    /* Allocate memory for linkedlist */
    ms->playerMovement = (List*)malloc(sizeof(List));
    ms->playerMovement->head = NULL;
    ms->playerMovement->count = 0;

    /* Read in the values for the map size */
    nReads = fscanf(fp, "%d %d", &tempRow, &tempCol);

    /* +2 to account for the border */
    ms->mv->row = tempRow + 2;
    ms->mv->col = tempCol + 2;

    ms->map = createMap(ms->map, ms->mv->row, ms->mv->col);

    /* Attempt to read the second line of the file */
    nReads = fscanf(fp, "%d %d %c", &tempRow, &tempCol, &tempChar);

    while(nReads == 3) /* file follows proper format */
    {
        if(tempChar == 'P') /* Player location has been specified */
        {
            /* Insert a node to hold the initial information */
            insertFirst(ms->playerMovement);

            /* Malloc struct to hold the data in the list node */
            ms->playerMovement->head->data = (Recent*)malloc(sizeof(Recent));

            /* Store the data, +1 to account for the border */
            ((Recent*)(ms->playerMovement->head->data))->playerRow = tempRow + 1;
            ((Recent*)(ms->playerMovement->head->data))->playerCol = tempCol + 1;

            /* Place the okayer at the specified location */
            ms->map[tempRow + 1][tempCol + 1] = 'P';
        }
        else if(tempChar == 'G') /* Goal location has been specified */
        {
            /* Store the information, +1 to account fo the border */
            ms->gv->row = tempRow + 1;
            ms->gv->col = tempCol + 1;

            /* Place the goal at the specified location */
            ms->map[ms->gv->row][ms->gv->col] = 'G';
        }
        else /* Collapsed floor location */
        {
            /* Place the collapsed floor at the specifed location */
            /* +1 to account for the border */
            ms->map[tempRow + 1][tempCol + 1] = tempChar;
        }

        /* Read the next line of the file */
        nReads = fscanf(fp, "%d %d %c", &tempRow, &tempCol, &tempChar);
    }

    /* Begin the game */
    game(ms);
}
