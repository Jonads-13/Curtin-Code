package edu.curtin.gameplugins;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;

public class Teleport implements MenuPlugin {

    private API api;
    @Override
    public void start(API api) {
        this.api = api;
    }

    @Override
    public boolean takeAction(String choice) {
        boolean didStuff = false;
        if(choice.toLowerCase().equals("teleport")) {
            didStuff = true;
        }
        return didStuff;
    }

    @Override
    public void displayMenuOption() {
        System.out.println("(T)eleport");
    }

    
}