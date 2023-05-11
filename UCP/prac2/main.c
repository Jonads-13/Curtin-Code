#include <stdio.h>
#include "macros.h"
#include "powers.h"

int main(void)
{
    int input, i, power;

    printf("Enter an integer:\n");
    scanf("%d", &input);

    if(BETWEEN(1,input,31))
    {
        for(i = 0; i < input; i++)
        {
            power = powersOfTwo();
        }
        printf("%d\n", power);

    }
    else
    {
        printf("Input was not within range");
    }

    return 0;      
}
