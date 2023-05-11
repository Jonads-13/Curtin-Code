CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic -g
OBJ = numbers.o orders.o user_input.o
EXEC = program

$(EXEC) : $(OBJ)
		$(CC) $(OBJ) -o $(EXEC)

numbers.o : numbers.c orders.c orders.h user_input.c user_input.h
		$(CC) -c numbers.c $(CFLAGS)

orders.o : orders.c
		$(CC) -c orders.c $(CFLAGS)

userinput.o : user_input.c
		$(CC) -c user_input.c $(CFLAGS)

clean: 
		rm -f $(EXEC) $(OBJ)
