package com.rohithsekar;
import java.util.*;

public class mySetpack
{
    //If we create a collection outside, of main method, it is treated as a member of the class. Hence
    //if we need to operate on the collection, we need to create an instance of the class to be able to use it.
    //Hence it is recommended to create the collection object inside the main method.
    public static void main(String[] args)
    {
        //A HashSet uses a Hashtable in its implementation. It is fast, but it does not maintain insertion order.
        Set<String> names = new HashSet<>();  //we can also pass arguments to  the constructor, if we want to.
        names.add("Jesse");
        names.add("Heisenberg");
        names.add("Skyler");
        names.add("Gus");

        names.forEach(System.out::println); //a method reference

        //duplicates can easily be removed from a list using a Set.
        List<String> mylist = new ArrayList<>();
        mylist.add("David");
        mylist.add("Jake");
        mylist.add("David");
        mylist.add("Jane");
        names.addAll(mylist); //adding the list containing duplicates to a set, will allow one of many copies.
        System.out.println(names.size());
        System.out.println(names);
        Iterator<String> myiterator = names.iterator();
        while(myiterator.hasNext())
        {
            System.out.println(myiterator.next());
        }

        //The Set is akin to the List, except that they don't allow duplicates. There are other two implementations
        //of Set interface, the TreeSet , which maintains the natural order of the elements in the Set, which uses
        //a Tree data structure in its implementation
        //LinkedHashSet is another implementation which preserves the order in which the elements are added.
        //It internally uses a linked lsit



    }
}
