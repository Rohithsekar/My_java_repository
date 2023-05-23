package com.rohithsekar.Threads;

/*
In Java, each thread has its own local memory or cache, which may store a copy of variables that are accessed frequently
by the thread. This local cache is used to optimize performance by reducing the need to access the main memory for every
variable access.

Without any synchronization mechanisms, such as the volatile keyword or locks, there is no guarantee that changes made
to a variable by one thread will be immediately visible to other threads. Each thread may continue using its locally
cached value of the variable, which can lead to inconsistencies and unexpected behavior.

In the case of the flag status, if it is not changed by any other thread and synchronization mechanisms are not used,
the worker thread may continue using its local cached value of the flag. As a result, even if the flag is updated by
another thread, the worker thread may not immediately see the change and may continue using the old cached value.

To ensure proper visibility of changes to shared variables across threads, synchronization mechanisms such as the
volatile keyword can be used. By marking a variable as volatile, the Java memory model guarantees that the variable's
value will always be read from the main memory, bypassing the local cache of each thread. This ensures that changes made
by one thread to the variable are visible to other threads, eliminating inconsistencies and ensuring thread-safety.
 */

class Labourer implements Runnable
{
    private volatile boolean haltFlag = false;

    public void run()
    {
        while (!haltFlag)
        {
            System.out.println("Hello");
        }
        System.out.println("Finished executing run.");
    }

    public void halt()
    {
        haltFlag = true;
    }
}
public class Volatile
{
    public static void main(String[] args)
    {
        Labourer labourer = new Labourer();
        Thread t = new Thread(labourer);
        t.start();

        try
        {
            Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            System.out.println("Exception caught");
        }

        labourer.halt();
    }
}
