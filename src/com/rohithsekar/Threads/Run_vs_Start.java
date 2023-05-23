package com.rohithsekar.Threads;

//When run method is actually called on a thread, it is like an ordinary method call to run method
//and no new thread is created. The main thread (or the thread in which the caller thread is created)
//is the one executing the run() method. No Multi-threading is involved.
//Only when the start method is used , the actual created thread executes the run method.

//We can't call start twice on the same thread, as it is already started. It will be in Runnable state, once
//it is started. Calling it so, will raise an IllegalStateException. On the other hand, we can call run multiple
// times on the thread, to bring it into running  state. (as it is an ordinary method call)


public class Run_vs_Start extends Thread
{
    public void run()
    {
        //This will print main thread as no new thread is created
        System.out.println("THe thread executing this is " + Thread.currentThread().getName() + " " + Thread.currentThread().getContextClassLoader());
    }

    public static void main(String[] args)
    {
        Run_vs_Start runVsStart = new Run_vs_Start();
        runVsStart.run();
        runVsStart.checkAccess();
    }
}

class Start extends Thread
{
    public Start(String name)
    {
        super(name);
    }
    public void run()
    {
        //This will print the calling thread name
        System.out.println("THe thread executing this is " + Thread.currentThread().getName());
    }

    public static void main(String[] args)
    {
        Start t = new Start("myThread"); //assigning a name to the thread
        t.start();
    }
}
