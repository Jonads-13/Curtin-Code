#ifndef MOVEMENT_H
#define MOVEMENT_H

typedef int (*FUNCPTR)(char**, int*, int*);

int validMove(char**, int*, int*);
FUNCPTR movePlayer(char*);
int moveUp(char**, int*, int*);
int moveDown(char**, int*, int*);
int moveLeft(char**, int*, int*);
int moveRight(char**, int*, int*);


#endif
