package transportapp.states;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class Onboard implements BoardedState
{
    public Onboard() {} // Constructor

    @Override
    public void tapCard(Passenger p, Vehicle v)
    {
        p.deductPayment(v.getFee()); 
        p.setBrdState(new Offboard());
    }
}
