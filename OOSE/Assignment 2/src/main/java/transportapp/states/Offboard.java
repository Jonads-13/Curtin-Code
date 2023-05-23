package transportapp.states;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class Offboard implements BoardedState
{
    public Offboard() {} // Constructor

    @Override
    public void tapCard(Passenger p, Vehicle v)
    {
        p.getAccState().tapCard(p);
        p.setBrdState(new Onboard());
    }
}
