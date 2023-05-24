package transportapp.observers;

import java.time.LocalTime;
import java.util.logging.Logger;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class OnboardObserver implements PassengerObserver
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(OnboardObserver.class.getName());

    @Override
    public void update(Passenger p, Vehicle v)
    {
        LocalTime localTime = LocalTime.now();
        String time = String.format("%d:%d:%d", localTime.getHour(),localTime.getMinute(), localTime.getSecond());
        System.out.println("Passenger " + p.getId() + " has disembarked " + v.getType() + " " + v.getId() + " at: " + time);
        v.removePassenger(p.getId());

        logger.info(()-> "Passenger " + p.getId() + " has disembarked " + v.getType() + " " + v.getId() + " at: " + time);

    }
}
