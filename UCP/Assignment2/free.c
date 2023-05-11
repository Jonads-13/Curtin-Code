/*
 * Title:     free
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Assertion: Contains funcitons for freeing the data malloc'd in the program
 */

#include <stdlib.h>
#include "linkedlist.h"
#include "structs.h"
#include "free.h"







/*
 * Title:     freeStructs
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Import:    ms (MapState*)
 * Export:    none
 * Assertion: Free all structs used in the MapState struct
 */

void freeStructs(MapState *ms)
{
    /* Call function to free the 2-D array used for the map */
    freeMap(ms->map, ms->mv->row);

    /* Free the MapValues struct */
    free(ms->mv);
    ms->mv = NULL;

    /* Free the GoalValues struct */
    free(ms->gv);
    ms->gv = NULL;

    /* Free the EndConditions struct */
    free(ms->end);
    ms->end = NULL;

    /* Free the generic linked list by sending a pointer
     * to a function to free the void* data */
    freeLinkedList(ms->playerMovement, &freeRecent);

    /* Free the overall MapState struct */
    free(ms);
    ms = NULL;
}






/*
 * Title:     freeMap
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Import:    map (char**), row (int)
 * Export:    none
 * Assertion: free a previously malloc'd 2-D array
 */

void freeMap(char** map, int row)
{
    int r;

    /* Free each sub array in reverse order */
    for (r = row - 1; r >= 0; r--) 
    {
        free(map[r]); /* Free the malloc'd sub array */
        map[r] = NULL; /* Set the free'd array to NULL */
    } 

    free(map); /* free the main 2-D array */ 
    map = NULL; /* set the free'd array to null */
}






/*
 * Title:     freeRecent
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Import:    data (void*)
 * Export:    none
 * Assertion: Function that frees the specific data used in the generic linkedlist
 */

void freeRecent(void *data)
{
    /* Type cast into relevant struct type */
    Recent *cur = (Recent*)data;

    free(cur);
    cur = NULL;
}
