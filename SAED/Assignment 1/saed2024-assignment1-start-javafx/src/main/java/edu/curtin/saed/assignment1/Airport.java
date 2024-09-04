package edu.curtin.saed.assignment1;

import java.util.concurrent.*;
import java.io.*;

public class Airport implements Runnable {
    private int id;
    private GridAreaIcon icon;
    private BlockingQueue<Integer> requests;
    private BlockingQueue<Plane> planes;
    private ExecutorService servicePool;

    public Airport(int id, GridAreaIcon icon) {
        this.id = id;
        this.icon = icon;
        this.requests = new ArrayBlockingQueue<>(App.QUEUE_LENGTH);
        this.planes = new ArrayBlockingQueue<>(App.QUEUE_LENGTH);
        this.servicePool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void run() {
        try {
            Process proc = Runtime.getRuntime().exec(
                new String[]{"saed_flight_requests",
                String.valueOf(App.NUMBER_OF_AIRPORTS),
                String.valueOf(id)});

            BufferedReader br = proc.inputReader();

            try {
                while(true) {
                    String line = br.readLine();
                    try {
                        if(line != null) {
                            int dest = Integer.parseInt(line);
                            requests.put(dest);
                        }
                    }
                    catch(NumberFormatException nfe) {
                            App.updateLog(line);
                    }
                }
            }
            catch(InterruptedException ie) {
                proc.destroy();
            }
        }
        catch(IOException ioe) {
            System.out.println("IOException with saed_filght_requests");
        }    

    }

    public int getNextFlightRequest() throws InterruptedException{
        return requests.take();
    }

    public GridAreaIcon getIcon() {
        return icon;
    }

    public int getID() {
        return id;
    }

    public void planeLanded(Plane plane) {
        Runnable service = () -> {
            try {
                Process proc = Runtime.getRuntime().exec(
                    new String[]{"saed_plane_service",
                    String.valueOf(this.id),
                    String.valueOf(plane.getID())});

                    BufferedReader br = proc.inputReader();
                    String message = br.readLine();
                    try {

                        while(message == null) {
                            message = br.readLine();
                        }
                        App.updateLog(message);
                        planes.put(plane);
                        proc.destroy();
                    }
                    catch(InterruptedException ie) {
                        proc.destroy();
                    }
            }
            catch(IOException ioe) {
                App.updateLog("IOException with saed_plane_service");
            }
        };

        servicePool.execute(service);
    }

    public Plane getNextPlane() throws InterruptedException {
        return planes.take();
    }

    public void endEverything() {
        if(!servicePool.isShutdown()) {
            servicePool.shutdown();
        }
    }
}
