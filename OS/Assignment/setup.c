#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "setup.h"
#include "functionality.h"
#include "free.h"
#include "queue.h"
#include "structs.h"
#include "macros.h"




void extractArgv(char** argv)
{
    Information *info = (Information*)malloc(sizeof(Information));

    /* Convert values from command line into integers*/
    info->m = atoi(argv[1]);
    info->c = atoi(argv[2]);
    info->w = atoi(argv[3]);
    info->d = atoi(argv[4]);
    info->i = atoi(argv[5]);

    if(valid(info)) /* Check the values are valid */
    {
        info->finished = FALSE;
        info->numFinishedTellers = 0;
        pthread_mutex_init(&(info->logLock), NULL);
        pthread_mutex_init(&(info->queueLock), NULL);
        pthread_mutex_init(&(info->terminateLock), NULL);

        initBank(info);
    }
    else
    {
        usage(); /* Display usage information */
    }
}




/* Inialise the bank struct */
void initBank(Information *info)
{
    Bank *bank = (Bank*)malloc(sizeof(Bank));

    /* Malloc and initialise each teller in the bank*/
    bank->teller1 = (Teller*)malloc(sizeof(Teller));
    bank->teller1->number = 1;
    bank->teller1->numCustomersServed = 0;

    bank->teller2 = (Teller*)malloc(sizeof(Teller));
    bank->teller2->number = 2;
    bank->teller2->numCustomersServed = 0;

    bank->teller3 = (Teller*)malloc(sizeof(Teller));
    bank->teller3->number = 3;
    bank->teller3->numCustomersServed = 0;

    bank->teller4 = (Teller*)malloc(sizeof(Teller));
    bank->teller4->number = 4;
    bank->teller4->numCustomersServed = 0;


    /* Malloc and initialise customer queue */
    bank->customers = (AllCustomers*)malloc(sizeof(AllCustomers));
    bank->customers->c_queue = (List*)malloc(sizeof(List));
    bank->customers->c_queue->max = info->m;
    bank->customers->c_queue->count = 0;
    bank->customers->c_queue->head = NULL;
    bank->customers->c_queue->tail = NULL;

    beginSimulation(bank, info);
}





void beginSimulation(Bank *bank, Information *info)
{
    /* Create the specific structs needed for pthread_create() */
    TellerMethod *teller1 = (TellerMethod*)malloc(sizeof(TellerMethod));
    TellerMethod *teller2 = (TellerMethod*)malloc(sizeof(TellerMethod));
    TellerMethod *teller3 = (TellerMethod*)malloc(sizeof(TellerMethod));
    TellerMethod *teller4 = (TellerMethod*)malloc(sizeof(TellerMethod));
    CustomerMethod *customerThread = (CustomerMethod*)malloc(sizeof(CustomerMethod));
    
    /* Appying the corresponding elements in bank to the matching thread */
    teller1->teller = bank->teller1;
    teller1->info = info;
    teller1->c_queue = bank->customers->c_queue;
    teller1->bank = bank;
    
    teller2->teller = bank->teller2;
    teller2->info = info;
    teller2->c_queue = bank->customers->c_queue;
    teller2->bank = bank;
    

    teller3->teller = bank->teller3;
    teller3->info = info;
    teller3->c_queue = bank->customers->c_queue;
    teller3->bank = bank;

    teller4->teller = bank->teller4;
    teller4->info = info;
    teller4->c_queue = bank->customers->c_queue;
    teller4->bank = bank;

    customerThread->c_queue = bank->customers->c_queue;
    customerThread->info = info;

    /* Create the five necessary threads */
    pthread_create(&(bank->teller1->tid), NULL, teller, (void*)teller1);
    pthread_create(&(bank->teller2->tid), NULL, teller, (void*)teller2);
    pthread_create(&(bank->teller3->tid), NULL, teller, (void*)teller3);
    pthread_create(&(bank->teller4->tid), NULL, teller, (void*)teller4);
    pthread_create(&(bank->customers->tid), NULL, customer, (void*)customerThread);

    /* Wait for each thread to terminate */
    pthread_join(bank->teller1->tid, NULL);
    pthread_join(bank->teller2->tid, NULL);
    pthread_join(bank->teller3->tid, NULL);
    pthread_join(bank->teller4->tid, NULL);
    pthread_join(bank->customers->tid, NULL);

    /* Begin freeing all allocated structs */
    freeStructs(bank, info, teller1, teller2, teller3, teller4, customerThread);
}





int valid(Information *info)
{
    /* If even one value is invalid*/
    if((info->m <= 0) || (info->c <= 0) || (info->w <= 0) || (info->d <= 0) || (info->i <= 0))
    {
        return FALSE;
    }
    else
    {
        return TRUE;
    }
}





void usage(void)
{
        /* Usage information */
        printf("Command line arguments should be as follows:\n");
        printf("\n./cq m c w d i\n\nWhere:\n");
        printf("m = Max length of the customer queue as a positive integer\n");
        printf("c = Amount of time between each customer joins the queue as a positive integer\n");
        printf("w = Amount of time to perform a withdrawal service as a positive integer\n");
        printf("d = Amount of time to perform a deposit service as a positive integer\n");
        printf("i = Amount of time to perform an information service as a positive integer\n");
}
