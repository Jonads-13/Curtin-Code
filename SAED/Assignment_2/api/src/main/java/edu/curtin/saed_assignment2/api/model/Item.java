package edu.curtin.saed_assignment2.api.model;
public class Item extends Cell {

    private String name;
    private String message;

    public Item(String name, String message) {
        super();
        this.name = name;
        this.message = message;
    }

    public Item(int row, int col, String name, String message) {
        super();
        this.row = row;
        this.col = col;
        this.name = name;
        this.message = message;
        label = " I ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String self = super.toString();
        return String.format("%s name: %s message: %s", self, name, message);
    }
}
