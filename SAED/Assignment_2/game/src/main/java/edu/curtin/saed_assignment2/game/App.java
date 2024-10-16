package edu.curtin.saed_assignment2.game;

import edu.curtin.saed_assignment2.game.controller.Start;

public class App {

    public static void main(String[] args) {
        if(args.length==0) {
            usage();
        }
        else {
            String filename = args[0];
            Start start = new Start(filename);
            start.setup();
        }
    }



    // Display how to use the program to the user
    private static void usage() {
        System.out.println("usage:\n$./graldew run --args=\"<path/to/inputfile>\"");
    }

}