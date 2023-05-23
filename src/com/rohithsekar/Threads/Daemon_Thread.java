package com.rohithsekar.Threads;

/*
Daemon thread in Java is a low-priority thread that runs in the background to perform tasks such as garbage
collection. Daemon thread in Java is also a service provider thread that provides services to the user thread.
Its life depends on the mercy of user threads i.e. when all the user threads die, JVM terminates this thread
automatically. In simple words, we can say that it provides services to user threads for background supporting
tasks. It has no role in life other than to serve user threads.

Example of Daemon Thread in Java: Garbage collection in Java (gc), finalizer, etc.

Properties of Java Daemon Thread:

 ->They can not prevent the JVM from exiting when all the user threads finish their execution.
 ->JVM terminates itself when all user threads finish their execution.
 ->If JVM finds a running daemon thread, it terminates the thread and, after that, shutdown it. JVM does not
   care whether the Daemon thread is running or not.
 ->It is an utmost low priority thread.

Default Nature of Daemon Thread:
By default, the main thread is always non-daemon but for all the remaining threads, daemon nature will be
inherited from parent to child. That is, if the parent is Daemon, the child is also a Daemon and if the parent
is a non-daemon, then the child is also a non-daemon.

Note: Whenever the last non-daemon thread terminates, all the daemon threads will be terminated automatically.
 */

public class Daemon_Thread extends Thread
{
    // Java program to demonstrate the usage of
    // setDaemon() and isDaemon() method.

    public Daemon_Thread(String name)
    {
        //set the name of the thread to this name in the parameterized Thread constructor (which only has
        // the String parameter) (child class(Daemon_Thread) uses the parent(Thread) class' parameterized constructor)
        super(name);
    }

        public void run()
        {
            // Checking whether the thread is Daemon or not
            if(Thread.currentThread().isDaemon())
            {
                System.out.println(getName() + " is Daemon thread");
            }

            else
            {
                System.out.println(getName() + " is User thread");
            }
        }

        public static void main(String[] args)
        {

            Daemon_Thread t1 = new Daemon_Thread("t1");
            Daemon_Thread t2 = new Daemon_Thread("t2");
            Daemon_Thread t3 = new Daemon_Thread("t3");

            // Setting user thread t1 to Daemon
            t1.setDaemon(true);

            //Note : If you wish to set a thread as Daemon-thread, set its daemon status to true before
            //it is started, else you will receive IllegalStateException
            //Setting a thread's daemon status to true, may raise a SecurityException, if the current
            //thread cannot modify this thread.

            // starting first 2 threads
            t1.start();
            t2.start();

            // Setting user thread t3 to Daemon
            t3.setDaemon(true);
            t3.start();


        }
    }


