package input.gui;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class KeyboardGuiKey
{
    private String keyRef, keyCharacter;
    private boolean keyIconActive;
    private BufferedImage keyIconImage;
    private int keyPosX, keyPosY;
    
    public KeyboardGuiKey(String ref, String character, BufferedImage image, int posX, int posY)
    {
        this.keyRef = ref;
        this.keyCharacter = character;
        if(image != null)
        {
            this.keyIconActive = true;
            this.keyIconImage = image;
        }
        else
        {
            this.keyIconActive = false;
            this.keyIconImage = null;
        }
        this.keyIconImage = image;
        this.keyPosX = posX;
        this.keyPosY = posY;
    }
    
    public void render(Graphics g, Rectangle frame, Color back, Color border, Color hover, Color text, Font font)
    {
        // Area
        Rectangle keyArea = new Rectangle(frame.x + this.keyPosX, frame.y + this.keyPosY, 20, 20);
        
        // Background
        if(keyArea.contains(Engine.getMousePoint())) {Drawing.fillRect(g, keyArea, hover);}
        //else {Drawing.fillRect(g, keyArea, back);}
        
        // Icon
        if(this.keyIconActive) {Drawing.drawImage(g, this.keyIconImage, keyArea.x, keyArea.y);}
        
        // Text
        else {Text.write(g, "A", keyArea.x + (keyArea.width / 2), keyArea.y + 15, "CENTER", font, text);}
        
        // Border
        Drawing.fillRect(g, keyArea, hover);
    }
    
}