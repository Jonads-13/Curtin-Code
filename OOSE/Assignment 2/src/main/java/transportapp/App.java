package transportapp;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import transportapp.vehicles.*;
import transportapp.exceptions.CancelledAccountException;
import transportapp.exceptions.MissingVehicleException;
import transportapp.fileio.ParseSetupFiles;
import transportapp.interactive.UserInput;
import transportapp.passengers.Passenger;

public class App
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public App() {}

    public static void main(String[] args)
    {
        ParseSetupFiles fileParser = new ParseSetupFiles();
        App app = new App();
        UserInput ui = new UserInput();

        try
        {
            String vehicleFile = ui.getFilename("vehicle");
    
            Map<Integer, Vehicle> vehicles = fileParser.readVehicleFile(vehicleFile);   
    
            String passengerFile = ui.getFilename("passenger");
            
            List<Passenger> passengers = fileParser.readPassengerFile(passengerFile);

            app.beginSimulation(vehicles, passengers);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            logger.warning(()-> "Error in file reading: " + e.getMessage());
        }

        ui.closeScanner();
    }

    public void beginSimulation(Map<Integer, Vehicle> vehicles, List<Passenger> passengers)
    {
        for (Passenger p : passengers) 
        {
            try
            {
                simulateJourney(p, vehicles);
            }
            catch(MissingVehicleException e)
            {
                System.out.println(e.getMessage());
                logger.warning(()-> e.getMessage());
            }
            catch(CancelledAccountException e2)
            {
                System.out.println("Passenger: " + p.getId() + "\'s journey ended early as their account was cancelled");
                logger.warning(()-> "Passenger: " + p.getId() + "\'s journey ended early as their account was cancelled");
            }
        }

        System.out.println("Simulation finished");
        logger.info("Simulation finished");
    }
    
    public void simulateJourney(Passenger p, Map<Integer, Vehicle> vehicles)
    {
        logger.info(()-> "Beginning simulation of Passenger: " + p.getId());
        System.out.println("\n\nBeginning simulation of Passenger: " + p.getId());
        List<Integer> itinerary = p.getItinerary();

        for(int legOfJourney : itinerary) 
        {
            Vehicle v = vehicles.get(legOfJourney);

            if(v == null)
            {
                throw new MissingVehicleException(legOfJourney + " is not valid as it could not be found. Please ensure it is in the vehicle file");
            }

            p.tapOn(v); // Boarding
            // Pretend there is travel time here
            p.tapOff(v); // Disembarking
        }

        logger.info(()-> "Passenger " + p.getId() + "\'s journey has successfully completed");
        System.out.println("Passenger " + p.getId() + "\'s journey has successfully completed\n\n");
    }
}
