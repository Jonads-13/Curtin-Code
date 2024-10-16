package edu.curtin.saed_assignment2.game.view;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.curtin.saed_assignment2.api.model.Cell;
import edu.curtin.saed_assignment2.api.model.Item;
import edu.curtin.saed_assignment2.api.model.Obstacle;
import edu.curtin.saed_assignment2.api.model.Player;
import edu.curtin.saed_assignment2.game.model.GameData;

public class Display {

    private final ResourceBundle bundle;
    private final Locale locale;

    public Display() {
        bundle = ResourceBundle.getBundle("game-bundle");
        locale = Locale.getDefault();
    }

    public Display(Locale locale) {
        bundle = ResourceBundle.getBundle("game-bundle", locale);
        this.locale = locale;
    }
    
    public void printScreen(GameData data) {
        Cell[][] map = data.getMap();
        Player p = data.getPlayer();
        int days = data.getDays();

        for(Cell[] row : map) {
            for (Cell cell : row) {
                if(cell.getVisible()) {
                    System.out.print(cell.getLabel());
                }
                else {
                    System.out.print(" # ");
                }
            }
            System.out.println();
        }
        ZonedDateTime date = ZonedDateTime.now().plusDays(days);
        DateTimeFormatter dtf = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(locale);
        String dateFmt = dtf.format(date);

        String top = String.format("%s%s, %s%d\n", bundle.getString("date"), dateFmt, bundle.getString("days"), days);
        System.out.println(top);

        // Display player inventory
        System.out.println(bundle.getString("inventory"));
        for(Item item : p.getInventory()) {
            System.out.println(item.getName() + ": " + item.getMessage());
        }
        System.out.println();
        System.out.print(bundle.getString("movement_prompt"));
    }

    public String getInvalidMessage() {
        return bundle.getString("invalid_location");
    }


    public String getFilledMessage() {
        return bundle.getString("filled_location");
    }

    public void showWrongInput() {
        System.out.println(bundle.getString("invalid_choice"));
    }

    public void showBlockedByObstacle(Obstacle o) {
        System.out.println(bundle.getString("obstacle_blocking"));
        for(String requirement : o.getItemRequirements()) {
            System.out.println(requirement);
        }
    }

    public void showTraversedObstacle() {
        System.out.println(bundle.getString("obstacle_traversed"));
    }

    public void showInvalidDirection() {
        System.out.println(bundle.getString("invalid_direction"));
    }

    public void showPlayerWon() {
        System.out.println(bundle.getString("won"));
    }

    public void showLocalePrompt() {
        System.out.println(bundle.getString("locale_prompt"));
    }

    public void showPickedUpItem(String item) {
        System.out.println(bundle.getString("acquired") + item);
    }

    public void showInvalidPlugin(String plugin) {
        System.out.println(bundle.getString("missing_plugin") + plugin);
    }
}

       
