package time;

public class Timestamp
{
    private long timestampMS;
    
    public Timestamp()
    {
        this.timestampMS = System.currentTimeMillis();
    }
    
    public Timestamp(long ms)
    {
        this.timestampMS = ms;
    }
    
}