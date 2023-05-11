/*
 * Title:     runtime
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Assertion: Functions related to the simulation during runtime
 */

#include <stdio.h>
#include "runtime.h"
#include "movement.h"
#include "map.h"
#include "newSleep.h"
#include "macros.h"
#include "free.h"






/*
 * Title:     begin
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  02/11/2022
 * Import:    state (State*)
 * Export:    none
 * Assertion: Handles the runtime of the simulation
 */

void begin(State* state)
{
    int iterations = 0;

    /* Print map once at the start of the simulation */
    printMap(state);

    /* Amount of iterations is less than the stipulated amount*/
    while(iterations <= state->max)
    {
        /* Call provided function to make the program sleep for the alotted time */
        newSleep(state->sleep);

        /* Perform actions with the Langton's Ant */
        changeLangColor(state->langAnt, state->maps->colorState);
        moveLang(state);

        /* Perform actions with the random ant */
        changeRandColor(state->randAnt, state->maps->colorState);
        moveRand(state->randAnt, state->maps->display);

        /* Print the map after all modifications have been made*/
        printMap(state);

        iterations++;
    }

    /* Free the structs used in the program */
    freeStructs(state);
}






/*
 * Title:     changeLangColor
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    lang (Properties*), colorState (int**)
 * Export:    none
 * Assertion: Swap the background color of the ant's location
 */

void changeLangColor(Ant* lang, int** colorState)
{
    /* Color swapping */
    if(colorState[lang->row][lang->col] == DEFAULT)
    {
        colorState[lang->row][lang->col] = RED;
    }
    else /* Current element has a background colour */
    {
        colorState[lang->row][lang->col] = DEFAULT;
    }  
}






/*
 * Title:     changeRandColor
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    
 * Export:    
 * Assertion: 
 */

void changeRandColor(Ant* rand, int** colorState)
{
    /* Color swapping */
    if(colorState[rand->row][rand->col] == DEFAULT)
    {
        colorState[rand->row][rand->col] = BLUE;
    }
    else /* Current element has a background colour */
    {
        colorState[rand->row][rand->col] = DEFAULT;
    }  
}







