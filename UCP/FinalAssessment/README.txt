Jacob Jonas, 18439731
README for UCP Final Assessment

Program is run by using the command ./ant <file_name> <teps_amount> <sleep_length>

If the proper command line arguments are not passed then usage information will be shown for the program.

The map file should have the following format:

num1 num2
num1 num2 char
num1 num2 char
0 0 0 0 0
0 1 1 0 0
0 0 1 0 1
0 0 0 1 1
0 1 1 1 1

The first two numbers correspond to the map size. num1 being amount of rows, num2 being the amount of columns.

The second line contains the starting location and orientation of the Langton's Ant. Orientation being either '^', '>', 'v' or '<'. 

The third line contains the same information as line 2 but for the random ant.

The remaining lines contain the color state of the map with '0' representing no background colour and '1' representing a green background colour

As this represents the map it needs to be equal in size to the dimensions stipulated in the first line. ie in the above example the numbers one the
first line would need to be '5' and '5' as that is the dimensions of the colour state map provided

4 files called map.txt, map2.txt, map3.txt and map4.txt has been included to help understand the format and for use as well..


---------------------------------------------------------------------------------------------------

main.c
Contains the main function for the program. Used for command line argument checking

structs.h
Contains type definitions of the various structs used in thr program

setup.c
Contains methods releveant to processsing the argv values and getting ready to create the map

setup.h
header file for setup.c 

map.c
Contains functions relating to creating/allocating 2-D arrays and printing the display

map.h
header file for map.c

runtime.c
Contains the function responsible for handling the simulation.

Mainly calls other fucntions to carry out tasks.

runtime.h 
header file for game.c 

movement.c
Contains functions relating to moving the ants on the map.

movement.h 
header file for movement.c 

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

newSleep.c
Provided by Antoni. Contains the function reqiured to make the program sleep

newSleep.h
Provided by Antoni. Header file for newSleep.c

makefile
Used to compile and link each .c file into one executable called "escape"

macros.h
Contains the type defintions of TRUE and FALSE, BLANK character and the DirectionPtr
