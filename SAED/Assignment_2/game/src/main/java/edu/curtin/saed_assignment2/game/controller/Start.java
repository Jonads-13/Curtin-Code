package edu.curtin.saed_assignment2.game.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
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
        try (Scanner sc = new Scanner(System.in)) {
            boolean finished = false;
            while(!finished) {
                display.printScreen(data.getMap(), data.getPlayer());
                move(sc);
                finished = true;
            }
        }
    }

    private void move(Scanner sc) {
        String choice = sc.next();
        System.out.println(choice);
        
    }

    
}
