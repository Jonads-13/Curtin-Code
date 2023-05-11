#ifndef FUNCTIONALITY_H
#define FUNCTIONALITY_H

#include "queue.h"
#include "structs.h"

void* customer(void*);
void* teller(void*);
void logTermination(TellerMethod*);
int getServiceTime(char, Information*);
char* getTime(void);
void finalLog(Bank*, Information*);

#endif
