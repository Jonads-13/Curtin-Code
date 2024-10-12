package edu.curtin.saed_assignment2;

public class Obstacle extends Cell {

    private String[] itemRequirements;

    public Obstacle(int r, int c, String[] requirements) {
        super(r,c );
        this.itemRequirements = requirements;
    }

    public String[] getItemRequirements() {
        return itemRequirements;
    }

    public void setItemRequirements(String[] requirements) {
        this.itemRequirements = requirements;
    }
}
