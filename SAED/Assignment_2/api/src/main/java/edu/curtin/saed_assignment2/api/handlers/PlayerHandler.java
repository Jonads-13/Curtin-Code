package edu.curtin.saed_assignment2.api.handlers;

import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;

public interface PlayerHandler {
    void handlePlayerMoved(int[] prevLocation, int[] newLocation);
    void handlePlayerTraversedObstacle(Obstacle obstacle);
    void handlePlayerPickedUpItem(Item item);
}
