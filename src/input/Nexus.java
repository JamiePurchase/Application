package input;

import java.awt.Point;
import java.awt.Polygon;

public class Nexus
{
    private Polygon area;

    public Nexus(Polygon polygon)
    {
        this.area = polygon;
    }

    public boolean intersect(Point point)
    {
        if(this.area.contains(point)) {return true;}
        return false;
    }

}