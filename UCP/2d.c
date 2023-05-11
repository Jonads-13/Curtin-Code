#include <stdio.h>

int main(int argc, char** argv)
{
    int i, t = 0;
    
    for(i = 0; i < 20; i++)
    {
        i++;
        t -= 1;
        printf("%d %d\n", i/2+1, t);
    }

    return 0;
}