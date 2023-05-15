package com.rohithsekar.myMappack;
import java.util.HashMap;

//HashMap class implements Map Interface, which is used for implementing key-value pairs
public class MyHashMap
{
    public static HashMap<String, String> languages = new HashMap<>();

    public static void initialize_languages()
    {
        languages.put("England", "English");
        languages.put("Ireland", "Irish");
        languages.put("Japan", "Japanese");
        languages.put("Deutschland", "Deutsch");
    }

    public static void main(String[] args)
    {
        initialize_languages();
        languages.put("France", "French");
        System.out.println(languages.size());
        for (String i : languages.keySet()) //keySet method prints values
        {
            System.out.println(i);
        }
        for (String i : languages.values())
        {
            System.out.println(i);
        }

        /*
        In Java, the Map interface provides a nested interface called Entry. Each entry in a map
        consists of a key and its corresponding value. The Entry interface defines methods to access
       and manipulate these key-value pairs.When iterating over a map using a loop, such as a for-each loop
       you can use the entrySet() method of the map to obtain a set of entries.
       Each entry can then be accessed using the getKey() and getValue() methods of the Entry interface.
         */
        for (HashMap.Entry<String, String> entry : languages.entrySet()) //entrySet method obtains a set of key-value pair
        {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }


    }
}