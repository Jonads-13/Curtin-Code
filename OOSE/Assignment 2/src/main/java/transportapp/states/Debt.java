package transportapp.states;

import java.util.logging.Logger;

import transportapp.interactive.UserInput;
import transportapp.passengers.Passenger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Debt state implementation on AccountState
 **/

public class Debt implements AccountState
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Debt.class.getName());

    private int numTimesDededucted; // Keeps track of amount of times travelled in debt state
    private int maxTimes; // Max travels in debt state before cancellation

    public Debt() // Constructor
    {
        numTimesDededucted = 0; 
        maxTimes = 3; // Abritrary number, can be whatever
    }

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion prompts user to add to their balance
     *
     * @param     p Passenger that tapped thier card
     *
     * @returns   void
     **/

    @Override
    public void tapCard(Passenger p)
    {
        UserInput input = new UserInput();
        
        System.out.println("Enter an amount of money to move " + p.getId() + "\'s standing out of debt");
        System.out.println(p.getId() + "\'s current balance is: " + p.getBalance());
        System.out.println(p.getId() + " has " + (maxTimes - numTimesDededucted) + " travel(s) left before cancellation");

        p.addBalance(input.getIntegerInput());
        logger.info(()-> "Passenger " + p.getId() + "\'s balance after adding to it: " + p.getBalance());

        if(p.getBalance() >= 0)
        {
            p.setAccState(new GoodStanding());
            logger.info(()-> "Passenger " + p.getId() + " has moved to good standing");
        }
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
        p.setBalance(p.getBalance() - fee); // Remove fee from passengers balance
        logger.info(()-> "Passenger " + p.getId() + "\'s balance after deduction: " + p.getBalance());

        numTimesDededucted++; // Increment the amount of times they have travelled in debt state

        if(numTimesDededucted == maxTimes) // Guard to cancel account
        {
            p.setAccState(new Cancelled()); // Account is now cancelled
            logger.info(()-> "Passenger " + p.getId() + " has had their account cancelled");
        }
    } // End deductPayment()
}
