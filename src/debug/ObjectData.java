package debug;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ObjectData
{
    private Object object;
    private ArrayList<String> data;
    private int width;
    
    public ObjectData(Object object)
    {
        this.object = object;
        this.data = new ArrayList();
        this.width = 0;
        addObject();
        addConstructor();
        addField();
        addMethod();
    }
    
    private void addConstructor()
    {
        Constructor[] constructor = this.object.getClass().getConstructors();
        addLine("CONSTRUCTORS");
        addLine();
        for(int x = 0; x < constructor.length; x++)
        {
            addLine(constructor[x].getName());
        }
        addLine();
    }
    
    private void addField()
    {
        Field[] field = this.object.getClass().getDeclaredFields();
        addLine("PROPERTIES");
        addLine();
        for(int x = 0; x < field.length; x++)
        {
            addLine(this.getCharacters(this.getVisibility(field[x].toString()), 11, " ") + this.getCharacters(field[x].getName(), 26, " ") + field[x].getType());
        }
        addLine();
    }
    
    private void addLine()
    {
        addLine("");
    }
    
    private void addLine(String line)
    {
        this.data.add(line);
        if(line.length() > width) {this.width = line.length();}
    }
    
    private void addMethod()
    {
        Method[] method = this.object.getClass().getMethods();
        addLine("METHODS");
        addLine();
        for(int x = 0; x < method.length; x++)
        {
            addLine(this.getCharacters(this.getVisibility(method[x].toString()), 11, " ") + this.getCharacters(method[x].getName(), 26, " ") + this.getCharacters("" + method[x].getReturnType(), 56, " ", true) + " ");
            // NOTE: will not line up properly because the maxmimum width has not been decided yet
            // NOTE: instead of spaces, we should say that string at that column (column width is dynamic to fit text but same all the way down)
        }
        addLine();
    }
    
    private void addObject()
    {
        addLine();
        addLine(this.object.getClass().getSimpleName().toUpperCase());
        addLine();
        addLine("Class:     " + this.object.getClass().getName());
        addLine("Super:     " + this.object.getClass().getSuperclass().getClass().getName());
        addLine();
    }
    
    private void drawLine(String line)
    {
        if(line.length() > 0) {System.out.println("| " + line + getCharacters(this.width - line.length() - 3, " ") + "|");}
        else {System.out.println("+" + this.getCharacters(this.width - 2, "-") + "+");}
    }
    
    private String getCharacters(int count, String repeat)
    {
        String chars = "";
        for(int x = 0; x < count; x++) {chars += repeat;}
        return chars;
    }
    
    private String getCharacters(String text, int count, String repeat)
    {
        return getCharacters(text, count, repeat, false);
    }
    
    private String getCharacters(String text, int count, String repeat, boolean reverse)
    {
        if(reverse) {return getCharacters(count - text.length(), repeat) + text;}
        return text + getCharacters(count - text.length(), repeat);
    }
    
    private String getVisibility(String description)
    {
        System.out.println("OBJECT DATA -> GET VISIBILITY (" + description + ")");
        if(description.contains("private")) {return "private";}
        if(description.contains("protected")) {return "protected";}
        if(description.contains("public")) {return "public";}
        return "unknown";
    }

    public void output()
    {
        for(int x = 0; x < this.data.size(); x++)
        {
            drawLine(this.data.get(x));
        }
    }

}