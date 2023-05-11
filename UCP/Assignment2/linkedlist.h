#ifndef LINKEDLIST_H
#define LINKEDLIST_H

typedef void (*FreePTR)(void*);

typedef struct ListNode
{
    void *data;
    struct ListNode *next;
}ListNode;

typedef struct List
{
    ListNode *head;
    int count;
    int max;
}List;

void insertFirst(List*);
void* removeFirst(List*);
void freeLinkedList(List*, FreePTR);

#endif
