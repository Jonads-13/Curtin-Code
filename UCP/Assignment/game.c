#include <stdio.h>
#include "map.h"
#include "movement.h"
#include "end.h"
#include "game.h"
#include "macros.h"


/* 
 * Method:    game
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Import:    map (char**), mapVals (int*), playerVals (int*), goalVals (int*)   
 * Export:    none 
 * Assertion: Everything related to the game during run-time 
 */

void game(char **map, int *mapVals, int *playerVals, int *goalVals)
{
    int won, *wonPtr, playerLost, *playerLostPtr, goalLost, *goalLostPtr;

    won = FALSE, playerLost = FALSE, goalLost = FALSE;
    wonPtr = &won, playerLostPtr = &playerLost, goalLostPtr = &goalLost;

    printMap(map, mapVals[0], mapVals[1]); /* print the map initially before the player has done anything */

    /* Keep playing the game while all win and lose conditions are false */
    while((!won) && (!playerLost) && (!goalLost))
    { 
        /* Ensures that floors aren't collapsing unless the player actually moves. */
        won = validMove(map, playerVals, mapVals);

        floorCollapse(map, mapVals); /* Create a collapsed floor */

        /* Player and goal lost conditions are separate 
         * to display a more useful message to the player */
        playerLost = playerTrapped(map, mapVals, playerVals, goalVals);
        goalLost = goalBlocked(map, mapVals, playerVals, goalVals);

        /* Print map at the end of the loop after everything has been done, ready for the next iteration */
        printMap(map, mapVals[0], mapVals[1]);
    }  
    /* Once the game is completed, get a message describing the outcome */
    endMessage(wonPtr, playerLostPtr, goalLostPtr);
}



