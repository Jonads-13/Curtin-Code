package transportapp.vehicles;

import java.util.Map;
import java.util.HashMap;

import transportapp.passengers.Passenger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Assertion Class to represent all vehicles in the transport app
 **/

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

      
      
      
      
      
      /**
       * @author    Jacob Jonas, 18439731
       *
       * @assertion Accessor
       *
       * @param     void
       *
       * @returns   id
       **/

      public int getId()
      {
            return id;
      } // End getId()

      
      
      
      
      
      /**
       * @author    Jacob Jonas, 18439731
       *
       * @assertion Accessor
       *
       * @param     void
       *
       * @returns   type
       **/

      public String getType() 
      {
            return type;      
      } // End getType()

      
      
      
      
      
      /**
       * @author    Jacob Jonas, 18439731
       *
       * @assertion Accessor
       *
       * @param     void
       *
       * @returns   fee
       **/

      public int getFee()
      {
            return fee;
      } // End getFee()

      
      
      
      
      
      /**
       * @author    Jacob Jonas, 18439731
       *
       * @assertion Add a passenger to the passenger list
       *
       * @param     p Passenger to add
       *
       * @returns   void
       **/

      public void addPassenger(Passenger p)
      {
            passengers.put(p.getId(), p);
      } // End addPassenger()

      
      
      
      
      
      /**
       * @author    Jacob Jonas, 18439731
       *
       * @assertion Remove a passenger from the passegner list
       *
       * @param     id id of the passenger to remove
       *
       * @returns   void
       **/

      public void removePassenger(int id)
      {
            passengers.remove(id);
      } // End removePassenger()

      
      
      
      
      
      /**
       * @author    Jacob Jonas, 18439731
       *
       * @assertion Get how many passengers are on the Vehicle
       *
       * @param     void
       *
       * @returns   Amount of passenegers currently onboard the Vehicle
       **/

      public int getNumPassengers()
      {
            return passengers.size();
      } // End getNumPassengers()
}
