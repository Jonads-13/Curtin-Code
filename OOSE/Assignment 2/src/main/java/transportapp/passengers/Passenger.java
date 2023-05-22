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
    private Set<PassengerObserver> obs;

    public Passenger(int id, int balance, AccountState aState, BoardedState bState)
    {
        this.id = id;
        this.balance = balance;
        this.accState = aState;
        this.brdState = bState;
        this.obs = new HashSet<>();
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

    public void tapCard(Vehicle v)
    {
        brdState.tapCard(this, v);
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

    public void addObserver(PassengerObserver newObs)
    {
        obs.add(newObs);
    }

    public Set<PassengerObserver> getObservers()
    {
        return obs;
    }

}
