CC = gcc
CFLAGS = -Wall -Werror -ansi -pedantic -pthread
OBJ = cq.o setup.o queue.o functionality.o free.o
EXEC = cq

ifdef VALGRIND
CFLAGS += -g
VALGRIND : clean $(EXEC)
endif

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -pthread -o $(EXEC)

cq.o : cq.c setup.h
	$(CC) -c cq.c $(CFLAGS)

setup.o : setup.c setup.h functionality.h queue.h structs.h macros.h free.h
	$(CC) -c setup.c $(CFLAGS)

queue.o : queue.c queue.h macros.h
	$(CC) -c queue.c $(CFLAGS)

functionality.o : functionality.c functionality.h structs.h queue.h macros.h
	$(CC) -c functionality.c $(CFLAGS)

free.o: free.c free.h structs.h queue.h
	$(CC) -c free.c $(CFLAGS)

clean: 
	rm -f $(EXEC) $(OBJ)

