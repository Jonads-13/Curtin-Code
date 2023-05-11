CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic
OBJ = filename1.o filename2.o filename3.o
EXEC = program

$(EXEC) : $(OBJ)
		$(CC) $(OBJ) -o $(EXEC)

filename1.o : filename1.c filename2.c filename2.h filename3.c filename3.h
		$(CC) -c filename1.c $(CFLAGS)

filename2.o : filename2.c
		$(CC) -c filename2.c $(CFLAGS)

filename3.o : filename3.c
		$(CC) -c filename3.c $(CFLAGS)

clean: 
		rm -f $(EXEC) $(OBJ)