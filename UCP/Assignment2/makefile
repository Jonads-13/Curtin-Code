CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic -g
OBJ = main.o random.o terminal.o setup.o map.o game.o movement.o end.o color.o linkedlist.o free.o
EXEC = escape

ifdef BORDERLESS
CFLAGS += -D BORDERLESS
BORDERLESS : clean $(EXEC)
endif

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

main.o : main.c random.h setup.h
	$(CC) -c main.c $(CFLAGS)

random.o : random.c random.h
	$(CC) -c random.c $(CFLAGS)

terminal.o : terminal.c terminal.h
	$(CC) -c terminal.c $(CFLAGS)

setup.o : setup.c setup.h map.h game.h linkedlist.h structs.h
	$(CC) -c setup.c $(CFLAGS)

map.o : map.c map.h random.h color.h linkedlist.h structs.h macros.h
	$(CC) -c map.c $(CFLAGS)

game.o : game.c game.h macros.h map.h movement.h end.h linkedlist.h structs.h
	$(CC) -c game.c $(CFLAGS)

movement.o : movement.c movement.h macros.h terminal.h linkedlist.h structs.h map.h
	$(CC) -c movement.c $(CFLAGS)

end.o : end.c end.h macros.h structs.h linkedlist.h
	$(CC) -c end.c $(CFLAGS)

color.o : color.c color.h
	$(CC) -c color.c $(CFLAGS)

linkedlist.o : linkedlist.c linkedlist.h
	$(CC) -c linkedlist.c $(CFLAGS)

free.o : free.h map.h structs.h linkedlist.h
	$(CC) -c free.c $(CFLAGS)

clean: 
	rm -f $(EXEC) $(OBJ)

