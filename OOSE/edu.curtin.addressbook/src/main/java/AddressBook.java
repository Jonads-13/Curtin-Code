package edu.curtin.addressbook;

//import java.security.KeyStore.Entry;
import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author ...
 */

public class AddressBook
{
    // Class fields
    private Map<String, Entry> people;
    private Map<String, Entry> emails;

    public AddressBook()
    {
        people = new HashMap<>();
        emails = new HashMap<>();
    }

    public void addEntry(String name, Entry entry)
    {
        people.put(name, entry);

        List<String> e = entry.getEmails();

        for (String email : e) 
        {
            emails.put(email, entry);    
        }
    }

    public Entry getEntryByPerson(String name)
    {
        return people.get(name);
    }

    public Entry getEntryByEmail(String email)
    {
        return emails.get(email);
    }
}
