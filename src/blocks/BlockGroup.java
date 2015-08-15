package blocks;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockGroup
{
    private String ref;
    private HashMap<String, Block> blocks;
    
    public BlockGroup (String ref)
    {
        this.ref = ref;
        this.blocks = new HashMap();
    }
    
    public void addBlock(String ref, Block block)
    {
        this.blocks.put(ref, block);
    }
    
    public String[] getBlockKeys()
    {
        return this.blocks.keySet().toArray(new String[this.blocks.size()]);
    }
    
    public String inputClick(MouseEvent e)
    {
        for(int x = 0; x < this.blocks.size(); x++)
        {
            if(this.blocks.get(this.getBlockKeys()[x]).getNexus().contains(e.getPoint())) {return this.getBlockKeys()[x];}
        }
        return "";
    }
    
    public void inputKey(String key)
    {
        if(key.equals("TAB"))
        {
            // Note: move focus to next block in array
            // If the block doesn't support focus then move on
        }
    }
    
    public void removeBlock(String ref)
    {
        this.blocks.remove(ref);
    }
    
    public void render(Graphics g)
    {
        for(int x = 0; x < this.blocks.size(); x++)
        {
            this.blocks.get(this.getBlockKeys()[x]).render(g);
        }
    }
    
}