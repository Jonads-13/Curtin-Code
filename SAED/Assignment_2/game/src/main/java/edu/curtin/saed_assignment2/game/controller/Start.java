package edu.curtin.saed_assignment2.game.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;
import org.python.util.*;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.handlers.LocaleHandler;
import edu.curtin.saed_assignment2.api.handlers.MenuHandler;
import edu.curtin.saed_assignment2.api.handlers.PlayerHandler;
import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.api.plugins.Plugin;
import edu.curtin.saed_assignment2.game.model.GameData;
import edu.curtin.saed_assignment2.game.model.exceptions.FilledLocationException;
import edu.curtin.saed_assignment2.game.model.exceptions.InvalidLocationException;
import edu.curtin.saed_assignment2.game.view.Display;

public class Start implements API {

    private final String filename;
    private Display display;
    private GameData data;
    private final List<MenuHandler> menuHandlers;
    private final List<PlayerHandler> playerHandlers;
    private final List<LocaleHandler> localeHandlers;



    // Constructor
    public Start(String f) {
        this.filename = f;
        data = new GameData();
        display = new Display();
        menuHandlers = new LinkedList<>();
        playerHandlers = new LinkedList<>();
        localeHandlers = new LinkedList<>();
    }






    // Initialise data structures for use throughout the game
    public void setup() {
        try (FileInputStream fis = new FileInputStream(filename)) {
            Parser p;

            // Determine which file encding to read
            if(filename.contains("utf8")) {
                p = new Parser(new InputStreamReader(fis, "UTF-8"));
            }
            else if(filename.contains("utf16")) {
                p = new Parser(new InputStreamReader(fis, "UTF-16"));
            }
            else if(filename.contains("utf32")) {
                p = new Parser(new InputStreamReader(fis, "UTF-32"));
            }
            else {
                throw new ParseException("Invalid File Format");
            }
            data = p.parse(data);
            data.initialiseMap();

            // Run any python scripts in the input file
            try(PythonInterpreter interpreter = new PythonInterpreter()) {
                initialiseScripts(interpreter);
                initialisePlugins();
                beginGame(); // Let's play
            }
        } 
        catch (ParseException | IOException e) {
            System.out.println(e.getMessage());
        }
        catch (InvalidLocationException ile) { // File format was fine, but data was not
            System.out.println(display.getInvalidMessage() + ile.getMessage());
        }
        catch (FilledLocationException fle) { // File format was fine, but data was not
            System.out.println(display.getFilledMessage() + fle.getMessage());
        }
    }





    // Game loop logic
    private void beginGame() {
        boolean finished = false;

        try (Scanner sc = new Scanner(System.in)) {
            while(!finished) { // Play game
                display.printScreen(data); // Display game state
                notifyMenuDisplayed(); // Plugins display their menu options

                String choice = sc.next().toUpperCase();
                if(choice.equals("Q")) { // User wants to quit
                    finished = true;
                    display.showQuitMessage();
                }
                else if(choice.equals("L")) { // Change locale
                    display.showLocalePrompt();
                    String code = sc.next();
                    Locale newLocale = Locale.forLanguageTag(code); // Get new locale
                    display = new Display(newLocale);
                    notifyLocaleChanged(newLocale); // tell any plugins that the locale has changed
                }
                else {
                    if(move(choice)) {
                        data.incrementDays(); // Move to the next day
                        finished = won(data.getDays()); // Check if goal reached
                    }
                }
            }
        }
    }







    // Try to move the player
    private boolean move(String choice) {
        int prevRow = data.getPlayer().getRow(), prevCol = data.getPlayer().getCol();
        boolean moved = true;
        
        switch(choice) {
            case "W" -> {
                moved = movePlayer(prevRow-1, prevCol); // Up
            }
            case "A" -> {
                moved = movePlayer(prevRow, prevCol-1); // Left
            }
            case "S" -> {
                moved = movePlayer(prevRow+1, prevCol); // Down
            }
            case "D" -> {
                moved = movePlayer(prevRow, prevCol+1); // Right
            }
            default -> { // Not direction, plugin menu option?
                if(!notifyMenuOptionSelected(choice)) { // If no plugins did anything
                    display.showWrongInput();
                    moved = false;
                }
            }
        }

        return moved;   
    }






    // Check if the player has reached the goal
    private boolean won(int days) {
        int pr = data.getPlayer().getRow();
        int pc = data.getPlayer().getCol();
        int gr = data.getGoal().getRow();
        int gc = data.getGoal().getCol();
        boolean won = false;

        if((pr==gr) && (pc==gc)) {
            won = true;
            display.showPlayerWon(days);
        }

        return won;
    }

    




    // Check if the player has the required items to traverse the obstacle
    public boolean traversedObstacle(Obstacle o) {
        Player player = data.getPlayer();
        List<String> requirements = o.getItemRequirements();
        List<Item> inventory = player.getInventory();
        int i = requirements.size(); // Number of items the player needs to hold

        // Loop while there are still items to check
        for(String requirement : requirements) {
            String normalisedRequirement = Normalizer.normalize(requirement, Normalizer.Form.NFC);
            for(Item item : inventory) { // Loop through inventory looking for required item
                String normalisedName = Normalizer.normalize(item.getName(), Normalizer.Form.NFC);
                if(normalisedRequirement.equals(normalisedName)) {
                    i--; // item found in inventory
                }
            }
        }

        boolean traversed;
        if(i <= 0) { // Found all requirements in player inventory
            display.showTraversedObstacle();
            traversed = true;
        }
        else { // Missing required items
            display.showBlockedByObstacle(o);
            traversed = false;
        }

        return traversed;
    }




