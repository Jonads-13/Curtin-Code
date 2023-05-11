package edu.curtin.addressbook;

import java.util.*;

public class SearchByName implements Option
{

    private Map<String, Entry> people;

    public SearchByName()
    {
        people = new HashMap<>();
    }

    @Override
    public void addEntry(String name, Entry entry)
    {
        people.put(name, entry);
    }

    @Override
    public String doOption(String name)
    {    
        return people.get(name).toString();
    }
}
