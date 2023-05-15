package com.rohithsekar.myArraypack;
import java.util.ArrayList;
import java.util.Collections;

public class ArrayList_$_collections
{
    public static ArrayList<String > cars = new ArrayList<>(); //(static)->belongs to the class , not instances of the class

    public static void initialize_cars()
    {
        cars.add("BMW");
        cars.add("Porsche");
        cars.add("Lexus");
    }

    public static void main(String[] args)
    {
        initialize_cars();
        cars.add("Mercedes");
        System.out.println(cars.size());
        for(String i : cars)
        {
            System.out.println(i);
        }
        cars.set(2,"Toyota");
        cars.remove(3);
        System.out.println("After setting the third element to Toyota and deleting Mercedes the list looks like:");
        for(String i : cars)
        {
            System.out.println(i);
        }
        System.out.println("After modifications to the list, its size now is " +  cars.size());
        Collections.sort(cars);
        System.out.println("After sorting the list, it looks like:");
         for(String i : cars)
         {
          System.out.println(i);
          }



    }

}
