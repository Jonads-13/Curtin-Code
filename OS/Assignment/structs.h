#ifndef STRUCTS_H
#define STRUCTS_H

#include <pthread.h>
#include "queue.h"

/* Holds command line parameters and shared variables */
typedef struct Information
{
    int m; /* Queue max length */
    int c; /* Customer entering queue interval */
    int w; /* Withrawal service time */
    int d; /* Deposit service time */
    int i; /* Information service time */
    int finished; /* Whether the customer file has been read completely */
    int numFinishedTellers; /* Used for checking and changing how many tellers have terminated */
    pthread_mutex_t logLock; /* Used to protect r_log */
    pthread_mutex_t queueLock; /* Used to protect c_queue */
    pthread_mutex_t terminateLock; /* Used to protect numFinishedTellers */
}Information;

typedef struct Teller
{
    pthread_t tid; /* Teller thread */
    int number; /* Identifier */
    char* startTime;
    char* terminateTime;
    int numCustomersServed;
}Teller;

typedef struct Customer
{
    int number;
    char service;
    int serviceLength; 
    char* arrivalTime;
	char* responseTime;
    char* finishTime;
}Customer;

typedef struct AllCustomers
{
    pthread_t tid;
    List *c_queue;
}AllCustomers;

typedef struct Bank
{
    Teller *teller1;
    Teller *teller2;
    Teller *teller3;
    Teller *teller4;
    AllCustomers *customers;
}Bank;

/* Used to send parameters to the method run by the teller threads */
typedef struct TellerMethod
{
    Teller *teller;
    Information *info;
    List *c_queue;
    Bank *bank;
}TellerMethod;

/* Used to send parameters to the method run by the customer thread */
typedef struct CustomerMethod
{
    List *c_queue;
    Information *info;
}CustomerMethod;

#endif
