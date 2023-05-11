#include <stdio.h>
#include "setup.h"
#include "macros.h"

int main(int argc, char** argv)
{
    if(argc != 6) /* Ensure there are enough command line arguments */
    {
        /* Display usage information */
        usage();
    }
    else
    {
        /* Ensures r_log is blank at the start of each separate 
         * program exectution 
         */
        FILE *logFile = fopen(LOG_FILE, "w");
        fclose(logFile);

        extractArgv(argv);
    }

    return 0;
}
