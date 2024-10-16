package edu.curtin.gameplugins;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.List;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.plugins.PlayerPlugin;
import edu.curtin.saed_assignment2.api.LocaleHandler;
import edu.curtin.saed_assignment2.api.model.Item;

public class Prize implements PlayerPlugin, LocaleHandler {
    
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
        api.registerPlayerPlugin(this);
    }

    @Override
    public void notifyLocaleChanged(Locale locale) {
        bundle = ResourceBundle.getBundle("prize-plugin", locale);
    }

    @Override
    public void takeAction(boolean didAction) {
        if(!itemGiven) {
            if(didAction) {
                count++;
                if(count == 5)
                {
                    List<Item> playerInventory = api.getPlayerInventory();
                    Item prize = new Item(bundle.getString("prize_name"), bundle.getString("prize_message"));
                    playerInventory.add(prize);
                    System.out.println(bundle.getString("prize_acquired"));
                    itemGiven = true;
                }
            }
        }
    }
}
