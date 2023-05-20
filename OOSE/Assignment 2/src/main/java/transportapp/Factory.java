package transportapp;

import java.util.logging.Logger;

import transportapp.vehicles.*;
import transportapp.passengers.*;


public class Factory 
{
    @SuppressWarnings("PMDNamingConventions")
    private static final Logger logger = Logger.getLogger(Factory.class.getName());

    public Vehicle createVehicle(String name, int id)
    {
        if(name.equals("Bus"))
        {
            return new Bus(id);
        }
        else if(name.equals("Train"))
        {
            return new Train(id);
        }
        else
        {
            logger.severe("name must be either \"Bus\" or \"Train\"");
            throw new IllegalArgumentException("name must be either \"Bus\" or \"Train\"");
        }
    }

    public Passenger createPassenger(String gender, int id)
    {
        if(gender.equals("M"))
        {
            return new Male();
        }
        else if(gender.equals("F"))
        {
            return new Female();
        }
        else
        {
            return new Other();
        }
    } 
}
