package transportapp.exceptions;

public class CancelledAccountException extends RuntimeException
{
    public CancelledAccountException(String msg)
    {
        super(msg);
    }    
}
