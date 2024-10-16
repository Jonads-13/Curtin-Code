package edu.curtin.saed_assignment2.api.model;

public class Cell {

    protected String label;
    protected int row;
    protected int col;
    protected boolean visible;

    public Cell() {
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

    public void setVisiblity(boolean visibility) {
        visible = visibility;
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

    @Override
    public String toString() {
        return String.format("%s %d %d", label, row, col);
    }
}
