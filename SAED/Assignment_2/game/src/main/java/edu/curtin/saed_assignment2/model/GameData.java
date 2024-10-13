package edu.curtin.saed_assignment2.model;

import java.util.LinkedList;
import java.util.List;

public class GameData {
    
    private Cell[][] map;
    private Player player;
    private Goal goal;
    private final List<Item> items;
    private final List<Obstacle> obstacles;
    private final List<String> plugins;
    private final List<String> scripts;

    public GameData() {
        map = new Cell[10][10];
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

    public void addItems(Item item) {
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

    public void addPlugins(String plugin) {
        plugins.add(plugin);
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void addScripts(String script) {
        scripts.add(script);
    }
}
