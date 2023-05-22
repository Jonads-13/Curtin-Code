package transportapp.factories;

import java.util.logging.Logger;

import transportapp.vehicles.*;
import transportapp.exceptions.InvalidVehicleException;
import transportapp.passengers.Passenger;
import transportapp.states.GoodStanding;
import transportapp.states.Offboard;


public class TransportAppFactory 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(TransportAppFactory.class.getName());

    public static Vehicle createVehicle(String name, int id)
    {
        if(name.equals("Bus"))
        {
            logger.info(()-> "Bus created with " + id + " id");
            return new Bus(id, name, 1);
        }
        else if(name.equals("Train"))
        {
            logger.info(()-> "Train created with " + id + " id");
            return new Train(id, name, 2);
        }
        else if(name.equals("Ferry"))
        {
            logger.info(()-> "Ferry created with " + id + " id");
            return new Ferry(id, name, 5);
        }
        else
        {
            logger.severe(()-> "name: " + name + ", must be either \"Bus\" or \"Train\" or \"Ferry\"");
            throw new InvalidVehicleException( "name: " + name + ", must be either \"Bus\" or \"Train\" or \"Ferry\"");
        }
    } 

    public static Passenger createPassenger(int id, int balance)
    {
        return new Passenger(id, balance, new GoodStanding(), new Offboard());
    }
}
