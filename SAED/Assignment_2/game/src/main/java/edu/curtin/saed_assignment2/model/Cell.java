package edu.curtin.saed_assignment2.model;

public class Cell {

    protected String label;
    protected int row;
    protected int col;
    protected boolean visible;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        visible = false;
        label = "   ";
    }

    public int[] getLocation() {
        return new int[]{row, col};
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setVisible() {
        visible = true;
    }
    
    public boolean getVisible() {
        return visible;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
