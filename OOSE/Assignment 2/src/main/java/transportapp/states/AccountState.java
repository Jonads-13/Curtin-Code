package transportapp.states;

import transportapp.passengers.Passenger;

public interface AccountState 
{
    public void tapCard(Passenger p);
    public void deductPayment(Passenger p, int fee);
}
