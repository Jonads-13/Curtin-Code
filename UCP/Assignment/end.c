/*
 * Title:     end
 * Author:    Jacob Jonas, 18439731
 * Created:   29/08/2022
 * Modified:  06/09/2022
 * Assertion: Contains the conditions for ending the game
 */

#include <stdio.h>
#include "end.h"
#include "macros.h"


/* 
 * Method:    playerTrapped
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Import:    map (char**), mv (int*), pv (int*), gv (int*)
 * Export:    lost (int)    
 * Assertion: Check all permutations of the player being trapped
 */

int playerTrapped(char **map, int *mv, int *pv, int *gv)
{
    int lost;
   /* Initialise as false so that if none of the folowing 
    * conditions are met, then the player isn't trapped 
    */
    lost = FALSE;

    #ifndef BORDERLESS /* When BORDERLESS is not defined */

    /* If all elements around the player contain a collapsed floor or the border
     * then the player is trapped */

        /* Checking above the player */
        if((map[pv[0] - 1][pv[1]] == '*') || (map[pv[0] - 1][pv[1]] == 'X'))
        {
            /* Checking below the player */
            if((map[pv[0] + 1][pv[1]] == '*') || (map[pv[0] + 1][pv[1]] == 'X'))
            {
                /* Checking to the left of the player */
                if((map[pv[0]][pv[1] - 1] == '*') || (map[pv[0]][pv[1] - 1] == 'X'))
                {
                    /* Checking to the right of the player */
                    if((map[pv[0]][pv[1] + 1] == '*') || (map[pv[0]][pv[1] + 1] == 'X'))
                    {
                        lost = TRUE;
                    }
                }
            }
        }
    #endif /* End BORDERLESS not defined */

    #ifdef BORDERLESS /* When BORDERLESS is defined */

    /* PLayer is surrounded by collapsed floors */

        /* Cheking above */
        if(map[pv[0] - 1][pv[1]] == 'X')
        {
            /* Checking below */
            if(map[pv[0] + 1][pv[1]] == 'X')
            {
                /* Checking left */
                if(map[pv[0]][pv[1] - 1] == 'X')
                {
                    /* Checking right */
                    if(map[pv[0]][pv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking the warping/wrap around locations and if they 
 * contain a collapsed floor.
 */
        /* Warp to bottom row check */

        /* Above the player is the border and the bottom row, same column is a collapsed floor */
        if((map[pv[0] - 1][pv[1]] == '*') && (map[mv[0] - 2][pv[1]] == 'X'))
        {
            /* Checking below the player */
            if(map[pv[0] + 1][pv[1]] == 'X')
            {
                /* Checking to the left*/
                if(map[pv[0]][pv[1] - 1] == 'X')
                {
                    /* Checking to the right*/
                    if(map[pv[0]][pv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Warp to top row check */

        /* Below the player is the border and the top row, same column is a collapsed floor */
        if((map[pv[0] + 1][pv[1]] == '*') && (map[1][pv[1]] == 'X'))
        {
            /* Checking above the player */
            if(map[pv[0] - 1][pv[1]] == 'X')
            {
                /* Checking to the left*/
                if(map[pv[0]][pv[1] - 1] == 'X')
                {
                    /* Checking to the right*/
                    if(map[pv[0]][pv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Warp to right column check */

        /* To the left of the player is the border 
         * and the right most column, same row is a colllapsed floor */
        if((map[pv[0]][pv[1] - 1 ] == '*') && (map[pv[0]][mv[1] - 2] == 'X'))
        {
            /* Checking above the player */
            if(map[pv[0] - 1][pv[1]] == 'X')
            {
                /* Checking below the player */
                if(map[pv[0] + 1][pv[1]] == 'X')
                {
                    /* Checking to the right*/
                    if(map[pv[0]][pv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Warp to left column check */

        /* To the right of the player is the border 
         * and the left most column, same row is a colllapsed floor */
        if((map[pv[0]][pv[1] + 1 ] == '*') && (map[pv[0]][1] == 'X'))
        {
            /* Checking above the player */
            if(map[pv[0] - 1][pv[1]] == 'X')
            {
                /* Checking below the player */
                if(map[pv[0] + 1][pv[1]] == 'X')
                {
                    /* Checking to the left */
                    if(map[pv[0]][pv[1] - 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking if the player is trapped in a corner */


        /* Top left corner player check */

        /* Above the player is the border and the bottom row, same column is a collapsed floor */
        if((map[pv[0] - 1][pv[1]] == '*') && (map[mv[0] - 2][pv[1]] == 'X'))
        {
            /* To the left of the player is the border 
             * and the right most column, same row is a colllapsed floor */
            if((map[pv[0]][pv[1] - 1] == '*') && (map[pv[0]][mv[1]-2] == 'X'))
            {
                /* Checking below the player */
                if(map[pv[0] + 1][pv[1]] == 'X')
                {
                    /* Checking to the right */
                    if(map[pv[0]][pv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Top right corner player check */

        /* Above the player is the border and the bottom row, same column is a collapsed floor */
        if((map[pv[0] - 1][pv[1]] == '*') && (map[mv[0] - 2][pv[1]] == 'X'))
        {
            /* To the right of the player is the border 
             * and the left most column, same row is a colllapsed floor */
            if((map[pv[0]][pv[1] + 1] == '*') && (map[pv[0]][1] == 'X'))
            {
                /* Checking below the player */
                if(map[pv[0] + 1][pv[1]] == 'X')
                {
                    /* Checking to the left */
                    if(map[pv[0]][pv[1] - 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Bottom left corner player check */

        /* Below the player is the border and the top row, same column is a collapsed floor */
        if((map[pv[0] + 1][pv[1]] == '*') && (map[1][pv[1]] == 'X'))
        {
            /* To the left of the player is the border 
             * and the right most column, same row is a colllapsed floor */
            if((map[pv[0]][pv[1] - 1] == '*') && (map[pv[0]][mv[1]-2] == 'X'))
            {
                /* Checking above the player */
                if(map[pv[0] - 1][pv[1]] == 'X')
                {
                    /* Checking to the right */
                    if(map[pv[0]][pv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Bottom right corner player check */

        /* Below the player is the border and the top row, same column is a collapsed floor */
        if((map[pv[0] + 1][pv[1]] == '*') && (map[1][pv[1]] == 'X'))
        {
            /* To the right of the player is the border 
             * and the left most column, same row is a colllapsed floor */
            if((map[pv[0]][pv[1] + 1] == '*') && (map[pv[0]][1] == 'X'))
            {
                /* Checking above the player */
                if(map[pv[0] - 1][pv[1]] == 'X')
                {
                    /* Checking to the left */
                    if(map[pv[0]][pv[1] - 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }
    #endif /* End BORDERLESS define */

    /* If any of the above conditions are met then the player is deemed trapped */
    return lost;
}




/* 
 * Method:    goalBlocked
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Import:    map (char**), mv (int*), pv (int*), gv (int*)   
 * Export:    lost (int) 
 * Assertion: Check all permutations of the goal being blocked
 */

int goalBlocked(char **map, int *mv, int *pv, int *gv)
{
    int lost;
    /* Initialise as false so that if none of the folowing conditions are met,
     * then the goal isn't blocked 
     */
    lost = FALSE;

    #ifndef BORDERLESS /* When BORDERLESS is not defined */

    /* If all elements around the goal contain a collapsed floor or the border
     * then the goal is blocked */

        /* Checking above the goal */
        if((map[gv[0] - 1][gv[1]] == '*') || (map[gv[0] - 1][gv[1]] == 'X'))
        {
            /* Checking below the goal */
            if((map[gv[0] + 1][gv[1]] == '*') || (map[gv[0] + 1][gv[1]] == 'X'))
            {
                /* Checking to the left of the goal */
                if((map[gv[0]][gv[1] - 1] == '*') || (map[gv[0]][gv[1] - 1] == 'X'))
                {
                    /* Checking to the right of the goal */
                    if((map[gv[0]][gv[1] + 1] == '*') || (map[gv[0]][gv[1] + 1] == 'X'))
                    {
                        lost = TRUE;
                    }
                }
            }
        }
    #endif /* End BORDERLESS not defined */

    #ifdef BORDERLESS /* When BORDERLESS is defined */

    /* Goal is surrounded by collapsed floors */

        /* Check above */
        if(map[gv[0] - 1][gv[1]] == 'X')
        {
            /* Check below */
            if(map[gv[0] + 1][gv[1]] == 'X')
            {
                /* Check left */
                if(map[gv[0]][gv[1] - 1] == 'X')
                {
                    /* Check right */
                    if(map[gv[0]][gv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking the warping/wrap around locations and if they 
 * contain a collapsed floor.
 */

        /* Check warping from the bottom row */

        /* Above the goal is the border and the bottom row, same column is a collapsed floor */
        if((map[gv[0] - 1][gv[1]] == '*') && (map[mv[0] - 2][gv[1]] == 'X'))
        {
            /* Checking below the goal */
            if(map[gv[0] + 1][gv[1]] == 'X')
            {
                /* Checking to the left */
                if(map[gv[0]][gv[1] - 1] == 'X')
                {
                    /* Checking to the right */
                    if(map[gv[0]][gv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Check warping from the top row */

        /* Below the goal is the border and the top row, same column is a collapsed floor */
        if((map[gv[0] + 1][gv[1]] == '*') && (map[1][gv[1]] == 'X'))
        {
            /* Cheking above the goal */
            if(map[gv[0] - 1][gv[1]] == 'X')
            {
                /* Checking to the left */
                if(map[gv[0]][gv[1] - 1] == 'X')
                {
                    /* Checking to the right */
                    if(map[gv[0]][gv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Check warping from the right-most column */

        /* To the left of the goal is the border 
         * and the right most column, same row is a colllapsed floor */
        if((map[gv[0]][gv[1] - 1 ] == '*') && (map[gv[0]][mv[1] - 2] == 'X'))
        {
            /* Checking above the goal */
            if(map[gv[0] - 1][gv[1]] == 'X')
            {
                /* Checking below the goal */
                if(map[gv[0] + 1][gv[1]] == 'X')
                {
                    /* Checking to the right */
                    if(map[gv[0]][gv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Checking warping from the left-most column */

        /* To the right of the goal is the border 
         * and the left most column, same row is a colllapsed floor */
        if((map[gv[0]][gv[1] + 1 ] == '*') && (map[gv[0]][1] == 'X'))
        {
            /* Checking above the goal */
            if(map[gv[0] - 1][gv[1]] == 'X')
            {
                /* Checking below the goal */
                if(map[gv[0] + 1][gv[1]] == 'X')
                {
                    /* Checking to the left */
                    if(map[gv[0]][gv[1] - 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking if the goal is blocked in a corner */

        /* Top left corner goal check */

        /* Above the goal is the border and the bottom row, same column is a collapsed floor */
        if((map[gv[0] - 1][gv[1]] == '*') && (map[mv[0] - 2][gv[1]] == 'X'))
        {
            /* To the left of the goal is the border 
             * and the right most column, same row is a colllapsed floor */
            if((map[gv[0]][gv[1] - 1] == '*') && (map[gv[0]][mv[1]-2] == 'X'))
            {
                /* Checking below the goal */
                if(map[gv[0] + 1][gv[1]] == 'X')
                {
                    /* Checking to the right */
                    if(map[gv[0]][gv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Top right corner goal check */

        /* Above the goal is the border and the bottom row, same column is a collapsed floor */
        if((map[gv[0] - 1][gv[1]] == '*') && (map[mv[0] - 2][gv[1]] == 'X'))
        {
            /* To the right of the goal is the border 
             * and the left most column, same row is a colllapsed floor */
            if((map[gv[0]][gv[1] + 1] == '*') && (map[gv[0]][1] == 'X'))
            {
                /* Checking below the goal */
                if(map[gv[0] + 1][gv[1]] == 'X')
                {
                    /* Checking to the left */
                    if(map[gv[0]][gv[1] - 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Bottom left corner goal check */

        /* Below the goal is the border and the top row, same column is a collapsed floor */
        if((map[gv[0] + 1][gv[1]] == '*') && (map[1][gv[1]] == 'X'))
        {
            /* To the left of the goal is the border 
             * and the right most column, same row is a colllapsed floor */
            if((map[gv[0]][gv[1] - 1] == '*') && (map[gv[0]][mv[1]-2] == 'X'))
            {
                /* Checking above the goal */
                if(map[gv[0] - 1][gv[1]] == 'X')
                {
                    /* Checking to the right */
                    if(map[gv[0]][gv[1] + 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }

        /* Bottom right corner goal check */

        /* Below the goal is the border and the top row, same column is a collapsed floor */
        if((map[gv[0] + 1][gv[1]] == '*') && (map[1][gv[1]] == 'X'))
        {
            /* To the right of the player is the border 
             * and the left most column, same row is a colllapsed floor */
            if((map[gv[0]][gv[1] + 1] == '*') && (map[gv[0]][1] == 'X'))
            {
                /* Checking above the goal */
                if(map[gv[0] - 1][gv[1]] == 'X')
                {
                    /* Checking to the left */
                    if(map[gv[0]][gv[1] - 1] == 'X')
                    {
                        lost = TRUE;
                    }
                }
            }
        }   
    #endif /* End BORDERLESS define */

    return lost;
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

void endMessage(int *won, int *pLost, int *gLost)
{
    if(*won) /* If the player reached the goal */
    {
        printf("\nCongratulations, you reached the goal. You win!\n\n");
    }
    else if(*pLost) /* If the player was trapped */
    {
        printf("\nYou were trapped. You lost\n\n");
    } 
    else if(*gLost) /* If the goal was blocked */
    {
        printf("\nThe goal was blocked. You lost\n\n");
    }
}
