package extend.window;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class WindowButton
{
    private String buttonRef;
    private Rectangle buttonArea;
    private Color buttonColorFill, buttonColorFocus, buttonColorText;
    private String buttonCaption;
    private BufferedImage buttonImage;
    private boolean buttonEnabled;
    
    public WindowButton(String ref, int posX, int posY, String caption)
    {
        this.buttonRef = ref;
        this.buttonArea = new Rectangle(posX, posY, 30, 30);
        this.buttonColorFill = Colour.getColourRGB(95, 139, 84);
        this.buttonColorFocus = Colour.getColourRGB(163, 103, 86);
        this.buttonColorText = Color.BLACK;
        this.buttonCaption = caption;
        this.buttonImage = null;
        this.buttonEnabled = true;
    }
    
    public WindowButton(String ref, int posX, int posY, BufferedImage image)
    {
        this.buttonRef = ref;
        this.buttonArea = new Rectangle(posX, posY, 30, 30);
        this.buttonColorFill = Colour.getColourRGB(95, 139, 84);
        this.buttonColorFocus = Colour.getColourRGB(163, 103, 86);
        this.buttonColorText = Color.BLACK;
        this.buttonCaption = "";
        this.buttonImage = image;
        this.buttonEnabled = true;
    }
    
    public WindowButton(String ref, Rectangle area, String caption, Color text, Color fill, Color focus, BufferedImage image, boolean enabled)
    {
        this.buttonRef = ref;
        this.buttonArea = area;
        this.buttonColorFill = fill;
        this.buttonColorFocus = focus;
        this.buttonColorText = text;
        this.buttonCaption = caption;
        this.buttonImage = image;
        this.buttonEnabled = enabled;
    }
    
    public boolean containClick(MouseEvent e)
    {
        if(this.buttonArea.contains(e.getPoint())) {return true;}
        return false;
    }
    
    public String getRef()
    {
        return this.buttonRef;
    }
    
    public void render(Graphics g)
    {
        // Background
        if(this.buttonArea.contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.buttonArea, this.buttonColorFocus);}
        else {Drawing.fillRect(g, this.buttonArea, this.buttonColorFill);}
        
        // Text
        if(this.buttonCaption.length() > 0) {Text.write(g, this.buttonCaption, (int) this.buttonArea.getCenterX(), (int) this.buttonArea.y + 22, "CENTER", new Font("Arial", Font.PLAIN, 26), this.buttonColorText);}
        
        // Image
        if(this.buttonImage != null) {Drawing.drawImage(g, this.buttonImage, this.buttonArea.x, this.buttonArea.y);}
    }
    
}