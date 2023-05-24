package com.rohithsekar.myOOPSpack;

/*
In Java, an abstract class can have various elements and features. Here is a list of what an abstract class can contain:

1. **Instance variables**: Abstract classes can have instance variables (fields) that hold data specific to each object
 created from the class.

2. **Methods**: Abstract classes can have both abstract and non-abstract methods.
   - Abstract methods: These are declared without an implementation and are meant to be overridden by the concrete
   (sub)classes that extend the abstract class.
   - Non-abstract methods: These have a complete implementation and can be directly used by objects of the abstract
   class. Subclasses can also choose to override non-abstract methods if needed.

3. **Constructors**: Abstract classes can have constructors to initialize the instance variables of the abstract class.
 These constructors are invoked when objects of the concrete subclasses are created.

4. **Access modifiers**: Abstract classes can specify access modifiers for their members (variables, constructors, and
methods), such as public, protected, or private, to control their visibility and accessibility.

5. **Static members**: Abstract classes can have static fields and methods that are associated with the class itself
rather than specific instances. Static members can be accessed directly using the class name, without creating an object
 of the class.

6. **Inheritance**: Abstract classes can extend other abstract classes or regular classes, forming an inheritance
hierarchy. They can serve as base classes for more specialized subclasses.

7. **Interfaces**: Abstract classes can implement interfaces. By implementing an interface, the abstract class inherits
the abstract methods of the interface and is responsible for providing their implementations.

8. **Final methods**: Abstract classes can have final methods, which cannot be overridden by subclasses. The final
keyword ensures that the method's implementation in the abstract class is the final implementation and cannot be
modified by subclasses.

9. **Abstract class extension**: An abstract class can also extend another abstract class. In this case, the subclass
would be responsible for providing implementations for all the abstract methods inherited from both the direct
superclass and any other abstract classes in the inheritance chain.

It's important to note that an abstract class cannot be instantiated directly, meaning you cannot create objects of the
abstract class itself. However, you can create objects of concrete subclasses that extend the abstract class.

Abstract classes provide a way to define common behaviors and structures for related classes while allowing subclasses
to provide specific implementations. They serve as a blueprint for concrete classes and help in achieving abstraction
and code reusability in object-oriented programming.
 */

abstract class Abstraction
{
    protected String name;
    protected int age;

    // Constructor
    public Abstraction(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    //abstract class can contain both abstract and regular methods
    public abstract void animalSound(); //abstract method does not have any body

    public void sleep()  //regular method has body
    {
        System.out.println("Zzz");
    }
}

class Duck extends Abstraction
{

    // Additional instance variable
    private String species;

    // Constructor
    public Duck(String name, int age, String species)
    {
        super(name, age);
        this.species = species;
    }
    public void animalSound()  //
    {
        System.out.println("Duck quacks");
    }

    // Additional instance method
    public void waddle()
    {
        System.out.println("Waddling!");
    }
}

class Main2
{
    public static void main(String[] args)
    {
        Duck duck = new Duck("Duckie", 14, "Asian");
        duck.animalSound();
        duck.sleep();
        duck.waddle();

    }
}
