package input;

import app.Engine;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse extends MouseAdapter implements MouseMotionListener
{
    private Point mousePoint;
    private boolean eventNew = false;
    private int idleTick = 0;
    
    private String getButton(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1) {return "LEFT";}
        if(e.getButton() == MouseEvent.BUTTON2) {return "WHEEL";}
        if(e.getButton() == MouseEvent.BUTTON3) {return "RIGHT";}
        return "";
    }
    
    public boolean getEventNew()
    {
        return this.eventNew;
    }
    
    public int getIdleTick()
    {
        return this.idleTick;
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        Engine.setMousePoint(new Point(e.getX(), e.getY()));
        Engine.inputMouse(e, "MOVE", this.getButton(e));
        Engine.setTooltipActive(false);
        setIdleTick();
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        this.eventNew = true;
        Engine.inputMouse(e, "CLICK", this.getButton(e));
    }
    
    @Override
    public void mouseReleased (MouseEvent e)
    {
        Engine.inputMouse(e, "CLICK_DONE", this.getButton(e));
    }
    
    public void setEventDone()
    {
        this.eventNew = false;
    }
    
    private void setIdleTick()
    {
        this.idleTick = 0;
    }
    
    public void tick()
    {
        this.idleTick ++;
    }

}