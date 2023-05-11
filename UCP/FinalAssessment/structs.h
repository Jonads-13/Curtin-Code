#ifndef STRUCTS_H
#define STRUCTS_H


typedef struct Ant
{
    int row;
    int col;
    char direction;
}Ant;

typedef struct MapData
{
    char** display;
    int** colorState;
    int rows;
    int cols;
}MapData;

typedef struct State
{
    MapData* maps;
    Ant* langAnt;
    Ant* randAnt;
    int max;
    float sleep;
}State;

typedef enum Color
{
    DEFAULT,
    GREEN,
    RED,
    BLUE
}Color;

typedef enum Direction
{
    NORTH,
    EAST,
    SOUTH,
    WEST
}Direction;

#endif

