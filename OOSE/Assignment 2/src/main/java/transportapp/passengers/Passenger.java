package transportapp.passengers;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Passenger class for use in the transport app
 **/

import java.util.*;

import transportapp.observers.PassengerObserver;
import transportapp.states.AccountState;
import transportapp.states.BoardedState;
import transportapp.vehicles.Vehicle;

public class Passenger 
{
    private int id; // Unique identifier
    private int balance; // How much they have to spend on travel
    private AccountState accState;
    private BoardedState brdState;
    private List<Integer> itinerary; // List of vehicle id to travel on
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

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Accessor
     *
     * @param     void
     *
     * @returns   id
     **/
    
    public int getId()
    {
        return id;
    } // End getId()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Mutator
     *
     * @param     b new balance of the passenger
     *
     * @returns   void
     **/

    public void setBalance(int b)
    {
        balance = b;
    } // End setBalance()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion "tops up" the passengers balance
     *
     * @param     extra amount to add to the passengers balance
     *
     * @returns   void
     **/

    public void addBalance(int extra)
    {
        balance += extra;
    } // End addBalance

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Accessor
     *
     * @param     void
     *
     * @returns   balance
     **/

    public int getBalance()
    {
        return balance;
    } // End getbalance

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Used when a passenger is boarding a vehicle
     *
     * @param     v Vehicle the passenger is boarding
     *
     * @returns   void
     **/

    public void tapOn(Vehicle v)
    {
        brdState.tapCard(this, v);

        // Notify observers of the event
        for (PassengerObserver obs: offboardObs) 
        {
            obs.update(this, v);
        }
    } // End tapOn()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Used when a passenger is disembarking a vehicle
     *
     * @param     v The vehicle the passeng er is disembarking
     *
     * @returns   void
     **/

    public void tapOff(Vehicle v)
    {
        brdState.tapCard(this, v);

        // Notify observers of the event
        for (PassengerObserver obs: onboardObs) 
        {
            obs.update(this, v);
        }
    } // End tapOff

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Mutator
     *
     * @param     newState the new account state to change to
     *
     * @returns   void
     **/

    public void setAccState(AccountState newState)
    {
        accState = newState;
    } // End setAccState

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Accessor
     *
     * @param     void
     *
     * @returns   accState
     **/

    public AccountState getAccState()
    {
        return accState;
    } // End getAccState

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Mutator
     *
     * @param     newState the new boarded state to change to
     *
     * @returns   void
     **/
    
    public void setBrdState(BoardedState newState)
    {
        brdState = newState;
    } // End setBrdState

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion calls deductPayment() on accState
     *
     * @param     fee amount to deduct
     *
     * @returns   void
     **/

    public void deductPayment(int fee)
    {
        accState.deductPayment(this, fee);
    } // End deductPayment()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Adds a vehicle as a leg of the passengers journey
     *
     * @param     legOfJourney id of the vehicle to travel on
     *
     * @returns   void
     **/

    public void addToItinerary(int legOfJourney)
    {
        itinerary.add(legOfJourney);
    } // End addToItinerary()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Accessor
     *
     * @param     void
     *
     * @returns   itinerary
     **/

    public List<Integer> getItinerary()
    {
        return itinerary;
    } // End getItinerary()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion adds an OffBoard observer
     *
     * @param     newObs the observer to add
     *
     * @returns   void
     **/

    public void addOffObserver(PassengerObserver newObs)
    {
        offboardObs.add(newObs);
    } // End addOffObserver()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion adds an OnBoard observer
     *
     * @param     newObs the observer to add
     *
     * @returns   void
     **/
    
    public void addOnObserver(PassengerObserver newObs)
    {
        onboardObs.add(newObs);
    } // End addOnObserver()

}
