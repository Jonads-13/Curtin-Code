package transportapp.observers;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class OffboardObserver implements PassengerObserver
{

    @Override
    public void update(Passenger p, Vehicle v)
    {
        System.out.println("Passenger " + p.getId() + " has boarded " + v.getType() + " " + v.getId() + " at: " + System.nanoTime());
        v.addPassenger(p);
    }
}
