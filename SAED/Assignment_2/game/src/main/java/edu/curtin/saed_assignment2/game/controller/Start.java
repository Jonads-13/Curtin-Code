package edu.curtin.saed_assignment2.game.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
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
                for(MenuPlugin mp : data.getMenuPlugins()) {
                    mp.displayMenuOption();
                }
                String choice = sc.next();
                if(move(choice)) {
                    data.incrementDays();
                    finished = won();
                }
            }
        }
    }

    private boolean move(String choice) {
        System.out.println(choice);
        return true;
        
    }

    private boolean won() {
        int pr = data.getPlayer().getRow();
        int pc = data.getPlayer().getCol();
        int gr = data.getGoal().getRow();
        int gc = data.getGoal().getCol();
        boolean won = false;

        if((pr==gr) && (pc==gc)){
            won = true;
        }

        return won;
    }

    
}
