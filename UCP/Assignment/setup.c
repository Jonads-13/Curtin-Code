#include <stdio.h>
#include <stdlib.h>
#include "setup.h"
#include "map.h"
#include "macros.h"







/*
 * Title:     extract
 * Author:    Jacob Jonas, 18439731
 * Created:   06/09/2022
 * Modified:  06/09/2022
 * Import:    argv (char**)
 * Export:    none
 * Assertion: Extract the values from argv
 */

void extract(char** argv)
{
    /* arrays to hold numbers needed for later
     * Allows pass-by-reference later */
    int *mapVals = (int*)malloc(2 * sizeof(int));
    int *playerVals = (int*)malloc(2 * sizeof(int)); 
    int *goalVals = (int*)malloc(2 * sizeof(int));

    /* assign argv values to corresponding arrays and convert them to integers */
    mapVals[0] = atoi(argv[1]); 
    mapVals[1] = atoi(argv[2]); 
    playerVals[0] = atoi(argv[3]); 
    playerVals[1] = atoi(argv[4]); 
    goalVals[0] = atoi(argv[5]);
    goalVals[1] = atoi(argv[6]);

    setup(mapVals, playerVals, goalVals);

    /* free malloc'd arrays */
    free(mapVals);
    mapVals = NULL;
    free(playerVals);
    playerVals = NULL;
    free(goalVals);
    goalVals = NULL;


}

/* 
 * Method:    setup
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  26/08/2022
 * Import:    argv (char**)
 * Export:    none
 * Assertion: Get everything ready to create the map for the game 
 */

void setup(int *mapVals, int *playerVals, int *goalVals)
{
    /* Checking that every value is valid before creating the mapVals 
    * if at any point, a value is not valid, print an error message 
    * the various VALID checks are written in "macros.h" */
    if(VALIDMAP(mapVals[0],mapVals[1])) 
    {
        if(VALIDNUM(mapVals[0], playerVals[0])) 
        {
            if(VALIDNUM(mapVals[1], playerVals[1])) 
            {
                if(VALIDNUM(mapVals[0], goalVals[0])) 
                {
                    if(VALIDNUM(mapVals[1],goalVals[1])) 
                    {
                        if(VALIDGOAL(goalVals[0], goalVals[1], playerVals[0], playerVals[1])) 
                        {
                            /* Increase the values to account for the '*' characters 
                             * to be printed as the border */
                            increaseValues(mapVals, 2);
                            increaseValues(playerVals, 1);
                            increaseValues(goalVals, 1);

                            /* Send values to create the map */
                            createMap(mapVals, playerVals, goalVals);
                        } 
                        else 
                        { 
                            printf("\nThe player and the goal cannot be in the same location you cheek :P\n\n");
                        }
                    } 
                    else 
                    { 
                        printf("Error: Please ensure the goal column less than the map column\n");
                    }
                } 
                else 
                { 
                    printf("Error: Please ensure the goal row less than the map row\n");
                }
            } 
            else 
            { 
                printf("Error: Please ensure the player column less than the map column\n");
            }
        } 
        else 
        { 
            printf("Error: Please ensure the player row less than the map row\n");
        }
    } 
    else 
    { 
       printf("Error: Please ensure the map is at least 5 x 5 and at most 25 x 25\n");
    }
}





/* 
 * Method:    increaseValues
 * Author:    Jacob Jonas, 18439731
 * Created:   24/08/2022
 * Modified:  26/08/2022
 * Import:    arr (int*), n (int)
 * Export:    none 
 * Assertion: Get everything ready to create the map for the game 
 */

void increaseValues(int* arr, int n)
{
    int i;
    for(i = 0; i < 2; i++) /* each array will only ever be 2 elements long */
    {
        /* Because map values need to increased by 2,
         * and player and goal values only need to be increase by 1,
         * n is needed to change the increase value */
        arr[i] = arr[i] + n; 
    }
}
