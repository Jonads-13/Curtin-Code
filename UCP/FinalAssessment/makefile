CC = gcc
CFLAGS = -g
OBJ = main.o random.o terminal.o setup.o map.o color.o runtime.o movement.o newSleep.o free.o
EXEC = ant

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

main.o : main.c random.h setup.h
	$(CC) -c main.c $(CFLAGS)

random.o : random.c random.h
	$(CC) -c random.c $(CFLAGS)

terminal.o : terminal.c terminal.h
	$(CC) -c terminal.c $(CFLAGS)

setup.o : setup.c structs.h setup.h map.h
	$(CC) -c setup.c $(CFLAGS)

map.o : map.c map.h random.h color.h structs.h macros.h
	$(CC) -c map.c $(CFLAGS)

color.o : color.c color.h
	$(CC) -c color.c $(CFLAGS)

runtime.o : runtime.c runtime.h structs.h map.h free.h newSleep.h
	$(CC) -c runtime.c $(CFLAGS)

movement.o : movement.c movement.h structs.h random.h
	$(CC) -c movement.c $(CFLAGS)

newSleep.o : newSleep.c newSleep.h
	$(CC) -c newSleep.c $(CFLAGS)

free.o : free.c free.h
	$(CC) -c free.c $(CFLAGS)

clean: 
	rm -f $(EXEC) $(OBJ)

