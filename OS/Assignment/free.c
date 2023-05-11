#include <stdio.h>
#include <stdlib.h>
#include "free.h"
#include "structs.h"
#include "queue.h"

void freeStructs(Bank *bank, Information *info, TellerMethod *t1, TellerMethod *t2, TellerMethod *t3, TellerMethod *t4, CustomerMethod *c)
{
    /* Free the structs used for the thread specific methods */
    free(t1);
    t1 = NULL;
    free(t2);
    t2 = NULL;
    free(t3);
    t3 = NULL;
    free(t4);
    t4 = NULL;

    free(c);
    c = NULL;

    pthread_mutex_destroy(&(info->logLock));
    pthread_mutex_destroy(&(info->queueLock));
    pthread_mutex_destroy(&(info->terminateLock));
    free(info);
    info = NULL;

    freeBank(bank);
}

void freeBank(Bank *bank)
{
    freeTeller(bank->teller1);
    freeTeller(bank->teller2);
    freeTeller(bank->teller3);
    freeTeller(bank->teller4);
    freeAllCustomers(bank->customers);

	free(bank);
	bank = NULL;
}

void freeTeller(Teller *t)
{
    /* Free the strings allocated by getTime() */
    free(t->startTime);
    t->startTime = NULL;
    free(t->terminateTime);
    t->terminateTime = NULL;

    free(t);
    t = NULL;
}

void freeAllCustomers(AllCustomers *c)
{
    /* Free the linked list queue */
    freeLinkedList(c->c_queue, &freeCustomer);
    free(c);
    c = NULL;
}

void freeCustomer(void *data)
{
    Customer *c = (Customer*)data;

    /* Free the strings allocated by getTime() */
    free(c->arrivalTime);
    c->arrivalTime = NULL;
	free(c->responseTime);
	c->responseTime = NULL;
    free(c->finishTime);
    c->finishTime = NULL;

    free(c);
    c = NULL;
}
