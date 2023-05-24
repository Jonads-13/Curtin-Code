Jacob Jonas, 18439731 README for Assignment 2, OOSE
======================================================

This is a simple simulation program designed to simulate the journeys of passengers and how their accounts might change during that journey

This program utilises two types of files in the setup of the program: A vehilces file and a passengers file
One of each of these have been provided for ease of use called vehilces.txt and passengers.txt, however you are also able to make your own

=========================================================================================================
The vehilces file format is as follows:

<type>;<id>;<fee>

type:       The type of vehicle. Only "Bus", "Train", and "Ferry" (without the quotes) are supported right now

id:         A unique indentifier for the vehicle in question. Represented as an integer

fee:        The amount it will cost a passenger to travel on this vehicle. Represented as an integer 

';' (semi-colon) characters are used to separate each of the 3 fields

=========================================================================================================
The passengers file format is as follows:

<id>;<balance>;<itinerary>

id:         A unique indentifier for the passenger in question. Represented as an integer

balance:    The starting amount of money the passenger has in their account. Represented as an integer

itinerary:  A sequence of vehicles id's separated by '-' characters e.g. "100-2-605-32" (minus the quotes). There must be at least 2 vehicle id's


Remember that any vehicle id's you speicify in the itinerary need to have a corresponding entry in the vehicles file

';' (semi-colon) characters are used to separate each of the 3 fields

=========================================================================================================

To run the program, type ./graldew run from the command line in the root directory of the project: "Assignment 2"

You will first be prompted for the name of the vehicles file: vehicles.txt is the name of the provided file 
You will then be prompted for the name of the passengers file: passengers.txt is the name of the provided file 

The program will then simulate each of the passengers itineraries using the vehicles from the speicified files

Depending on how the itineraries play out, you may be prompted to enter an amount to increase a passengers balance in order to elevate them out of debt.
While it would be easy to enter a big number to ensure they stay out of debt, try to see if you can get their account cancelled 
Note: 0 is a valid integer...

After the final passengers journey has finished being simulated the program will end

Thank you for using this application

