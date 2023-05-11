#ifndef MACROS_H
#define MACROS_H

#define FALSE 0
#define TRUE !FALSE

#define BLANK ' '

#define VALIDMAP(row, col) ((row >= 5) && (row <= 25) && (col >=5) && (col <= 25))
#define VALIDNUM(bound, n)  ((n >= 0) && (n < bound))
#define VALIDGOAL(gr, gc, pr, pc) ((gr != pr) || (gc != pc))

#endif
