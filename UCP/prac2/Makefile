CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic
OBJ = main.o powers.o
EXEC = program

$(EXEC) : $(OBJ)
		$(CC) $(OBJ) -o $(EXEC)

main.o : main.c macros.h powers.h
		$(CC) -c main.c $(CFLAGS)

powers.o : powers.c
		$(CC) -c powers.c $(CFLAGS)

clean: 
		rm -f $(EXEC) $(OBJ)
