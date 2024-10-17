package edu.curtin.gameplugins;

import java.util.Locale;
import java.util.ResourceBundle;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.handlers.LocaleHandler;
import edu.curtin.saed_assignment2.api.handlers.PlayerHandler;
import edu.curtin.saed_assignment2.api.plugins.Plugin;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;

public class Prize implements Plugin, PlayerHandler, LocaleHandler {
    
    private API api;
    private int count;
    private boolean itemGiven;
    private ResourceBundle bundle;

    @Override
    public void start(API api)
    {
        this.api = api;
        count = 0;
        itemGiven = false;
        bundle = ResourceBundle.getBundle("prize-bundle");
        api.registerPlayerHandler(this);
        api.registerLocaleHandler(this);
    }

    @Override
    public void handleLocaleChanged(Locale locale) {
        bundle = ResourceBundle.getBundle("prize-bundle", locale);
    }

    @Override
    public void handlePlayerMoved(int[] prevLocation, int[] newLocation) {
       // This plugin does nothing on this action
    }

    @Override
    public void handlePlayerTraversedObstacle(Obstacle obstacle) {
        if(!itemGiven) {
            count++;
            check();
        }
    }

    @Override
    public void handlePlayerPickedUpItem(Item item) {
        if(!itemGiven) {
            count++;
            check();
        }
    }

    private void check() {
        if(count == 5)
        {
            Item prize = new Item(bundle.getString("prize_name"), bundle.getString("prize_message"));
            api.addToPlayerInventory(prize);
            System.out.println(bundle.getString("prize_acquired"));
            itemGiven = true;
        }
    }
}
