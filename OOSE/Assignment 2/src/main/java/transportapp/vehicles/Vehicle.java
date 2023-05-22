package transportapp.vehicles;

public abstract class Vehicle 
{
      protected int id;
      protected String type;
      protected int fee;

      public Vehicle(int id, String type, int fee)
      {
            this.id = id;
            this.type = type;
            this.fee = fee;
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
}
