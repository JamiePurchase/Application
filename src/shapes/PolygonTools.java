package shapes;

import java.awt.Polygon;

public class PolygonTools
{
    
    public static Polygon translate(Polygon original, int alterX, int alterY)
    {
        int[] originalX = original.xpoints;
        int[] originalY = original.ypoints;
        int[] resultX = new int[originalX.length];
        int[] resultY = new int[originalY.length];
        for(int x = 0; x < originalX.length; x++) {resultX[x] = originalX[x] + alterX;}
        for(int y = 0; y < originalY.length; y++) {resultY[y] = originalY[y] + alterY;}
        return new Polygon(resultX, resultY, resultX.length);
    }
    
}