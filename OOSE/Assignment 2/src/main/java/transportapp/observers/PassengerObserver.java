package transportapp.observers;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public interface PassengerObserver 
{
    public void update(Passenger p, Vehicle v);
}
