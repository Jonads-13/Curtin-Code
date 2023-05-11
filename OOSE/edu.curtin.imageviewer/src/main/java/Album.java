package edu.curtin.imageviewer;
import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    // FIXME: Insert your fields, constructors and methods here.
    private List<ImageRecord> images;

    public Album()
    {
        images = new LinkedList<>();
    }

    public void addImage(ImageRecord image)
    {
        images.add(image);
    }

    public ImageRecord getImage(int i)
    {
        return images.get(i);
    }

    public int getSize()
    {
        return images.size();
    }


}
