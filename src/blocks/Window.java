package blocks;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Window
{
    private String title;
    private boolean close;
    private BufferedImage icon;
    private Rectangle rect;
    private final int barHeight = 36;
    private HashMap<String, Color> scheme;
    
    public Window(String title, boolean close, BufferedImage icon)
    {
        this.title = title;
        this.close = close;
        this.icon = icon;
        this.rect = new Rectangle(0, 0, 1366, 768);
        this.scheme = getSchemeDefault();
    }
    
    public Window(String title, boolean close, BufferedImage icon, Rectangle rect)
    {
        this.title = title;
        this.close = close;
        this.icon = icon;
        this.rect = rect;
        this.scheme = getSchemeDefault();
    }
    
    private Color getSchemeColour(String name)
    {
        return this.scheme.get(name);
    }
    
    private HashMap<String, Color> getSchemeDefault()
    {
        HashMap<String, Color> scheme = new HashMap();
        scheme.put("BACKGROUND", Colour.getColourRGB(0, 0, 0));
        scheme.put("BORDER", Colour.getColourRGB(0, 0, 0));
        scheme.put("FRAME", Colour.getColourRGB(100, 0, 100));
        scheme.put("TEXT", Colour.getColourRGB(0, 0, 0));
        return scheme;
    }
    
    private Rectangle getRectClose()
    {
        return new Rectangle(this.rect.x - 38, this.rect.y + 2, 32, 32);
    }
    
    public void render(Graphics g)
    {
        this.renderFrame(g);
        this.renderTitle(g);
        this.renderClose(g);
    }
    
    private void renderClose(Graphics g)
    {
        if(this.close)
        {
            BufferedImage button = Drawing.getImage("icon/close.png");
            if(getRectClose().contains(Engine.getMousePoint())) {button = Drawing.getImage("icon/closeHover.png");}
            g.drawImage(button, this.rect.x - 38, this.rect.y + 2, null);
        }
    }
    
    private void renderFrame(Graphics g)
    {
        // Background
        g.setColor(this.getSchemeColour("FRAME"));
        g.fillRect(this.rect.x, this.rect.y, this.rect.width, this.barHeight);
        
        // Border
        g.setColor(this.getSchemeColour("BORDER"));
        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.barHeight);
    }
    
    private void renderTitle(Graphics g)
    {
        int textX = this.rect.x + 6;
        if(this.icon != null)
        {
            textX = this.rect.x + 60;
            g.drawImage(this.icon, this.rect.x + 6, this.rect.y + 2, null);
        }
        g.setFont(Fonts.getFont("STANDARD"));
        g.setColor(this.getSchemeColour("TEXT"));
        Text.write(g, this.title, this.rect.x + 60, this.rect.y + 25);
    }
    
    public void setScheme(Color background, Color border, Color frame, Color text)
    {
        this.scheme.put("BACKGROUND", background);
        this.scheme.put("BORDER", border);
        this.scheme.put("FRAME", frame);
        this.scheme.put("TEXT", text);
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
}