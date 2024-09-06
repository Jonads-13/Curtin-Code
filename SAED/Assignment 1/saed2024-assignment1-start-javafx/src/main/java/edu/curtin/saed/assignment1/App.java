package edu.curtin.saed.assignment1;

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
    // Promoted to class fields to allow access from anywhere in this class
    private TextArea textArea;
    private GridArea area;
    private Label statusText;

    private SimData data; // Will hold all data needed for the simulation
    private Thread[] schedulerThreads; // The main threads to manage each airport





    public static void main(String[] args)
    {
        launch();
    }




    @Override
    public void start(Stage stage)
    {
        data = new SimData(this);
        schedulerThreads = new Thread[SimData.NUMBER_OF_AIRPORTS]; // One scheduler per airport
        // Set up the main "top-down" display area. This is an example only, and you should
        // change this to set it up as you require.
        area = new GridArea(SimData.GRID_WIDTH, SimData.GRID_HEIGHT, data);
        area.setGridLines(false); // If desired
        area.setStyle("-fx-background-color: #0094FF;");

        // Set up other key parts of the user interface. You'll also want to adjust this.

        var startBtn = new Button("Start");
        var endBtn = new Button("End");

        startBtn.setOnAction((event) ->
        {
            System.out.println("Start button pressed");
            Runnable begin = () -> {
                beginSimulation();
            };
            new Thread(begin, "begin-thread").start();
            System.out.println("Begun simulation");
        });
        endBtn.setOnAction((event) ->
        {
            System.out.println("End button pressed");
            endSimulation();
        });
        stage.setOnCloseRequest((event) ->
        {
            System.out.println("Close button pressed");
            endSimulation();
        });

        // Initial stat display
        statusText = new Label(String.format("Total Flights Completed: %d | Flights in Progress: %d | Planes being Serviced: %d", 0,0,0));
        textArea = new TextArea();
        initialise(); // Create all airports and planes

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

        var scene = new Scene(contentPane, 1200, 1000);
        stage.setScene(scene);
        stage.show();
    }




    // Used to place airports randomly
    private double getRandomNumber(double min, double max) 
    {
        return (Math.random() * (max-min)) + min;
    }




    // Create airports and planes and place them in the grid area
    private void initialise() 
    {
        // Create the specified number of airports at random locations
        for(int i = 0; i < SimData.NUMBER_OF_AIRPORTS; i++) 
        {
            double x = getRandomNumber(0, SimData.GRID_WIDTH-1);
            double y = getRandomNumber(0, SimData.GRID_HEIGHT-1);

            // Don't put two airports at the same location
            while(area.isLocationFilled(x, y)) 
            {
                x = getRandomNumber(0, SimData.GRID_WIDTH-1);
                y = getRandomNumber(0, SimData.GRID_HEIGHT-1);
            }

            int airportID = i;
            GridAreaIcon airportIcon = new GridAreaIcon(
                x,   // x
                y,   // y
                0.0, // rotation (degrees)
                1.0, // scale
                App.class.getClassLoader().getResourceAsStream("kame_house.png"),  // Image (InputStream)
                "Airport " + airportID // caption 
            ); 
            area.getIcons().add(airportIcon); 
            Airport airport = new Airport(airportID, airportIcon, data);
            
            // Spawn the specified number of planes at each airport
            for(int j = 0; j < SimData.NUMBER_OF_PLANES; j++) 
            {
                int planeID = (i * SimData.NUMBER_OF_PLANES + j);
                GridAreaIcon planeIcon = new GridAreaIcon(
                    x, y, 0, 1.0,
                    App.class.getClassLoader().getResourceAsStream("goku_nimbus.png"),
                    "Plane " + planeID // caption
                );

                planeIcon.setShown(false); // Hide plane as it's not flying
                area.getIcons().add(planeIcon); 

                Plane plane = new Plane(planeID, planeIcon, data);
                airport.addPlane(plane); // Add to airport's plane list
            }

            // Add airport to global data
            data.addAirport(airportID, airport);

            // Create thread for scheduler that will manage the airport
            schedulerThreads[i] = new Thread(new AirportScheduler(airport, data), "Airport-scheduler");
        }
    }




    // Add message to the textArea
    public void updateLog(String message) 
    {
        Platform.runLater(() -> {
            textArea.appendText(message);
        });
    }




    // Update visual position of planes and stats display
    public void updateDisplay() 
    {
        Platform.runLater(() -> {
            // Get latest stats to display
            int numCompleted = data.getNumFlightsCompleted();
            int numflights = data.getNumCurrentFlights();
            int numService = data.getNumPlanesInService();
            statusText.setText(String.format("Total Flights Completed: %d | Flights in Progress: %d | Planes being Serviced: %d", numCompleted, numflights, numService));
            area.requestLayout();
        });
    }




    // Start the scheduler threads
    private void beginSimulation() 
    {
        // Only try to start the threads if they aren't running
        if(!schedulerThreads[0].isAlive()) 
        {
            for(Thread thread : schedulerThreads) 
            {
                thread.start();
            }
        }
    }




    // Interrupt the scheduler threads and shutdown the thread pools
    private void endSimulation() 
    {
        // Only try to interrupt the threads if they are running
        if(schedulerThreads[0].isAlive())
        {
            for (Thread thread : schedulerThreads) 
            {
                thread.interrupt();
            }
        }
        data.shutdownPools();
    }
}
