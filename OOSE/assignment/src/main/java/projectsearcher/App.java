package projectsearcher;

import java.io.File;
import java.util.logging.Logger;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   30/03/2023
 * @Modified  11/04/2023
 * @Assert    Conatains the main funtion of the application
 **/

public class App
{
    // Warning suppressed under advice from David given in the form of a piazza reply.
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args)
    {
        // Creating new objects to call methods on
        Initiialise init = new Initiialise();
        Interactive interact = new Interactive();

        if(args.length < 1)
        {
            logger.info(() -> "Using the current directory");
            Entry root = init.directorySearch(".");
            logger.info(() -> "Using directory: " + root.getName());
            interact.mainMenu(root);
        }
        else
        {
            logger.info(() -> "Using a user specified directory");
            if(new File(args[0]).isDirectory()) // Check user entered a valid directory
            {
                Entry root = init.directorySearch(args[0]);
                logger.info(() -> "Using directory: " + root.getName());
                interact.mainMenu(root);
            }
            else
            {
                System.out.println(args[0] + " is not a valid directory. Please specify a valid directory");
                logger.warning(() -> "User did not enter a valid directory:" + args[0]);
            }
        }

        interact.closeScanner();
    } // End main()   
}