/*
 * Title:     movement
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Assertion: All movement related functions for the game
 */

#include <stdio.h>
#include "movement.h"
#include "macros.h"
#include "terminal.h"




/*
 * Title:     validMove
 * Author:    Jacob Jonas, 18439731
 * Created:   06/09/2022
 * Modified:  06/09/2022
 * Import:    map (char**), playerVals (int*), mapVals (int*)
 * Export:    won (int)
 * Assertion: Determine if a players move is valid
 */

int validMove(char **map, int *playerVals, int *mapVals)
{
    int playerRowTemp, playerColTemp, won;
    char direction, *directionPtr = &direction;
    FUNCPTR funcPtr;

    /* Variables to check if the player has actually moved */
    playerRowTemp = playerVals[0];
    playerColTemp = playerVals[1];

    while((playerRowTemp == playerVals[0]) && (playerColTemp == playerVals[1]))
    {
        disableBuffer(); /* Allow instantaneous input without "enter" key */
        scanf(" %c", directionPtr); /* Get user's desired direction */
        enableBuffer(); /* Fix the terminal */

        /* get a pointer to a function that will perform the movement of the player */
        funcPtr = movePlayer(directionPtr);

        /* won condition is based on the return value of the movement functions */
        won = (funcPtr)(map, playerVals, mapVals);
    }
    
    return won;
}






/* 
 * Method:    movePlayer
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Import:    direction (char*) 
 * Export:    ptr (FUNCPTR)   
 * Assertion: Return a function pointer that will carry out the chosen method direction
 */

FUNCPTR movePlayer(char *direction)
{
    /* Declare function pointer */
    FUNCPTR ptr;
    ptr = NULL;

    /* Depending on player input ,
     * Return a pointer to the corresponding function */
    switch(*direction)
    {
        /* Any particular reason why we weren't supposed to account for capital letters? 
         *
         * I've commented them out but doing this does work and I can't see why Antoni
         * told us not to account for it because this isn't something extremely difficult
         * or out-of-scope for the unit.
         */

        /* case 'W': */ 
        case 'w': 
            ptr = &moveUp; 
        break;

        /* case 'S': */
        case 's':
            ptr = &moveDown;
        break;

        /* case 'A': */
        case 'a':
            ptr = &moveLeft;
        break;

        /* case 'D': */
        case 'd':
            ptr = &moveRight;
        break;
    }

    return ptr; /* return function pointer */
}






/* 
 * Method:    moveUp
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    map (char**), mv (int*), pv (int*), gv (int*)
 * Export:    won (int)    
 * Assertion: Move the player up and return whether they won
 */

