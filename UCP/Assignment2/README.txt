Jacob Jonas, 18439731
README for UCP Assignment 2

Program is run by using the command ./escape <map file name>

If the proper command line arguments are not passed then usage information will be shown for the program.

The map file should have the following format:

num1 num2
num1 num2 char
num1 num2 char
...
etc.

The first two numbers correspond to the map size. num1 being amount of rows, num2 being the amount of columns

Every line after is the row and column location of a either the player 'P', goal 'G', or a collapsed floor 'X'

A file called map.txt has been included for use.

There should only be one player and one goal present in the file, 
however there can be as many collapsed floors as can fit in the map.

After the map file has been read and the map displayed, follow the prompts to press either the 'w', 's', 'a', 'd' or 'u' keys 
to move up, down, left, right or undo your previous move respectively.

The game ends when the player either reaches the goal and they win,
or if they or the goal get surrounded by 'X' characters 

---------------------------------------------------------------------------------------------------

main.c
Contains the main function for the program. Used for command line argument checking

structs.h
Contains type definitions of the various structs used in thr program

linkedlist.c
Contains the methods used by the generic linked list

linkedlist.h
Header file for linkedlist.c. Also contains the type definition of the generic linked list itself

setup.c
Contains methods releveant to processsing the argv values and getting ready to create the map

setup.h
header file for setup.c 

map.c
Contains functions relating to creating, printing and placing collapsed floors on the map

map.h
header file for map.c

game.c
Contains the function responsible for handling the game during run-time, 
as well as printing the ending message depending on the game outcome. 

Mainly calls other fucntions to carry out tasks.

game.h 
header file for game.c 

movement.c
Contains functions relating to moving the player around the map, These functions also determine of the player has won the game

movement.h 
header file for movement.c 

end.c 
Contains functions relating to loss conditions of the game.

end.h 
header file for end.c 

free.c
Contains functions related to freeing structs used i the program

free.h
Header file for free.c

terminal.c 
Provided by Antoni. Used to disable and re-enable the buffer allowing for user input without pressing enter each time 

terminal.h 
Provided by Antoni. header file for terminal.c 

random.c 
Provided by Antoni. Contains random number generator

random.h 
Provided by Antoni. header file for random.c 

color.c
Provided by Antoni. Contains functions required to change the colour of either the printed text or the background

color.h
Provided by Antoni. Header file for color.c

makefile
Used to compile and link each .c file into one executable called "escape"

macros.h
Contains the type defintions of TRUE and FALSE, BLANK character and the DirectionPtr
