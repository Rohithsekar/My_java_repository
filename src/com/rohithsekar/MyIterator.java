package com.rohithsekar;
import java.util.*;

public class MyIterator
{
    public static ArrayList<String> fruits = new ArrayList<>();


    public static void initialize_fruits()
    {
        fruits.add("Pomegranate");
        fruits.add("Orange");
        fruits.add("Peach");
    }


    public static void main(String[] args)
    {
        initialize_fruits();
        Iterator<String> it = fruits.iterator();

        System.out.println(it.next()); //prints the first element in the collection

        //looping through the collection
        while(it.hasNext())
        {
            System.out.println(it.next());
        }

        //Iterator is designed to modify elements in the collection as they iterate through the
        //collection. remove method is one such method.

        //Since ArrayList class implements List interface, a list variable can hold an ArrayList object

        List<Integer> numbers = new ArrayList<>();
        numbers.add(12);
        numbers.add(8);
        numbers.add(2);
        numbers.add(23);
        Iterator<Integer> myit = numbers.iterator();
        while(myit.hasNext()) {
            Integer i = myit.next();
            if(i < 10) {
                myit.remove();
            }
        }
        System.out.println(numbers);
    }
}


