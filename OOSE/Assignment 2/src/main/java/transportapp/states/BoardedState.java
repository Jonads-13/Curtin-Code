package transportapp.states;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public interface BoardedState 
{
    public void tapCard(Passenger p, Vehicle v);    
}
