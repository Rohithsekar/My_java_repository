package com.rohithsekar.Threads;


class A extends Thread   //A is no ordinary class. It is a thread now
{
    public void run()
    {
        for (int i = 0; i <= 100; i++)
        {
            System.out.println("hi");
        }
    }
}

class B extends Thread //B is no ordinary class. It is a thread now
{
    public void run()
    {
        for (int i = 0; i <= 100; i++)
        {
            System.out.println("Hello");
        }
    }
}



public class SimpleThread
{
    public static void main(String[] args)
    {
        A a = new A(); //creating a thread object and assigning it to a thread variable a
        B b = new B(); //creating a thread object and assigning it to a thread variable b
        a.start(); //bringing the thread to action
        b.start(); //bringing the thread to action

        //When you run the main method, you will see the two threads getting executed concurrently.
        //The time scheduler splits the execution time for the two threads. When one thread is actively
        //executing, the other thread waits for its time. But, we have no control over the scheduler to
        //dictate time span for a thread execution.
    }

}
