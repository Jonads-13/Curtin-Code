# Report for Assignment 1 for Unix and C Programming:

Purpose: Explain in more detail how the program works and the reason for any choices in design.

Format: Each file included in the program is a header with the description of what it does underneath

<br>
<br>

# main.c

Contains the "main" function of the whole program and, as such, is where it starts and ends.
Only purpose is to check that the amount of command line arguments is correct, after which it sends argv to setup.c

<br>
<br>


# setup.c

### extract():
Extracts the values from argv, converts them to integers and finally assigns them to corresponding smaller malloc'd int arrays called mapVals, playerVals and goalVals.

Using int arrays lowers memory usage as everytime these values are needed in a different function, 
they are passed by reference not value.

After extracting the values, the function then sends the int arrays to setup().

Once the progrsm returns to this function, that means the game has finished and thus it frees the malloc'd int arrays as they are no longer necessary.

<br>
<br>

### setup():

Checks that all numbers are valid by running checks using the macros written in "macros.h"

After every number is deemed valid, they are then sent to increase() to account for the border

After they have been increased the int arrays are then sent to createMap() in map.c

<br>
<br>

### increase():
Increases all values in an import int array by an imported parameter which depends on whether they are values for the map or values for the player or goal.

map values are increased by 2 to account for the order in both extremes (lower and upper) whereas player and goal values only have to account for the border on the lower extreme so they are only increased by 1.

<br>
<br>

# map.c
### createMap():
Mallocs a new 2-D character array with the amount of sub-arrays as the number of rows the user entered earlier.

Then for each sub-array it mallocs a character array the size of the amount of columns the user enterd earlier.

Once the 2-D array has been fully malloc'd, the first row is then filled with '*' characters to visulaise the upper border.

Then, all rows between the top and bottom, not inclusive, are filled with blank spaces apart from the left-most and right-most columns. These will contain '*' characters to visualise the left and right border.

The bottom row is then filled with '*' characters to visualise the lower border.

The player and goal are then palced in their respective locations stipulated by the user earlier.

The newly created 2-D character array and the 3 int arrays are sent to game() in game.c to begin runtime of the game

Once th program returns to this function, that means the game is finised and thus the 2-D array can now be freed.

The sub-arrays are freed first in the reverse order they were allocated.

Once they are freed the main array can be freed

<br>
<br>

### printMap():
2 for loops, one nested, to print the entire contents of the map.

Also prints the available movement options to the user

<br>
<br>

### floorCollapse()
Begins a while loop that repeatedly creates a random locatiion in the map to place an 'X' character. The loop only exits when the location generated does not contain either the player, the goal, the border, or another collapsed floor.

After the location is deemed valid, and 'X' is placed at that location in the map.

# game.c
### game()
Begins by calling printMap() from map.c

Once the map has been initially printed, a while loop begins that doesn't exit until the player has either won or lost the game. 

Winning is dertermined by the return value given by the function chosen by movePlayer

After the player has moved floorCollapse() from map.c is then called

playerTrapped()and goalBlocked() from end.c are then called to check if the player has lost.

finally the printMap() is called after all changes for this iteration have been made.

Once the player has either won or lost, endMessage() from end.c is called to tell the player the outcome.

<br>
<br>

# movement.c

### validMove();
Assigns some temporary varaibles to the player current location.

A while loop then starts which will only exits once the player location before the loop is different from the location that would be used outside the loop.

Inside the loop disableBuffer() is called to allow user input without pressing "enter" each time and user input is then gotten.

enableBuffer() is then called as to not break the terminal

movePlayer() is then called which return a pointer to a function to carry out the player's desired movement.

The corresponding function is then called and movement is carried out.

The won condition return by the movement function is the returned to game()

<br>
<br>

### movePlayer():
Takes in the character entered by the user for which way they want to move and returns a pointer to the function that will carry oit that movement

### moveUp():
Initially checks if the player is moving onto the goal
and if they are then iy replcaes the goal with the player and returns that the player has won


If BORDERLESS hasn't been defined, then the player cannot move to a location occupied by either a 'X' or '*' character.

Otherwise the player is moved to the empty space, a ' ' character is placed where they have moved to, and the players location information is updated to reflect the chage in position.

If BORDERLESS has been defined, then the player still cannot move onto an 'X', however they CAN "move" onto a '*' character provided the bottom row in the corresponding column does NOT contain a 'X'

This will allow the player to "warp" from the top row to the bottom row.

Once again if they "warp" onto the goal location, then they are deemed to have won the game.

<br>
<br>

### moveDown();
The same idea as moveUp() except this method allows the player to warp from the bottom row to the top row, in the case of BORDERLESS being defined.

<br>
<br>

### moveLeft():
The same idea as moveUp() except this method allows the player to warp from the left-most column to the right-most column, in the case of BORDERLESS being defined.

<br>
<br>

### moveRight():
The same idea as moveUp() except this method allows the player to warp from the right-most column to the left-most column, in the case of BORDERLESS being defined.

<br>
<br>

# end.c
### playerTrapped():
If BORDERLESS has not been defined then all that is needed to check is whether the player is surrounded by either a collapsed floor or the border.

However, if BORDERLESS has been defined, then after the check for surrouned by collapsed floors, the function then checks if the player is unable to warp in any direction.

The player is unable to warp if the the place they would warp to contains a collapsed floor

That location and all other surrounding elements contain a collasped floor, then the player is deemed trapped.

The function checks this for each direction the player can warp

Once it has checked each direction, it then needs to check that the player is not trapped in a corner.

A corner means the player can warp in two directions and it must check both of the corresponding warp locations for collapsed floors.

If any of these conditions have been met the player is deemed to have lost.

As the if statements are largely similar in structure, I could've have refactored them into 1 or 2 functions and just called that. However, this would mean I would need 8 variables to hold the return values and then check if any of them were returned as true. I decided that while it doesn't look the greatest and is very long, the way I've implemented it is the better way as then there is only one variable returning the true or false condition.

<br>
<br>

### goalBlocked():
Same idea as playerTrapped except that it now checks it in respect to the goal and whether or not it is inaccesible by the player.

Again, for BORDERLESS being defined, then it must check the locations that the player could warp from and also if the goal is blocked in a corner.





