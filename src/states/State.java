package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State
{

    public abstract void render(Graphics g);
    public abstract void tick();

    public void inputKey(String key, String action)
    {
        // Debug
        //Console.print("STATE (SUPERCLASS) -> INPUT KEY (" + key + ", " + action + ")");
        
        if(action.equals("PRESS")) {this.inputKeyPress(key);}
        if(action.equals("PRESS_DONE")) {this.inputKeyRelease(key);}
        if(action.equals("TYPE")) {this.inputKeyType(key);}
    }

    public abstract void inputKeyPress(String key);
    public abstract void inputKeyRelease(String key);
    public abstract void inputKeyType(String key);

    public void inputMouse(MouseEvent e, String action, String button)
    {
        if(action.equals("CLICK"))
        {
            if(button.equals("LEFT")) {this.inputMouseClickL(e);}
            if(button.equals("RIGHT")) {this.inputMouseClickR(e);}
        }
        //if(type.equals("CLICK_DONE")) {this.inputMouseRelease();}
        if(action.equals("MOVE")) {this.inputMouseMove(e);}
    }

    public abstract void inputMouseClickL(MouseEvent e);
    public abstract void inputMouseClickR(MouseEvent e);
    public abstract void inputMouseMove(MouseEvent e);

}