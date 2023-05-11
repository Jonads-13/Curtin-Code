#ifndef SETUP_H
#define SETUP_H

#include "structs.h"

void extract(char**);
void setup(State*, FILE*);
Ant* initAnt(Ant*, char**, int*, int*, char*);
void readColorState(State*, FILE*);

#endif
