/*
 * Title:     movement
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Assertion: All movement related functions for the game
 */

#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "structs.h"
#include "macros.h"
#include "map.h"
#include "movement.h"
#include "terminal.h"




/*
 * Title:     validMove
 * Author:    Jacob Jonas, 18439731
 * Created:   06/09/2022
 * Modified:  06/09/2022
 * Import:    ms (MapState*)
 * Export:    won (int)
 * Assertion: Determine if a players move is valid
 */

void validMove(MapState *ms)
{
    int playerRowTemp, playerColTemp;
    DirectionPTR dPtr;

    /* Variables to check if the player has actually moved */
    playerRowTemp = ((Recent*)(ms->playerMovement->head->data))->playerRow;
    playerColTemp = ((Recent*)(ms->playerMovement->head->data))->playerCol;

    while((playerRowTemp == ((Recent*)(ms->playerMovement->head->data))->playerRow) && 
          (playerColTemp == ((Recent*)(ms->playerMovement->head->data))->playerCol))
    {
        /* get a pointer to a function that will perform the movement of the player */
        dPtr = movePlayer(ms);

        /* Move the player s */
        (dPtr)(ms);
    }
    
}






/* 
 * Method:    movePlayer
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  09/10/2022
 * Import:    ms (MapState*) 
 * Export:    ptr (DirectionPTR)   
 * Assertion: Return a pointer to a function that will carry out the chosen direction
 */

DirectionPTR movePlayer(MapState *ms)
{
    int close = FALSE;
    char *direction = (char*)malloc(sizeof(char));

    /* Declare function pointer */
    DirectionPTR ptr;
    ptr = NULL;

    do
    {
        disableBuffer(); /* Allow instantaneous input without "enter" key */
        scanf(" %c", direction); /* Get user's desired direction */
        enableBuffer(); /* Fix the terminal */

        /* Depending on player input ,
        * Return a pointer to the corresponding function */
        switch(*direction)
        {
            case 'w': 
                ptr = &moveUp; 
                close = TRUE;
            break;

            case 's':
                ptr = &moveDown;
                close = TRUE;
            break;

            case 'a':
                ptr = &moveLeft;
                close = TRUE;
            break;

            case 'd':
                ptr = &moveRight;
                close = TRUE;
            break;

            case 'u':
                ptr = &undo;
                close = TRUE;
            break;

            default:
                printMap(ms);
                printf("Invalid key\n");
        }
    }while(!close);

    free(direction);
    direction = NULL;

    return ptr; /* return function pointer */
}






/* 
 * Method:    moveUp
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    ms->map (char**), mv (int*), pv (int*), gv (int*)
 * Export:    won (int)    
 * Assertion: Move the player up and return whether they won
 */

