package com.rohithsekar.Lambdas;

/*
In Java, Lambda expressions basically express instances of functional interfaces (An interface with a
single abstract method is called a functional interface.The interface can have any number of static and default
methods, but it must contain exactly one abstract method). Lambda Expressions in Java are the same as  lambda
functions which are the short block of code that accepts input as parameters and returns a resultant  value.
Lambda Expressions are recently included in Java SE 8.

Functionalities of Lambda Expression in Java:
Lambda Expressions implement the only abstract function and therefore implement functional interfaces lambda
expressions are added in Java 8 and provide the below functionalities.

Enable to treat functionality as a method argument, or code as data.
A function that can be created without belonging to any class.
A lambda expression can be passed around as if it was an object and executed on demand.
 */

interface Animal_sound
{
    void sound();

    default void print()
    {
        System.out.println("Hello world");
    }
}

interface Add
{
    int sum(int x, int y);
}
public class Mylambda
{
    public static void main(String[] args)
    {
        //The implementation of the abstract interface is written as lambda expression and assigned to
        //the interface variable. Now calling the abstract method on the functional interface object, will
        //execute its implementation. The abstract method name is not required, since there is only one
        //abstract method in the functional interface. An explicit return statement is not required for
        //implementing a functional interface which has a return type, if the only line in the implementation
        //is the return statement.
       Animal_sound dog = () -> System.out.println("Barks");
       dog.sound();
       Add add = (x,y)-> x+y; //No explicit return statement required.
       System.out.println(add.sum(5,6));
    }
}
