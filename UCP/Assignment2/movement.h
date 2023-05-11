#ifndef MOVEMENT_H
#define MOVEMENT_H

void validMove(MapState*);
DirectionPTR movePlayer(MapState*);
void moveUp(MapState*);
void moveDown(MapState*);
void moveLeft(MapState*);
void moveRight(MapState*);
void undo(MapState*);


#endif
