package transportapp.passengers;

import java.util.*;

import transportapp.observers.*;

public abstract class Passenger 
{
    protected int id;
    protected int balance; 
    protected String gender;
    protected PassengerState state;
    protected Set<PassengerObserver> obs;

    public Passenger(int id, int balance, String gender)
    {
        this.id = id;
        this.balance = balance;
        this.gender = gender;
        this.state = new PassengerState();
        this.obs = new HashSet<>();
    }

    public void tapCard()
    {
        for(PassengerObserver obsvr : obs) 
        {
            obsvr.notify();    
        }

        state.tapCard(this);
    }

    public void setState(PassengerState newState)
    {
        state = newState;
    }


}
