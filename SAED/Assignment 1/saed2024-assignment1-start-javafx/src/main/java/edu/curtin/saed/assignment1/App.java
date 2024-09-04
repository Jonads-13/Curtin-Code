package edu.curtin.saed.assignment1;

import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This is demonstration code intended for you to modify. Currently, it sets up a rudimentary
 * JavaFX GUI with the basic elements required for the assignment.
 *
 * (There is an equivalent Swing version of this, which you can use if you have trouble getting
 * JavaFX as a whole to work.)
 *
 * You will need to use the GridArea object, and create various GridAreaIcon objects, to represent
 * the on-screen map.
 *
 * Use the startBtn, endBtn, statusText and textArea objects for the other input/output required by
 * the assignment specification.
 *
 * Break this up into multiple methods and/or classes if it seems appropriate. Promote some of the
 * local variables to fields if needed.
 */
public class App extends Application
{
    // Default values for the simulation
    public static final double GRID_WIDTH = 30;
    public static final double GRID_HEIGHT = 30;
    public static final int NUMBER_OF_AIRPORTS = 10;
    public static final int NUMBER_OF_PLANES = 10;
    public static final double AIRSPEED = 1;
    public static final int QUEUE_LENGTH = 30;

    private static TextArea textArea;
    private static GridArea area;

    private static Map<Integer, Airport> airports = new HashMap<>();
    private Map<Integer, Plane> planes = new HashMap<>();
    private AirportScheduler scheduler = null;
    private Thread schedulerThread;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        // Set up the main "top-down" display area. This is an example only, and you should
        // change this to set it up as you require.

        area = new GridArea(GRID_WIDTH, GRID_HEIGHT);
        // area.setGridLines(false); // If desired
        area.setStyle("-fx-background-color: #006000;");

        // Set up other key parts of the user interface. You'll also want to adjust this.

        var startBtn = new Button("Start");
        var endBtn = new Button("End");

        startBtn.setOnAction((event) ->
        {
            System.out.println("Start button pressed");
            Runnable begin = () -> {
                beginSimulation();
            };
            Thread beginThread = new Thread(begin, "begin");
            beginThread.start();
            // TODO: start logic
        });
        endBtn.setOnAction((event) ->
        {
            System.out.println("End button pressed");
            Runnable ending = ()-> {
                schedulerThread.interrupt();
                scheduler = null;
                
            };
            Thread endThread = new Thread(ending, "end");
            endThread.start();

            // TODO: end logic
        });
        stage.setOnCloseRequest((event) ->
        {
            System.out.println("Close button pressed");
            // TODO: close logic
        });
        var statusText = new Label("Label Text");
        textArea = new TextArea();
        // textArea.appendText("Sidebar\n");
        // textArea.appendText("Text\n");


        // Below is basically just the GUI "plumbing" (connecting things together).

        var toolbar = new ToolBar();
        toolbar.getItems().addAll(startBtn, endBtn, new Separator(), statusText);

        var splitPane = new SplitPane();
        splitPane.getItems().addAll(area, textArea);
        splitPane.setDividerPositions(0.75);

        stage.setTitle("Air Traffic Simulator");
        var contentPane = new BorderPane();
        contentPane.setTop(toolbar);
        contentPane.setCenter(splitPane);

        initialise();

        var scene = new Scene(contentPane, 1200, 1000);
        stage.setScene(scene);
        stage.show();
    }

    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max-min)) + min;
    }

    private void initialise() {
        // Create the specified number of airports at random locations
        for(int i = 0; i < NUMBER_OF_AIRPORTS; i++) {
            double x = getRandomNumber(0, GRID_WIDTH-1);
            double y = getRandomNumber(0, GRID_HEIGHT-1);

            // Don't put two airports at the same location
            while(area.isLocationFilled(x, y)) {
                x = getRandomNumber(0, GRID_WIDTH-1);
                y = getRandomNumber(0, GRID_HEIGHT-1);
            }

            int airportID = i;
            GridAreaIcon airportIcon = new GridAreaIcon(
                x,   // x
                y,   // y
                0.0, // rotation (degrees)
                1.0, // scale
                App.class.getClassLoader().getResourceAsStream("airport.png"),  // Image (InputStream)
                "Airport " + airportID); // caption 
            area.getIcons().add(airportIcon); 
            Airport airport = new Airport(airportID, airportIcon);
            airports.put(airportID, airport);
            
            // Spawn the specified number of planes at each airport
            for(int j = 0; j < NUMBER_OF_PLANES; j++) {
                int planeID = (i * NUMBER_OF_PLANES + j);
                GridAreaIcon planeIcon = new GridAreaIcon(
                    x, y, 45.0, 1.0,
                    App.class.getClassLoader().getResourceAsStream("plane.png"),
                    "Plane " + planeID); // caption
                    planeIcon.setShown(false);
                area.getIcons().add(planeIcon);
                Plane plane = new Plane(planeID, planeIcon);
                planes.put(planeID, plane);
            }
        }
    }

    public static void updateLog(String message) {
        Platform.runLater(() -> {
            textArea.appendText(message + "\n");
        });
    }

    public static void updateDisplay() {
        Platform.runLater(() -> {
            area.requestLayout();
        });
    }

    private void beginSimulation() {
        if(scheduler == null) {
            scheduler = new AirportScheduler(airports);
            schedulerThread = new Thread(scheduler, "scheduler-thread");
            schedulerThread.start();
        }
    }

    public static boolean isDoubleEqual(double a, double b) {
        return Math.abs(a - b) < 0.0000001;
    }

    public static Airport getAirport(int id) {
        return airports.get(id);
    }
}
