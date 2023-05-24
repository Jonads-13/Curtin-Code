package transportapp.states;

import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Implementaion of a the state of a passenger onboard/travelling
 **/

public class Onboard implements BoardedState
{
    public Onboard() {} // Constructor

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion calls deductpayment() and then changes to the OffBoard state
     *
     * @param     p Passenger that tapped their card
     * @param     v Vehicle that the passenger tapped their card on
     *
     * @returns   void
     **/
    
    @Override
    public void tapCard(Passenger p, Vehicle v)
    {
        p.deductPayment(v.getFee()); 
        p.setBrdState(new Offboard());
    } // End tapCard()
}
