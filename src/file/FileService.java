package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileService
{
    
    public static void clearFile(String path)
    {
        saveFile("", path);
    }
    
    public static void createFolder(String path)
    {
        new File(path).mkdir();
    }
    
    public static boolean findFile(String path)
    {
        if(new File(path).exists()) {return true;}
        return false;
    }
    
    public static String getExtension(File file)
    {
        if(file.getName().lastIndexOf(".") != -1 && file.getName().lastIndexOf(".") != 0)
        {
            return file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return "";
    }
    
    public static String getFileName(File file)
    {
        return file.getName().substring(0, file.getName().length() - 7);
    }
    
    private static String getBreak()
    {
        return System.getProperty("line.separator");
    }
    
    public static ArrayList<File> getFolder(String path, String ext)
    {
        return getFolder(path, true, false, ext);
    }
    
    public static ArrayList<File> getFolder(String path, boolean getFiles, boolean getDirectories, String getExt)
    {
        ArrayList<File> result = new ArrayList();
        File[] folder = new File(path).listFiles();
        for(int f = 0; f < folder.length; f++)
        {
            if(getFiles && folder[f].isFile())
            {
                if(getExt != null)
                {
                    if(getExtension(folder[f]).equals(getExt)) {result.add(folder[f]);}
                }
                else {result.add(folder[f]);}
            }
            if(getDirectories && folder[f].isDirectory()) {result.add(folder[f]);}
        }
        return result;
    }
    
    public static ArrayList<String> loadFile(String path)
    {
        ArrayList<String> data = new ArrayList();
        boolean active = true;
        BufferedReader reader;
        String line;
        try
        {
            reader = new BufferedReader(new FileReader(path));
            try
            {
                while(active)
                {
                    line = reader.readLine();
                    if(line != null) {data.add(line);}
                    else {active = false;}
                }
                reader.close();
            }
            catch(IOException ex) {System.out.println(ex);}
        }
        catch(FileNotFoundException ex) {System.out.println(ex);}
        return data;
    }
    
    public static void saveFile(String data, String path)
    {
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(new FileWriter(path, false));
            writer.printf("%s" + "%n", data);
            writer.close();
        }
        catch (IOException ex) {System.out.println(ex);}
    }
    
    public static void saveFile(ArrayList<String> data, String path)
    {
        String condense = "";
        for(int x = 0; x < data.size(); x++)
        {
            condense += data.get(x);
            if(x < data.size() - 1) {condense += getBreak();}
        }
        saveFile(condense, path);
    }
    
    public static void setFileHidden(String path, boolean hidden)
    {
        try {setFileHidden(new File(path), hidden);}
        catch (InterruptedException ex) {System.out.println(ex);}
        catch (IOException ex) {System.out.println(ex);}
    }
    
    public static void setFileHidden(File file, boolean hidden) throws InterruptedException, IOException
    {
        String setting = "+H";
        if(!hidden) {setting = "-H";}
        Process p = Runtime.getRuntime().exec("attrib " + setting + " " + file.getPath());
        p.waitFor();
    }
    
}