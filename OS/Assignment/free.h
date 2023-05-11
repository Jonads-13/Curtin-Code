#ifndef FREE_H
#define FREE_H

#include "structs.h"
#include "queue.h"

void freeStructs(Bank*, Information*, TellerMethod*, TellerMethod*, TellerMethod*, TellerMethod*, CustomerMethod*);
void freeBank(Bank*);
void freeTeller(Teller*);
void freeAllCustomers(AllCustomers*);
void freeCustomer(void*);


#endif
