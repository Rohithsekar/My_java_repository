package com.rohithsekar.myStreampack;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyStream
{
    static List<Integer> numbers = Arrays.asList(5,3,4,1,5);

    //Here we are going to map one collection into another collection defined by the relation x->x*x(squaring)
    //The mapped collection is a List collection, where duplicates are allowed.

    static List<Integer> square = numbers.stream().map(x->x*x).collect(Collectors.toList());
    // the . operator here refers to sequentially operating on the previous operation
    //stream() is a method defined in the Collection interface, which is the root interface of the
    // Java Collections Framework and hence Streams can operate on collections.
    // The map is an intermediate operation while collect is a terminal operation. They are defined in the
    // Stream interface which belongs to the package java.util

    //Here we are going to map one collection into another collection defined by the relation x->x*x(squaring)
    //The mapped collection is a Set collection, where duplicates are not allowed.
   static Set<Integer> setsquare = numbers.stream().map(x->x*x).collect(Collectors.toSet());

   static List<String> names = Arrays.asList("Dog", "Cat", "Lion");

   //filter operation
   static Set<String> set_names = names.stream().filter(s->s.startsWith("D")).collect(Collectors.toSet());

   //sorted operation
   static List<Integer> sorted_set_squares = setsquare.stream().sorted().collect(Collectors.toList());




    public static void main(String[] args)
    {
        System.out.println(square); //prints the collection
        System.out.println(setsquare); //prints the collection
        System.out.println(set_names);
        System.out.println(sorted_set_squares);

        //Terminal operations other than collect
        Integer a = 2;
        numbers.stream().map(x->x/a).forEach(y->System.out.println(y));
        List<Integer> mynum = Arrays.asList(5,6,7);
        mynum.stream().reduce(0,(x,i)->x+i);

    }
}
