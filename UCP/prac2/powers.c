#include <stdio.h>

/*int powersOfTwo();

int main(void)
{
    int i;
    int power;
    int choice;

    scanf("%d", &choice);

    for(i = 0; i < choice; i++)
    {
        power = powersOfTwo();
    }

    printf("%d", power);

    return 0;
}*/

int powersOfTwo(void)
{
    static int power = 1;

    power = power * 2;

    return power;
}
