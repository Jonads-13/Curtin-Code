package transportapp.exceptions;

public class MissingVehicleException extends RuntimeException
{
    public MissingVehicleException(String msg)
    {
        super(msg);
    }    
}
