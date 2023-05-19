package passengers;

public abstract class Passenger 
{
    protected String name;
    protected PassengerState state;
    protected int balance; 

    public void tapCard()
    {
        state.tapCard(this);
    }
}
