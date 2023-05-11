#include <stdio.h>
#include "linkedlist.h"
#include "structs.h"
#include "macros.h"
#include "map.h"
#include "movement.h"
#include "end.h"
#include "free.h"
#include "game.h"


/* 
 * Method:    game
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/10/2022
 * Import:    ms (MapState*)  
 * Export:    none 
 * Assertion: Everything related to the game during run-time 
 */

void game(MapState *ms)
{
    /* Allows distinction between whether the player has 
     * moved or only undid a previous move */
    int count = ms->playerMovement->count;

    /* Initialise all end conditions as false */
    ms->end->won = FALSE, ms->end->playerTrapped = FALSE, ms->end->goalBlocked = FALSE;

    printMap(ms);  /*print the map initially before the player has done anything */

    /* Keep playing the game while all win and lose conditions are false */
    while((!ms->end->won) && (!ms->end->playerTrapped) && (!ms->end->goalBlocked))
    { 
        /* Ensures that floors aren't collapsing unless the player actually moves. */
        validMove(ms);

        /* Ensures that floors aren't collapsing when the players chooses undo 
         * If the initial count value is less than the current count then that means
         * a new node was added to the list which means the player moved.
         * 
         * However if the player chose undo, then the initial count would not be 
         * larger than the current count, meaning a floor will not collapse */
        if(count < ms->playerMovement->count)
        {
            floorCollapse(ms); /* Create a collapsed floor */

            /* Player and goal lost conditions are separate 
             * to display a more useful message to the player */
            playerTrapped(ms);
            goalBlocked(ms);
        }

        /* Print map at the end of the loop after everything has been done, 
         * ready for the next iteration */
        printMap(ms);

        /* update the count value ready for the next iteration */
        count = ms->playerMovement->count;
    }  
    /* Once the game is completed, get a message describing the outcome */
    endMessage(ms->end);

    /* Call function to free all the structs malloc'd at the start of the program in setup() */
    freeStructs(ms);
}





/* 
 * Method:    endMessage
 * Author:    Jacob Jonas, 18439731
 * Created:   29/08/2022
 * Modified:  29/08/2022
 * Import:    won (int*), pLost (int*), gLost (int*)   
 * Export:    none
 * Assertion: Display a message to the player the end of the game describing the outcome
 */

void endMessage(EndConditions* end)
{
    if(end->won) /* If the player reached the goal */
    {
        printf("\nCongratulations, you reached the goal. You win!\n\n");
    }
    else if(end->playerTrapped) /* If the player was trapped */
    {
        printf("\nYou were trapped. You lost\n\n");
    } 
    else if(end->goalBlocked) /* If the goal was blocked */
    {
        printf("\nThe goal was blocked. You lost\n\n");
    }
}

