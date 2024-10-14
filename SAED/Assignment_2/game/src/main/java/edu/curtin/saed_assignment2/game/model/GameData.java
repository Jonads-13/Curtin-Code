package edu.curtin.saed_assignment2.game.model;

import java.util.LinkedList;
import java.util.List;

import edu.curtin.saed_assignment2.ParseException;
import edu.curtin.saed_assignment2.api.API;
import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Goal;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.api.plugins.MenuPlugin;

public class GameData implements API {
    
    private Cell[][] map;
    private final List<Cell> specialCells;
    private Player player;
    private Goal goal;
    private final List<Item> items;
    private final List<Obstacle> obstacles;
    private final List<String> plugins;
    private final List<String> scripts;
    private final List<MenuPlugin> menuPlugins;
    private int days;

    public GameData() {
        map = new Cell[10][10];
        specialCells = new LinkedList<>();
        player = new Player(0,0);
        goal = new Goal(5,5);
        items = new LinkedList<>();
        obstacles = new LinkedList<>();
        plugins = new LinkedList<>();
        scripts = new LinkedList<>();
        menuPlugins = new LinkedList<>();
        days = 0;
    }



    // Interface methods

    @Override
    public int[] getPlayerLocation() {
        return new int[]{player.getRow(), player.getCol()};
    }

    @Override
    public List<Item> getPlayerInventory() {
        return player.getInventory();
    }

    @Override
    public Item getMostRecentItem() {
        return player.getInventory().getLast();
    }

    @Override
    public int[] getMapSize() {
        return new int[]{map.length, map[0].length};
    }

    @Override
    public Cell getMapCell(int r, int c) {
        return map[r][c];
    }

    @Override
    public boolean getCellVisbility(int r, int c) {
        return map[r][c].getVisible();
    }

    @Override
    public void registerMenuPlugin(MenuPlugin mp) {
        menuPlugins.add(mp);
    }

    @Override
    public boolean movePlayer(int r, int c) {
        boolean moved = false;
        if(validLocation(r, c)) {
            map[r][c] = player;
            map[player.getRow()][player.getCol()] = new Cell();
            player.setRow(r); 
            player.setCol(c);
            showAroundPlayer();
            moved = true;
        }
        return moved;
    }

    @Override
    public boolean notifyMenuPlugins(String choice) {
        boolean didStuff = false;
        for(MenuPlugin mp : menuPlugins) {
            if(mp.takeAction(choice)) {
                didStuff = true;
            }
        }
        return didStuff;
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

    public void initialiseMap() throws ParseException {
        for (Cell[] row : map) {
            for (int i = 0; i < row.length; i++) {
                row[i] = new Cell();
            }
        }

        // Attempt to place all special cells in the map
        for(Cell cell : specialCells) {
            int r = cell.getRow(), c = cell.getCol();
            if(!validLocation(r, c)) { // out of bounds?
                String message = (map.length-1) + "," + (map[0].length-1) + "\n" + r + "," + c;
                throw new ParseException(message);
            }

            if(locationFilled(cell.getRow(), cell.getCol())) { // Already filled location?
                throw new ParseException(r + "," + c);
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
        if(validLocation(r-1, c)) { map[r-1][c].setVisible(); }
        if(validLocation(r+1, c)) { map[r+1][c].setVisible(); }
        if(validLocation(r, c-1)) { map[r][c-1].setVisible(); }
        if(validLocation(r, c+1)) { map[r][c+1].setVisible(); }
    }

    private boolean locationFilled(int r, int c) {
        boolean filled = false;

        // If the location of the cell is already filled by another special cell, then bad
        if ((map[r][c] instanceof Player) || (map[r][c] instanceof Goal) ||
            (map[r][c] instanceof Item) || (map[r][c] instanceof Obstacle)) {
                filled = true;
            }
        return filled;
    }

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

    public List<MenuPlugin> getMenuPlugins() {
        return menuPlugins;
    }
}
