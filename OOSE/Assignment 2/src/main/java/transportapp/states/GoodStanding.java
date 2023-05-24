package transportapp.states;

import java.util.logging.Logger;

import transportapp.passengers.Passenger;

public class GoodStanding implements AccountState 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(GoodStanding.class.getName());

    public GoodStanding() {}

    @Override
    public void tapCard(Passenger p)
    {
        // Empty
    }

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
    }
}
