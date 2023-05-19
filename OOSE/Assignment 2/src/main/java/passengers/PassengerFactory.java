package passengers;

public class PassengerFactory
{
    public Passenger createPassenger(String gender, int id)
    {
        if(gender.equals("M"))
        {
            return new Male();
        }
        else if(gender.equals("F"))
        {
            return new Female();
        }
        else
        {
            return new NonBinary();
        }
    }    
}
