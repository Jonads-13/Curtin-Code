package edu.curtin.saed_assignment2.game.model;

import java.util.LinkedList;
import java.util.List;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Goal;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.game.model.exceptions.InvalidLocationException;
import edu.curtin.saed_assignment2.game.model.exceptions.FilledLocationException;

// Class to act as database for the game
public class GameData {
    
    private Cell[][] map;
    private Player player;
    private Goal goal;
    private int days;
    private final List<Item> items;
    private final List<Obstacle> obstacles;
    private final List<Cell> specialCells;
    private final List<String> plugins;
    private final List<String> scripts;


    // Constructor
    public GameData() {
        // Default values, will either be overwritten or game is stopped
        map = new Cell[10][10];
        player = new Player(0,0);
        goal = new Goal(5,5);
        days = 0;
        items = new LinkedList<>();
        obstacles = new LinkedList<>();
        specialCells = new LinkedList<>();
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





    // Fill the map with all the specified types of Cells
    public void initialiseMap() throws ParseException {
        for (Cell[] row : map) { // Create Cell object at each map index
            for (int i = 0; i < row.length; i++) {
                row[i] = new Cell();
            }
        }

        // Attempt to place all special cells in the map
        for(Cell cell : specialCells) {
            int r = cell.getRow(), c = cell.getCol();
            if(!validLocation(r, c)) { // out of bounds?
                String message = (map.length-1) + "," + (map[0].length-1) + "\n" + r + "," + c;
                throw new InvalidLocationException(message);
            }

            if(locationFilled(cell.getRow(), cell.getCol())) { // Already filled location?
                throw new FilledLocationException(r + "," + c);
            }

            // No thrown exceptions so we can place the cell in the map
            map[r][c] = cell;
        }

        showAroundPlayer();
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





    // Change cells around player to be visible
    public void showAroundPlayer() {
        int r = player.getRow(), c = player.getCol();

        // If the cell is within bounds, make it visible
        if(validLocation(r-1, c)) { map[r-1][c].setVisibility(true); }
        if(validLocation(r+1, c)) { map[r+1][c].setVisibility(true); }
        if(validLocation(r, c-1)) { map[r][c-1].setVisibility(true); }
        if(validLocation(r, c+1)) { map[r][c+1].setVisibility(true); }
    }




    // Check if a map element already has a special cell there
    private boolean locationFilled(int r, int c) {
        boolean filled = false;

        // If the location of the cell is already filled by another special cell, then bad
        if ((map[r][c] instanceof Player) || (map[r][c] instanceof Goal) ||
            (map[r][c] instanceof Item) || (map[r][c] instanceof Obstacle)) {
                filled = true;
            }
        return filled;
    }




    // Check location is within map bounds
    public boolean validLocation(int r, int c) {
        boolean valid = false;

        if (((r < map.length) && (r >= 0)) && 
            ((c < map[0].length) && (c >= 0))) {
            valid = true; // location within bounds
        }

        return valid;
    }




    public void incrementDays() {
        days++;
    }



    

    public int getDays() {
        return days;
    }
}
