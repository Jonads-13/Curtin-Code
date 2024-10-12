package edu.curtin.saed_assignment2;

public class App {

    public static void main(String[] args) {
        Cell[][] map = initMap();
        Player player = new Player(2,5);
        map[2][5] = player;
        Goal goal = new Goal(8,9);
        map[8][9] = goal;

        printMap(map);
    }

    private static Cell[][] initMap() {
        Cell[][] map = new Cell[10][10];

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

    private static void printMap(Cell[][] map) {
        for(Cell[] row : map) {
            for (Cell cell : row) {
                if(cell instanceof Player){
                    System.out.print(" P ");
                }
                else if(cell instanceof Goal) {
                    System.out.print(" G ");
                }
                else if(cell.getVisited()) {
                    System.out.print("   ");
                }
                else {
                    System.out.print(" # ");
                }
            }
            System.out.println();
        }
    }
    
}
