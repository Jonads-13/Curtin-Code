package transportapp.observers;

import java.time.LocalTime;
import java.util.logging.Logger;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class OffboardObserver implements PassengerObserver
{
    @SuppressWarnings("PMD.FieldnamingConventions")
    private static final Logger logger = Logger.getLogger(OffboardObserver.class.getName());

    @Override
    public void update(Passenger p, Vehicle v)
    {
        LocalTime localTime = LocalTime.now();
        String time = String.format("%d:%d:%d", localTime.getHour(),localTime.getMinute(), localTime.getSecond());
        System.out.println("Passenger " + p.getId() + " has boarded " + v.getType() + " " + v.getId() + " at: " + time);
        v.addPassenger(p);

        logger.info(()-> "Passenger " + p.getId() + " has boarded " + v.getType() + " " + v.getId() + " at: " + time);
    }
}
