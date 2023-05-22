package com.rohithsekar.Threads;


class C extends Thread
{
    public void run()
    {
        for (int i = 0; i <= 100; i++)
        {
            System.out.println("Halo"); //prints this
            try
            {
                sleep(10); //wait for 10 seconds
            } catch (InterruptedException e) //since the thread's normal execution is interrupted, it throws an Interrupted exception
            {
                throw new RuntimeException(e);
            }
        }
    }
}

class D extends Thread
{
    public void run()
    {
        for (int i = 0; i <= 100; i++)
        {
            System.out.println("Tschuss");
            try
            {
                sleep(10);
            } catch (InterruptedException e) //since the thread's normal execution is interrupted, it throws an Interrupted exception
            {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Priority_Thread
{
    public static void main(String[] args)
    {
        A a = new A();
        C c = new C();
        D d = new D();

        //the getPriority method of the Thread class prints the priority level of the thread object.
        //By default, all threads, have a normal priority level of 5.
        //There are constants defined in the Thread class, which we can pass as arguments to the setPriority method
        //which will assign a priority level to a thread.

        System.out.println(c.getPriority()); //outputs 5 , normal priority level
        //Constants defined in Threads class. Max=10, Min=1, Normal=5
        c.setPriority(Thread.MAX_PRIORITY);
        d.setPriority(Thread.MIN_PRIORITY);
        a.setPriority(Thread.NORM_PRIORITY);

        //Although we set priorities, it is upto the time scheduler to schedule time for the threads.
        //The time scheduler have algorithm optimized for executing threads on different criteria.
        //It schedules time for threads based on the specific algorithmic implementation.

        c.start();
        d.start();
        a.start();
    }
}
