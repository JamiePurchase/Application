package gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Clipboard
{
    private HashMap<String, BufferedImage> contents = null;
    
    public Clipboard()
    {
        this.clear();
    }
    
    public void addImage(String ref, BufferedImage image)
    {
        this.contents.put(ref, image);
    }
    
    public void clear()
    {
        this.contents = new HashMap();
    }
    
    public BufferedImage getImage(String ref)
    {
        return this.contents.get(ref);
    }
    
    public String getKeys()
    {
        return this.contents.toString();
    }
    
}