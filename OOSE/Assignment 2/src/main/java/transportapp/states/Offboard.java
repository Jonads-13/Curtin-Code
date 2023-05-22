package transportapp.states;

import java.util.Set;

import transportapp.exceptions.CancelledAccountException;
import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;
import transportapp.observers.PassengerObserver;

public class Offboard implements BoardedState
{
    public Offboard() {} // Constructor

    @Override
    public void tapCard(Passenger p, Vehicle v)
    {
        try
        {
            p.getAccState().tapCard(p);
            p.setBrdState(new Onboard());
            
            Set<PassengerObserver> observers = p.getObservers();

            for(PassengerObserver obsvr : observers) 
            {
                obsvr.updateBoarded(p, v);
            }

        }
        catch(CancelledAccountException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
