package edu.curtin.saed.assignment1;

import java.util.concurrent.*;
import java.util.*;

public class AirportScheduler implements Runnable {
    private Map<Integer, Airport> airports;
    private ExecutorService flightPool;

    public AirportScheduler(Map<Integer, Airport> airports) {
        this.airports = airports;
        flightPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void run() {

    }

    private void parentAirport(Airport airport) {
        Runnable airportParent = () -> {
            try {
                while(true) {
                    int destination = airport.getNextFlightRequest();
                    Airport dest = airports.get(destination);
                    Plane takeOff = airport.getNextPlane();
                    takeOff.setDestination(dest);
                    flightPool.submit(takeOff);
                }
            } 
            catch(InterruptedException ie) {
    
            }
        };
        Thread parentThread = new Thread(airportParent, "airport-parent-thread");
        parentThread.start();
    }

    public void endEverything() {
        flightPool.shutdown();
        for (Airport airport : airports.values()) {
            airport.endEverything();
        }
    }
    
}
