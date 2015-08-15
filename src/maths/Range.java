package maths;

public class Range
{
    private int min, max;
    
    public Range(int min, int max)
    {
        this.min = min;
        this.max = max;
    }
    
    public boolean contains(int number)
    {
        if(number >= this.min && number <= this.max) {return true;}
        return false;
    }
    
    public int getDifference()
    {
        return this.max - this.min;
    }
    
    public int getMin()
    {
        return this.min;
    }
    
    public int getMax()
    {
        return this.max;
    }
    
}