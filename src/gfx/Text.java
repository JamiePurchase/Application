package gfx;

import app.Engine;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Text
{
    
    public static int getTextHeight(Graphics g, String text)
    {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(g.getFont()).getHeight();
    }
    
    public static int getTextWidth(Graphics g, String text)
    {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(g.getFont()).stringWidth(text);
    }
    
    public static int getTextWidth(Graphics g, String text, String font)
    {
        g.setFont(Fonts.getFont(font));
        return getTextWidth(g, text);
    }
    
    public static int getTextWidth(String text, String font)
    {
        Graphics g = Engine.getCanvas().getGraphics();
        g.setFont(Fonts.getFont(font));
        return getTextWidth(g, text);
    }

    public static void write(Graphics g, String write, int posX, int posY)
    {
        g.drawString(write, posX, posY);
    }

    public static void write(Graphics g, String write, int posX, int posY, String align)
    {
        if(align == "CENTER") {posX = posX - (getTextWidth(g, write) / 2);}
        if(align == "RIGHT") {posX = posX - getTextWidth(g, write);}
        g.drawString(write, posX, posY);
    }

    public static void write(Graphics g, String write, int posX, int posY, String align, String font, String colour)
    {
        g.setFont(Fonts.getFont(font));
        g.setColor(Colour.getColour(colour));
        write(g, write, posX, posY, align);
    }
    
    public static void writeRotate(Graphics g, String write, int posX, int posY, String font, String colour)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString(write, posX, posY);
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.PI / 4.0);
        g2d.setTransform(at);
        g2d.drawString(write, posX, posY);
        g2d.dispose();
        at.setToRotation(0);
        g2d.setTransform(at);
    }
    
    public static void writeShadow(Graphics g, String write, int posX, int posY)
    {
        writeShadow(g, write, posX, posY, "LEFT", Colour.getColour("TEXT_SHADOW"));
    }
    
    public static void writeShadow(Graphics g, String write, int posX, int posY, String align, Color shadow)
    {
        if(align == "CENTER") {posX = posX - (getTextWidth(g, write) / 2);}
        if(align == "RIGHT") {posX = posX - getTextWidth(g, write);}
        Color rgb = g.getColor();
        g.setColor(shadow);
        g.drawString(write, posX + 1, posY + 1);
        g.setColor(rgb);
        g.drawString(write, posX, posY);
    }
    
}