package maths;

import java.util.Random;

public class Maths
{
    
    public static float percent(int current, int maximum)
    {
        return (current / maximum) * 100;
    }

    public static int randomInt(int range)
    {
        return randomInt(0, range);
    }
    
    public static int randomInt(int min, int max)
    {
        return new Random().nextInt((max - min) + 1) + min;
    }

}