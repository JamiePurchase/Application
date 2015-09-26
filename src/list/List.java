package list;

import java.util.ArrayList;

public class List
{
    private ArrayList<String> listData;
    private String listDelimiter;
    
    public List(String data)
    {
        this.listDelimiter = "|";
        this.listData = this.createArray(data);
    }
    
    public List(String data, String delimiter)
    {
        this.listDelimiter = delimiter;
        this.listData = this.createArray(data);
    }
    
    private ArrayList<String> createArray(String data)
    {
        ArrayList<String> array = new ArrayList();
        return array;
    }
    
    public String get(int pos)
    {
        return this.listData.get(pos);
    }
    
    private String getDelimiter()
    {
        return "|";
    }
    
    private int getListSize(String data)
    {
        int count = 0;
        int index = 0;
        while(index != -1)
        {
            index = data.indexOf(this.getDelimiter(), index);
            if(index != -1)
            {
                count ++;
                index += this.getDelimiter().length();
            }
        }
        return count;
    }
    
    public int size()
    {
        return this.listData.size();
    }
    
    public ArrayList<String> toArray()
    {
        return this.listData;
    }
    
}