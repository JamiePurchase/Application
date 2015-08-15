package maths;

public class Portion
{
    private int now, max;
    
    public Portion(int now, int max)
    {
        this.now = now;
        this.max = max;
    }
    
    public float getPercent()
    {
        return (float)this.getValueNow() / (float)this.getValueMax() * 100f;
    }
    
    public int getValueNow()
    {
        return this.now;
    }
    
    public int getValueMax()
    {
        return this.max;
    }
    
    public boolean isEmpty()
    {
        if(this.now < 1) {return true;}
        return false;
    }
    
    public boolean isFull()
    {
        if(this.now == this.max) {return true;}
        return false;
    }
    
}