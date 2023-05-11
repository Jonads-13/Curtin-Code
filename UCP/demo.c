#include<stdio.h>
#include<stdlib.h>

void isEven(int x); /*forward declaration*/


int main()
{
	int n;

	scanf("%d",&n);

	isEven(n);


	return 0;
}


void isEven(int x)
{
	if(x % 2 == 0)
	{
		printf("%d is even\n",x);
	}
	else
	{
		printf("%d is odd\n", x);
	}
}
