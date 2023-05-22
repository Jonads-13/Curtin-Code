package transportapp;

import java.io.IOException;
import java.util.*;

import transportapp.vehicles.*;
import transportapp.fileio.ParseSetupFiles;
import transportapp.passengers.Passenger;

public class App
{
    public App() {}
    public static void main(String[] args)
    {
        ParseSetupFiles fileParser = new ParseSetupFiles();
        App app = new App();

        try(Scanner sc = new Scanner(System.in))
        {
            String vehicleFile = app.getFilename("vehicle", sc);
    
            Map<Integer, Vehicle> vehicles = fileParser.readVehicleFile(vehicleFile);   
    
            String passengerFile = app.getFilename("passenger", sc);
            
            List<Passenger> passengers = fileParser.readPassengerFile(passengerFile);

            app.beginSimulation(vehicles, passengers);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getFilename(String type, Scanner sc)
    {
        System.out.println("Please enter the name of the " + type + "file to use: ");
        System.out.println("Note: default is: " + type + "s.txt");
        return sc.nextLine();
    }

    public void beginSimulation(Map<Integer, Vehicle> vehicles, List<Passenger> passengers)
    {
        for (Passenger p : passengers) 
        {
            simulateJourney(p, vehicles);
        }
    }

    public void simulateJourney(Passenger p, Map<Integer, Vehicle> vehicles)
    {
        List<Integer> itinerary = p.getItinerary();

        for (int legOfJourney : itinerary) 
        {
            Vehicle v = vehicles.get(legOfJourney);

            


        }
    }
}
