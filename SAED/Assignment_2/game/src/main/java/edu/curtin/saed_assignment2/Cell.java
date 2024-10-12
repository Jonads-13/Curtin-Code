package edu.curtin.saed_assignment2;

public class Cell {

    protected int row;
    protected int col;
    protected boolean visited;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        visited = false;
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

    public void setVisited() {
        visited = true;
    }
    
    public boolean getVisited() {
        return visited;
    }
}
