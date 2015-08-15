package blocks.form;

import app.Engine;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;

// NOTE: all form elements should probably inherit from an abstract form element class
// so that all form elements can be grouped and focus can be handles

public class Textbox extends BlockForm
{
    private Rectangle rect;
    private String value;
    private int valueMax;
    private boolean focus;
    private int carotTick;
    private boolean carotDraw;
    
    public Textbox(Rectangle rect, int valueMax)
    {
        super("");
        this.rect = rect;
        this.value = "";
        this.valueMax = valueMax;
        this.focus = false;
    }
    
    private boolean getFocus()
    {
        return this.focus;
    }
    
    private boolean getHover()
    {
        if(this.rect.contains(Engine.getMousePoint())) {return true;}
        return false;
    }
    
    public Rectangle getNexus()
    {
        return this.rect;
    }
    
    public void inputKeyPress(String key)
    {
        if(key.equals("ENTER"))
        {
            // NOTE: some textboxes will know what event to trigger if the user presses enter
        }
        if(key.equals("BACKSPACE")) {this.valueBackspace();}
        if(Engine.getKeyboard().isAlphanumeric(key)) {this.valueAdd(key);}
    }
    
    public void render(Graphics g)
    {
        this.renderFrame(g);
        this.renderValue(g);
    }
    
    private void renderFrame(Graphics g)
    {
        // NOTE: make a visual change if this.getFocus() or this.getHover() are true
        
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        
        // Border
        g.setColor(Color.WHITE);
        g.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    
    private void renderValue(Graphics g)
    {
        // Text
        g.setColor(Color.BLACK);
        g.setFont(Fonts.getFont("STANDARD"));
        g.drawString(this.value, this.rect.x + 15, this.rect.y + 10);
        
        // Cursor
        if(this.getFocus() && this.carotDraw)
        {
            g.drawString("|", this.rect.x + Text.getTextWidth(g, this.value) + 25, this.rect.y + 10);
        }
    }
    
    public void tick()
    {
        this.carotTick += 1;
        if(this.carotTick > 12)
        {
            this.carotTick = 1;
            if(this.carotDraw) {this.carotDraw = false;}
            else {this.carotDraw = true;}
        }
    }
    
    private void valueAdd(String add)
    {
        if(this.value.length() < this.valueMax) {this.value += add;}
    }
    
    private void valueBackspace()
    {
        this.value = this.value.substring(0, this.value.length() - 1);
    }

}