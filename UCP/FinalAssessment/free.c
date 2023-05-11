/*
 * Title:     free
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  01/11/2022
 * Assertion: Contains funcitons for freeing the data malloc'd in the program
 */

#include <stdlib.h>
#include "structs.h"
#include "free.h"







/*
 * Title:     freeStructs
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  01/11/2022
 * Import:    state (State*)
 * Export:    none
 * Assertion: Free all structs used in the State struct
 */

void freeStructs(State* state)
{
    /* Call function to free the 2-D arrays used for the map and color state */
    freeDisplay(state->maps->display, state->maps->rows);
    freeColorState(state->maps->colorState, state->maps->rows);

    /* Free the maps MapData struct */
    free(state->maps);
    state->maps = NULL;

    /* Free the langAnt Properties struct */
    free(state->langAnt);
    state->langAnt = NULL;

    /* Free the randAnt Properties struct */
    free(state->randAnt);
    state->randAnt = NULL;


    /* Free the overall State struct */
    free(state);
    state = NULL;
}






/*
 * Title:     freeDisplay
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  01/11/2022
 * Import:    map (char**), row (int)
 * Export:    none
 * Assertion: Free a previously malloc'd 2-D char array
 */

void freeDisplay(char** display, int row)
{
    int r;

    /* Free each sub array in reverse order */
    for (r = row - 1; r >= 0; r--) 
    {
        free(display[r]); /* Free the malloc'd sub array */
        display[r] = NULL; /* Set the free'd array to NULL */
    } 

    free(display); /* free the main 2-D array */ 
    display = NULL; /* set the free'd array to null */
}






/*
 * Title:     freeColorState
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    colorState (int**), row (int)
 * Export:    none
 * Assertion: Free a previously malloc'd 2-D int array
 */

void freeColorState(int** colorState, int row)
{
    int r;

    /* Free each sub array in reverse order */
    for (r = row - 1; r >= 0; r--) 
    {
        free(colorState[r]); /* Free the malloc'd sub array */
        colorState[r] = NULL; /* Set the free'd array to NULL */
    } 

    free(colorState); /* free the main 2-D array */ 
    colorState = NULL; /* set the free'd array to null */
}






/*
 * Title:     freeTempPointers
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    tempRow (int*), tempCol (int*), tempChar (char*)
 * Export:    none
 * Assertion: free the temporary pointers used in the intialisation of the program
 */

void freeTempPointers(int* tempRow, int* tempCol, char* tempChar)
{
    free(tempRow); 
    tempRow = NULL;

    free(tempCol); 
    tempCol = NULL;

    free(tempChar); 
    tempChar = NULL;
}
