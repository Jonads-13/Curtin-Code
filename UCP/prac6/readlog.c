#include <stdio.h>
#include <stdlib.h>
#include "string.h"

int main(int argc, char** argv)
{
    FILE *log;
    char beg[7], rest[1000], *substring;
    int hour, minute, second, timeSince;
    

    if(argc < 2)
    {
        printf("Error enter a file name in the command line");
    }
    else
    {
        log = fopen(argv[1], "r");
        if(log == NULL)
        {
            perror("Error in reading file");
        }
        else
        {

            fscanf("3%s %d %d:%d:%d 30%s", log);


            while(fgets(beg, 7, log) != NULL)
            {
                fscanf(log, "%d", &hour);
                fgetc(log);
                fscanf(log, "%d", &minute);
                fgetc(log);
                fscanf(log, "%d", &second);

                fgets(rest, 1000, log);

                substring = strstr("fail", rest);

                if(substring == NULL)
                {
                    printf("substring is null\n");
                }
                else
                {
                    printf("substring isn't null");
                    timeSince = (hour*3600) + (minute*60) + second;
                    printf("%d seconds since:  %s\n",timeSince, rest);
                }
            }
            
            fclose(log);
        }
    }

    return 0;
}
