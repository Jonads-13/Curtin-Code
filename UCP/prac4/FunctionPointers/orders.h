#ifndef ORDERS_H
#define ORDERS_H

typedef void (*FUNCPTR)(int*,int*,int*);
FUNCPTR orders(char choice);
void ascending2(int *ptr1, int *ptr2);
void ascending3(int *ptr1, int *ptr2, int *ptr3);
void descending2(int *ptr1, int *ptr2);
void descending3(int *ptr1, int *ptr2, int *ptr3);

#endif


