package edu.curtin.saed_assignment2;

public class Item extends Cell {

    private String name;
    private String message;

    public Item(int row, int col, String name, String message) {
        super(row, col);
        this.name = name;
        this.message = message;
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

}
