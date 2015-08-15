package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class NetworkService
{
    
    public static ArrayList<String> request(URL url)
    {
        ArrayList<String> data = new ArrayList();
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String webLine;
            while((webLine = reader.readLine()) != null) {data.add(webLine);}
            reader.close();
        }
        catch(IOException ex) {System.out.println(ex);}
        return data;
    }

}