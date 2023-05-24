package transportapp.observers;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Observer interface
 **/

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public interface PassengerObserver 
{
    public void update(Passenger p, Vehicle v);
}
