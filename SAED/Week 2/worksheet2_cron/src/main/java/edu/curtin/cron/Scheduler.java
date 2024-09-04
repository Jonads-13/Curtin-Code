package edu.curtin.cron;
import java.util.*;

/**
 * The scheduler keeps track of all the jobs, and runs each one at the appropriate time. (You need
 * to fill in the details!)
 */
public class Scheduler
{
    private ArrayList<Job> jobs;
    private Thread thread;
    private Object mutex;

    public void addJob(Job newJob)
    {
        // Lock when adding to list
        synchronized(mutex)
        {
            jobs.add(newJob);
        }
    }
    
    public void start()
    {
        Runnable task = () -> {
            try
            {
                int counter = 0;
                while(true)
                {
                    // Lock while looping over jobs list
                    synchronized(mutex)
                    {
                        for (Job job : jobs) 
                        {
                            if(job.getDelay() % counter == 0)
                            {
                                Thread jobThread = new Thread(job, "job-thread");
                                jobThread.start();
                            }
                        }
                    }
                    Thread.sleep(1000L);
                    counter++;
                }
            }
            catch(InterruptedException ie)
            {
                
            }
        };
        thread = new Thread(task, "scheduler-thread");
        thread.start();
    }

    public void stop()
    {
        if(thread == null) {
            throw new IllegalStateException();
        }
        thread.interrupt();
        thread = null;
    }
}
