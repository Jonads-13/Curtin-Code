package transportapp.fileio;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import transportapp.exceptions.InvalidPassengerException;
import transportapp.exceptions.InvalidVehicleException;
import transportapp.factories.TransportAppFactory;
import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class ParseSetupFiles 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(ParseSetupFiles.class.getName()); 

    public ParseSetupFiles() {} // Constructor

    public Map<Integer, Vehicle> readVehicleFile(String filename) throws IOException
    {
        Map<Integer, Vehicle> vehicles = new HashMap<>();
        String line;
        int lineNum = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            line = br.readLine();
            lineNum++;

            while(line != null)
            {
                Vehicle v = parseVehicle(line);
                vehicles.put(v.getId(), v);

                line = br.readLine();
                lineNum++;
            }
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("Invalid integer on line " + lineNum + " of " + filename + " " + nfe.getMessage());
            logger.warning(()-> "File contained a non Integer type where they should have been an integer: " + nfe.getMessage());
            throw nfe;
        }
        catch(InvalidVehicleException ive)
        {
            System.out.println("Invalid Vehicle on line " + lineNum + " of " + filename);
            logger.warning(()-> "Invalid Vehicle in " + filename + ": " + ive.getMessage());
            throw ive;
        }

        return vehicles;
    }

    public Vehicle parseVehicle(String line)
    {
        String[] split = line.split(";", 3);
                
        if(split.length != 3)
        {
            throw new InvalidVehicleException("Not enough fields");
        }

        int id = Integer.parseInt(split[1]);
        int fee = Integer.parseInt(split[2]);

        return TransportAppFactory.createVehicle(split[0], id, fee);


    }

    public List<Passenger> readPassengerFile(String filename) throws IOException
    {
        List<Passenger> passengers = new LinkedList<>();
        String line;
        int lineNum = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            line = br.readLine();
            lineNum++;
            
            while(line != null)
            {
                Passenger p = parsePassenger(line);
                passengers.add(p);

                line = br.readLine();
                lineNum++;
            }
        }
        catch(InvalidPassengerException ipe)
        {
            System.out.println("Invalid Passenger on line " + lineNum + " of " + filename);
            logger.warning(()-> "Invalid Passenger in " + filename + ": " + ipe.getMessage());
            throw ipe;
        }

        return passengers;
    }

    public Passenger parsePassenger(String line)
    {
        String[] split = line.split(";",3);

        if(split.length != 3)
        {
            throw new InvalidPassengerException("Not enough fields to create a passenger");
        }

        int id = Integer.parseInt(split[0]);
        int balance = Integer.parseInt(split[1]);
        String itinerary = split[2];
        
        Passenger p = TransportAppFactory.createPassenger(id, balance, itinerary);

        return p;
    }
}
