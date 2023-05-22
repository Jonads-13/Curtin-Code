package transportapp.observers;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class PassengerObserver 
{
    public PassengerObserver() {}

    public void update(Passenger p, String msg, Vehicle v)
    {
        System.out.println("Passenger: " + p.getId() + " has " + msg + " " + v.getType() + ": " + v.getId() + " at: " + System.nanoTime());
    }
}
