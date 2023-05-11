#include <stdio.h>
#include <stdlib.h>

/* define the struct in the header file */
typedef struct Date
{
    int day;
    int month;
    int year;
}DateNew;

typedef struct Student
{
    char name[1000];
    char ID[9];
    DateNew DOB;
    int age;
    char unit[100];
}StudentNew; 

int main(int argc, char** argv)
{
    StudentNew myStudent1, myStudent2, *myStudentP;

    myStudentP = (StudentNew*)malloc(sizeof(StudentNew));

    StudentNew myStudentI = {"Jacob Jonas", "18439731", {23,12,1996}, "Unix and C Programming"};

    myStudent1.age = 100;
    myStudent2.age = myStudent1.age + 1;

    myStudent1.DOB.day = 10;

    myStudent2 = myStudent1;

    /* Both these mean the same thing */
    (*myStudentP).age = 50;
    myStudentP->age = 50;



    free(myStudentP);
    myStudentP = NULL;

    return 0;
}