package transportapp.states;

import transportapp.passengers.Passenger;

public class GoodStanding implements AccountState 
{
    public GoodStanding() {}

    public void tapCard(Passenger p)
    {
        // Empty
    }

    public void deductPayment(Passenger p, int fee)
    {
        p.setBalance(p.getBalance() - fee);

        if(p.getBalance() < 0)
        {
            p.setAccState(new Debt());
        }
    }
}
