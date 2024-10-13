package edu.curtin.saed_assignment2.view;

import edu.curtin.saed_assignment2.model.classes.Cell;

public class Display {
    
    public void showMap(Cell[][] map) {
        for(Cell[] row : map) {
            for (Cell cell : row) {
                if(cell.getVisible()) {
                    System.out.println(cell.getLabel());
                }
                else {
                    System.out.println("###");
                }
            }
        }
    }
}
