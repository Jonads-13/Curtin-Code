package edu.curtin.saed_assignment2.controller;

import java.io.FileInputStream;
import java.io.IOException;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.Parser;
import edu.curtin.saed_assignment2.model.GameData;

public class Start {
    String filename;
    GameData data;

    public Start(String f) {
        this.filename = f;
        data = new GameData();
    }

    public void init() {
        
        try (FileInputStream fis = new FileInputStream(filename)) {
            Parser p = new Parser(fis);
            data = p.parse(data);
            System.out.println(data.getGoal());
        } 
        catch (ParseException | IOException e) {
        }
    }

    
}
