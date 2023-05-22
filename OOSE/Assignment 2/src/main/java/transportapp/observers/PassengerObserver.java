package transportapp.observers;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class PassengerObserver 
{
    public PassengerObserver() {}

    public void updateBoarded(Passenger p, Vehicle v)
    {
        System.out.println("Passenger: " + p.getId() + " has boarded " + v.getType() + ": " + v.getId() + " at: " + System.nanoTime());
        v.addPassenger(p);
    }

    public void updateDisembarked(Passenger p, Vehicle v)
    {
        System.out.println("Passenger: " + p.getId() + " has disembarked " + v.getType() + ": " + v.getId() + " at: " + System.nanoTime());
        v.removePassenger(p.getId());
    }
}
