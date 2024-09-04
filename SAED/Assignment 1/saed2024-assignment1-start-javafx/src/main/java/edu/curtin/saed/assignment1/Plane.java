package edu.curtin.saed.assignment1;

public class Plane implements Runnable {
    private int id;
    private GridAreaIcon icon;
    private Airport dest;

    public Plane(int id, GridAreaIcon icon) {
        this.id = id;
        this.icon = icon;
    }

    public int getID() {
        return id;
    }

    public GridAreaIcon getIcon() {
        return icon;
    }
    public void setDestination(Airport dest) {
        this.dest = dest;
    }

    @Override
    public void run() {
        long timeToSleep = (long)(1/App.AIRSPEED);
        double destX = dest.getIcon().getX();
        double destY = dest.getIcon().getY();

        try{
            // Loop while not at the destination airport
            while((!App.isDoubleEqual(destX, icon.getX())) && !App.isDoubleEqual(destY, icon.getY())) {
                calculateTrajectory(destX, destY);
                App.updateDisplay();
                Thread.sleep(timeToSleep);
            }
            dest.planeLanded(this); // Begin servicing task
        }
        catch(InterruptedException ie) {

        }
    }

    private void calculateTrajectory(double destX, double destY) {
        double xDistance = destX - icon.getX();
        double yDistance = destY - icon.getY();
        double curX = icon.getX(), curY = icon.getY(); // Current Coordinates
        double newX = curX, newY = curY; // Variables for new coordinates
        
        // Calculate new Y coordinate
        if(yDistance >= 1) {
            newY = curY + 1;
        }
        else if(Math.abs(yDistance) < 1) {
            newY = curY + yDistance;
        }
        else if(yDistance <= -1) {
            newY = curY - 1;
        }

        // Calculate new X coordinate
        if(xDistance >= 1) {
            newX = curX + 1;
        }
        else if(Math.abs(xDistance) < 1) {
            newX = curX + xDistance;
        }
        else if(xDistance <= -1) {
            newX = curX - 1;
        }

        // Update plane position
        icon.setPosition(newX, newY);
    }
}
                                                                                                                                                                          