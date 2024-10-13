package edu.curtin.saed_assignment2.api.plugins;

import edu.curtin.saed_assignment2.api.API;

public interface MenuPlugin {
    void start(API api);

    boolean takeAction(String choice);
    void displayMenuOption();
}
