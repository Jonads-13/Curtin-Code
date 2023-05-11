#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv)
{
    /* File pointers */
    FILE *fp, *fw;
    int a, b, c;
    int line = 1;

    if(argc > 1)
    {
        fp = fopen(argv[1], "r"); /* open file for reading */

        if(fp == NULL)
        {
            /* Get most recent error and print it to the terminal */
            perror("error");
        }
        else
        {
            fw = fopen(argv[2], "w");

            /* read the file */
            while(fscanf(fp,"%d %d %d", &a, &b, &c) == 3)
            {
                fprintf(fw, "line %d: %d %d %d\n", line, a, b, c);
                line++;
            }

            /* close file ony after the file as read */
            fclose(fp);
        }
    }
    else
    {
        printf("no file\n");
    }


    return 0;
}
