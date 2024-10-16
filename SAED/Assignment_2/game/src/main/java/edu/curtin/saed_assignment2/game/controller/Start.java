package edu.curtin.saed_assignment2.game.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.LocaleHandler;
import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.api.plugins.InventoryPlugin;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;
import edu.curtin.saed_assignment2.api.plugins.PlayerPlugin;
import edu.curtin.saed_assignment2.game.model.GameData;
import edu.curtin.saed_assignment2.game.model.exceptions.FilledLocationException;
import edu.curtin.saed_assignment2.game.model.exceptions.InvalidLocationException;
import edu.curtin.saed_assignment2.game.view.Display;

public class Start implements API {

    private final String filename;
    private Display display;
    private GameData data;
    private final List<MenuPlugin> menuPlugins;
    private final List<PlayerPlugin> playerPlugins;
    private final List<LocaleHandler> localeHandlers;

    public Start(String f) {
        this.filename = f;
        data = new GameData();
        display = new Display();
        menuPlugins = new LinkedList<>();
        playerPlugins = new LinkedList<>();
        localeHandlers = new LinkedList<>();
    }

    public void setup() {
        try (FileInputStream fis = new FileInputStream(filename)) {
            Parser p = new Parser(fis);
            data = p.parse(data);
            data.initialiseMap();
            initialisePlugins();
            beginGame();
        } 
        catch (ParseException | IOException e) {
            System.out.println(e.getMessage());
        }
        catch (InvalidLocationException ile) {
            System.out.println(display.getInvalidMessage() + ile.getMessage());
        }
        catch (FilledLocationException fle) {
            System.out.println(display.getFilledMessage() + fle.getMessage());
        }
    }

    private void beginGame() {
        boolean finished = false;

        try (Scanner sc = new Scanner(System.in)) {
            
            while(!finished) { // Play game
                display.printScreen(data); // Display game state
                for(MenuPlugin mp : menuPlugins) {
                    mp.displayMenuOption(); // Any plugin menu options?
                }
                String choice = sc.next().toUpperCase();
                if(choice.equals("Q")) { // User wants to quit
                    finished = true;
                }
                else if(choice.equals("L")) {
                    display.showLocalePrompt();
                    String code = sc.next();
                    Locale newLocale = Locale.forLanguageTag(code);
                    display = new Display(newLocale);
                    notifyLocaleHandlers(newLocale);
                }
                else {
                    if(move(choice)) { // 
                        data.incrementDays(); // Move to the next day
                        finished = won(); // Check if goal reached
                    }
                }
                
            }
        }
    }

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
            default -> {
                if(!notifyMenuPlugins(choice)) {
                    display.showWrongInput();
                    moved = false;
                }
            }
        }

        // If location is the same then the player didn't move
        return moved;
        
    }

    private boolean won() {
        int pr = data.getPlayer().getRow();
        int pc = data.getPlayer().getCol();
        int gr = data.getGoal().getRow();
        int gc = data.getGoal().getCol();
        boolean won = false;

        if((pr==gr) && (pc==gc)) {
            won = true;
            display.showPlayerWon();
        }

        return won;
    }

     public boolean traversedObstacle(Obstacle o) {
         Player player = data.getPlayer();
         List<String> requirements = o.getItemRequirements();
         List<Item> inventory = player.getInventory();
         int i = requirements.size(); // Number of items the player needs to acquire

         // Loop while there are still items to check
         for(String requirement : requirements) {
            String normalisedRequirement = Normalizer.normalize(requirement, Normalizer.Form.NFC);
            for(Item item : inventory) { // Loop through inventory looking for required item
                String normalisedName = Normalizer.normalize(item.getName(), Normalizer.Form.NFC);
                    if(normalisedRequirement.equals(normalisedName)) {
                        i--; // One item found in inventory
                   }
            }
        }

        boolean traversed;
        if(i <= 0) { // Found all requirements in player inventory
            display.showTraversedObstacle();
            traversed = true;
        }
        else {
            display.showBlockedByObstacle(o);
            traversed = false;
        }

        return traversed;
     }

     public void pickUpItem(Item item) {
        data.getPlayer().addToInventory(item);
        display.showPickedUpItem(String.format("%s: %s",item.getName(), item.getMessage()));
     }

     private void changePlayerLocation(int r, int c) {
        Cell[][] map = data.getMap();
        Player player = data.getPlayer();

        map[r][c] = player;
        Cell replace = new Cell();
        replace.setVisible();
        map[player.getRow()][player.getCol()] = replace;
        player.setRow(r); 
        player.setCol(c);
        data.showAroundPlayer();
     }

     private void initialisePlugins() {
        for(String name : data.getPlugins()) {
            try {
                Class<?> pluginClass = Class.forName(name);
                switch(pluginClass.getConstructor().newInstance()) { // Attempt to cast plugin to each specified interface
                    case MenuPlugin menuPlugin-> {
                        menuPlugin.start(this);
                    }
                    case PlayerPlugin playerPlugin -> {
                        playerPlugin.start(this);
                    }
                    default -> {
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
    public boolean getCellVisbility(int r, int c) {
        return data.getMap()[r][c].getVisible();
    }

    @Override
    public void registerMenuPlugin(MenuPlugin mp) {
        menuPlugins.add(mp);
    }

    @Override
    public boolean movePlayer(int r, int c) {
        boolean moved = false;
        Cell[][] map = data.getMap();

        if(data.validLocation(r, c)) {
            switch (map[r][c]) {
                case Obstacle obstacle -> { 
                    if(traversedObstacle(obstacle)) {
                        changePlayerLocation(r, c);
                        moved = true;
                        notifyPlayerPlugins(true);
                    }
                }
                case Item item -> {
                    pickUpItem(item); 
                    changePlayerLocation(r, c);
                    moved = true;
                    notifyPlayerPlugins(true);
                }
                default -> {
                    changePlayerLocation(r, c);
                    moved = true;
                    notifyPlayerPlugins(false);
                }
            }
        }
        else {
            display.showInvalidDirection();
        }
        return moved;
    }

    @Override
    public boolean notifyMenuPlugins(String choice) {
        boolean didStuff = false;
        for(MenuPlugin mp : menuPlugins) {
            if(mp.takeAction(choice)) {
                didStuff = true;
            }
        }
        return didStuff;
    }

    @Override
    public void registerInventoryPlugin(InventoryPlugin mp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerInventoryPlugin'");
    }

    @Override
    public boolean notifyInventoryPlugins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyInventoryPlugins'");
    }

    @Override
    public void registerPlayerPlugin(PlayerPlugin pp) {
        playerPlugins.add(pp);
    }

    @Override
    public void notifyPlayerPlugins(boolean didAction) {
        for(PlayerPlugin pp : playerPlugins) {
            pp.takeAction(didAction);
        }
    }

    @Override
    public void registerLocaleHandler(LocaleHandler lh) {
        localeHandlers.add(lh);
    }

    @Override
    public void notifyLocaleHandlers(Locale l) {
        for (LocaleHandler lh : localeHandlers) {
            lh.notifyLocaleChanged(l);
        }
    }
}
