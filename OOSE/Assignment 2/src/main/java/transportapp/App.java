package transportapp;

import vehicles;

public class App
{
    public static void main(String[] args)
    {
        System.out.println("Hello world");

        Vehicle b = new Bus(7);
        
        // If you wish to change the name and/or package of the class containing 'main()', you 
        // will also need to update the 'mainClass = ...' line in build.gradle.
    }
}