    // Player moved over an item
    public void pickUpItem(Item item) {
        data.getPlayer().addToInventory(item);
        display.showPickedUpItem(String.format("%s: %s",item.getName(), item.getMessage()));
    }




    // Move the player location
    private void changePlayerLocation(int r, int c) {
        Cell[][] map = data.getMap();
        Player player = data.getPlayer();

        map[r][c] = player; // Place player at new location

        // Blank cell for where the player came from
        Cell replace = new Cell();
        replace.setVisibility(true);
        map[player.getRow()][player.getCol()] = replace; 

        // Set new player location
        player.setRow(r); 
        player.setCol(c);
        data.showAroundPlayer();
    }




    // Start all python scripts from input file
    private void initialiseScripts(PythonInterpreter interpreter) {
        for (String script : data.getScripts()) {
            interpreter.set("api", this);
            interpreter.exec(script);
        }
    }





    // Create all plugins from input file
    private void initialisePlugins() {
        for(String name : data.getPlugins()) {
            try {
                Class<?> pluginClass = Class.forName(name);
                switch(pluginClass.getConstructor().newInstance()) { // Attempt to cast plugin to each specified interface
                    case Plugin plugin-> {
                        plugin.start(this);
                        display.showPluginStarted(plugin.getClass().getName());
                    }
                    default -> { // No plugin interface specified
                        display.showInvalidPlugin(name);
                    }
                }
            }
            catch(ClassNotFoundException | IllegalAccessException | IllegalArgumentException | 
                    InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                System.out.println(e);
            }
        }
    }




    // Interface methods

    @Override
    public int[] getPlayerLocation() {
        return new int[]{data.getPlayer().getRow(), data.getPlayer().getCol()};
    }

    @Override
    public List<Item> getPlayerInventory() {
        return data.getPlayer().getInventory();
    }

    @Override
    public Item getMostRecentItem() {
        return data.getPlayer().getInventory().getLast();
    }

    @Override
    public int[] getMapSize() {
        return new int[]{data.getMap().length, data.getMap()[0].length};
    }

    @Override
    public Cell getMapCell(int r, int c) {
        return data.getMap()[r][c];
    }

    @Override
    public void setMapCell(Cell newCell, int r, int c) {
        data.getMap()[r][c] = newCell;
    }

    @Override
    public Cell[][] getMap() {
        return data.getMap();
    }

    @Override
    public int[] getGoalLocation() {
        return data.getGoal().getLocation();
    }

    @Override
    public boolean getCellVisbility(int r, int c) {
        return data.getMap()[r][c].getVisible();
    }

    @Override
    public void setCellVisibility(int r, int c, boolean visible) {
        data.getMap()[r][c].setVisibility(visible);
    }

    @Override
    public void registerMenuHandler(MenuHandler mh) {
        menuHandlers.add(mh);
    }





    @Override
    public boolean movePlayer(int r, int c) {
        boolean moved = false;
        Cell[][] map = data.getMap();
        int[] prevLocation = getPlayerLocation();
        int[] newLocation = new int[]{r, c};

        if(data.validLocation(r, c)) { // Can the player actually move to the destination
            switch (map[r][c]) { // Which Cell instance is the destination
                case Obstacle obstacle -> { 
                    if(traversedObstacle(obstacle)) { // Attempt to traverse the obstacle
                        changePlayerLocation(r, c);
                        moved = true;

                        // Notify handlers/scripts
                        notifyPlayerTraversedObstacle(obstacle);
                        notifyPlayerMoved(prevLocation, newLocation);
                    }
                }
                case Item item -> {
                    pickUpItem(item); 
                    changePlayerLocation(r, c);
                    moved = true;

                    // Notify handlers/scripts
                    notifyPlayerPickedUpItem(item);
                    notifyPlayerMoved(prevLocation, newLocation);
                }
                default -> { // Nothing special, just move player
                    changePlayerLocation(r, c);
                    moved = true;

                    // Notify handlers/scripts
                    notifyPlayerMoved(prevLocation, newLocation);
                }
            }
        }
        else { // Tried to go outside the map
            display.showInvalidDirection();
        }

        return moved;
    }






    @Override
    public boolean notifyMenuOptionSelected(String choice) {
        boolean didStuff = false;
        for(MenuHandler mp : menuHandlers) {
            // See if each handler has a case for the menu choice
            if(mp.handleMenuOptionSelected(choice)) {
                didStuff = true;
            }
        }
        return didStuff;
    }

    @Override
    public void notifyMenuDisplayed() {
        for(MenuHandler mp : menuHandlers) {
            mp.handleMenuDisplayed(); // Any plugin menu options?
        }
    }

    @Override
    public void registerPlayerHandler(PlayerHandler ph) {
        playerHandlers.add(ph);
    }

    @Override
    public void notifyPlayerMoved(int[] prevLocation, int[] newLocation) {
        for(PlayerHandler ph : playerHandlers) {
            ph.handlePlayerMoved(prevLocation, newLocation);
        }
    }

    @Override
    public void notifyPlayerPickedUpItem(Item item) {
        for(PlayerHandler ph : playerHandlers) {
            ph.handlePlayerPickedUpItem(item);
        }
    }

    @Override
    public void notifyPlayerTraversedObstacle(Obstacle o) {
        for(PlayerHandler ph : playerHandlers) {
            ph.handlePlayerTraversedObstacle(o);
        }
    }

    @Override
    public void registerLocaleHandler(LocaleHandler lh) {
        localeHandlers.add(lh);
    }

    @Override
    public void notifyLocaleChanged(Locale l) {
        for (LocaleHandler lh : localeHandlers) {
            lh.handleLocaleChanged(l);
        }
    }

    @Override
    public void addToPlayerInventory(Item item) {
        data.getPlayer().addToInventory(item);
    }
}
