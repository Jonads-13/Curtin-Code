package revisionsessions.ise;

import java.util.Scanner;


public class BadCode
{
   public static int count;
   public static boolean isNegative;
   public static Scanner sc;
   public static int convNum;


    public static void main( String[] args )
    {
        sc = new Scanner( System.in );
        count = 0;
        System.out.println( "How many conversions would you like?");
        convNum = sc.nextInt();
        while( convNum < 0 )
        {
            sc.nextLine();
            System.out.println( "Please only enter a positive number");
            convNum = sc.nextInt();
        }


        while( convNum > 0 )
        {
            System.out.println( "inches(1) or cm(2)?");
            int choice = sc.nextInt();
            while( choice < 1 || choice > 2 )
            {
                sc.nextLine();
                System.out.println( "Please only enter a between 1 and 2");
                choice = sc.nextInt();
            }
            boolean isInches;
            if( choice == 1 )
            {
                isInches = true;
            }
            else
            {
                isInches = false;
            }
            
            double input = getUserDouble(isInches);
            boolean isNegative;

            if( input < 0 )
            {
                isNegative = true;
            }
            else
            {
                isNegative = false;
            }

            if(isNegative)
            {
                System.out.println("Please only enter a positive number");
            }
            else
            {
                if( isInches )
                {
                    System.out.println( "Conversion is: " +  inchesToMeters(input) );  
                }
                else
                {
                    System.out.println( "Conversion is: " +  cmTometersOrInches(input, isInches) );  
                }
                
                addToCount();
                --convNum;   
            }
        }


       System.out.println( "Completed " + count + " conversions");
       sc.close();


   }


   public static double getUserDouble( boolean isInches )
   {
       double input;
       if( isInches )
       {
           System.out.println( "Please enter your inches to convert" );
           input = sc.nextDouble();
           while( input < 0 )
           {
               sc.nextLine();
               System.out.println( "Please only enter a positive number");
               input = sc.nextDouble();
           }
       }
       else
       {
           System.out.println( "Please enter your cm to convert" );
           input = sc.nextDouble();
           while( input < 0 )
           {
               sc.nextLine();
               System.out.println( "Please only enter a positive number");
               input = sc.nextDouble();
           }
       }
       return input;
   }




   public static void addToCount()
   {
       ++count;
   }




   public static double inchesToMeters( double inches )
   {
       if( inches > 0 || inches <= 0 )
       {
           System.out.println( "Converting now" );
       }
       ++count;
       --convNum;
       return inches/39.37;
   }




   public static double cmTometersOrInches( double cm, boolean toInches )
   {
       double output;
       if( toInches )
       {
           output = 39.37f*cm;
       }
       else
       {
           output = cm/100;
       }
       ++count;
       --convNum;
       return output;
   }
}



