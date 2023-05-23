package transportapp.states;

import transportapp.interactive.UserInput;
import transportapp.passengers.Passenger;

public class Debt implements AccountState
{
    private int numTimesDededucted; // Trigger for when to cancel an account
    private int maxTimes;

    public Debt() // Constructor
    {
        numTimesDededucted = 0; 
        maxTimes = 3; // Abritrary number can be whatever
    }

    @Override
    public void tapCard(Passenger p)
    {
        UserInput ui = new UserInput();
        System.out.println("Enter an amount of money to move " + p.getId() + "\'s standing out of debt");
        System.out.println(p.getId() + "\'s current balance is: " + p.getBalance());
        System.out.println(p.getId() + " has " + (maxTimes - numTimesDededucted) + " travels left before cancellation");

        p.addBalance(ui.getIntegerInput());
    }

    @Override
    public void deductPayment(Passenger p, int fee)
    {
        p.setBalance(p.getBalance() - fee);

        numTimesDededucted++;

        if(p.getBalance() > 0)
        {
            p.setAccState(new GoodStanding());
        }
        else if(numTimesDededucted == maxTimes) // Trigger to cancel account
        {
            p.setAccState(new Cancelled());
        }
    }
}
