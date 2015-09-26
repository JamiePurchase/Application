/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;

public class ArrayTools
{
    
    // Appends all objects of an array list to another array list
    public static ArrayList<Object> combine(ArrayList<Object> original, ArrayList<Object> combine)
    {
        for(int x = 0; x < combine.size(); x++)
        {
            original.add(combine.get(x));
        }
        return original;
    }
    
    // Creates a new array list with an amount of objects from the end of another array list
    public static ArrayList<Object> groupEnd(ArrayList<Object> original, int count)
    {
        ArrayList<Object> result = new ArrayList();
        if(original.size() <= count) {return original;}
        for(int x = original.size() - count; x < original.size(); x++)
        {
            result.add(original.get(x));
        }
        return result;
    }
    
    // Creates a new array list with an amount of objects from the start of another array list
    public static ArrayList<Object> groupStart(ArrayList<Object> original, int count)
    {
        ArrayList<Object> result = new ArrayList();
        if(original.size() <= count) {return original;}
        for(int x = 0; x < count; x++)
        {
            result.add(original.get(x));
        }
        return result;
    }
    
}