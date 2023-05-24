package transportapp;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;

import transportapp.vehicles.*;
import transportapp.exceptions.*;
import transportapp.fileio.ParseSetupFiles;
import transportapp.interactive.UserInput;
import transportapp.passengers.Passenger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Where the app begins and ends execution. Contain main simulation methods
 **/

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
            // Get filename from the user to use to create the vehicles
            String vehicleFile = ui.getFilename("vehicle");
    
            // Create Map from the provided file
            Map<Integer, Vehicle> vehicles = fileParser.readVehicleFile(vehicleFile);   
    
            // Get filename from the user to use to create the passnegers
            String passengerFile = ui.getFilename("passenger");
            
            // Create List from the provided file
            List<Passenger> passengers = fileParser.readPassengerFile(passengerFile);

            // Start simulating passenger journeys
            app.beginSimulation(vehicles, passengers);
        }
        catch(IOException e) // Possible file error ie doesn't exist or missing permissions
        {
            System.out.println(e.getMessage());
            logger.warning(()-> "Error in file reading: " + e.getMessage());
        }
        catch(InvalidVehicleException ive) // Something went wrong with the vehicle file
        {
            System.out.println("Vehicle file is incorrect. Please check and ensure the format is correct. Specific details in the README");
        }
        catch(InvalidPassengerException ipe) // Something went wrong with th passenger file
        {
            System.out.println("Passenger file is incorrect. Please check and ensure the format is correct. Specific details in the README");
        }
        catch(NumberFormatException nfe) // An integer was needed but not provided
        {
            System.out.println("Please check and ensure the file format is correct. Specific details in the README");
        }

        ui.closeScanner(); // Closes the scanner used in the UserInput class
    } // End main()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion simulates each passengers journey sequentially
     *
     * @param     vehicles Map of vehicles that the passenger may travel on
     * @param     Passenger List of all passengers whose journey's we will simulate
     *
     * @returns   void
     **/

    public void beginSimulation(Map<Integer, Vehicle> vehicles, List<Passenger> passengers)
    {
        for (Passenger p : passengers) // Iterate through each passenger
        {
            try
            {
                simulateJourney(p, vehicles); // Simulate their journey
            }
            catch(MissingVehicleException mve) // Vehicle specified in passenger itinerary wasn't in vehicles 
            {
                System.out.println(mve.getMessage());
                logger.warning(()-> mve.getMessage());
            }
            catch(CancelledAccountException cae) // Passenger had their accoutn cancelled
            {
                System.out.println("Passenger: " + p.getId() + "\'s journey ended early as their account was cancelled");
                logger.warning(()-> "Passenger: " + p.getId() + "\'s journey ended early as their account was cancelled");
            }
        }

        System.out.println("Simulation finished");
        logger.info("Simulation finished");
    } // End beginSimulation()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Simulate a passengers journey
     *
     * @param     p Passeneger whose journey we will simulate
     * @param     vehicles Map of vehicles the passenger may travel on
     *
     * @returns   void
     **/
    
    public void simulateJourney(Passenger p, Map<Integer, Vehicle> vehicles)
    {
        logger.info(()-> "Beginning simulation of Passenger: " + p.getId());
        System.out.println("\n\nBeginning simulation of Passenger: " + p.getId());

        List<Integer> itinerary = p.getItinerary();

        for(int legOfJourney : itinerary) // Iterate through each vehicle the passenger will travel on
        {
            Vehicle v = vehicles.get(legOfJourney);

            if(v == null) // Vehicle hasn't been defined
            {
                throw new MissingVehicleException(legOfJourney + " is not valid as it could not be found. Please ensure it is in the vehicle file");
            }

            p.tapOn(v); // Boarding
            // Pretend there is travel time here
            p.tapOff(v); // Disembarking
        }

        logger.info(()-> "Passenger " + p.getId() + "\'s journey has successfully completed");
        System.out.println("Passenger " + p.getId() + "\'s journey has successfully completed\n\n");
    } // End simulateJourney()
}
