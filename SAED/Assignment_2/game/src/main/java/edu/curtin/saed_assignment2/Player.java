package edu.curtin.saed_assignment2;

import java.util.LinkedList;
import java.util.List;

public class Player extends Cell {

    private final List<Item> inventory;

    public Player(int r, int c) {
        super(r, c);
        inventory = new LinkedList<>();
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
}
