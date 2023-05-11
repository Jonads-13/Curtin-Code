#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <pthread.h>
#include "functionality.h"
#include "free.h"
#include "structs.h"
#include "queue.h"
#include "macros.h"





void* customer(void *thread)
{
    /* Type cast to relevant struct */
    CustomerMethod *customerThread = (CustomerMethod*)thread;
    FILE *customerFile = fopen(CUSTOMER_FILE, "r");
    FILE *logFile = NULL;
    int nReads, tempCNum;
    char tempService;

    /* File reading failed */
    if(customerFile == NULL)
    {
        perror(CUSTOMER_FILE);
        return NULL;
    }
    else
    {
        /* Begin file reading */
        nReads = fscanf(customerFile, "%d %c", &tempCNum, &tempService);

        while(nReads == 2) /* While the file format is correct */
        {
            /* Malloc memory for new customer */
            Customer *c = (Customer*)malloc(sizeof(Customer));

            /* Wait until the queue has a free slot */
            while(isFull(customerThread->c_queue)) { /* Do Nothing */ } 

            /* Initialise new customer */
            c->number = tempCNum;
            c->service = tempService;
            c->serviceLength = getServiceTime(c->service, customerThread->info);

            /* Accessing shared variable so using mutex lock */
            pthread_mutex_lock(&(customerThread->info->queueLock));
            insertLast(customerThread->c_queue, c); /* Place sutomer into the queue */
            c->arrivalTime = getTime();

            pthread_mutex_lock(&(customerThread->info->logLock));

            logFile = fopen(LOG_FILE, "a");
            fprintf(logFile, "-----------------------------------------------------------------------\n");
            fprintf(logFile, "%d: %c\n", c->number, c->service);
            fprintf(logFile, "Arrival time: %s\n", c->arrivalTime);
            fprintf(logFile, "-----------------------------------------------------------------------\n\n");
            fclose(logFile);
            
            pthread_mutex_unlock(&(customerThread->info->queueLock)); 
            pthread_mutex_unlock(&(customerThread->info->logLock)); 

            /* Wait specifed time until reading next customer from file */
            sleep(customerThread->info->c);

            /* Read next line from the file */
            nReads = fscanf(customerFile, "%d %c", &tempCNum, &tempService);
        }

        fclose(customerFile);
        customerThread->info->finished = TRUE;

        return NULL;
    }
}





void* teller(void* thread)
{
    /* Type cast to relevant struct */
    TellerMethod *tellerThread = (TellerMethod*)thread;
    Customer *c = NULL;
    FILE *logFile = NULL;

    tellerThread->teller->startTime = getTime();

    /* Loop until the customer thread has finished and the queue is empty */
    while((tellerThread->info->finished == FALSE) || (tellerThread->c_queue->count != 0))
    {
        /* Accessing shared data of the queue and log file */
        pthread_mutex_lock(&(tellerThread->info->queueLock));
        
        /* Wait until the queue has customers in it */
        if(tellerThread->c_queue->count != 0)
        {
            c = (Customer*)removeFirst(tellerThread->c_queue);
		    c->responseTime = getTime();

            pthread_mutex_unlock(&(tellerThread->info->queueLock));


            pthread_mutex_lock(&(tellerThread->info->logLock));

            logFile = fopen(LOG_FILE, "a");
            fprintf(logFile, "Teller: %d\n",  tellerThread->teller->number);
            fprintf(logFile, "Customer: %d\n", c->number);
            fprintf(logFile, "Arrival time: %s\n", c->arrivalTime);
            fprintf(logFile, "Response time: %s\n\n", c->responseTime);
            fclose(logFile);

            pthread_mutex_unlock(&(tellerThread->info->logLock));

            /* Simulate the time it takes to perform the service */
            sleep(c->serviceLength);
            c->finishTime = getTime();

            pthread_mutex_lock(&(tellerThread->info->logLock));

            logFile = fopen(LOG_FILE, "a");
            fprintf(logFile, "Teller: %d\n",  tellerThread->teller->number);
            fprintf(logFile, "Customer: %d\n", c->number);
            fprintf(logFile, "Arrival time: %s\n", c->arrivalTime);
            fprintf(logFile, "Completion time: %s\n\n", c->finishTime);
            fclose(logFile);
            tellerThread->teller->numCustomersServed++;

            pthread_mutex_unlock(&(tellerThread->info->logLock));

	        freeCustomer((void*)c); /* Free the removed customer */
        }
        else{ pthread_mutex_unlock(&(tellerThread->info->queueLock)); }
    }

    pthread_mutex_lock(&(tellerThread->info->terminateLock));
    if(tellerThread->info->numFinishedTellers == 3)
    {
        pthread_mutex_unlock(&(tellerThread->info->terminateLock));
        finalLog(tellerThread->bank, tellerThread->info);
        logTermination(tellerThread);
    }
    else
    {
        tellerThread->info->numFinishedTellers++;
    
        pthread_mutex_lock(&(tellerThread->info->logLock));

        logTermination(tellerThread);

        pthread_mutex_unlock(&(tellerThread->info->logLock));
        pthread_mutex_unlock(&(tellerThread->info->terminateLock));
    }

    return NULL;
}


/* Wrotes the termiantion information for a teller to r_log */
void logTermination(TellerMethod *tellerThread)
{
        /* Write termination information to the log file */
        FILE *logFile = fopen(LOG_FILE, "a");
        tellerThread->teller->terminateTime = getTime();
        fprintf(logFile, "Teller: %d\n", tellerThread->teller->number);
        fprintf(logFile, "#served customers: %d\n", tellerThread->teller->numCustomersServed);
        fprintf(logFile, "Start time: %s\n", tellerThread->teller->startTime);
        fprintf(logFile, "Terminate time: %s\n\n", tellerThread->teller->terminateTime);
        fclose(logFile);
}




/* Determines which service duration to return */
int getServiceTime(char service, Information *info)
{
    switch (service)
    {
        case 'W':
            return info->w; 
        break;

        case 'D':
            return info->d;
        break;

        case 'I':
            return info->i;
        break;
        
        default:
            return 1; /* If file has mistake, then return a default value of 1 second */
        break;
    }
}



/* Function to get the current time as a String in hours:minutes:seconds */
char* getTime(void)
{   
    char* timeString = (char*)malloc(9 * sizeof(char));
    time_t rawTime;
    struct tm *timeInfo;
    
    time(&rawTime);
    timeInfo = localtime(&rawTime);

    /* Format the time as a string */
    sprintf(timeString, "%02d:%02d:%02d", timeInfo->tm_hour, timeInfo->tm_min, timeInfo->tm_sec);

    return timeString;
}



/* Prints the summary information of the program to the log file */
void finalLog(Bank* bank, Information *info)
{
    FILE *logFile = fopen(LOG_FILE, "a");
    int total = bank->teller1->numCustomersServed 
              + bank->teller2->numCustomersServed 
              + bank->teller3->numCustomersServed 
              + bank->teller4->numCustomersServed;

    fprintf(logFile, "Teller Statistics\n");
    fprintf(logFile, "Teller-1 served %d customers\n", bank->teller1->numCustomersServed);
    fprintf(logFile, "Teller-2 served %d customers\n", bank->teller2->numCustomersServed);
    fprintf(logFile, "Teller-3 served %d customers\n", bank->teller3->numCustomersServed);
    fprintf(logFile, "Teller-4 served %d customers\n", bank->teller4->numCustomersServed);
    fprintf(logFile, "\nTotal number of customers served: %d customers\n\n", total);

    fclose(logFile);
}
