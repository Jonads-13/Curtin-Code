package edu.curtin.saed_assignment2.api.model;
import java.util.List;

public class Obstacle extends Cell {

    private List<String> itemRequirements;

    public Obstacle(int row, int col, List<String> requirements) {
        super();
        this.row = row;
        this.col = col;
        this.itemRequirements = requirements;
        label = " O ";
    }

    public List<String> getItemRequirements() {
        return itemRequirements;
    }

    public void setItemRequirements(List<String> requirements) {
        this.itemRequirements = requirements;
    }

    public String getRequirementsDisplay() {
        String list = "";
        for (String requirement : itemRequirements) {
            list+=requirement + "\n";
        }
        return list;
    }

    @Override
    public String toString() {
        String self = super.toString();
        return self + " requirements:\n" + getRequirementsDisplay();
    }


}