void moveUp(MapState* ms)
{
    int pr = ((Recent*)ms->playerMovement->head->data)->playerRow;
    int pc = ((Recent*)ms->playerMovement->head->data)->playerCol;

    if(ms->map[pr - 1][pc] == 'G') /* If player is moving onto the goal */ 
    {
        ms->map[pr - 1][pc] = 'P'; /* Place the player at the goal location */
        ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

        pr = pr - 1; /* Update the player's location */
        ms->end->won = TRUE; /* The player has now won the game */
    }
    
    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((ms->map[pr - 1][pc] == 'X') || (ms->map[pr - 1][pc] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(ms->map[pr - 1][pc] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((ms->map[pr - 1][pc] == '*') && (ms->map[ms->mv->row - 2][pc] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((ms->map[pr - 1][pc] == '*') && (ms->map[ms->mv->row - 2][pc] != 'X'))
        {
            if(ms->map[ms->mv->row - 2][pc] == 'G') /* If the player is moving onto the goal */
            {
                ms->map[ms->mv->row - 2][pc] = 'P'; /* Place the player at the goal location */
                ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

                pr = ms->mv->row - 2; /* Update the player's location */
                ms->end->won = TRUE; /* The player has now won the game */
            }
            else
            {
                ms->map[ms->mv->row - 2][pc] = 'P'; /* Move the player to the free element */
                ms->map[pr][pc] = BLANK; /* Place a BLANK space where the player was */

                pr = ms->mv->row - 2; /* Update player location */
            }
        }
    #endif

    else
    { 
        ms->map[pr - 1][pc] = 'P'; /* Move the player to the free element */
        ms->map[pr][pc] = BLANK; /* Place a blank space where the player was */

        pr = pr - 1; /* Update player location */
    }

    /* The player has moved */
    if(pr != ((Recent*)ms->playerMovement->head->data)->playerRow)
    {
        /* Add a new list node */
        insertFirst(ms->playerMovement);
        ms->playerMovement->head->data = (Recent*)malloc(sizeof(Recent));

        /* Place the new location into the list node */
        ((Recent*)ms->playerMovement->head->data)->playerRow = pr;
        ((Recent*)ms->playerMovement->head->data)->playerCol = pc;
    }
}





/* 
 * Method:    moveDown
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    ms->map (char**), mv (int*), pv (int*), gv (int*)   
 * Export:    won (int)   
 * Assertion: Move the player down and return whether they won
 */

void moveDown(MapState* ms)
{
    int pr = ((Recent*)ms->playerMovement->head->data)->playerRow;
    int pc = ((Recent*)ms->playerMovement->head->data)->playerCol;
  
    if(ms->map[pr + 1][pc] == 'G') /* If player is moving onto the goal */
    {
        ms->map[pr + 1][pc] = 'P'; /* Place the player at the goal location */
        ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

        pr = pr + 1; /* Update the player's location */
        ms->end->won = TRUE; /* The player has now won the game */
    }

    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((ms->map[pr + 1][pc] == 'X') || (ms->map[pr + 1][pc] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(ms->map[pr + 1][pc] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((ms->map[pr + 1][pc] == '*') && (ms->map[1][pc] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((ms->map[pr + 1][pc] == '*') && (ms->map[1][pc] != 'X'))
        {
            if(ms->map[1][pc] == 'G') /* If the player is moving onto the goal */
            {
                ms->map[1][pc] = 'P'; /* Place the player at the goal location */
                ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

                pr = 1; /* Update the player's location */
                ms->end->won = TRUE; /* Player has now won the game */
            }
            else
            {
                ms->map[1][pc] = 'P'; /* Move the player to the free element */
                ms->map[pr][pc] = BLANK; /* Place a BLANK space where the player was */

                pr = 1; /* Update player's location */
            }
        }
    #endif

    else
    {
        ms->map[pr + 1][pc] = 'P'; /* Move the player to the free element */
        ms->map[pr][pc] = BLANK; /* Place a BLANK space where the player was */

        pr = pr + 1; /* Update the player's location */
    }

    /* The player has moved */
    if(pr != ((Recent*)ms->playerMovement->head->data)->playerRow)
    {
        /* Add a new list node */
        insertFirst(ms->playerMovement);
        ms->playerMovement->head->data = (Recent*)malloc(sizeof(Recent));

        /* Place the new location into the list node */
        ((Recent*)ms->playerMovement->head->data)->playerRow = pr;
        ((Recent*)ms->playerMovement->head->data)->playerCol = pc;
    }
}





/* 
 * Method:    moveLeft
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    ms->map (char**), mv (int*), pv (int*), gv (int*)   
 * Export:    won (int)   
 * Assertion: Move the player left and return whether they won
 */

void moveLeft(MapState *ms)
{
    int pr = ((Recent*)ms->playerMovement->head->data)->playerRow;
    int pc = ((Recent*)ms->playerMovement->head->data)->playerCol;

    if(ms->map[pr][pc - 1] == 'G') /* If player is moving onto the goal */
    {
        ms->map[pr][pc - 1] = 'P'; /* Place the player at the goal location */
        ms->map[pr][pc] = BLANK; /* Place a BLANK character were the player was */

        pc = pc - 1; /* Update the player's location */
        ms->end->won = TRUE; /* Player has now won the game */
    }

    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((ms->map[pr][pc - 1] == 'X') || (ms->map[pr][pc - 1] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(ms->map[pr][pc - 1] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((ms->map[pr][pc - 1] == '*') && (ms->map[pr][ms->mv->col - 2] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((ms->map[pr][pc - 1] == '*') && (ms->map[pr][ms->mv->col - 2] != 'X'))
        {
            if(ms->map[pr][ms->mv->col - 2] == 'G') /* If the player is moving onto the goal */
            {
                ms->map[pr][ms->mv->col - 2] = 'P'; /* Place the player at the goal location */
                ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

                pc = pc - 1; /* Update the player's location */
                ms->end->won = TRUE; /* Player has now won the game */
            }
            else
            {
                ms->map[pr][ms->mv->col - 2] = 'P'; /* Move the player to the free element */
                ms->map[pr][pc] = BLANK; /* Place a BLANK space where the player was */
                pc = ms->mv->col - 2; /* Update the player's location */
            }
        }
    #endif

    else
    {
        ms->map[pr][pc - 1] = 'P'; /* Move the player to the free element */
        ms->map[pr][pc] = BLANK; /* Place a BLANK space where the player was */

        pc = pc - 1; /* Update the player's location */
    }

    /* The player has moved */
    if(pc != ((Recent*)ms->playerMovement->head->data)->playerCol)
    {
        /* Add a new node to the list */
        insertFirst(ms->playerMovement);
        ms->playerMovement->head->data = (Recent*)malloc(sizeof(Recent));

        /* Place the new location into the list node */
        ((Recent*)ms->playerMovement->head->data)->playerRow = pr;
        ((Recent*)ms->playerMovement->head->data)->playerCol = pc;
    }
}





/* 
 * Method:    moveRight
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    ms->map (char**), mv (int*), pv (int*), gv (int*)    
 * Export:    won (int)  
 * Assertion: Move the player right and return whether they won
 */

void moveRight(MapState *ms)
{
    int pr = ((Recent*)ms->playerMovement->head->data)->playerRow;
    int pc = ((Recent*)ms->playerMovement->head->data)->playerCol;

    if(ms->map[pr][pc + 1] == 'G') /* If the player is moving onto the goal */
    {
        ms->map[pr][pc + 1] = 'P'; /* Place the player at the goal location */
        ms->map[pr][pc] = BLANK; /* Place a BLANK character where tthe player was */

        pc = pc + 1; /* Update player's location */
        ms->end->won = TRUE; /* Player has now won the game */
    }

    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((ms->map[pr][pc + 1] == 'X') || (ms->map[pr][pc + 1] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(ms->map[pr][pc + 1] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((ms->map[pr][pc + 1] == '*') && (ms->map[pr][1] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((ms->map[pr][pc + 1] == '*') && (ms->map[pr][1] != 'X'))
        {
            if(ms->map[pr][1] == 'G') /* If the player is moving onto the goal */
            {
                ms->map[pr][1] = 'P'; /* Place the player at the goal location */
                ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

                pc = pc + 1; /* Update player's location */
                ms->end->won = TRUE; /* PLayer has now won the game */
            }
            else
            {
                ms->map[pr][1] = 'P'; /* Move the player to the free element */
                ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */
                pc = 1; /* Update plaer's location */
            }
        }
    #endif

    else
    {
        ms->map[pr][pc + 1] = 'P'; /* Move the player to the free element */
        ms->map[pr][pc] = BLANK; /* Place a BLANK character where the player was */

        pc = pc + 1; /* Update player's location */
    }

    /* Player has moved */
    if(pc != ((Recent*)ms->playerMovement->head->data)->playerCol)
    {
        /* Add a new node into the list */
        insertFirst(ms->playerMovement);
        ms->playerMovement->head->data = (Recent*)malloc(sizeof(Recent));

        /* Place the new location into the new ndoe */
        ((Recent*)ms->playerMovement->head->data)->playerRow = pr;
        ((Recent*)ms->playerMovement->head->data)->playerCol = pc;
    } 
}






/*
 * Title:     undo
 * Author:    Jacob Jonas, 18439731
 * Created:   08/10/2022
 * Modified:  08/10/2022
 * Import:    ms (ms->mapState*)
 * Export:    won (int)
 * Assertion: Undo the last move done by the player
 */

void undo(MapState *ms)
{
    int row, col;
    Recent* latest;

    /* If there is more than one node in the list */
    if(ms->playerMovement->head->next != NULL)
    {
        /* typecast the data from the removed node */
        latest = (Recent*)removeFirst(ms->playerMovement);

        /* remove most recent collapsed floor */
        ms->map[latest->floorRow][latest->floorCol] = BLANK;

        /* remove the player from their most recent location */
        ms->map[latest->playerRow][latest->playerCol] = BLANK;

        /* Update the player's location to their previous location */
        row = ((Recent*)ms->playerMovement->head->data)->playerRow;
        col = ((Recent*)ms->playerMovement->head->data)->playerCol;
        ms->map[row][col] = 'P';

        /* free the data that was removed from the list */
        free(latest);
        latest = NULL;
    }
}


