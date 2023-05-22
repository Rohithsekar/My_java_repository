package com.rohithsekar.Threads;

//In the SimpleThread and Priority_Thread examples, we extended Thread class and made a class as a Thread.
//There is another way to create and start threads using an interface called the Runnable which is implemented by
//the Thread class. The Runnable is a functional interface, which has one abstract method called run. There is no
//start method in Runnable interface. However, we can use the start method as follows: First, we create an instance of the
// class implementing Runnable and assign it to Runnable variable.The Thread class has multiple overloaded
// versions of constructors, one of which  can take in a runnable object. We can pass the runnable variable into the
// Thread constructor (thus creating a thread object) and assign the resulting thread object to a thread variable.
// Now, since the thread object has the start method, we can invoke the start method on that thread object which has the
//information as to which implementing class to act upon.

class E implements Runnable
{
    @Override
    public void run()
    {
        for(int i =0; i<5; i++)
        {
            System.out.println("Herzlich Willkommen");
        }

    }
}

class F implements Runnable
{
    @Override
    public void run()
    {
        for(int i =0; i<5; i++)
        {
            System.out.println("Guten Tag");
        }
    }
}
public class Runnable_interface
{
    public static void main(String[] args)
    {

        //  create an instance of the class implementing Runnable and assign it to Runnable variable.
        Runnable runnable1 = new E();
        Runnable runnable2 = new F();

     // pass the runnable variable into the Thread constructor (thus creating a thread object) and assign the resulting
        // thread object to a thread variable.

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        //the thread object has the start method, we can invoke the start method on that thread object which has the
       //information as to which implementing class to act upon.

        t1.start();
        t2.start();

    }
}
