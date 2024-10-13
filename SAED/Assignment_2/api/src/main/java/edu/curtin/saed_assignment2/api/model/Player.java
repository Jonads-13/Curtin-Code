package edu.curtin.saed_assignment2.api.model;
import java.util.LinkedList;
import java.util.List;

public class Player extends Cell {

    private final List<Item> inventory;

    public Player(int row, int col) {
        super(row, col);
        inventory = new LinkedList<>();
        visible = true;
        label = " P ";
    }

    public void moveUp() {
        row -= 1;
    }

    public void moveDown() {
        row += 1;
    }

    public void moveLeft() {
        col -= 1;
    }

    public void moveRight() {
        col += 1;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
