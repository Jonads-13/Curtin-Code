package transportapp.states;

import transportapp.passengers.Passenger;

public class Debt implements AccountState
{
    private int numTimesDededucted;

    public Debt()
    {
        numTimesDededucted = 0;
    }

    public void tapCard(Passenger p)
    {
        // Empty
    }

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
