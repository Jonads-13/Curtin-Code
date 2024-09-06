package edu.curtin.saed.assignment1;

import java.util.concurrent.*;
import java.io.*;

public class Airport implements Runnable 
{
    private int id;
    private GridAreaIcon icon;
    private SimData data;

    // Unbounded because they are constanly being taken from and won't get too big
    private BlockingQueue<Integer> requests = new LinkedBlockingQueue<>();
    private BlockingQueue<Plane> planes = new LinkedBlockingQueue<>();




    // Constructor
    public Airport(int id, GridAreaIcon icon, SimData data) 
    {
        this.id = id;
        this.icon = icon;
        this.data = data;
    }





    @Override
    public void run() // Producer thread
    {
        try 
        {
            // Begin flight request process
            Process proc = Runtime.getRuntime().exec(
                new String[]{"saed_flight_requests",
                    String.valueOf(SimData.NUMBER_OF_AIRPORTS),
                    String.valueOf(id)
                }
            );

            try(BufferedReader br = proc.inputReader()) 
            {
                while(true) 
                {
                    String line = br.readLine();
                    try 
                    {
                        if(line != null) // Don't try to parse empty lines
                        {
                            int dest = Integer.parseInt(line);
                            requests.put(dest); // Add to the blocking queue
                        }
                    }
                    catch(NumberFormatException nfe) 
                    {
                        // David specified that if this exception was caught to assume it was a string message
                        data.updateLog(line);
                    }
                }
            }
            catch(InterruptedException ie) 
            {
                System.out.println("Flight requests interruputed");
                proc.destroy();
            }
        }
        catch(IOException ioe) 
        {
            System.out.println("IOException with saed_filght_requests");
        }    

    }

    // So the app can add planes to the airport without knowing about it being a blocking queue
    public void addPlane(Plane p) 
    {
        planes.add(p);
    }

    public GridAreaIcon getIcon() 
    {
        return icon;
    }

    public int getID() 
    {
        return id;
    }




    // Runnable task to service a plane when it lands
    public void planeLanded(Plane plane) 
    {
        // Update the stats
        data.decrementNumCurrentFlights();
        data.incrementNumFlightsCompleted();

        Runnable service = () -> {
            try 
            {
                data.updateLog(String.format("Plane %d has landed at airport %d", plane.getID(), this.id));

                // Begin plane service process
                Process proc = Runtime.getRuntime().exec(
                    new String[]{"saed_plane_service",
                        String.valueOf(this.id),
                        String.valueOf(plane.getID())
                    }
                );

                try(BufferedReader br = proc.inputReader()) 
                {
                    data.incrementNumPlanesInService(); // Update stats;

                    String message = br.readLine();

                    // Read until service is done
                    while(message == null) 
                    {
                        message = br.readLine(); 
                    }

                    data.decrementNumPlanesInService(); // Update stats
                    data.updateLog(message); // Send message to GUI
                    planes.put(plane); // Plane is ready to recieve a flight request
                    proc.destroy(); 
                }
                catch(InterruptedException ie) 
                {
                    System.out.println("Service interrupted");
                    proc.destroy();
                }
            }
            catch(IOException ioe) 
            {
                System.out.println("IOException with saed_plane_service");
            }
        };

        data.addToServicePool(service); // Add to thread pool for service tasks
    }




    
    /*
     * Following two methods are called by consumer threads to take
     * items from the blocking queues
     */

    public int getNextFlightRequest() throws InterruptedException 
    {
        return requests.take();
    }

    
    public Plane getNextPlane() throws InterruptedException 
    {
        return planes.take();
    }
}
