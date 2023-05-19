package vehicles;

import java.util.logging.Logger;

public class VehicleFactory 
{
    private static final Logger logger = Logger.getLogger(VehicleFactory.class.getName());

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
}
