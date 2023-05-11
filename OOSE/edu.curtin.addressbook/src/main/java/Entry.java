package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author ...
 */
public class Entry 
{
    private String name;
    private List<String> emails;

    public Entry(String pName, List<String> pEmails)
    {
        name = pName;
        emails = new LinkedList<>(pEmails);
    }

    public String getName()
    {
        return name;
    }

    public List<String> getEmails()
    {
        return emails;
    }

    @Override
    public String toString()
    {
        String entryString = this.name + "\nAll E-mails for " + this.name + ":\n\t";

        for (String e : emails)
        {
            entryString += e + "\n\t";
        }

        return entryString;
    }

}
