#ifndef STRUCTS_H
#define STRUCTS_H

typedef struct Recent
{
    int playerRow;
    int playerCol;
    int floorRow;
    int floorCol;
}Recent;

typedef struct EndConditions
{
    int won;
    int playerTrapped;
    int goalBlocked;
}EndConditions;

typedef struct MapValues
{
    int row;
    int col;
}MapValues;

typedef struct GoalValues
{
    int row;
    int col;
}GoalValues;

typedef struct MapState 
{
    struct MapValues *mv;
    struct GoalValues *gv;
    struct EndConditions* end;
    char **map;
    struct List *playerMovement;
}MapState;

#endif
