/*
 * Title:     end
 * Author:    Jacob Jonas, 18439731
 * Created:   29/08/2022
 * Modified:  06/09/2022
 * Assertion: Contains the conditions for ending the game
 */

#include <stdio.h>
#include "linkedlist.h"
#include "structs.h"
#include "end.h"
#include "macros.h"


/* 
 * Method:    playerTrapped
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  06/09/2022
 * Import:    ms (MapState*)
 * Export:    none   
 * Assertion: Check all permutations of the player being trapped
 */

void playerTrapped(MapState *ms)
{
    /* Copy the needed values into variables with much shorter names */
    int pr = ((Recent*)ms->playerMovement->head->data)->playerRow;
    int pc = ((Recent*)ms->playerMovement->head->data)->playerCol;

    #ifndef BORDERLESS /* When BORDERLESS is not defined */

    /* If all elements around the player contain a collapsed floor or the border
     * then the player is trapped */

        /* Checking above the player */
        if((ms->map[pr - 1][pc] == '*') || (ms->map[pr - 1][pc] == 'X'))
        {
            /* Checking below the player */
            if((ms->map[pr + 1][pc] == '*') || (ms->map[pr + 1][pc] == 'X'))
            {
                /* Checking to the left of the player */
                if((ms->map[pr][pc - 1] == '*') || (ms->map[pr][pc - 1] == 'X'))
                {
                    /* Checking to the right of the player */
                    if((ms->map[pr][pc + 1] == '*') || (ms->map[pr][pc + 1] == 'X'))
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }
    #endif /* End BORDERLESS not defined */

    #ifdef BORDERLESS /* When BORDERLESS is defined */

    /* PLayer is surrounded by collapsed floors */

        /* Cheking above */
        if(ms->map[pr - 1][pc] == 'X')
        {
            /* Checking below */
            if(ms->map[pr + 1][pc] == 'X')
            {
                /* Checking left */
                if(ms->map[pr][pc - 1] == 'X')
                {
                    /* Checking right */
                    if(ms->map[pr][pc + 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking the warping/wrap around locations and if they 
 * contain a collapsed floor.
 */
        /* Warp to bottom row check */

        /* Above the player is the border and the bottom row, same column is a collapsed floor */
        if((ms->map[pr - 1][pc] == '*') && (ms->map[ms->mv->row - 2][pc] == 'X'))
        {
            /* Checking below the player */
            if(ms->map[pr + 1][pc] == 'X')
            {
                /* Checking to the left*/
                if(ms->map[pr][pc - 1] == 'X')
                {
                    /* Checking to the right*/
                    if(ms->map[pr][pc + 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

        /* Warp to top row check */

        /* Below the player is the border and the top row, same column is a collapsed floor */
        if((ms->map[pr + 1][pc] == '*') && (ms->map[1][pc] == 'X'))
        {
            /* Checking above the player */
            if(ms->map[pr - 1][pc] == 'X')
            {
                /* Checking to the left*/
                if(ms->map[pr][pc - 1] == 'X')
                {
                    /* Checking to the right*/
                    if(ms->map[pr][pc + 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

        /* Warp to right column check */

        /* To the left of the player is the border 
         * and the right most column, same row is a colllapsed floor */
        if((ms->map[pr][pc - 1 ] == '*') && (ms->map[pr][ms->mv->col - 2] == 'X'))
        {
            /* Checking above the player */
            if(ms->map[pr - 1][pc] == 'X')
            {
                /* Checking below the player */
                if(ms->map[pr + 1][pc] == 'X')
                {
                    /* Checking to the right*/
                    if(ms->map[pr][pc + 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

        /* Warp to left column check */

        /* To the right of the player is the border 
         * and the left most column, same row is a colllapsed floor */
        if((ms->map[pr][pc + 1 ] == '*') && (ms->map[pr][1] == 'X'))
        {
            /* Checking above the player */
            if(ms->map[pr - 1][pc] == 'X')
            {
                /* Checking below the player */
                if(ms->map[pr + 1][pc] == 'X')
                {
                    /* Checking to the left */
                    if(ms->map[pr][pc - 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking if the player is trapped in a corner */


        /* Top left corner player check */

        /* Above the player is the border and the bottom row, same column is a collapsed floor */
        if((ms->map[pr - 1][pc] == '*') && (ms->map[ms->mv->row - 2][pc] == 'X'))
        {
            /* To the left of the player is the border 
             * and the right most column, same row is a colllapsed floor */
            if((ms->map[pr][pc - 1] == '*') && (ms->map[pr][ms->mv->col-2] == 'X'))
            {
                /* Checking below the player */
                if(ms->map[pr + 1][pc] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[pr][pc + 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

        /* Top right corner player check */

        /* Above the player is the border and the bottom row, same column is a collapsed floor */
        if((ms->map[pr - 1][pc] == '*') && (ms->map[ms->mv->row - 2][pc] == 'X'))
        {
            /* To the right of the player is the border 
             * and the left most column, same row is a colllapsed floor */
            if((ms->map[pr][pc + 1] == '*') && (ms->map[pr][1] == 'X'))
            {
                /* Checking below the player */
                if(ms->map[pr + 1][pc] == 'X')
                {
                    /* Checking to the left */
                    if(ms->map[pr][pc - 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

        /* Bottom left corner player check */

        /* Below the player is the border and the top row, same column is a collapsed floor */
        if((ms->map[pr + 1][pc] == '*') && (ms->map[1][pc] == 'X'))
        {
            /* To the left of the player is the border 
             * and the right most column, same row is a colllapsed floor */
            if((ms->map[pr][pc - 1] == '*') && (ms->map[pr][ms->mv->col-2] == 'X'))
            {
                /* Checking above the player */
                if(ms->map[pr - 1][pc] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[pr][pc + 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }

        /* Bottom right corner player check */

        /* Below the player is the border and the top row, same column is a collapsed floor */
        if((ms->map[pr + 1][pc] == '*') && (ms->map[1][pc] == 'X'))
        {
            /* To the right of the player is the border 
             * and the left most column, same row is a colllapsed floor */
            if((ms->map[pr][pc + 1] == '*') && (ms->map[pr][1] == 'X'))
            {
                /* Checking above the player */
                if(ms->map[pr - 1][pc] == 'X')
                {
                    /* Checking to the left */
                    if(ms->map[pr][pc - 1] == 'X')
                    {
                        ms->end->playerTrapped = TRUE;
                    }
                }
            }
        }
    #endif /* End BORDERLESS define */
}




/* 
 * Method:    goalBlocked
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  08/10/2022
 * Import:    ms (MapState*)  
 * Export:    none 
 * Assertion: Check all permutations of the goal being blocked
 */

void goalBlocked(MapState *ms)
{
    /* Copy the needed values into a variables with shorter names */
    int gr = ms->gv->row;
    int gc = ms->gv->col;

    #ifndef BORDERLESS /* When BORDERLESS is not defined */

    /* If all elements around the goal contain a collapsed floor or the border
     * then the goal is blocked */

        /* Checking above the goal */
        if((ms->map[gr - 1][gc] == '*') || (ms->map[gr - 1][gc] == 'X'))
        {
            /* Checking below the goal */
            if((ms->map[gr + 1][gc] == '*') || (ms->map[gr + 1][gc] == 'X'))
            {
                /* Checking to the left of the goal */
                if((ms->map[gr][gc - 1] == '*') || (ms->map[gr][gc - 1] == 'X'))
                {
                    /* Checking to the right of the goal */
                    if((ms->map[gr][gc + 1] == '*') || (ms->map[gr][gc + 1] == 'X'))
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }
    #endif /* End BORDERLESS not defined */

    #ifdef BORDERLESS /* When BORDERLESS is defined */

    /* Goal is surrounded by collapsed floors */

        /* Check above */
        if(ms->map[gr - 1][gc] == 'X')
        {
            /* Check below */
            if(ms->map[gr + 1][gc] == 'X')
            {
                /* Check left */
                if(ms->map[gr][gc - 1] == 'X')
                {
                    /* Check right */
                    if(ms->map[gr][gc + 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking the warping/wrap around locations and if they 
 * contain a collapsed floor.
 */

        /* Check warping from the bottom row */

        /* Above the goal is the border and the bottom row, same column is a collapsed floor */
        if((ms->map[gr - 1][gc] == '*') && (ms->map[ms->mv->row - 2][gc] == 'X'))
        {
            /* Checking below the goal */
            if(ms->map[gr + 1][gc] == 'X')
            {
                /* Checking to the left */
                if(ms->map[gr][gc - 1] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[gr][gc + 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

        /* Check warping from the top row */

        /* Below the goal is the border and the top row, same column is a collapsed floor */
        if((ms->map[gr + 1][gc] == '*') && (ms->map[1][gc] == 'X'))
        {
            /* Cheking above the goal */
            if(ms->map[gr - 1][gc] == 'X')
            {
                /* Checking to the left */
                if(ms->map[gr][gc - 1] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[gr][gc + 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

        /* Check warping from the right-most column */

        /* To the left of the goal is the border 
         * and the right most column, same row is a colllapsed floor */
        if((ms->map[gr][gc - 1 ] == '*') && (ms->map[gr][ms->mv->col - 2] == 'X'))
        {
            /* Checking above the goal */
            if(ms->map[gr - 1][gc] == 'X')
            {
                /* Checking below the goal */
                if(ms->map[gr + 1][gc] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[gr][gc + 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

        /* Checking warping from the left-most column */

        /* To the right of the goal is the border 
         * and the left most column, same row is a colllapsed floor */
        if((ms->map[gr][gc + 1 ] == '*') && (ms->map[gr][1] == 'X'))
        {
            /* Checking above the goal */
            if(ms->map[gr - 1][gc] == 'X')
            {
                /* Checking below the goal */
                if(ms->map[gr + 1][gc] == 'X')
                {
                    /* Checking to the left */
                    if(ms->map[gr][gc - 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

/* The following if statements are checking if the goal is blocked in a corner */

        /* Top left corner goal check */

        /* Above the goal is the border and the bottom row, same column is a collapsed floor */
        if((ms->map[gr - 1][gc] == '*') && (ms->map[ms->mv->row - 2][gc] == 'X'))
        {
            /* To the left of the goal is the border 
             * and the right most column, same row is a colllapsed floor */
            if((ms->map[gr][gc - 1] == '*') && (ms->map[gr][ms->mv->col-2] == 'X'))
            {
                /* Checking below the goal */
                if(ms->map[gr + 1][gc] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[gr][gc + 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

        /* Top right corner goal check */

        /* Above the goal is the border and the bottom row, same column is a collapsed floor */
        if((ms->map[gr - 1][gc] == '*') && (ms->map[ms->mv->row - 2][gc] == 'X'))
        {
            /* To the right of the goal is the border 
             * and the left most column, same row is a colllapsed floor */
            if((ms->map[gr][gc + 1] == '*') && (ms->map[gr][1] == 'X'))
            {
                /* Checking below the goal */
                if(ms->map[gr + 1][gc] == 'X')
                {
                    /* Checking to the left */
                    if(ms->map[gr][gc - 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

        /* Bottom left corner goal check */

        /* Below the goal is the border and the top row, same column is a collapsed floor */
        if((ms->map[gr + 1][gc] == '*') && (ms->map[1][gc] == 'X'))
        {
            /* To the left of the goal is the border 
             * and the right most column, same row is a colllapsed floor */
            if((ms->map[gr][gc - 1] == '*') && (ms->map[gr][ms->mv->col-2] == 'X'))
            {
                /* Checking above the goal */
                if(ms->map[gr - 1][gc] == 'X')
                {
                    /* Checking to the right */
                    if(ms->map[gr][gc + 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }

        /* Bottom right corner goal check */

        /* Below the goal is the border and the top row, same column is a collapsed floor */
        if((ms->map[gr + 1][gc] == '*') && (ms->map[1][gc] == 'X'))
        {
            /* To the right of the player is the border 
             * and the left most column, same row is a colllapsed floor */
            if((ms->map[gr][gc + 1] == '*') && (ms->map[gr][1] == 'X'))
            {
                /* Checking above the goal */
                if(ms->map[gr - 1][gc] == 'X')
                {
                    /* Checking to the left */
                    if(ms->map[gr][gc - 1] == 'X')
                    {
                        ms->end->goalBlocked = TRUE;
                    }
                }
            }
        }   
    #endif /* End BORDERLESS define */
}

