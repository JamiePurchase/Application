package shapes;

import java.awt.Rectangle;

public class RectangleTools
{
    
    public static Rectangle inner(Rectangle original, int inner)
    {
        return new Rectangle(original.x + inner, original.y + inner, original.width - (inner * 2), original.height - (inner * 2));
    }
    
}