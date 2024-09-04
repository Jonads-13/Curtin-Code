package edu.curtin.cron;
import java.io.*;

/**
 * The logger is in charge of writing output to 'cron.log'. It does this in its own thread, but 
 * assumes that other threads will call the setMessage() in order to provide messages to log. (You 
 * need to fill in the details!)
 */
public class Logger
{
    private String message;
    private Thread thread;
    private Object monitor;
    
    public void setMessage(String newMessage) throws InterruptedException
    {   
        // TODO: ...
    }
    
    public void start()
    {
        // TODO: ...
    }
    
    public void stop()
    {
        // TODO: ...
    }
}
