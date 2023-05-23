package transportapp.states;

import transportapp.interactive.UserInput;
import transportapp.passengers.Passenger;

public class Debt implements AccountState
{
    private int numTimesDededucted;

    public Debt()
    {
        numTimesDededucted = 0;
    }

    @Override
    public void tapCard(Passenger p)
    {
        UserInput ui = new UserInput();
        System.out.println("Enter an amount of money to move " + p.getId() + "\'s standing out of debt");
        System.out.println(p.getId() + "\'s current balance is: " + p.getBalance());
        System.out.println(p.getId() + " has " + (3 - numTimesDededucted) + " travels left before cancellation");

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
        else if(numTimesDededucted == 3)
        {
            p.setAccState(new Cancelled());
        }
    }
}
