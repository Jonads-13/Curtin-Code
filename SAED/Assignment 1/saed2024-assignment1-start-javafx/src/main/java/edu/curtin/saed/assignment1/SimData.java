package edu.curtin.saed.assignment1;

import java.util.*;
import java.util.concurrent.*;

public class SimData 
{
    // Default values for the simulation
    public static final double GRID_WIDTH = 30;
    public static final double GRID_HEIGHT = 30;
    public static final int NUMBER_OF_AIRPORTS = 10;
    public static final int NUMBER_OF_PLANES = 5;
    public static final double AIRSPEED = 1.0;

    private App app; // App instance
    private Map<Integer, Airport> airports = new HashMap<>(); // Holds all airports in the simulation

    // Stats tracking variables, not atomic as not explicitly taught
    private int numCurrentFlights = 0;
    private int numFlightsCompleted = 0;
    private int numPlanesInService = 0;

    // Mutexs to be able the safely read and write to the above stat variables
    private Object currentMutex = new Object();
    private Object completeMutex = new Object();
    private Object serviceMutex = new Object();

    // Thread pools
    private ExecutorService flightPool = Executors.newFixedThreadPool(NUMBER_OF_AIRPORTS * NUMBER_OF_PLANES);
    private ExecutorService servicePool = Executors.newFixedThreadPool(NUMBER_OF_AIRPORTS * NUMBER_OF_PLANES);



    // Constructor
    public SimData(App instance) 
    {
        this.app = instance;
    }


    

    // Get and airport by its ID
    public Airport getAirport(int id) 
    {
        return airports.get(id);
    }

    // Add an airport to the HashMap
    public void addAirport(int id, Airport ap)
    {
        airports.put(id, ap);
    }





    // Formats the message before sending to the GUI
    public void updateLog(String message) 
    {
        app.updateLog(message + "\n");
    }





    // Just a wrapper so that other classes don't also need a reference to the App
    public void updateDisplay() 
    {
        app.updateDisplay();
    }





    public void incrementNumFlightsCompleted() 
    {
        synchronized(completeMutex) // Thread safety
        {
            numFlightsCompleted++;
        }
    }

    public void incrementNumCurrentFlights() 
    {
        synchronized(currentMutex) // Thread safety
        {
            numCurrentFlights++;
        }
    }

    public void decrementNumCurrentFlights() 
    {
        synchronized(currentMutex) // Thread safety
        {
            numCurrentFlights--;
        }
    }
    
    public void decrementNumPlanesInService() 
    {
        synchronized(serviceMutex) // Thread safety
        {
            numPlanesInService--;
        }
    }

    public void incrementNumPlanesInService() 
    {
        synchronized(serviceMutex) // Thread safety
        {
            numPlanesInService++;
        }
    }

    public int getNumFlightsCompleted() 
    {
        synchronized(completeMutex) // Thread safety
        {
            return numFlightsCompleted;
        }
    }

    public int getNumCurrentFlights() 
    {
        synchronized(currentMutex) // Thread safety
        {
            return numCurrentFlights;
        }
    }

    public int getNumPlanesInService() 
    {
        synchronized(serviceMutex) // Thread safety
        {
            return numPlanesInService;
        }
    }



    /*
     * These two methods add Runnable tasks to the respective pool
     */

    public void addToFlightPool(Runnable flight) 
    {
        flightPool.submit(flight);
    }

    public void addToServicePool(Runnable service) 
    {
        servicePool.submit(service);
    }





    // Attempts to shutdown the threadpools
    public void shutdownPools() 
    {
        if(!flightPool.isShutdown()) 
        {
            flightPool.shutdownNow();
        }

        if(!servicePool.isShutdown())
        {
            servicePool.shutdownNow();
        }
    }



    // Checks double equality because of floating point fun
    public boolean isDoubleEqual(double a, double b) 
    {
        return Math.abs(a - b) < 0.0000001; // Close enough right?
    }
}
