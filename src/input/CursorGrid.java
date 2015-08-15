package input;

import gfx.Colour;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class CursorGrid 
{
    private int cursorX, cursorY, sizeX, sizeY;
    private boolean moveH, moveV;
    private Font cursorFont;
    private Color cursorColour;
    
    public CursorGrid(int sizeX, int sizeY)
    {
        this.cursorX = 0;
        this.cursorY = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cursorFont = Fonts.getFont("STANDARD");
        this.cursorColour = Colour.getColour("TITLE_TEXT");
    }
    
    public CursorGrid(int sizeX, int sizeY, Font font, Color color)
    {
        this.cursorX = 0;
        this.cursorY = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cursorFont = font;
        this.cursorColour = color;
    }
    
    public void move(String direction)
    {
        if(direction.equals("DOWN") && this.cursorY < this.sizeY - 1) {this.cursorY += 1;}
        if(direction.equals("LEFT") && this.cursorX > 0) {this.cursorX -= 1;}
        if(direction.equals("RIGHT") && this.cursorX < this.sizeX - 1) {this.cursorX += 1;}
        if(direction.equals("UP") && this.cursorY > 0) {this.cursorY -= 1;}
    }
    
    public void render(Graphics g)
    {
        g.setColor(Colour.getColour("TITLE_TEXT"));
        g.setFont(Fonts.getFont("STANDARD"));
        Text.write(g, ">", 370, (this.cursorY * 50) + 350);
    }
    
}