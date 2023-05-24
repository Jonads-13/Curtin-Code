package transportapp.states;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Interface that represents a passengers boarded state
 **/

public interface BoardedState 
{
    public void tapCard(Passenger p, Vehicle v);    
}
