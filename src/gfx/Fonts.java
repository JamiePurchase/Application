package gfx;

import java.awt.Font;
import java.util.HashMap;

public class Fonts 
{
    private static HashMap<String, Font> fonts = new HashMap();
    
    public static void addFont(String name, Font font)
    {
        fonts.put(name, font);
    }
    
    public static Font getFont(String font)
    {
        if(fonts.containsKey(font)) {return fonts.get(font);}
        return new Font("Times New Roman", Font.PLAIN, 26);
    }
    
}