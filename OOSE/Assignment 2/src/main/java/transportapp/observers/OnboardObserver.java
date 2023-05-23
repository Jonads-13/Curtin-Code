package transportapp.observers;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class OnboardObserver implements PassengerObserver
{

    @Override
    public void update(Passenger p, Vehicle v)
    {
        System.out.println("Passenger " + p.getId() + " has disembarked " + v.getType() + " " + v.getId() + " at: " + System.nanoTime());
        v.removePassenger(p.getId());
    }
}
