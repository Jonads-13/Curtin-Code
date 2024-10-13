package edu.curtin.saed_assignment2.game.view;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.game.model.GameData;

public class Display {

    private final ResourceBundle bundle;

    public Display() {
        bundle = ResourceBundle.getBundle("bundle");
    }

    public Display(Locale locale) {
        bundle = ResourceBundle.getBundle("bundle", locale);
    }
    
    public void printScreen(GameData data) {
        Cell[][] map = data.getMap();
        Player p = data.getPlayer();
        int days = data.getDays();

        for(Cell[] row : map) {
            for (Cell cell : row) {
                if(cell.getVisible()) {
                    System.out.println(cell.getLabel());
                }
                else {
                    System.out.println("###");
                }
            }
        }

        String top = String.format("%s%s, %s%d", bundle.getString("date"), LocalDate.now().plusDays(days), bundle.getString("days"), days);
        System.out.println(top);

        // Display player inventory
        System.out.println(bundle.getString("inventory"));
        for(Item item : p.getInventory()) {
            System.out.println(item.getName() + ": " + item.getMessage());
        }
        System.out.println(bundle.getString("movement_prompt"));
    }

    public String getInvalidMessage() {
        return bundle.getString("invalid_location");
    }


    public String getFilledMessage() {
        return bundle.getString("filled_location");
    }
}

       
