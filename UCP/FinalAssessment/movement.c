/*
 * Title:     movement
 * Author:    Jacob Jonas, 18439731
 * Created:   22/08/2022
 * Modified:  1/11/2022
 * Assertion: All movement related functions for the simulation
 */

#include <stdio.h>
#include <stdlib.h>
#include "macros.h"
#include "movement.h"
#include "random.h"






/*
 * Title:     moveLang
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    lang (Properties*), colorState (int**)
 * Export:    none
 * Assertion: Determine which plane to move the ant in
 */

void moveLang(State* state)
{
    /* Ant is orietated facing up or down */
    if((state->langAnt->direction == 'v') || (state->langAnt->direction == '^'))
    {
        moveHorizontally(state);
    }
    else /* Ant is orietated facing left or right */
    {
        moveVertically(state);
    }
}






/*
 * Title:     moveRand
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    state (State*)
 * Export:    none
 * Assertion: move the random ant
 */

void moveRand(Ant* rand, char** display)
{
    int randNum = randomNum(0,3); /* get random number */

    /* Move in a direction depending on the random number */
    switch(randNum)
    {
        case NORTH:
            moveUp(rand, display);
        break;

        case EAST:
            moveRight(rand, display);
        break;

        case SOUTH:
            moveDown(rand, display);
        break;

        case WEST:
            moveLeft(rand, display);
        break; 
    }
}






/*
 * Title:     moveHorizontally
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  02/11/2022
 * Import:    lang (Properties*), colorState (int**)
 * Export:    none
 * Assertion: Move the lang ant left or right
 */

void moveHorizontally(State* state)
{
    /* For shorter referencing */
    Ant* lang = state->langAnt;
    int** colorState = state->maps->colorState;
    char** display = state->maps->display;

    /* Logic is reversed because the color change happens before movement checks 
     * 
     * If an element has the DEFAULT background color now, that means it had a color
     * when the ant moved onto it in the previous iteration 
     * 
     * If an element doesn't have the DEFAULT background color now, that means 
     * it was DEFAULT when the ant moved onto it in the previous iteration 
     */
    if((lang->direction == 'v') && (colorState[lang->row][lang->col] == DEFAULT))
    {
        moveRight(lang, display);   
    }
    else if((lang->direction == 'v') &&(colorState[lang->row][lang->col] != DEFAULT))
    {
        moveLeft(lang, display);
    }
    else if((lang->direction == '^') &&(colorState[lang->row][lang->col] == DEFAULT))
    {
        moveLeft(lang, display);
    }
    else if((lang->direction == '^') &&(colorState[lang->row][lang->col] != DEFAULT))
    {
        moveRight(lang, display);
    }
}






/*
 * Title:     moveRight
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    lang (Properties*), display (char**)
 * Export:    none
 * Assertion: 
 */

void moveRight(Ant* ant, char** display)
{
    ant->direction = '>';
    display[ant->row][ant->col] = BLANK;

    /* Only move the location of there is nothing blocking the path */
    if(display[ant->row][ant->col + 1] == BLANK)
    {
        ant->col += 1;
    }
    
    display[ant->row][ant->col] = ant->direction;
}






/*
 * Title:     moveLeft
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    lang (Properties*), display (char**)
 * Export:    none
 * Assertion: 
 */

void moveLeft(Ant* ant, char** display)
{
    ant->direction = '<';
    display[ant->row][ant->col] = BLANK;

    /* Only move the location of there is nothing blocking the path */
    if(display[ant->row][ant->col - 1] == BLANK)
    {
        ant->col -= 1;
    }
    
    display[ant->row][ant->col] = ant->direction;    
}





/*
 * Title:     moveVertically
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  02/11/2022
 * Import:    lang (Properties*), colorState (int**)
 * Export:    none
 * Assertion: move the lang ant up or down
 */

void moveVertically(State* state)
{
    /* For shorter referencing */
    Ant* lang = state->langAnt;
    int** colorState = state->maps->colorState;
    char** display = state->maps->display;


    /* Logic is reversed because the color change happens before movement checks 
     * 
     * If an element has the DEFAULT background color now, that means it had a color
     * when the ant moved onto it in the previous iteration 
     * 
     * If an element doesn't have the DEFAULT background color now, that means 
     * it was DEFAULT when the ant moved onto it in the previous iteration 
     */
    if((lang->direction == '<') && (colorState[lang->row][lang->col] == DEFAULT))
    {
        moveDown(lang, display);
    }
    else if((lang->direction == '<') &&(colorState[lang->row][lang->col] != DEFAULT))
    {
        moveUp(lang, display);
    }
    else if((lang->direction == '>') &&(colorState[lang->row][lang->col] == DEFAULT))
    {
        moveUp(lang, display);
    }
    else if((lang->direction == '>') &&(colorState[lang->row][lang->col] != DEFAULT))
    {
        moveDown(lang, display);
    }
}






/*
 * Title:     moveDown
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    lang (Properties*), display (char**)
 * Export:    none
 * Assertion: 
 */

void moveDown(Ant* ant, char** display)
{
    /* change the ant's orietation */
    ant->direction = 'v';
    display[ant->row][ant->col] = BLANK;

    /* Only move the location if there is nothing blocking the path */
    if(display[ant->row + 1][ant->col] == BLANK)
    {
        ant->row += 1;
    }
    
    display[ant->row][ant->col] = ant->direction;
}






/*
 * Title:     moveUp
 * Author:    Jacob Jonas, 18439731
 * Created:   01/11/2022
 * Modified:  01/11/2022
 * Import:    lang (Properties*), display (char**)
 * Export:    none
 * Assertion: 
 */

void moveUp(Ant* ant, char** display)
{
    ant->direction = '^';
    display[ant->row][ant->col] = BLANK;

    /* Only move the location if there is nothing blocking the path */
    if(display[ant->row - 1][ant->col] == BLANK)
    {
        ant->row -= 1;
    }
    
    display[ant->row][ant->col] = ant->direction;
}


