#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>

int main(int argc, char** argv)
{
    pid_t pid;
    pid = fork();

    if(pid == 0)
    {
        printf("Child process\n");
    }
    else
    {
        pid = fork();

        if(pid == 0)
        {

            printf("Child process\n");
        }
        else
        {
            pid = fork();
            if(pid == 0)
            {
                printf("Child process\n");
            }
            else
            {
                printf("Parent Process\n");
            }
        }

    }

    return 0;
}
