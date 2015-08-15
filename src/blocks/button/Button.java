package blocks.button;

import app.Engine;
import blocks.Block;
import gfx.Colour;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button extends Block
{
    private String ref;
    private Rectangle posRect;
    private Color rgbBorder, rgbFill, rgbHover, rgbShadow;
    private String captionText;
    private Font captionFont;
    
    public Button(String ref, int posX, int posY, String caption, String font, String fill, String hover, String border, String shadow)
    {
        super();
        this.ref = ref;
        this.initRect(posX, posY, 200, 50);
        this.initColours(fill, hover, border, shadow);
        this.initCaption(caption, font);
    }
    
    public Rectangle getNexus()
    {
        return this.posRect;
    }
    
    private void initCaption(String caption, String font)
    {
        this.captionText = caption;
        this.captionFont = Fonts.getFont(font);
    }
    
    private void initColours(String fill, String hover, String border, String shadow)
    {
        this.rgbFill = Colour.getColour(fill);
        this.rgbHover = Colour.getColour(hover);
        this.rgbBorder = Colour.getColour(border);
        this.rgbShadow = Colour.getColour(shadow);
    }
    
    private void initRect(int posX, int posY, int wide, int high)
    {
        this.posRect = new Rectangle(posX - (wide / 2), posY - (high / 2), wide, high);
    }
    
    private boolean isHover()
    {
        if(this.posRect.contains(Engine.getMousePoint())) {return true;}
        return false;
    }
    
    public void render(Graphics g)
    {
        // Shadow
        g.setColor(this.rgbShadow);
        g.fillRect(this.posRect.x + 5, this.posRect.y + 5, this.posRect.width, this.posRect.height);
        
        // Fill
        if(this.isHover()) {g.setColor(this.rgbHover);}
        else {g.setColor(this.rgbFill);}
        g.fillRect(this.posRect.x, this.posRect.y, this.posRect.width, this.posRect.height);
        
        // Border
        g.setColor(this.rgbBorder);
        g.drawRect(this.posRect.x, this.posRect.y, this.posRect.width, this.posRect.height);
        g.drawRect(this.posRect.x + 1, this.posRect.y + 1, this.posRect.width - 2, this.posRect.height - 2);
        
        // Caption
        g.setFont(this.captionFont);
        Text.write(g, this.captionText, this.posRect.x + (this.posRect.width / 2), this.posRect.y + (this.posRect.height / 2) + 10, "CENTER");
    }
    
}