package debug;

import file.FileService;

public class Seneschal
{
    private static final String PATH = "C:/Users/Jamie/Documents/NetBeansProjects/Applications/jSeneschal/report/report.txt";

    public static void clear()
    {
        FileService.clearFile(PATH);
    }
    
    public static void write(String text)
    {
        FileService.saveFile(text, PATH);
    }

}