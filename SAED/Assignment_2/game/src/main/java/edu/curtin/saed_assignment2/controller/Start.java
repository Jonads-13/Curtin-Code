package edu.curtin.saed_assignment2.controller;

import edu.curtin.saed_assignment2.model.Cell;
import edu.curtin.saed_assignment2.model.Goal;
import edu.curtin.saed_assignment2.model.Item;
import edu.curtin.saed_assignment2.model.Obstacle;
import edu.curtin.saed_assignment2.model.Player;

public class Start {

    private Cell[][] map;
    private Player player;
    private Goal goal;

    public Start(int rows, int columns, int pr, int pc, int gr, int gc) {
        map = new Cell[rows][columns];
        player = new Player(pr, pc);
        goal = new Goal(gr, gc);
        initMap();
    }

    public void init() {
        map[2][5] = player;
        map[player.getRow()-1][player.getCol()].setVisible();
        map[player.getRow()+1][player.getCol()].setVisible();
        map[player.getRow()][player.getCol()+1].setVisible();
        map[player.getRow()][player.getCol()-1].setVisible();
        map[8][9] = goal;
    }

    private Cell[][] initMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++){
                map[i][j] = new Cell(i,j);
            }
        }

        map[1][5] = new Obstacle(1,5, new String[]{"Golden Lance", "Silver Ring"});
        map[3][4] = new Obstacle(3,4, new String[]{"Golden Lance", "Silver Ring"});
        map[2][2] = new Item(2,2, "Golden Lance", "Lance made of gold");
        map[5][5] = new Item(5,5, "Silver Ring", "Ring made of silver");

        return map;
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

    
}
