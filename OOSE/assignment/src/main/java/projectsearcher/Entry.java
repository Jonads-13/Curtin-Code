package projectsearcher;

import java.util.*;
import java.io.*;

/**
 * @Author    Jacob Jonas, 18439731
 * @Created   30/03/2023
 * @Modified  11/04/2023
 * @Assertion Interface for composite pattern implementaion
 **/

public interface Entry 
{
    // Defined methods for other classes to implement
    public File getFile();
    public String getName();
    public int getNumIncludedLines();
    public void displayCount(int indents);
    public void displayLines(int indents);
    public void applyCriteria(List<Criteria> criteria);
    public void addChild(Entry child);
    public void display(String format);
    public boolean includeLine(String line, List<Criteria> criteria);
}
