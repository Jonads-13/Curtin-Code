package transportapp.fileio;

import java.io.*;
import java.util.*;

import transportapp.factories.TransportAppFactory;
import transportapp.passengers.Passenger;
import transportapp.vehicles.Vehicle;

public class ParseSetupFiles 
{
    public ParseSetupFiles() {} // Constructor

    public Map<Integer, Vehicle> readVehicleFile(String filename) throws IOException
    {
        Map<Integer, Vehicle> vehicles = new HashMap<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            line = br.readLine();

            while(line != null)
            {
                String[] split = line.split(";", 2);
                int id = Integer.parseInt(split[1]);

                Vehicle v = TransportAppFactory.createVehicle(split[0], id);
                vehicles.put(id, v);

                line = br.readLine();
            }
        }

        return vehicles;
    }

    public List<Passenger> readPassengerFile(String filename)
    {
        List<Passenger> passengers = new LinkedList<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            line = br.readLine();

            while(line != null)
            {
                Passenger p = parsePassenger(line);

                passengers.add(p);

                line = br.readLine();
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

        return passengers;
    }

    public Passenger parsePassenger(String line)
    {
        String[] split = line.split(";",3);

        int id = Integer.parseInt(split[0]);
        int balance = Integer.parseInt(split[1]);
        String itinerary = split[2];
        
        Passenger p = TransportAppFactory.createPassenger(id, balance, itinerary);

        return p;
    }
}