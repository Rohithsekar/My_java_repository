package com.rohithsekar.myListpack;
import java.util.LinkedList;
import java.util.Collections;

public class MyLinkedList
{
   public static LinkedList<Character> myCharacter = new LinkedList<>();

   public static void initilaize_myCharacter()
   {
       myCharacter.add('c');
       myCharacter.add('d');
   }

    public static void main(String[] args)
    {
        initilaize_myCharacter();
        myCharacter.add('e');
         for(Character i : myCharacter)
          {
          System.out.println(i);
          }
         myCharacter.addFirst('a'); //stacking
         myCharacter.addLast('y'); //enqueueing
          for(Character i : myCharacter)
           {
           System.out.println(i);
           }
          myCharacter.removeFirst();
        System.out.println("After using removeFirst method on the list object, the list looks like:");
         for(Character i : myCharacter )
          {
          System.out.println(i);
          }
         myCharacter.removeLast();
        System.out.println("After using removeLast method on the list object, the list looks like:");
          for(Character i : myCharacter)
           {
           System.out.println(i);
           }
          System.out.print("\n");
        System.out.println("Getting and printing the first and last elements of the list:");
        System.out.println(myCharacter.getFirst());
        System.out.println(myCharacter.getLast());
        myCharacter.addFirst('z');
        Collections.sort(myCharacter);
        System.out.println("After sorting, the list looks like:");
         for(Character i : myCharacter )
          {
          System.out.println(i);
          }
    }

}
