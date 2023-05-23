package com.rohithsekar.Threads;

/*
The wait() and notify() are methods of the Object class. They were introduced to part ways with polling, which
is the process of repeatedly checking for a condition to be fulfilled. Polling wastes CPU resources considerably,
hence it is not preferred.

wait() Method:
wait() method is a part of java.lang.Object class. When wait() method is called, the calling thread stops its
execution until notify() or notifyAll() method is invoked by some other Thread.

The wait() method has 3 variations:

1. wait(): This is a basic version of the wait() method which does not take any argument. It will cause the
thread to wait till notify is called.
public final void wait()

2. wait(long timeout): This version of the wait() method takes a single timeout argument. It will cause the
thread to wait either till notify is called or till timeout (One which occurs earlier).
public final void wait(long timeout)

3. wait(long timeout, int nanoseconds): This version of the wait() method takes a timeout argument as well as a
nanosecond argument for extra precision.
public final void wait(long timeout, int nanoseconds)

notify() Method:
The notify() method is defined in the Object class, which is Java’s top-level class. It’s used to wake up only
one thread that’s waiting for an object, and that thread then begins execution. The thread class notify() method
is used to wake up a single thread.
public final void notify()

When wait() is called on a thread holding the monitor lock, it surrenders the monitor lock and enters the
waiting state. There can be multiple threads in the waiting state at a time.

When notify() is called on a thread holding the monitor lock, it symbolizes that the thread is soon going
to surrender the lock. One of the waiting threads is randomly selected and notified about the same. The notified
thread then exits the waiting state and enters the blocked state where it waits till the previous thread has
given up the lock and this thread has acquired it. Once it acquires the lock, it enters the runnable state where
it waits for CPU time, and then it starts running.


*/
// Java Program to demonstrate usage of wait() and notify()

class demo
{
    // variable to check if part1 has returned
    // volatile used to prevent threads from
    // storing local copies of variable
    volatile boolean part1done = false;

    // method synchronized on this
    // i.e. current object of demo
    synchronized void part1()
    {
        System.out.println("Welcome to India");
        part1done = true;
        System.out.println("Thread t1 about to surrender lock");
        // notify the waiting thread, if any
        notify();
    }

    // method synchronized on this
    // i.e. current object of demo
    synchronized void part2()
    {
        // loop to prevent spurious wake-up
        while (!part1done)
        {
            try
            {
                System.out.println("Thread t2 waiting");
                // wait till notify is called
                wait();
                System.out.println("Thread t2 running again");
            }
            catch (Exception e)
            {
                System.out.println(e.getClass());
            }
        }
        System.out.println("Do visit Taj Mahal");
    }
}

public class Wait_and_notify
{
    public static void main(String[] args)
    {

        // Make an instance of demo class
        demo obj = new demo();

        // Thread t1 will call part1()

        //Anonymous inner class is created to avoid creating a separate class , just for the sake of
        //implementing an interface.

        //When a new Thread object is created, it goes to the constructor. If an object of the class implementing the
        // Runnable interface is passed as the constructor argument, it goes to the class implementing Runnable and
        // then the command comes to the closing parenthesis of the constructor. Since we create an anonymous class
        // implementing Runnable, we have to provide the class body before we can enclose the Thread object constructor.
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                obj.part1();
            }
        });


        /*
        In Java, when you create an anonymous class, the opening brace `{` of the class definition must follow
        the class instantiation immediately after the closing parenthesis of the constructor arguments. This
        syntax is required by the Java language specification. The reason for this syntax is that the opening
        brace `{` denotes the beginning of the class body, where you define the implementation of methods or
        add any additional members to the anonymous class. Placing the closing parenthesis immediately after
        `new Runnable()` would imply that there are no additional members or implementation for the anonymous
        class, which is not the intended behavior.

        To adhere to the Java syntax rules, you need to place the opening brace `{` after the class
        instantiation and then proceed with defining the class body within the braces.

        In the example provided, the correct syntax is to have the opening brace immediately following
       `new Runnable()`, indicating the beginning of the anonymous class definition, and then you define the
       `run()` method implementation within the class body.
         */

        // Thread t2 will call part2()

        //Anonymous class, written to avoid creating a class that implements Runnable
        Thread t2 = new Thread(new Runnable()   //An anonymous class is created implementing the Runnable interface
        {
            //Now the opening brace of the anonymous class should immediately follow the anonymous class
            // instantiation before the closing paranthesis of the
            public void run()
            {
                obj.part2();
            }
        });

        // Start t2 and then t1
        t2.start();
        t1.start();
    }
}

/*

When a thread is notified and awoken from the wait() method, it resumes executing the code from where it left.
When a thread is notified and awoken from the wait() method, it does not bypass the while loop condition.
It needs to recheck the condition after being notified to guard against spurious wake-ups. Spurious wake-ups
can occur even without an explicit call to notify(), so it's essential to verify the condition before
proceeding.

In the provided program, the while loop in the part2() method is used to prevent spurious wake-ups and ensure
the thread waits until the condition (part1done) is satisfied. If a thread wakes up from the wait() call due
to a spurious wake-up, it rechecks the condition within the while loop before proceeding further. If the
condition is still not satisfied, the thread goes back to waiting by executing the wait() method again.

Here's a breakdown of the flow:

The t2 thread starts and enters the part2() method.
It encounters the while loop and evaluates the condition (!part1done). If the condition is true
(indicating that part1() has not completed), the thread enters the wait() state, suspending its execution.
While in the wait() state, the t1 thread executes and eventually completes the part1() method.
After part1() completes, it calls notify() to awaken any threads waiting on the same object (in this case, obj).
The t2 thread is now eligible to run again and leaves the wait() state.
However, before proceeding further, the thread rechecks the condition within the while loop (!part1done).
If the condition is false, indicating that part1() has completed, the thread continues execution from the point
 immediately after the wait() call. It prints "Thread t2 running again" and proceeds with the rest of the method.
If the condition is still true, the thread goes back to the wait() state and waits for another notification.
This process repeats until the condition is finally satisfied, and the thread can proceed with the remaining code in the part2() method.
By including the while loop and rechecking the condition after waking up from wait(), the program guards
against spurious wake-ups and ensures that the thread waits until the desired condition is met.
 */
