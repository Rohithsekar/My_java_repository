package com.rohithsekar.myOOPSpack;

abstract class Abstraction
{
    //abstract class can contain both abstract and regular methods
    public abstract void animalSound(); //abstract method does not have any body

    public void sleep()  //regular method has body
    {
        System.out.println("Zzz");
    }
}

class Duck extends Abstraction
{
    public void animalSound()  //
    {
        System.out.println("Duck quacks");
    }
}

class Main2
{
    public static void main(String[] args)
    {
        Abstraction duck = new Duck();
        duck.animalSound();
    }
}
