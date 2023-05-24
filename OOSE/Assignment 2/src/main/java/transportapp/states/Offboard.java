package transportapp.states;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Implementation of the state of a passenger being offboard/not travelling
 **/

public class Offboard implements BoardedState
{
    public Offboard() {} // Constructor

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion calls tapCardt() of the accState and then changes to the OnBoard state
     *
     * @param     p Passenger that tapped their card
     * @param     v Vehicle that the passenger tapped their card on
     *
     * @returns   void
     **/
    
    
    @Override
    public void tapCard(Passenger p, Vehicle v)
    {
        p.getAccState().tapCard(p);
        p.setBrdState(new Onboard());
    } // End tapCard()
}
