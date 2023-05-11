#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "boolean.h"






/*
 * Title:     insertFirst
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Import:    list (List*)
 * Export:    none
 * Assertion: insert a new node into the import list
 */

void insertFirst(List *list)
{
    /* Malloc the memory for the new node */
    ListNode *newNode = (ListNode*)malloc(sizeof(ListNode));
    newNode->next = NULL;

    /* list is empty */
    if(list->head == NULL)
    {
        list->head = newNode;
    }
    else
    {
        /* The newNode's next points to the current head */
        newNode->next = list->head;
        list->head = newNode; /* head is now the newNode */
    }

    list->count++;
}






/*
 * Title:     insertLast
 * Author:    Jacob Jonas, 18439731
 * Created:   24/04/2023
 * Modified:  24/04/2023
 * Import:    List
 * Export:    none
 * Assertion: Insert a node at the end of the list
 */

void insertLast(List *list)
{
    /* Malloc the memory for the new node */
    ListNode *newNode = (ListNode*)malloc(sizeof(ListNode));
    newNode->next = NULL;

    /* list is empty */
    if(list->head == NULL)
    {
        list->head = newNode;
    }
    else
    {
        ListNode *cur = list->head;
        while(cur->next != NULL)
        {
            cur = cur->next;
        }

        cur->next = newNode;
    }
}






/*
 * Title:     removeFirst
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Import:    list (List*)
 * Export:    data (void*)
 * Assertion: remove the first node from the imported list and return it's data
 */

void* removeFirst(List* list)
{
    void *data;
    ListNode *curHead;
    
    /* There is only one node in the list */
    if(list->head->next == NULL)
    { 
        /* Get the data to return */
        data = list->head->data;

        /* Free the head */
        free(list->head);
        list->head = NULL;
    }
    else
    {
        /* Get the data to return */
        data = list->head->data;

        /* Copy the node to be free'd */
        curHead = list->head;

        /* head points the second node in the list */
        list->head = list->head->next;

        /* free the first node */
        free(curHead);
        curHead = NULL;
    }

    list->count--;

    return data;
}

int full(List *list)
{
    if(list->count == list->max)
    {
        return TRUE;
    }
    else
    {
        return FALSE;
    }

}






/*
 * Title:     freeLinkedList
 * Author:    Jacob Jonas, 18439731
 * Created:   09/10/2022
 * Modified:  09/10/2022
 * Import:    list (List*), freeptr (FreePTR)
 * Export:    none
 * Assertion: Free all the nodes and data in an import list
 */

void freeLinkedList(List* list, FreePTR freePtr)
{
    ListNode *cur, *next;

    /* start the iteration at the head */
    cur = list->head;

    /* Iterate until the end of the list */
    while(cur != NULL)
    {
        /* Get the next node before freeing the current node */
        next = cur->next;

        /* send the void* data to the imported function to be free'd */
        (freePtr)(cur->data);
        free(cur); /* free the current node */

        /* update the current node for the next iteration */
        cur = next;
    }

    /* free the overally list */
    free(list);
    list = NULL;
}
