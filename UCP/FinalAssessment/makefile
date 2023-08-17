CC = gcc
CFLAGS = -g -ansi -pedantic -Wall
OBJ = main.o random.o terminal.o setup.o map.o color.o runtime.o movement.o newSleep.o free.o
EXEC = ant

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

main.o : random.h setup.h
	$(CC) -c main.c $(CFLAGS)

random.o : 
	$(CC) -c random.c $(CFLAGS)

terminal.o : 
	$(CC) -c terminal.c $(CFLAGS)

setup.o : structs.h map.h
	$(CC) -c setup.c $(CFLAGS)

map.o : random.h color.h structs.h macros.h
	$(CC) -c map.c $(CFLAGS)

color.o : 
	$(CC) -c color.c $(CFLAGS)

runtime.o : structs.h map.h free.h newSleep.h
	$(CC) -c runtime.c $(CFLAGS)

movement.o : structs.h random.h
	$(CC) -c movement.c $(CFLAGS)

newSleep.o : 
	$(CC) -c newSleep.c $(CFLAGS)

free.o : 
	$(CC) -c free.c $(CFLAGS)

clean: 
	rm -f $(EXEC) $(OBJ)

