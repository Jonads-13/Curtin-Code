package edu.curtin.saed_assignment2.game.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;
import edu.curtin.saed_assignment2.game.model.GameData;
import edu.curtin.saed_assignment2.game.model.exceptions.FilledLocationException;
import edu.curtin.saed_assignment2.game.model.exceptions.InvalidLocationException;
import edu.curtin.saed_assignment2.game.view.Display;

public class Start {

    private final String filename;
    private final Display display;
    private GameData data;

    public Start(String f) {
        this.filename = f;
        data = new GameData();
        display = new Display();
    }

    public void setup() {
        try (FileInputStream fis = new FileInputStream(filename)) {
            Parser p = new Parser(fis);
            data = p.parse(data);
            data.initialiseMap();
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
            for(String name : data.getPlugins()) {
                try {
                    Class<?> pluginClass = Class.forName(name);
                    MenuPlugin pluginObj = (MenuPlugin) pluginClass.getConstructor().newInstance();
                    pluginObj.start(data);
                    data.registerMenuPlugin(pluginObj);
                }
                catch(ClassNotFoundException | IllegalAccessException | IllegalArgumentException | 
                        InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                    System.out.println(e);
                }
            }

            while(!finished) {
                display.printScreen(data);
                List<MenuPlugin> mps = data.getMenuPlugins();
                for(MenuPlugin mp : mps) {
                    mp.displayMenuOption();
                }
                String choice = sc.next();
                if(choice.toUpperCase().equals("Q")) {
                    finished = true;
                }
                else {
                    if(move(choice.toUpperCase(), mps)) {
                        data.incrementDays();
                        finished = won();
                    }
                }
            }
        }
    }

    private boolean move(String choice, List<MenuPlugin> mps) {
        int prevRow = data.getPlayer().getRow(), prevCol = data.getPlayer().getCol();
        boolean pluginDidStuff = false;
        
        switch(choice) {
            case "W" -> {
                if(canMove(prevRow-1, prevCol)) {
                    data.movePlayer(prevRow-1, prevCol);
                }
            }
            case "A" -> {
                if(canMove(prevRow, prevCol-1)) {
                    data.movePlayer(prevRow, prevCol-1);
                }
            }
            case "S" -> {
                if(canMove(prevRow+1, prevCol)) {
                    data.movePlayer(prevRow+1, prevCol);
                }
            }
            case "D" -> {
                if(canMove(prevRow, prevCol+1)) {
                    data.movePlayer(prevRow, prevCol+1);
                }
            }
            default -> {
                for(MenuPlugin mp : mps) {
                    if(mp.takeAction(choice)) {
                        pluginDidStuff = true;
                        break; 
                    }
                }

                if(!pluginDidStuff) {
                    display.showWrongInput();
                }
            }
        }

        // If location is the same then the player didn't move
        return !((prevRow == data.getPlayer().getRow()) && (prevCol == data.getPlayer().getCol()));
        
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

    public boolean canMove(int r, int c) {
        boolean canMove = false;
        Cell[][] map = data.getMap();
        if(data.validLocation(r, c)) {
            switch (map[r][c]) {
                case Obstacle obstacle -> { 
                    canMove = traverseObstacle(obstacle);
                }
                case Item item -> {
                    pickUpItem(item); 
                    canMove = true;
                }
                default -> {
                    canMove = true;
                }
            }
        }
        else {
            display.showInvalidDirection();
        }

        return canMove;
     }

     public boolean traverseObstacle(Obstacle o) {
        boolean traverse = false;
        Player player = data.getPlayer();
        List<String> requirements = o.getItemRequirements();
        List<Item> inventory = player.getInventory();

        for(String requirement : requirements) {
            String normalisedRequirement = Normalizer.normalize(requirement, Normalizer.Form.NFC);
            for(Item item : inventory) {
                String normalisedName = Normalizer.normalize(item.getName(), Normalizer.Form.NFC);
                    if(normalisedRequirement.equals(normalisedName)) {
                        traverse = true;
                        break;
                    }
            }
            if(traverse) { 
                display.showTraversedObstacle();
                break;
            }
            else {
                display.showBlockedByObstacle(o);
            }
        }

        return traverse;
     }

     public void pickUpItem(Item item) {
        data.getPlayer().addToInventory(item);
     }
}
