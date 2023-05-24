package transportapp.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.Logger;

import transportapp.exceptions.InvalidPassengerException;
import transportapp.exceptions.InvalidVehicleException;
import transportapp.factories.TransportAppFactory;
import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Handles all fileio aspects of the transport app
 **/

public class ParseSetupFiles 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(ParseSetupFiles.class.getName()); 

    public ParseSetupFiles() {} // Constructor

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Reads the specifed file to create a Map of vehicles
     *
     * @param     filename Name of the file to read from
     *
     * @returns   Map of Integer, Vehicle pairs where the Integer is the Vehicle id
     * 
     * @throws    IOException
     **/

    public Map<Integer, Vehicle> readVehicleFile(String filename) throws IOException
    {
        Map<Integer, Vehicle> vehicles = new HashMap<>(); // Store all the Vehicles
        String line;
        int lineNum = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            line = br.readLine();
            lineNum++;

            while(line != null)
            {
                Vehicle v = parseVehicle(line); // Create a Vehicle from each line
                vehicles.put(v.getId(), v);

                line = br.readLine();
                lineNum++;
            }
        }
        catch(InvalidVehicleException ive)
        {
            System.out.println("Invalid Vehicle on line " + lineNum + " of " + filename);
            logger.severe(()-> "Invalid Vehicle in " + filename + ": " + ive.getMessage());
            throw ive;
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("Invalid integer on line " + lineNum + " of " + filename + " " + nfe.getMessage());
            logger.severe(()-> "File contained a non Integer type where there should have been an integer: " + nfe.getMessage());
            throw nfe;
        }

        return vehicles;
    } // End readVehicleFile()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Extract the necessary information from the line of the file
     *
     * @param     line line from the file being read
     *
     * @returns   Vehicle created using information that is extracted
     **/

    public Vehicle parseVehicle(String line)
    {
        // Split the line about the separators
        String[] split = line.split(";");
                
        if(split.length < 3)
        {
            logger.severe(()-> "Not enough fields to create a vehicle: " + line);
            throw new InvalidVehicleException("Not enough fields to create a vehicle: " + line);
        }
        else if(split.length > 3)
        {
            logger.severe(()-> "Too many fields to create a vehicle: " + line);
            throw new InvalidVehicleException("Too many fields to create a vehicle: " + line);
        }

        // Possible exceptions are caught by the caller
        int id = Integer.parseInt(split[1]);
        int fee = Integer.parseInt(split[2]);

        // Create and return the Vehilce object
        return TransportAppFactory.createVehicle(split[0], id, fee);
    } // End ParseVehicle()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Reads the specfied file to create a list of passengers
     *
     * @param     filename Name of the file to read
     *
     * @returns   List of Passengers created from the file
     **/

    public List<Passenger> readPassengerFile(String filename) throws IOException
    {
        List<Passenger> passengers = new LinkedList<>(); // Store all the Passengers
        String line;
        int lineNum = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            line = br.readLine();
            lineNum++;
            
            while(line != null)
            {
                Passenger p = parsePassenger(line); // Create a Passenger from each line
                passengers.add(p);

                line = br.readLine();
                lineNum++;
            }
        }
        catch(InvalidPassengerException ipe)
        {
            System.out.println("Invalid Passenger on line " + lineNum + " of " + filename);
            logger.severe(()-> "Invalid Passenger in " + filename + ": " + ipe.getMessage());
            throw ipe;
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("Invalid Integer on line " + lineNum + " of " + filename + " " + nfe.getMessage());
            logger.severe(()-> "File contained a non Integer type where there should have been an integer: " + nfe.getMessage());
            throw nfe;
        }

        return passengers;
    } // End readPassengerFile()

    
    
    
    
    
    /**
     * @author    Jacob Jonas, 18439731
     *
     * @assertion Extract the necessary onformation from the line of the file
     *
     * @param     line line read from the file
     *
     * @returns   Created Passenger using the extraced information
     **/

    public Passenger parsePassenger(String line)
    {
        // Split the line about the separators
        String[] split = line.split(";");

        if(split.length < 3)
        {
            logger.severe(()-> "Not enough fields to create a passenger: " + line);
            throw new InvalidPassengerException("Not enough fields to create a passenger: " + line);
        }
        else if(split.length > 3)
        {
            logger.severe(()-> "Too many fields to create a passenger: " + line);
            throw new InvalidPassengerException("Too many fields to create a passenger: " + line);
        }

        // Possible exceptions are caught by the caller
        int id = Integer.parseInt(split[0]);
        int balance = Integer.parseInt(split[1]);
        String itinerary = split[2];
        
        return TransportAppFactory.createPassenger(id, balance, itinerary);
    } // End parsePassenger
}
