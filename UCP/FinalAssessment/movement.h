#ifndef MOVEMENT_H
#define MOVEMENT_H

#include "structs.h"

void moveLang(State*);
void moveRand(Ant*, char**);
void moveHorizontally(State*);
void moveRight(Ant*, char**);
void moveLeft(Ant*, char**);
void moveVertically(State*);
void moveDown(Ant*, char**);
void moveUp(Ant*, char**);


#endif
