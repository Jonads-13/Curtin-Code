package edu.curtin.saed_assignment2.model;

import java.util.LinkedList;
import java.util.List;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.model.classes.Cell;
import edu.curtin.saed_assignment2.model.classes.Goal;
import edu.curtin.saed_assignment2.model.classes.Item;
import edu.curtin.saed_assignment2.model.classes.Obstacle;
import edu.curtin.saed_assignment2.model.classes.Player;

public class GameData {
    
    private Cell[][] map;
    private final List<Cell> specialCells;
    private Player player;
    private Goal goal;
    private final List<Item> items;
    private final List<Obstacle> obstacles;
    private final List<String> plugins;
    private final List<String> scripts;

    public GameData() {
        map = new Cell[10][10];
        specialCells = new LinkedList<>();
        player = new Player(0,0);
        goal = new Goal(5,5);
        items = new LinkedList<>();
        obstacles = new LinkedList<>();
        plugins = new LinkedList<>();
        scripts = new LinkedList<>();
    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public List<Cell> getSpecialCells() {
        return specialCells;
    }

    public void addSpecialCell(Cell specialCell) {
        specialCells.add(specialCell);
    }

    public void initialiseMap() throws ParseException{
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell(i,j);
            }
        }

        // Attempt to place all special cells in the map
        for(Cell cell : specialCells) {
            if(!validLocation(cell)) { // out of bounds?
                String message = "Specified locations must be within map bounds: " +
                                    (map.length-1) + "," + (map[0].length-1) + "\n" + 
                                    cell.getRow() + "," + cell.getCol();
                throw new ParseException(message);
            }

            if(locationFilled(cell)) { // Already filled location?
                throw new ParseException("Cannot have more than one type of special cell in a location: " + cell.getRow() + "," + cell.getCol());
            }

            // No thrown exceptions so we can place the cell in the map
            map[cell.getRow()][cell.getCol()] = cell;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<String> getPlugins() {
        return plugins;
    }

    public void addPlugin(String plugin) {
        plugins.add(plugin);
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void addScript(String script) {
        scripts.add(script);
    }

    public void displayData() {
        for(Cell[] row : map) {
            for(Cell cell : row) {
                System.out.print(cell.getLabel());
            }
            System.out.println();
        }
        System.out.println(player);
        System.out.println(goal);

        for(Item item : items) {
            System.out.println(item);
        }
        for(Obstacle obstacle : obstacles) {
            System.out.println(obstacle);
        }
        for(String plugin : plugins) {
            System.out.println(plugin);
        }
        for(String script : scripts) {
            System.out.println(script);
        }
    }

    private boolean locationFilled(Cell cell) {
        boolean filled = false;

        // If the location of the cell is already filled by another special cell, then bad
        if ((map[cell.getRow()][cell.getCol()] instanceof Player) || 
            (map[cell.getRow()][cell.getCol()] instanceof Goal) ||
            (map[cell.getRow()][cell.getCol()] instanceof Item) ||
            (map[cell.getRow()][cell.getCol()] instanceof Obstacle)) {
                filled = true;
            }
        return filled;
    }

    private boolean validLocation(Cell cell) {
        boolean valid = false;

        if (((cell.getRow() < map.length) && (cell.getRow() >= 0)) && 
            ((cell.getCol() < map[0].length) && (cell.getCol() >= 0))) {
            valid = true; // location within bounds
        }

        return valid;

    }
}
