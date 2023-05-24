package com.rohithsekar;

import java.io.Serializable;

/*
POJO stands for Plain Old Java Object(POJO) first endorsed by an American Software Engineer, Martin Fowler.
A Pojo class is just a regular class which doesn't have any particular requisites, but it should not
do the following things to be a POJO:

It should not extend any other classes or implement any interfaces. (It exists all by its own).
It should not have any annotations attached to it.

The POJO class can have any type of field, constructors and methods. (No restriction on access modifiers)

A JavaBeans is a kind of POJO but with some restrictions imposed:
It should implement the Serializable interface.
It should have no-args public constructor.(Default constructor)
Its attributes should be private and should have public getter, setter methods.
 */
public class POJO_$_JAVABEANS //a POJO class
{
    String name;
    int age;
    //This is all pretty much required to be a POJO class. But to be useful, it should contain some methods,
    //so that its objects can be used to do some tasks.

    /*
    A POJO class is typically a simple data container that encapsulates fields with their getters and setters, without
    any additional behavior or business logic. It is used to represent data and is often used in frameworks like
    JavaBeans or for data transfer between different layers of an application.
     */
}

class JavaBeans implements Serializable //A JavaBeans class
{
    private String name; //private field
    private int age; //private field

    //default constructor. Java provides one for us.

    public void setAge(int age)       //public setter method
    {
        this.age = age;
    }
    public void setName(String name)  //public setter method
    {
        this.name = name;
    }

    public int getAge()  //public getter method
    {
        return age;
    }

    public String getName()   //public getter method
    {
        return name;
    }

}