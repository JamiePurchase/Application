package input;

import app.Engine;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
    private boolean eventNew = false;
    
    public boolean getEventNew()
    {
        return this.eventNew;
    }
    
    private String getKeyName(KeyEvent e)
    {
        // Command Keys
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {return "ENTER";}
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {return "ESCAPE";}
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {return "SPACE";}
        //if(e.getKeyChar().equals(" ")) {return "SPACE";}
        
        // Directional Keys
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {return "DOWN";}
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {return "LEFT";}
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {return "RIGHT";}
        if(e.getKeyCode() == KeyEvent.VK_UP) {return "UP";}
        
        // Alphabetical Keys
        if(e.getKeyCode() == KeyEvent.VK_A) {return "A";}
        if(e.getKeyCode() == KeyEvent.VK_B) {return "B";}
        if(e.getKeyCode() == KeyEvent.VK_D) {return "D";}
        if(e.getKeyCode() == KeyEvent.VK_S) {return "S";}
        if(e.getKeyCode() == KeyEvent.VK_W) {return "W";}
        
        // Unknown Key
        return "";
    }
    
    public boolean isAlphabetical(String key)
    {
        if(Character.isAlphabetic(key.charAt(0))) {return true;}
        return false;
    }
    
    public boolean isAlphanumeric(String key)
    {
        if(isAlphabetical(key)) {return true;}
        if(isNumeric(key)) {return true;}
        return false;
    }
    
    public boolean isNumeric(String key)
    {
        if(Character.isDigit(key.charAt(0))) {return true;}
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        /*Console.print("INPUT KEYBOARD -> KEY TYPED (" + e + ")");
        Console.print("   getKeyName = " + this.getKeyName(e));
        Console.print("   e.getKeyCode = " + e.getKeyCode());*/
        //
        this.eventNew = true;
        if(this.getKeyName(e) != null) {Engine.getState().inputKey(this.getKeyName(e), "TYPE");}
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        this.eventNew = true;
        if(this.getKeyName(e) != null) {Engine.getState().inputKey(this.getKeyName(e), "PRESS");}
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(this.getKeyName(e) != null) {Engine.getState().inputKey(this.getKeyName(e), "PRESS_DONE");}
    }
    
    public void setEventDone()
    {
        this.eventNew = false;
    }
    
}