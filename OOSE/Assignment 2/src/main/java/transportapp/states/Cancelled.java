package transportapp.states;

import java.util.logging.Logger;

import transportapp.exceptions.CancelledAccountException;
import transportapp.passengers.Passenger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Implentation of a cancelled account state
 **/

public class Cancelled implements AccountState
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Cancelled.class.getName());  

    public Cancelled() {} // Constructor


    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion throws exception when a cancelled account tries to tap their account
     *
     * @param     p Passenger that tapped their card
     *
     * @returns   void
     * 
     * @throws    CancelledAccountException
     **/
    
    @Override
    public void tapCard(Passenger p)
    {
        logger.warning(() -> "Passenger: " + p.getId() + " has had their account cancelled due to outstanding debt");
        throw new CancelledAccountException("Passenger: " + p.getId() + " has had their account cancelled due to outstanding debt");
    } // End tapCard()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Bad things have happened if called in this state
     *
     * @param     p Passenger to deduct fee from
     * @param     fee amount to deduct from passenger
     *
     * @returns   void
     * 
     * @throws    IllegalStateException
     **/
    
    @Override
    public void deductPayment(Passenger p, int fee)
    {
        logger.severe("Should never call this function in cancelled state");
        throw new IllegalStateException();
    } // End deductPayment()
}
