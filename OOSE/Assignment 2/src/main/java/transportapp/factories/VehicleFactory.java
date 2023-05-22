package transportapp.factories;

import java.util.logging.Logger;

import transportapp.vehicles.*;
import transportapp.exceptions.InvalidVehicleException;


public class VehicleFactory 
{
    @SuppressWarnings("PMDNamingConventions")
    private static final Logger logger = Logger.getLogger(VehicleFactory.class.getName());

    public Vehicle createVehicle(String name, int id)
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
        else
        {
            logger.severe(()-> "name: " + name + ", must be either \"Bus\" or \"Train\"");
            throw new InvalidVehicleException( "name: " + name + ", must be either \"Bus\" or \"Train\"");
        }
    } 
}
