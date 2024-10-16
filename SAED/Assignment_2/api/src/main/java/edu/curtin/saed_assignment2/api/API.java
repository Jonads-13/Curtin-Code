package edu.curtin.saed_assignment2.api;

import java.util.List;
import java.util.Locale;

import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.plugins.InventoryHandler;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;
import edu.curtin.saed_assignment2.api.plugins.PlayerPlugin;

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

    int[] getGoalLocation();

    void setCellVisibility(int r, int c, boolean visible);

    void registerMenuPlugin(MenuPlugin mp);

    boolean notifyMenuPlugins(String choice);

    void registerInventoryHandler(InventoryHandler ih);

    boolean notifyInventoryHandlers();

    void registerPlayerPlugin(PlayerPlugin pp);

    void notifyPlayerPlugins(boolean didAction);

    void registerLocaleHandler(LocaleHandler lh);

    void notifyLocaleHandlers(Locale l);

    boolean movePlayer(int r, int c); // returns success
}
