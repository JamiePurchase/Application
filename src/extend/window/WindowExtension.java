package extend.window;

import app.Engine;
import debug.Console;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class WindowExtension
{
    // Window
    private Color windowBkgColour, windowFrameColour;
    private int windowFrameWidth;
    
    // Display
    private int displaySizeMaxX, displaySizeMaxY, displaySizeMidX, displaySizeMidY;
    private boolean displayStateMax;
    
    // Titlebar
    private Rectangle titleRect;
    private Color titleBkgColour, titleTextColour;
    private String titleTextValue;
    private Font titleTextFont;
    private ArrayList<WindowButton> titleButton;
    
    // Input
    private boolean inputMouseActive;
    private Point inputMousePoint;
    
    // Render
    private Rectangle renderRect;
    
    public WindowExtension(String titleString)
    {
        // Window
        this.windowBkgColour = Color.WHITE;
        this.windowFrameColour = Color.BLACK;
        this.windowFrameWidth = 0;
        
        // Display
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.displaySizeMaxX = dim.width;
        this.displaySizeMaxY = dim.height;
        this.displaySizeMidX = Engine.width;
        this.displaySizeMidY = Engine.height;
        this.displayStateMax = false;
        
        // Titlebar
        this.titleRect = new Rectangle(0, 0, Engine.width, 40);
        this.titleBkgColour = Colour.getColourRGB(95, 139, 84);
        this.titleTextValue = titleString;
        this.titleTextColour = Color.BLACK;
        this.titleTextFont = new Font("Times New Roman", Font.PLAIN, 22);
        this.titleButton = new ArrayList();
        this.titleButton.add(new WindowButton("WINDOW_CLOSE", Engine.width - 35, 5, "x"));
        this.titleButton.add(new WindowButton("WINDOW_MAX", Engine.width - 70, 5, "■"));
        //this.titleButton.add(new WindowButton("WINDOW_MAX", Engine.width - 70, 5, Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/Libraries/Application/src/extend/window/resources/buttonMax.png")));
        
        // Input
        this.inputMouseActive = false;
        this.inputMousePoint = null;
        
        // Render
        this.renderRect = new Rectangle(0 + this.windowFrameWidth, this.titleRect.y + this.titleRect.height + this.windowFrameWidth, Engine.width - (2 * this.windowFrameWidth), Engine.height - this.titleRect.height - (2 * this.windowFrameWidth));
    }
    
    public Point getCanvasPoint(Point pointRender)
    {
        return new Point(pointRender.x - this.renderRect.x, pointRender.y - this.renderRect.y);
    }
    
    public Rectangle getRenderArea()
    {
        return this.renderRect;
    }
    
    public Rectangle getRenderFill()
    {
        return new Rectangle(0, 0, this.renderRect.width, this.renderRect.height);
    }
    
    public int getRenderFillCenterX()
    {
        return (int) this.getRenderFill().getCenterX();
    }
    
    public int getRenderFillCenterY()
    {
        return (int) this.getRenderFill().getCenterY();
    }
    
    public void inputMouse(MouseEvent e, String action)
    {
        // Mouse click initiated
        if(action.equals("CLICK"))
        {
            // Check toolbar buttons
            if(inputMouseButton(e)) {return;}
            
            // Click on the toolbar and drag to move the frame
            if(this.titleRect.contains(e.getPoint()))
            {
                inputMouseActive = true;
                inputMousePoint = e.getPoint();
            }
        }
        
        // Mouse click released
        else if(action.equals("CLICK_DONE"))
        {    
            if(inputMouseActive)
            {
                int moveX = e.getPoint().x - inputMousePoint.x;
                int moveY = e.getPoint().y - inputMousePoint.y;
                Console.print("Drag from " + inputMousePoint.x + ", " + inputMousePoint.y + " to " + e.getPoint().x + ", " + e.getPoint().y);
                Console.print(" moving " + moveX + " x, " + moveY + " y");
                Engine.display.frameMove(moveX, moveY);
                inputMouseActive = false;
                inputMousePoint = null;
            }
        }
    }
    
    private boolean inputMouseButton(MouseEvent e)
    {
        // Check toolbar buttons
        if(this.titleButton.size() < 1) {return false;}
        String buttonRef = "";
        for(int x = 0; x < this.titleButton.size(); x++)
        {
            if(this.titleButton.get(x).containClick(e)) {buttonRef = this.titleButton.get(x).getRef();}
        }
                
        // Clicking on the max button switches between partial and maximum window size
        if(buttonRef.equals("WINDOW_MAX"))
        {
            if(this.displayStateMax)
            {
                this.displayStateMax = false;
                Engine.setDisplaySize(this.displaySizeMidX, this.displaySizeMidY);
            }
            else
            {
                this.displayStateMax = true;
                Engine.setDisplaySize(this.displaySizeMaxX, this.displaySizeMaxY);
            }
            this.resize();
            return true;
        }

        // Clicking on the close button always exits
        if(buttonRef.equals("WINDOW_CLOSE")) {System.exit(0);}
        
        // Return
        return false;
    }
    
    public void render(Graphics g)
    {
        // Window Background
        //Drawing.fillScreen(g, this.windowBkgColour);

        // Window Frame
        if(this.windowFrameWidth > 0) {Drawing.drawRect(g, new Rectangle(0, 0, Engine.width, Engine.height), this.windowFrameWidth, this.windowFrameColour);}
        
        // Titlebar Background
        Drawing.fillRect(g, this.titleRect, this.titleBkgColour);
        
        // Titlebar Border
        if(this.windowFrameWidth > 0) {Drawing.drawLine(g, this.titleRect.x, this.titleRect.height, this.titleRect.x + this.titleRect.width, this.windowFrameWidth, this.windowFrameColour);}
        
        // Titlebar Text
        Text.write(g, this.titleTextValue, 15, 27, "LEFT", this.titleTextFont, this.titleTextColour);
        
        // Titlebar Buttons
        if(this.titleButton.size() > 0) {this.renderButtons(g);}
    }
    
    private void renderButtons(Graphics g)
    {
        for(int x = 0; x < this.titleButton.size(); x++)
        {
            this.titleButton.get(x).render(g);
        }
    }
    
    private void resize()
    {
        this.titleRect = new Rectangle(0, 0, Engine.width, 40);
        this.titleButton = new ArrayList();
        this.titleButton.add(new WindowButton("WINDOW_CLOSE", Engine.width - 35, 5, "x"));
        this.titleButton.add(new WindowButton("WINDOW_MAX", Engine.width - 70, 5, "■"));
    }
    
}