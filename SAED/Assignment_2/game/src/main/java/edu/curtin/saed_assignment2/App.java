package edu.curtin.saed_assignment2;

import edu.curtin.saed_assignment2.controller.Start;

public class App {

    public static void main(String[] args) {
        if(args == null) {
            usage();
        }
        String filename = args[0];
        Start start = new Start(filename);
        start.init();
    }

    private static void usage() {
        System.out.println("usage:\n $./graldew run --args=\"<path/to/inputfile>\"");
    }

}