int moveUp(char** map, int *pv, int *mv)
{
    int won = FALSE; /* Used to determine if the player won */

    if(map[pv[0] - 1][pv[1]] == 'G') /* If player is moving onto the goal */ 
    {
        map[pv[0] - 1][pv[1]] = 'P'; /* Place the player at the goal location */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

        pv[0] = pv[0] - 1; /* Update the player's location */
        won = TRUE; /* The player has now won the game */
    }
    
    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((map[pv[0] - 1][pv[1]] == 'X') || (map[pv[0] - 1][pv[1]] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(map[pv[0] - 1][pv[1]] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((map[pv[0] - 1][pv[1]] == '*') && (map[mv[0] - 2][pv[1]] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((map[pv[0] - 1][pv[1]] == '*') && (map[mv[0] - 2][pv[1]] != 'X'))
        {
            if(map[mv[0] - 2][pv[1]] == 'G') /* If the player is moving onto the goal */
            {
                map[mv[0] - 2][pv[1]] = 'P'; /* Place the player at the goal location */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

                pv[0] = mv[0] - 2; /* Update the player's location */
                won = TRUE; /* The player has now won the game */
            }
            else
            {
                map[mv[0] - 2][pv[1]] = 'P'; /* Move the player to the free element */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK space where the player was */

                pv[0] = mv[0] - 2; /* Update player location */
            }
        }
    #endif

    else
    { 
        map[pv[0] - 1][pv[1]] = 'P'; /* Move the player to the free element */
        map[pv[0]][pv[1]] = BLANK; /* Place a blank space where the player was */

        pv[0] = pv[0] - 1; /* Update player location */
    }

    return won;
}





/* 
 * Method:    moveDown
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    map (char**), mv (int*), pv (int*), gv (int*)   
 * Export:    won (int)   
 * Assertion: Move the player down and return whether they won
 */

int moveDown(char** map, int *pv, int *mv)
{
    int won = FALSE; /* Used to determine if the player won */
  
    if(map[pv[0] + 1][pv[1]] == 'G') /* If player is moving onto the goal */
    {
        map[pv[0] + 1][pv[1]] = 'P'; /* Place the player at the goal location */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

        pv[0] = pv[0] + 1; /* Update the player's location */
        won = TRUE; /* The player has now won the game */
    }

    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((map[pv[0] + 1][pv[1]] == 'X') || (map[pv[0] + 1][pv[1]] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(map[pv[0] + 1][pv[1]] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((map[pv[0] + 1][pv[1]] == '*') && (map[1][pv[1]] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((map[pv[0] + 1][pv[1]] == '*') && (map[1][pv[1]] != 'X'))
        {
            if(map[1][pv[1]] == 'G') /* If the player is moving onto the goal */
            {
                map[1][pv[1]] = 'P'; /* Place the player at the goal location */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

                pv[0] = 1; /* Update the player's location */
                won = TRUE; /* Player has now won the game */
            }
            else
            {
                map[1][pv[1]] = 'P'; /* Move the player to the free element */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK space where the player was */

                pv[0] = 1; /* Update player's location */
            }
        }
    #endif

    else
    {
        map[pv[0] + 1][pv[1]] = 'P'; /* Move the player to the free element */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK space where the player was */

        pv[0] = pv[0] + 1; /* Update the player's location */
    }
 
    return won;
}





/* 
 * Method:    moveLeft
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    map (char**), mv (int*), pv (int*), gv (int*)   
 * Export:    won (int)   
 * Assertion: Move the player left and return whether they won
 */

int moveLeft(char** map, int *pv, int *mv)
{
    int won = FALSE; /* Used to determine if the player won */

    if(map[pv[0]][pv[1] - 1] == 'G') /* If player is moving onto the goal */
    {
        map[pv[0]][pv[1] - 1] = 'P'; /* Place the player at the goal location */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character were the player was */

        pv[1] = pv[1] - 1; /* Update the player's location */
        won = TRUE; /* Player has now won the game */
    }

    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((map[pv[0]][pv[1] - 1] == 'X') || (map[pv[0]][pv[1] - 1] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(map[pv[0]][pv[1] - 1] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((map[pv[0]][pv[1] - 1] == '*') && (map[pv[0]][mv[1] - 2] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((map[pv[0]][pv[1] - 1] == '*') && (map[pv[0]][mv[1] - 2] != 'X'))
        {
            if(map[pv[0]][mv[1] - 2] == 'G') /* If the player is moving onto the goal */
            {
                map[pv[0]][mv[1] - 2] = 'P'; /* Place the player at the goal location */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

                pv[1] = pv[1] - 1; /* Update the player's location */
                won = TRUE; /* Player has now won the game */
            }
            else
            {
                map[pv[0]][mv[1] - 2] = 'P'; /* Move the player to the free element */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK space where the player was */
                pv[1] = mv[1] - 2; /* Update the player's location */
            }
        }
    #endif

    else
    {
        map[pv[0]][pv[1] - 1] = 'P'; /* Move the player to the free element */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK space where the player was */

        pv[1] = pv[1] - 1; /* Update the player's location */
    }
    
    return won;
}





/* 
 * Method:    moveRight
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  29/08/2022
 * Import:    map (char**), mv (int*), pv (int*), gv (int*)    
 * Export:    won (int)  
 * Assertion: Move the player right and return whether they won
 */

int moveRight(char** map, int *pv, int *mv)
{
    int won = FALSE; /* Used to determine if the player won */

    if(map[pv[0]][pv[1] + 1] == 'G') /* If the player is moving onto the goal */
    {
        map[pv[0]][pv[1] + 1] = 'P'; /* Place the player at the goal location */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where tthe player was */

        pv[1] = pv[1] + 1; /* Update player's location */
        won = TRUE; /* Player has now won the game */
    }

    #ifndef BORDERLESS /* When BORDERLESS is not defined */
        /* Don't allow the player to move if they are trying 
         * to move onto a 'X' or '*' character occupied space */
        else if((map[pv[0]][pv[1] + 1] == 'X') || (map[pv[0]][pv[1] + 1] == '*'))
        {}
    #endif

    #ifdef BORDERLESS /* When BORDERLESS is defined */
        /* Player can't move onto an 'X' character */
        else if(map[pv[0]][pv[1] + 1] == 'X')
        {}
        /* The player cannot "warp" if the other side has an 'X' character */
        else if((map[pv[0]][pv[1] + 1] == '*') && (map[pv[0]][1] == 'X'))
        {}
        /* Player can "warp" if the other side does not have an 'X' character */
        else if((map[pv[0]][pv[1] + 1] == '*') && (map[pv[0]][1] != 'X'))
        {
            if(map[pv[0]][1] == 'G') /* If the player is moving onto the goal */
            {
                map[pv[0]][1] = 'P'; /* Place the player at the goal location */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

                pv[1] = pv[1] + 1; /* Update player's location */
                won = TRUE; /* PLayer has now won the game */
            }
            else
            {
                map[pv[0]][1] = 'P'; /* Move the player to the free element */
                map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */
                pv[1] = 1; /* Update plaer's location */
            }
        }
    #endif

    else
    {
        map[pv[0]][pv[1] + 1] = 'P'; /* Move the player to the free element */
        map[pv[0]][pv[1]] = BLANK; /* Place a BLANK character where the player was */

        pv[1] = pv[1] + 1; /* Update player's location */
    }
   
    return won;   
}


