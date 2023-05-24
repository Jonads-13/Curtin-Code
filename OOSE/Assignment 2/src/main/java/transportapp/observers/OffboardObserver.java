package transportapp.observers;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Used to observe events that happen when a passegner is not travelling
 **/

import java.time.LocalTime;
import java.util.logging.Logger;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class OffboardObserver implements PassengerObserver
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(OffboardObserver.class.getName());

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion prints boarding information to the terminal and adds the passenger to the respective vehicle
     *
     * @param     p Passenger doing the boarding
     * @param     v Vehicle being boarded
     *
     * @returns   void
     **/

    @Override
    public void update(Passenger p, Vehicle v)
    {
        LocalTime localTime = LocalTime.now();
        String time = String.format("%d:%d:%d", localTime.getHour(),localTime.getMinute(), localTime.getSecond());
        System.out.println("Passenger " + p.getId() + " has boarded " + v.getType() + " " + v.getId() + " at: " + time);
        v.addPassenger(p);

        logger.info(()-> "Passenger " + p.getId() + " has boarded " + v.getType() + " " + v.getId() + " at: " + time);
    } // End update()
}
