package edu.curtin.saed_assignment2.model;

import java.util.List;

public class Obstacle extends Cell {

    private List<String> itemRequirements;

    public Obstacle(int row, int col, List<String> requirements) {
        super(row, col);
        this.itemRequirements = requirements;
        label = " O ";
    }

    public List<String> getItemRequirements() {
        return itemRequirements;
    }

    public void setItemRequirements(List<String> requirements) {
        this.itemRequirements = requirements;
    }

    public void displayRequirements() {
        System.out.println("Reqiured to pass this obstacle: ");
        for (String requirement : itemRequirements) {
            System.out.println(requirement);
        }
    }
}
