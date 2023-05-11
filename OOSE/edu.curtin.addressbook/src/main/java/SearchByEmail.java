package edu.curtin.addressbook;

import java.util.*;

public class SearchByEmail implements Option
{
    private Map<String, Entry> emails;

    public SearchByEmail()
    {
        emails = new HashMap<>();
    }

    @Override
    public void addEntry(String name, Entry entry)
    {
        emails.put(name, entry);
    }

    @Override
    public String doOption(String email)
    {
        return  "\n" + email + " Belongs to:\n" + emails.get(email).toString();
    }
}
