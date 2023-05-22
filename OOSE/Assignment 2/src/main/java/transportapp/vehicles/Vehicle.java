package transportapp.vehicles;

import java.util.*;

import transportapp.passengers.Passenger;

public class Vehicle 
{
      protected int id;
      protected String type;
      protected int fee;
      protected Map<Integer, Passenger> passengers;

      public Vehicle(int id, String type, int fee)
      {
            this.id = id;
            this.type = type;
            this.fee = fee;
            this.passengers = new HashMap<>();
      }

      public int getId()
      {
            return id;
      }

      public String getType() 
      {
            return type;      
      }

      public int getFee()
      {
            return fee;
      }

      public void addPassenger(Passenger p)
      {
            passengers.put(p.getId(), p);
      }

      public void removePassenger(int id)
      {
            passengers.remove(id);
      }

      public int getNumPassengers()
      {
            return passengers.size();
      }


}
