package transportapp.states;

import java.util.logging.Logger;

import transportapp.passengers.Passenger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Implementaion of an account in good standing
 **/

public class GoodStanding implements AccountState 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(GoodStanding.class.getName());

    public GoodStanding() {} // Constructor


    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Does nothing in this state
     *
     * @param     p Passenger that tapped thier card
     *
     * @returns   void
     **/
    
    @Override
    public void tapCard(Passenger p)
    {
        // Empty
    } // End tapCard()


    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion deducts the fee of the travel from the passenger
     *
     * @param     p Passenger to deduct money from
     * @param     fee amount to deduct from the passenger 
     *
     * @returns   void
     **/

    @Override
    public void deductPayment(Passenger p, int fee)
    {
        p.setBalance(p.getBalance() - fee);
        logger.info(()-> "Passenger " + p.getId() + "\'s balance after deduction: " + p.getBalance());

        if(p.getBalance() < 0)
        {
            p.setAccState(new Debt());
            logger.info(()-> "Passenger " + p.getId() + " has moved into debt");
        }
    } // End deductPayment()
}
