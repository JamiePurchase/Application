package extend.keyboard;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class KeyboardButton
{
    public final String ref;
    public final Rectangle area;
    private final BufferedImage image;
    
    public KeyboardButton(String ref, Rectangle area)
    {
        this.ref = ref;
        this.area = area;
        this.image = Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/Libraries/Application/src/extend/keyboard/buttonKey.png");
    }
    
    public KeyboardButton(String ref, int posX, int posY)
    {
        this.ref = ref;
        this.area = new Rectangle(posX, posY, 26, 26);
        this.image = Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/Libraries/Application/src/extend/keyboard/buttonKey.png");
    }
    
    public KeyboardButton(String ref, Rectangle area, String image)
    {
        this.ref = ref;
        this.area = area;
        this.image = Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/Libraries/Application/src/extend/keyboard/buttonKey" + image + ".png");
    }
    
    // NOTE: create an alternate constructor that allows for a polygon to be passed-in (for the enter button)
    
    public void renderHighlight(Graphics g)
    {
        Drawing.drawImage(g, this.image, this.area.x, this.area.y);
    }
    
}