package transportapp.factories;

import java.util.logging.Logger;

import transportapp.vehicles.*;
import transportapp.exceptions.InvalidPassengerException;
import transportapp.exceptions.InvalidVehicleException;
import transportapp.observers.OffboardObserver;
import transportapp.observers.OnboardObserver;
import transportapp.passengers.Passenger;
import transportapp.states.GoodStanding;
import transportapp.states.Offboard;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Factory to create the necessary objects for the transport app
 **/


public class TransportAppFactory 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(TransportAppFactory.class.getName());

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Creates a vehicle based on imported parameters
     *
     * @param     type type of vehicle eg Bus, Train, Ferry
     * @param     id Unique identifier for the vehicle
     * @param     fee How much it will cost to travel on the vehicle
     *
     * @returns   The created Vehicle
     **/

    public static Vehicle createVehicle(String type, int id, int fee)
    {
        if(type.equals("Bus"))
        {
            logger.info(()-> "Bus created with id: " + id + " and a fee of: " + fee);
            return new Bus(id, type, fee);
        }
        else if(type.equals("Train"))
        {
            logger.info(()-> "Train created with id: " + id + " and a fee of: " + fee);
            return new Train(id, type, fee);
        }
        else if(type.equals("Ferry"))
        {
            logger.info(()-> "Ferry created with id: " + id + " and a fee of: " + fee);
            return new Ferry(id, type, fee);
        }
        else
        {
            logger.severe(()-> "name: " + type + " is invalid. Must be either \"Bus\" or \"Train\" or \"Ferry\"");
            throw new InvalidVehicleException( "name: " + type + ", must be either \"Bus\" or \"Train\" or \"Ferry\"");
        }
    } // End createVehicle()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Creates a Passenger with the imported parameters
     *
     * @param     id Unique identifier for the passenger
     * @param     balance Starting balance of the passenger
     * @param     itinerary Represents the vehicles the passenger will travel on
     *
     * @returns   The created Passenger
     **/

    public static Passenger createPassenger(int id, int balance, String itinerary)
    {
        Passenger p = new Passenger(id, balance, new GoodStanding(), new Offboard());

        // Splits the itinerary into each vehicle
        String[] splitItinerary = itinerary.split("-");

        if(splitItinerary.length == 1) // Not the correct separator used
        {
            throw new InvalidPassengerException("Itinerary must be separated by \'-\' and must have at least 2 vehicle id\'s");
        }

        for(String legOfJourney : splitItinerary) // Add each vehicle to the itinerary list
        {
            int leg = Integer.parseInt(legOfJourney);
            p.addToItinerary(leg);
        }

        // Add observers
        p.addOffObserver(new OffboardObserver());
        p.addOnObserver(new OnboardObserver());

        logger.info(()-> "Passenger " + id + " created with starting balance of: " + balance);

        return p;
    } // End createPassenger
}
