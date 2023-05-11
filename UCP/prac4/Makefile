CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic
OBJ = main.o arrays.o
EXEC = program

$(EXEC) : $(OBJ)
		$(CC) $(OBJ) -o $(EXEC)

main.o : main.c arrays.c arrays.h
		$(CC) -c main.c $(CFLAGS)

arrays.o : arrays.c
		$(CC) -c arrays.c $(CFLAGS)

clean: 
		rm -f $(EXEC) $(OBJ)