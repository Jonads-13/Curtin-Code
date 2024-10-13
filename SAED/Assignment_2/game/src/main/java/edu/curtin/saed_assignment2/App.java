package edu.curtin.saed_assignment2;

import java.io.FileInputStream;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        String filename = args[0];
        try(FileInputStream fis = new FileInputStream(filename)) {
            Parser p = new Parser(fis);
            p.dsl();
        } catch (ParseException | IOException e) {
           System.out.println(e.getMessage());
        }
        
    }

}