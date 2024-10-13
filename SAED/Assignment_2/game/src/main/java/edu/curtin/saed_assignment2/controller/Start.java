package edu.curtin.saed_assignment2.controller;

import java.io.FileInputStream;
import java.io.IOException;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
import edu.curtin.saed_assignment2.model.GameData;
import edu.curtin.saed_assignment2.view.Display;

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
            display.showMap(data.getMap());
        } 
        catch (ParseException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    
}
