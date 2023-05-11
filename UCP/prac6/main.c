#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv)
{
    FILE *source, *dest;
    int readChar;
    char writeChar;


    if(argc < 3)
    {
        printf("Error specify more files");
    }
    else
    {
        source = fopen(argv[1], "r");
        if(source == NULL)
        {
            perror("Error opening file to read");
        }
        else
        {
            dest = fopen(argv[2], "w");
            if(dest == NULL)
            {
                perror("Error opeing file to write");
            }
            else
            {  
                readChar = fgetc(source);

                while(readChar != EOF)
                {
                    writeChar = (int)readChar;
                    fputc(writeChar, dest);
                    readChar = fgetc(source);

                }

                fclose(source);
                fclose(dest);
            }          
        }






    }
    

    return 0;
}
