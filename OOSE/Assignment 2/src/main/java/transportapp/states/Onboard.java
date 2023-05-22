package transportapp.states;

import java.util.Set;

import transportapp.observers.PassengerObserver;
import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class Onboard implements BoardedState
{
    public Onboard() {} // Constructor

    @Override
    public void tapCard(Passenger p, Vehicle v)
    {
        p.deductPayment(2); // For simplicity fee for all travel is 2
        
        p.setBrdState(new Offboard());
          
        Set<PassengerObserver> observers = p.getObservers();

        for(PassengerObserver obsvr : observers) 
        {
            obsvr.updateDisembarked(p, v);    
        }
    }
}
