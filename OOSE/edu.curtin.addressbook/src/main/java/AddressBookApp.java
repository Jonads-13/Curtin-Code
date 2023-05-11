package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...
 */

public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    private static Map<String, Option> options = new HashMap<>();

    public static void main(String[] args)
    {
        // initialise Options container
        options.put("1", new SearchByName());
        options.put("2", new SearchByEmail());

        String fileName;        
        
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            options = readAddressBook(fileName, options);
            showMenu(options);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static Map<String, Option> readAddressBook(String fileName, Map<String, Option> options) throws IOException
    {        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                String[] parts = line.split(":");
                
                options = createEntry(options, parts);
                
                line = reader.readLine();
            }
        }
        
        return options;
    }
    
    public static Map<String, Option> createEntry(Map<String, Option> options, String[] parts)
    {
        String name = parts[0];
        List<String> emails = new LinkedList<>();

        for(int i = 1; i < parts.length; i++)
        {
            emails.add(parts[i]);
        }

        Entry temp = new Entry(name, emails);

        options.get("1").addEntry(name, temp);

        for(String e : emails)
        {
            options.get("2").addEntry(e,temp);
        }

        return options;
    }

    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(Map<String, Option> options)
    {
        boolean done = false;
        while(!done)
        {
            String choice;
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                choice = input.nextLine();
                
                if((choice.equals("1")) || (choice.equals("2")))
                {
                    System.out.println("Enter your search query:");
                    System.out.println(options.get(choice).doOption(input.nextLine()));
                }
                else if(choice.equals("3"))
                {
                    done = true;
                }
                else
                {
                    System.out.println("Enter a valid number");
                }

            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }

}
