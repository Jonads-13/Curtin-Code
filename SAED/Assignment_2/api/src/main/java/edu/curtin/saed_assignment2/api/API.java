package edu.curtin.saed_assignment2.api;

import java.util.List;

import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;

public interface API {
    
    // The player’s location;
    int[] getPlayerLocation();

    // The player’s inventory (including the ability to see which item was the most recently acquired);
    List<Item> getPlayerInventory();

    Item getMostRecentItem();

    // The grid size;
    int[] getMapSize();

    // Grid square contents—any item, obstacle or goal at any given location;
    Cell getMapCell(int r, int c);

    // Grid square visibility.
    boolean getCellVisbility(int r, int c);

    void registerMenuPlugin(MenuPlugin mp);

    boolean movePlayer(int r, int c) // returns success
;
}
