#include <stdio.h>

/*Forward declarations*/
int factorial(int n); 
long factorialRecursive(int n);

int main(void)
{
    int close = 0, input = 0;

    while(close == 0)
    {
        printf("Enter a positive number:");

        scanf("%d", &input);

        if(input >= 0)
        {
            int calcFac = factorialRecursive(input);
            printf("%d", calcFac);
        }
        else
        {
            printf("Input not valid");
            close = 1;
        }
        
    }

    return 0;
}

int factorial(int n)
{
    int fact = n;

    if(n > 0)
    {
        while(n > 1)
        {
            fact = fact*(n-1);
            n--;
        }
    }
    else
    {
        fact = 1;
    } 

    return fact;
}

long factorialRecursive(int n)
{
    long factorial = 1;

    if(n == 0)
    {
        factorial = 1;
    }
    else
    {
        factorial = n * factorialRecursive(n-1);
    }

    return factorial;
}
