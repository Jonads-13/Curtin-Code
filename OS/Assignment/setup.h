#ifndef SETUP_H
#define SETUP_H

#include "structs.h"

void extractArgv(char**);
void initBank(Information*);
void beginSimulation(Bank*, Information*);
int valid(Information*);
void usage(void);

#endif
