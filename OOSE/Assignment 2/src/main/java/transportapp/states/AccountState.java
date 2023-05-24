package transportapp.states;

import transportapp.passengers.Passenger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Interface that represents a passengers account state
 **/

public interface AccountState 
{
    public void tapCard(Passenger p);
    public void deductPayment(Passenger p, int fee);
}
