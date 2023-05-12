package com.rohithsekar.mypack;
/* Polymorphism means "many forms". The inherited classes can have their own implementation of the parent class
methods, overriding the parent implementation of the method. The parent class method tells the child class what to do,
but not how to do, which the child class does in its own way.
 */
public class Polymorphism
{
    public void animalSound()
    {
        System.out.println("Animal makes sounds.");
    }
}
class Pig extends Polymorphism
{
    public void animalSound()
    {
        System.out.println("The pig oinks");
    }
}

class Dog extends Polymorphism
{
    public void animalSound()
    {
        System.out.println("The dog barks");
    }
}

class Cat extends Polymorphism
{
    public void animalSound()
    {
        System.out.println("The cat meows");
    }
}

class Animalsound
{
    public static void main(String[] args)
    {
        Polymorphism mypolymorphism = new Polymorphism();
        Pig myPig = new Pig();
        Dog myDog = new Dog();
        Cat myCat = new Cat();
        mypolymorphism.animalSound();
        myPig.animalSound();
        myDog.animalSound();
        myCat.animalSound();

    }
}