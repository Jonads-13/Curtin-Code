package edu.curtin.gameplugins;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.plugins.PlayerPlugin;
import edu.curtin.saed_assignment2.api.LocaleHandler;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Cell;

public class Prize implements PlayerPlugin, LocaleHandler {
    
    private API api;
    private int itemsAcquired;
    private int obstaclesTraversed;
    private boolean itemGiven;
    private ResourceBundle bundle;

    @Override
    public void start(API api)
    {
        this.api = api;
        itemsAcquired = 0;
        obstaclesTraversed = 0;
        itemGiven = false;
        bundle = ResourceBundle.getBundle("prize-bundle");
    }

    @Override
    public void notifyLocaleChanged(Locale locale) {
        bundle = ResourceBundle.getBundle("prize-plugin", locale);
    }

    @Override
    public void itemAcquired() {
        itemsAcquired++;
        check();
    }

    @Override
    public void obstacleTraversed() {
        obstaclesTraversed++;
        check();
    }

    private void check() {
        if(!itemGiven) {
            if(itemsAcquired + obstaclesTraversed == 5)
            {
                List<Item> playerInventory = api.getPlayerInventory();
                Item prize = new Item(bundle.getString("prize_name"), bundle.getString("prize_message"));
                playerInventory.add(prize);
            }
        }
    }
}
