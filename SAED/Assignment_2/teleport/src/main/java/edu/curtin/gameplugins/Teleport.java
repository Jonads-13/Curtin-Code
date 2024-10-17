package edu.curtin.gameplugins;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.handlers.LocaleHandler;
import edu.curtin.saed_assignment2.api.handlers.MenuHandler;
import edu.curtin.saed_assignment2.api.plugins.Plugin;

// Plugin to add the random teleport functionality
public class Teleport implements Plugin, MenuHandler, LocaleHandler {

    private API api;
    private boolean done;
    private Random rand;
    private ResourceBundle bundle;

    @Override
    public void start(API api) {
        this.api = api;
        done = false;
        rand = new Random();
        bundle = ResourceBundle.getBundle("teleport-bundle");
        api.registerMenuHandler(this);
        api.registerLocaleHandler(this);
    }

    @Override
    public boolean handleMenuOptionSelected(String choice) {
        boolean didStuff = false; // If this plugin took action on the menu choice

        if(done) { // Already used teleport option before?
            System.out.println(bundle.getString("completed"));
        }
        else {
            if(choice.toLowerCase().equals("t")) {
                int[] location = api.getPlayerLocation();
                int[] mapSize = api.getMapSize();
                int pr = location[0], pc = location[1];
                int rows = mapSize[0], cols = mapSize[1];
                int r, c;

                do { // Loop until a random location is chosen that isn't the players current location
                    r = getRandomNumber(rows);
                    c = getRandomNumber(cols);
                } while(sameLocation(r, c, pr, pc));

                // Attempt to move the player. If couldn't move, ie obstacle, then doesn't count as teleport
                if(api.movePlayer(r, c)) {
                    didStuff = true;
                    done = true;
                    System.out.println(String.format("%s %d,%d",bundle.getString("teleported"),r,c));
                }
            }
        }
        return didStuff;
    }

    @Override
    public void handleMenuDisplayed() {
        System.out.print(bundle.getString("menu_option"));
    }

    @Override
    public void handleLocaleChanged(Locale locale) {
        bundle = ResourceBundle.getBundle("teleport-bundle", locale);
    }


    // Checks if two coordinates are the same
    private boolean sameLocation(int r, int c, int pr, int pc) {
        return ((r == pr) && (c == pc));
    }


    // Get a random integer
    private int getRandomNumber(int max) 
    {
        return rand.nextInt(max);
    }
}