package edu.curtin.saed.assignment1;

public class Plane implements Runnable 
{
    private int id;
    private GridAreaIcon icon;
    private int origin; // Only used for more informative departure message, so don't need whole object
    private Airport dest;
    private SimData data;




    // Constructor
    public Plane(int id, GridAreaIcon icon, SimData data) 
    {
        this.id = id;
        this.icon = icon;
        this.data = data;
    }




    public int getID() 
    {
        return id;
    }

    public GridAreaIcon getIcon() 
    {
        return icon;
    }

    public void setDestination(int dest) 
    {
        this.dest = data.getAirport(dest);
    }

    public void setOrigin(int origin) 
    {
        this.origin = origin;
    }





    @Override
    public void run() 
    {
        long timeToSleep = (long)((1.0/SimData.AIRSPEED) * 1000); // Determines how often the plane should update its position
        double destX = dest.getIcon().getX(), destY = dest.getIcon().getY();

        icon.setShown(true); // Plane is flying, so show it
        data.updateLog(String.format("Plane %d has departed from Airport %d to Airport %d", this.id, this.origin, this.dest.getID()));
        data.incrementNumCurrentFlights(); // Update stats

        try
        {
            // Loop while not at the destination airport
            while((!data.isDoubleEqual(destX, icon.getX())) || !data.isDoubleEqual(destY, icon.getY())) 
            {
                calculateTrajectory(destX, destY); // Move location
                data.updateDisplay();
                Thread.sleep(timeToSleep); // Wait to move again
            }
            icon.setShown(false); // Plane is at destination, so hide it
            dest.planeLanded(this); // Begin servicing task
        }
        catch(InterruptedException ie) 
        {
            System.out.println("Plane interrupted");
        }
    }






    // Move the icon in the correct direction towards the destination
    private void calculateTrajectory(double destX, double destY) 
    {
        double xDistance = destX - icon.getX();
        double yDistance = destY - icon.getY();
        double curX = icon.getX(), curY = icon.getY(); // Current Coordinates
        double newX = curX, newY = curY; // Variables for new coordinates
        
        // Calculate new Y coordinate
        if(yDistance >= 1) 
        {
            newY = curY + 1;
        }
        else if(Math.abs(yDistance) < 1) // Last move along this axis
        {
            newY = curY + yDistance;
        }
        else if(yDistance <= -1) 
        {
            newY = curY - 1;
        }
        
        // Calculate new X coordinate
        if(xDistance >= 1) 
        {
            newX = curX + 1;
        }
        else if(Math.abs(xDistance) < 1) // Last move along this axis
        {
            newX = curX + xDistance;
        }
        else if(xDistance <= -1) 
        {
            newX = curX - 1;
        }

        // Update plane position
        icon.setPosition(newX, newY);
    }
}
                                                                                                                                                                          