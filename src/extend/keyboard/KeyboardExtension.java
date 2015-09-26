package extend.keyboard;

import app.Engine;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class KeyboardExtension
{
    // Keyboard
    private Rectangle keyboardArea;
    private final ArrayList<KeyboardButton> keyboardKeys;
    private final BufferedImage keyboardModal = Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/Libraries/Application/src/extend/keyboard/modal.png");
    private final BufferedImage keyboardImage = Drawing.getImageFile("C:/Users/Jamie/Documents/NetBeansProjects/Libraries/Application/src/extend/keyboard/keyboard.png");
    
    // Background
    private BufferedImage backgroundImage;
    
    // Input
    //private ArrayList<KeyboardCharacter> inputValue;
    private String inputValue;
    private int inputLength;
    private KeyboardType inputType;
    
    // Option
    private boolean optionCaps, optionNum, optionShift;
    
    public KeyboardExtension(int length, KeyboardType type)
    {
        // Keyboard
        this.keyboardArea = new Rectangle(Engine.getScreenCenterX() - 300, Engine.getScreenCenterY() - 150, 600, 300);
        this.keyboardKeys = new ArrayList();
        this.initKeys();
        
        // Background
        //this.backgroundImage = get Canvas Image
        
        // Input
        //this.inputValue = new ArrayList();
        this.inputValue = "";
        this.inputLength = length;
        this.inputType = type;
        
        // Option
        this.optionCaps = false;
        this.optionNum = false;
        this.optionShift = false;
    }
    
    private void actionAccept()
    {
        /*
        if(this.inputValue.size() > 0)
        {
            // Engine needs to remove this object and pass the value back to whatever class wants it
        }
        else
        {
            // Cannot accept an empty string
        }
            */
    }
    
    private void actionButton(KeyboardButton button)
    {
        // TEMP
        if(button.ref.equals("SPACE")) {this.inputValue = "SPACE WAS PRESSED";}
    }
    
    private Rectangle getAreaKeyboard()
    {
        return new Rectangle(this.keyboardArea.x + 22, this.keyboardArea.y + 94, 556, 184);
    }
    
    private void initKeys()
    {
        this.initKeysAdd("SPACE", 120, 154, 142, 26, "Space");
    }
    
    private void initKeysAdd(String ref, int posX, int posY)
    {
        this.keyboardKeys.add(new KeyboardButton(ref, new Rectangle(this.getAreaKeyboard().x + posX, this.getAreaKeyboard().y + posY, 26, 26)));
    }
    
    private void initKeysAdd(String ref, int posX, int posY, int sizeX, int sizeY, String image)
    {
        this.keyboardKeys.add(new KeyboardButton(ref, new Rectangle(this.getAreaKeyboard().x + posX, this.getAreaKeyboard().y + posY, sizeX, sizeY), image));
    }
    
    public void inputClick(MouseEvent e)
    {
        // Accept
        //
        
        // NOTE: create a cancel button (it might be optional to allow the keyboard to be cancelled)
        
        // Keyboard
        if(this.getAreaKeyboard().contains(e.getPoint())) {this.inputClickKeyboard(e);}
    }
    
    private void inputClickKeyboard(MouseEvent e)
    {
        for(int x = 0; x < this.keyboardKeys.size(); x++)
        {
            if(this.keyboardKeys.get(x).area.contains(e.getPoint()))
            {
                this.actionButton(this.keyboardKeys.get(x));
                return;
            }
        }
    }
    
    public void render(Graphics g)
    {
        renderBackground(g);
        renderModal(g);
    }
    
    private void renderBackground(Graphics g)
    {
        //Drawing.drawImage(g, this.backgroundImage, 0, 0);
        // TEMP
        Drawing.fillScreen(g, Color.BLACK);
    }
    
    private void renderModal(Graphics g)
    {
        // Modal
        Drawing.drawImage(g, this.keyboardModal, this.keyboardArea.x, this.keyboardArea.y);
        
        // Input
        renderModalInput(g);
        
        // Keyboard
        renderModalKeyboard(g);
    }
    
    private void renderModalInput(Graphics g)
    {
        // TEMP
        Text.write(g, this.inputValue, this.keyboardArea.x + 25, this.keyboardArea.y + 25, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
    }
    
    private void renderModalKeyboard(Graphics g)
    {
        // Keyboard
        Drawing.drawImage(g, this.keyboardImage, this.getAreaKeyboard().x, this.getAreaKeyboard().y);
        
        // Highlight
        for(int x = 0; x < this.keyboardKeys.size(); x++)
        {
            if(this.keyboardKeys.get(x).area.contains(Engine.getMousePoint())) {this.keyboardKeys.get(x).renderHighlight(g);}
        }
    }
    
    public void tick()
    {
        //
    }
    
}