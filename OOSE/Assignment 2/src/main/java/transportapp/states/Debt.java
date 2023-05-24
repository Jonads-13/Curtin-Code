package transportapp.states;

import java.util.logging.Logger;

import transportapp.interactive.UserInput;
import transportapp.passengers.Passenger;

public class Debt implements AccountState
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(Debt.class.getName());

    private int numTimesDededucted; // Trigger for when to cancel an account
    private int maxTimes;

    public Debt() // Constructor
    {
        numTimesDededucted = 0; 
        maxTimes = 3; // Abritrary number, can be whatever
    }

    @Override
    public void tapCard(Passenger p)
    {
        UserInput ui = new UserInput();
        System.out.println("Enter an amount of money to move " + p.getId() + "\'s standing out of debt");
        System.out.println(p.getId() + "\'s current balance is: " + p.getBalance());
        System.out.println(p.getId() + " has " + (maxTimes - numTimesDededucted) + " travel(s) left before cancellation");

        p.addBalance(ui.getIntegerInput());
        logger.info(()-> "Passenger " + p.getId() + "\'s balance after adding to it: " + p.getBalance());

        if(p.getBalance() >= 0)
        {
            p.setAccState(new GoodStanding());
            logger.info(()-> "Passenger " + p.getId() + " has moved to good standing");
        }
    }

    @Override
    public void deductPayment(Passenger p, int fee)
    {
        p.setBalance(p.getBalance() - fee);
        logger.info(()-> "Passenger " + p.getId() + "\'s balance after deduction: " + p.getBalance());

        numTimesDededucted++;

        if(numTimesDededucted == maxTimes) // Guard to cancel account
        {
            p.setAccState(new Cancelled());
            logger.info(()-> "Passenger " + p.getId() + " has had their account cancelled");
        }
    }
}
