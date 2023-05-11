Jacob Jonas, 18439731
README for UCP Assignment 1

Main.c
Contains the main function for the program. Used for command line argument checking

setup.c
Contains methods releveant to processsing the argv values and getting ready to create the map

setup.h
header file for setup.c 

map.c
Contains functions relating to creating, printing and editing the map

map.h
header file for map.c

game.c
Contains the funtion responsible for handling the game during run-time. mainly calls other fucntions to carry out tasks.

game.h 
header file for game.c 

movement.c
Conatins functions relating to moving the player around the map, These functions also determine of the player has won the game

movement.h 
header file for movement.c 

end.c 
Contains functions realting to loss conditions of the game as well as printing the ending message depending on the game outcome.

end.h 
header file for end.c 

terminal.c 
Provided by Antoni. Used to disable and re-enable the buffer allowing for user input without pressing enter each time 

terminal.h 
Provided by Antoni. header file for terminal.c 

random.c 
Provided by Antoni. Contains random number generator

random.h 
Provided by Antoni. header file for random.c 

makefile
Used to compile and link each .c file into one executable called "escape"

macros.h
Contains the defintions of TRUE and FALSE, as well as the valid number checks used in setup.c
