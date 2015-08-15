package blocks.form;

import blocks.Block;

public abstract class BlockForm extends Block
{
    private String ref;
    private boolean visible;
    
    public BlockForm(String ref)
    {
        this.ref = ref;
        this.visible = true;
    }
    
    public boolean isVisible()
    {
        return this.visible;
    }
    
}