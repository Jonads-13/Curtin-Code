package edu.curtin.gameplugins;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.LocaleHandler;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;

public class Teleport implements MenuPlugin, LocaleHandler {

    private API api;
    private boolean done;
    private ResourceBundle bundle;

    @Override
    public void start(API api) {
        this.api = api;
        done = false;
        bundle = ResourceBundle.getBundle("teleport-bundle");
        api.registerMenuPlugin(this);
        api.registerLocaleHandler(this);
    }

    @Override
    public boolean takeAction(String choice) {
        boolean didStuff = false;

        if(done) {
            System.out.println(bundle.getString("completed"));
        }
        else {
            if(choice.toLowerCase().equals("t")) {
                int[] location = api.getPlayerLocation();
                int[] mapSize = api.getMapSize();
                int pr = location[0], pc = location[1];
                int rows = mapSize[0], cols = mapSize[1];
                int r, c;

                do {
                    r = getRandomNumber(rows);
                    c = getRandomNumber(cols);
                } while(sameLocation(r, c, pr, pc));

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
    public void displayMenuOption() {
        System.out.println(bundle.getString("menu_option"));
    }

    @Override
    public void notifyLocaleChanged(Locale locale) {
        System.out.println("got here");
        bundle = ResourceBundle.getBundle("teleport-bundle", locale);
    }

    private boolean sameLocation(int r, int c, int pr, int pc) {
        return ((r == pr) && (c == pc));
    }

    private int getRandomNumber(int max) 
    {
        Random rand = new Random();
        return rand.nextInt(max);
    }
}