#ifndef LINKEDLIST_H
#define LINKEDLIST_H

/* Previoulsy submitted for UCP in Semester 2, 2022 */

typedef void (*FreePTR)(void*);

typedef struct ListNode
{
    void *data;
    struct ListNode *next;
}ListNode;

typedef struct List
{
    ListNode *head;
    ListNode *tail;
    int count;
    int max;
}List;

void insertFirst(List*, void*);
void insertLast(List*, void*);
void* removeFirst(List*);
int isFull(List*);
void freeLinkedList(List*, FreePTR);

#endif
