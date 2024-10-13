package edu.curtin.saed_assignment2.model;

public class Obstacle extends Cell {

    private String[] itemRequirements;

    public Obstacle(int row, int col, String[] requirements) {
        super(row, col);
        this.itemRequirements = requirements;
        label = " O ";
    }

    public String[] getItemRequirements() {
        return itemRequirements;
    }

    public void setItemRequirements(String[] requirements) {
        this.itemRequirements = requirements;
    }
}
