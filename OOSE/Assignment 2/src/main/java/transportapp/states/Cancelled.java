package transportapp.states;

import java.util.logging.Logger;

import transportapp.exceptions.CancelledAccountException;
import transportapp.passengers.Passenger;

public class Cancelled implements AccountState
{
    private static final Logger logger = Logger.getLogger(Cancelled.class.getName());  

    public Cancelled() {} // Constructor

    public void tapCard(Passenger p)
    {
        logger.severe("Passenger: " + p.getId() + " has had their account cancelled due to outstanding debt");
        throw new CancelledAccountException("Passenger: " + p.getId() + " has had their account cancelled due to outstanding debt");
    }
    
    public void deductPayment(Passenger p, int fee)
    {
        logger.severe("Should never call this function in cancelled state");
        throw new IllegalStateException();
    }
}
