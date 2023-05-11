#include <stdio.h>

int main(void)
{
	int count, i;
	scanf("%d", &count);

	i = 0;
	while(i < count)
	{
		printf("%d", i);
		i++;
	}

	return 0;
}
