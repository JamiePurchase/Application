package gfx;

import app.Engine;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Drawing
{
    private static Object Colours;
    public static void drawImageOpaque(Graphics g, BufferedImage image, int posX, int posY, float alpha)
    {
        // Set Opacity
        Graphics2D g2D = (Graphics2D) g;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2D.setComposite(composite);

        // Draw Image
        g2D.drawImage(image, posX, posY, null);

        // Clear Opacity
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2D.setComposite(composite);
    }
    
    public static void drawRect(Graphics g, Rectangle rect)
    {
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    public static void drawRect(Graphics g, int posX, int posY, int posW, int posH)
    {
        g.drawRect(posX, posY, posW, posH);
    }
    
    public static void drawRect(Graphics g, Rectangle rect, Color color)
    {
        g.setColor(color);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    public static void drawRect(Graphics g, Rectangle rect, String colour)
    {
        drawRect(g, rect, Colour.getColour(colour));
    }
    
    public static void drawRect(Graphics g, Rectangle rect, int width, String colour)
    {
        g.setColor(Colour.getColour(colour));
        for(int x = 0; x < width; x++)
        {
            drawRect(g, rect.x + x, rect.y + x, rect.width - (x * 2), rect.height - (x * 2));
        }
    }
    
    public static void fadeRect(Graphics g, int posX, int posY, int sizeX, int sizeY)
    {
        Drawing.drawImageOpaque(g, Drawing.resize(Drawing.getImage("interface/fadeBlack.png"), sizeX, sizeY), posX, posY, 0.5f);
    }
    
    public static void fadeRect(Graphics g, int posX, int posY, int sizeX, int sizeY, Color colour, float opacity)
    {
        Drawing.drawImageOpaque(g, getImageRect(sizeX, sizeY, colour), posX, posY, opacity);
    }

    public static void fadeScreen(Graphics g)
    {
        Drawing.drawImageOpaque(g, getImage("interface/fadeBlack.png"), 0, 0, 0.75f);
    }
    
    public static void fadeScreen(Graphics g, Color color)
    {
        Drawing.drawImageOpaque(g, getImageRect(1366, 768, color), 0, 0, 0.75f);
    }
    
    public static void fadeScreen(Graphics g, String colour)
    {
        fadeScreen(g, Colour.getColour(colour));
    }
    
    public static void fillRect(Graphics g, Rectangle rect)
    {
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    public static void fillRect(Graphics g, Rectangle rect, Color color)
    {
        fillRect(g, rect.x, rect.y, rect.width, rect.height, color);
    }
    
    public static void fillRect(Graphics g, Rectangle rect, String colour)
    {
        fillRect(g, rect.x, rect.y, rect.width, rect.height, colour);
    }
    
    public static void fillRect(Graphics g, int posX, int posY, int posW, int posH, Color color)
    {
        g.setColor(color);
        g.fillRect(posX, posY, posW, posH);
    }
    
    public static void fillRect(Graphics g, int posX, int posY, int posW, int posH, String colour)
    {
        fillRect(g, posX, posY, posW, posH, Colour.getColour(colour));
    }
        
    public static BufferedImage flipImage(BufferedImage image)
    {
        AffineTransform transform1 = AffineTransform.getScaleInstance(-1, 1);
        transform1.translate(-image.getWidth(null), 0);
        AffineTransformOp transform2 = new AffineTransformOp(transform1, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return transform2.filter(image, null);
    }
	
    public static BufferedImage getImage(String filepath)
    {
        return getImageFile(Engine.getResourcePath() + filepath);
    }
        
    public static BufferedImage getImageFile(String filepath)
    {
        BufferedImage image = null;
        try {image = ImageIO.read(new File(filepath));}
        catch (IOException e) {System.out.println(e + " " + filepath);}
        return image;
    }
    
    public static BufferedImage getImageRect(int wide, int high, Color colour)
    {
        BufferedImage imgNew = new BufferedImage(wide, high, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imgNew.createGraphics();
        g2d.setColor(colour);
        g2d.fillRect(0, 0, wide, high);
        g2d.dispose();
        return imgNew;
    }
        
    public static BufferedImage resize(BufferedImage imgOld, int newW, int newH)
    { 
        Image imgTemp = imgOld.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage imgNew = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imgNew.createGraphics();
        g2d.drawImage(imgTemp, 0, 0, null);
        g2d.dispose();
        return imgNew;
    }
    
    public static void rotate(Graphics g, Image image, ImageObserver observer)
    {
        // NOTE: encorporate the way in which the resize method creates and returns a BufferedImage
        AffineTransform identity = new AffineTransform();
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform trans = new AffineTransform();
        trans.setTransform(identity);
        trans.rotate( Math.toRadians(45) );
        g2d.drawImage(image, trans, observer);
    }
}