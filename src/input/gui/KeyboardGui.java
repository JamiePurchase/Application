package input.gui;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class KeyboardGui
{
    private Rectangle guiArea;
    private Color guiColorFrameBack, guiColorFrameBorder, guiColorKeyBack, guiColorKeyHover, guiColorKeyText;
    private Font guiFontText;
    private ArrayList<KeyboardGuiKey> guiKeys;
    
    public KeyboardGui()
    {
        this.init(new Rectangle(383, 50, 600, 200), Color.BLACK, Color.GREEN, Color.BLACK, Color.GRAY, Color.GREEN, new Font("Courier New", Font.PLAIN, 18));
    }
    
    public KeyboardGui(Rectangle rect)
    {
        this.init(rect, Color.WHITE, Color.BLACK, Color.WHITE, Color.GRAY, Color.BLACK, new Font("Courier New", Font.PLAIN, 18));
    }
    
    public Rectangle getArea()
    {
        return this.guiArea;
    }
    
    private void init(Rectangle rect, Color back, Color border, Color key, Color hover, Color text, Font font)
    {
        this.guiArea = rect;
        this.guiColorFrameBack = back;
        this.guiColorFrameBorder = border;
        this.guiColorKeyBack = key;
        this.guiColorKeyHover = hover;
        this.guiColorKeyText = text;
        this.guiFontText = font;
        this.guiKeys = new ArrayList();
        this.initKeys();
    }
    
    private void initKeys()
    {
        this.guiKeys.add(new KeyboardGuiKey("ESC", "", null, 10, 10));
    }
    
    public void render(Graphics g)
    {
        this.renderFrame(g);
        this.renderKeys(g);
    }
    
    private void renderFrame(Graphics g)
    {
        Drawing.fillRect(g, this.getArea(), this.guiColorFrameBack);
        Drawing.drawRect(g, this.getArea(), this.guiColorFrameBorder);
    }
    
    private void renderKeys(Graphics g)
    {
        for(int x = 0; x < this.guiKeys.size(); x++)
        {
            this.guiKeys.get(x).render(g, this.getArea(), this.guiColorKeyBack, this.guiColorFrameBorder, this.guiColorKeyHover, this.guiColorKeyText, this.guiFontText);
        }
    }
    
}