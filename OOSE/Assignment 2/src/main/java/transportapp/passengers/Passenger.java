package transportapp.passengers;

import java.util.*;

import transportapp.observers.PassengerObserver;
import transportapp.states.AccountState;
import transportapp.states.BoardedState;
import transportapp.vehicles.Vehicle;

public class Passenger 
{
    private int id;
    private int balance;
    private AccountState accState;
    private BoardedState brdState;
    private List<Integer> itinerary;
    private Set<PassengerObserver> offboardObs;
    private Set<PassengerObserver> onboardObs;

    public Passenger(int id, int balance, AccountState aState, BoardedState bState)
    {
        this.id = id;
        this.balance = balance;
        this.accState = aState;
        this.brdState = bState;
        this.itinerary = new LinkedList<>();
        this.offboardObs = new HashSet<>();
        this.onboardObs = new HashSet<>();
    }

    public int getId()
    {
        return id;
    }

    public void setBalance(int b)
    {
        balance = b;
    }

    public void addBalance(int extra)
    {
        balance += extra;
    }

    public int getBalance()
    {
        return balance;
    }

    public void tapOn(Vehicle v)
    {
        brdState.tapCard(this, v);

        for (PassengerObserver obs: offboardObs) 
        {
            obs.update(this, v);
        }
    }

    public void tapOff(Vehicle v)
    {
        brdState.tapCard(this, v);

        for (PassengerObserver obs: onboardObs) 
        {
            obs.update(this, v);
        }
    }

    public void setAccState(AccountState newState)
    {
        accState = newState;
    }

    public AccountState getAccState()
    {
        return accState;
    }

    public void setBrdState(BoardedState newState)
    {
        brdState = newState;
    }

    public void deductPayment(int fee)
    {
        accState.deductPayment(this, fee);
    }

    public void addToItinerary(int legOfJourney)
    {
        itinerary.add(legOfJourney);
    }

    public List<Integer> getItinerary()
    {
        return itinerary;
    }

    public void addOffObserver(PassengerObserver newObs)
    {
        offboardObs.add(newObs);
    }
    
    public void addOnObserver(PassengerObserver newObs)
    {
        onboardObs.add(newObs);
    }

}
