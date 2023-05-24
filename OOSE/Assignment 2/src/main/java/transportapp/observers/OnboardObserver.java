package transportapp.observers;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Used to observe events that happen while a passenger is travelleing
 **/

import java.time.LocalTime;
import java.util.logging.Logger;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class OnboardObserver implements PassengerObserver
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(OnboardObserver.class.getName());

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion prints disembarking information to the terminal and removes the passenger from the respective vehicle
     *
     * @param     p Passenger doing the disembarking
     * @param     v Vehicle being disembarked
     *
     * @returns   void
     **/

    @Override
    public void update(Passenger p, Vehicle v)
    {
        LocalTime localTime = LocalTime.now();
        String time = String.format("%d:%d:%d", localTime.getHour(),localTime.getMinute(), localTime.getSecond());
        System.out.println("Passenger " + p.getId() + " has disembarked " + v.getType() + " " + v.getId() + " at: " + time);
        v.removePassenger(p.getId());

        logger.info(()-> "Passenger " + p.getId() + " has disembarked " + v.getType() + " " + v.getId() + " at: " + time);
    } // End update()
}
