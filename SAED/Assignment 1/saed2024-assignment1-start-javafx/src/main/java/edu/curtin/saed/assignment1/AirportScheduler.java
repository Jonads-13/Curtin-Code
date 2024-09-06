package edu.curtin.saed.assignment1;


public class AirportScheduler implements Runnable 
{
    private Airport airport;
    private SimData data;

    // Constructor
    public AirportScheduler(Airport airport, SimData data) 
    {
        this.airport = airport;
        this.data = data;
    }

    @Override
    public void run() // Consumer thread
    {
        // Producer thread
        Thread airportThread = new Thread(airport, "airport-thread");
        airportThread.start();

        try 
        {
            while(true) 
            {
                int dest = airport.getNextFlightRequest(); // Get next request from producer
                Plane takeOff = airport.getNextPlane(); // Get a plane to do the flight request
                takeOff.setDestination(dest); 
                takeOff.setOrigin(airport.getID());
                data.addToFlightPool(takeOff); // Add to thread pool
            }
        }
        catch(InterruptedException ie) 
        {
            System.out.println("Scheduler interrupted");
            airportThread.interrupt(); // Propogate interruption to producer
        }
    }
}
