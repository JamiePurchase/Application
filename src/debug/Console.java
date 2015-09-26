package debug;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Console
{
    
    public static void br()
    {
        System.out.println("");
    }
    
    public static void error(Exception e)
    {
        System.out.println(e.toString());
    }
    
    public static void print(String content)
    {
        System.out.println(content);
    }
    
    public static void print(ArrayList<String> content)
    {
        for(int x = 0; x < content.size(); x++)
        {
            System.out.println(content.get(x));
        }
    }
    
    public static void print(Rectangle rect)
    {
        System.out.println("rectangle: " + rect.x + ", " + rect.y + ". " + rect.width + ", " + rect.height);
    }
    
    public static void tableBr()
    {
        System.out.println("|                                                                                                  |");
    }
    
    public static void tableLine()
    {
        System.out.println("+--------------------------------------------------------------------------------------------------+");
    }
    
    public static void tableWrite(String content)
    {
        System.out.println("| " + content + tableContentSpace(96 - content.length()) + " |");
    }
    
    public static void tableWrite(String content1, String content2)
    {
        System.out.println("| " + content1 + tableContentSpace(96 - content1.length() - content2.length()) + content2 + " |");
    }
    
    private static String tableContentSpace(int amount)
    {
        String space = "";
        for(int x = 0; x < amount; x++) {space += " ";}
        return space;
    }
    
}