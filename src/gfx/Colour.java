package gfx;

import java.awt.Color;
import java.util.HashMap;

public class Colour
{
    private static HashMap<String, Color> colours = new HashMap();
    
    public static void addColour(String colour, int r, int g, int b)
    {
        colours.put(colour, getColourRGB(r, g, b));
    }
    
    public static Color getColour(String colour)
    {
        if(colours.containsKey(colour)) {return colours.get(colour);}
        return Color.BLACK;
    }
    
    public static Color getColourRGB(int r, int g, int b)
    {
        float hsb[] = Color.RGBtoHSB(r,g,b,null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }
    
    public static void loadColours()
    {
        addColour("BLACK", 0, 0, 0);
        addColour("BLUE", 0, 0, 255);
        addColour("GREEN", 0, 255, 0);
        addColour("RED", 255, 0, 0);
        addColour("STEEL", 130, 130, 130);
        addColour("WHITE", 255, 255, 255);
    }
    
}