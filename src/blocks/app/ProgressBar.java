package blocks.app;

import blocks.Block;
import gfx.Colour;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ProgressBar extends Block
{
    private Rectangle posRect;
    private Color rgbBkg, rgbBorder, rgbFill, rgbShadow;
    private int value;
    
    public ProgressBar(int posX, int posY, int width, String fill, String bkg, String border, String shadow)
    {
        super();
        this.posRect = new Rectangle(posX, posY, width, 30);
        this.rgbBkg = Colour.getColour(bkg);
        this.rgbFill = Colour.getColour(fill);
        this.rgbBorder = Colour.getColour(border);
        this.rgbShadow = Colour.getColour(shadow);
        this.value = 0;
    }
    
    public Rectangle getNexus()
    {
        return this.posRect;
    }
    
    public void render(Graphics g)
    {
        // Shadow
        g.setColor(this.rgbShadow);
        g.fillRect(this.posRect.x + 5, this.posRect.y + 5, this.posRect.width, this.posRect.height);
        
        // Background
        g.setColor(this.rgbBkg);
        g.fillRect(this.posRect.x, this.posRect.y, this.posRect.width, this.posRect.height);
        
        // Fill
        g.setColor(this.rgbFill);
        g.fillRect(this.posRect.x, this.posRect.y, (this.posRect.width / 100) * this.value, this.posRect.height);
        
        // Border
        g.setColor(this.rgbBorder);
        g.drawRect(this.posRect.x, this.posRect.y, this.posRect.width, this.posRect.height);
        g.drawRect(this.posRect.x + 1, this.posRect.y + 1, this.posRect.width - 2, this.posRect.height - 2);
    }
    
    public void setValue(int newValue)
    {
        this.value = newValue;
    }
    
}