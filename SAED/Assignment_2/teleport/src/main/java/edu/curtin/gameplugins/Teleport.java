package edu.curtin.gameplugins;

import java.util.Random;
import java.util.ResourceBundle;

import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;

public class Teleport implements MenuPlugin {

    private API api;
    private boolean done;
    private ResourceBundle bundle;

    @Override
    public void start(API api) {
        this.api = api;
        done = false;
        bundle = ResourceBundle.getBundle("teleport-bundle");
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
                int pr = location[0], pc = location[1];
                int[] mapSize = api.getMapSize();
                int rows = mapSize[0], cols = mapSize[1];
                int x = -1, y = -1; // Deliberate bad location

                while(!validLocation(x, y, rows, cols) || sameLocation(x, y, pr, pc)) 
                {
                    x = getRandomNumber(rows);
                    y = getRandomNumber(cols);
                }

                api.movePlayer(x, y);

                didStuff = true;
                done = didStuff;
            }
        }
        return didStuff;
    }

    @Override
    public void displayMenuOption() {
        System.out.println(bundle.getString("menu_option"));
    }

    private boolean validLocation(int r, int c, int rows, int cols) {
        return (((r < rows) && (r >= 0)) && ((c < cols) && (c >= 0)));
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