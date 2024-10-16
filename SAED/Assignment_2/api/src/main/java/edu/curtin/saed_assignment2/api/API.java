package edu.curtin.saed_assignment2.api;

import java.util.List;
import java.util.Locale;

import edu.curtin.saed_assignment2.api.handlers.InventoryHandler;
import edu.curtin.saed_assignment2.api.handlers.LocaleHandler;
import edu.curtin.saed_assignment2.api.handlers.MenuHandler;
import edu.curtin.saed_assignment2.api.handlers.PlayerHandler;
import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;

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

    void setMapCell(Cell newCell, int r, int c);

    Cell[][] getMap();

    // Grid square visibility.
    boolean getCellVisbility(int r, int c);

    int[] getGoalLocation();

    void setCellVisibility(int r, int c, boolean visible);

    void registerMenuHandler(MenuHandler mh);

    boolean notifyMenuHandlers(String choice);

    void registerInventoryHandler(InventoryHandler ih);

    void notifyInventoryHandlers(Item item);

    void registerPlayerHandler(PlayerHandler ph);

    void notifyPlayerHandlers(boolean didAction, int[] prevLocation, int[] newLocation);

    void registerLocaleHandler(LocaleHandler lh);

    void notifyLocaleHandlers(Locale l);

    boolean movePlayer(int r, int c); // returns success
}
