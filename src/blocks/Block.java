package blocks;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Block
{
    private String ref;
    private boolean enable;
    
    public Block()
    {
        this.ref = "";
        this.enable = true;
    }
    
    public Block(String ref)
    {
        this.ref = ref;
        this.enable = true;
    }
    
    public abstract Rectangle getNexus();
    
    public boolean isEnabled()
    {
        return this.enable;
    }
    
    public void setEnabled(boolean value)
    {
        this.enable = value;
    }
    
    public abstract void render(Graphics g);

}