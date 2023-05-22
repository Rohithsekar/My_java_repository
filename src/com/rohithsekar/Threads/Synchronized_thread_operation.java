package com.rohithsekar.Threads;

/* Mutation (changing values of variables) and Multi-threads are dangerous combinations. Because, when you mute the
value of a variable inside a run method, which is what a thread object calls upon, there might be cases where there are
multiple threads called on the same run method, and if the threads mute the value of a mutable type simultaneously,
the resulting value or behaviour can be unpredictable. To ensure this from not happening, the classes implementing
the Runnable interface should be made thread-safe. That is, at any one particular time instance, only one thread can
access and mute the value. Java provides the key-word 'synchronized' to prevent multiple threads from executing
simultaneously.
 */

class Counter implements Runnable
{
    int count = 0;

    //To ensure the expected output, you need to synchronize the critical section of code where the shared variable is
    //accessed and modified. One way to achieve this is by using the synchronized keyword. By synchronizing the run()
    // method or the critical section within it, you ensure that only one thread can access and modify the shared
    // variable at any given time.
    public synchronized void run() //sync
    {

        for(int i =0; i<2; i++)
        {
            System.out.println("Current Thread: "  + Thread.currentThread().getName());
            count++;
        }
    }
}


public class Synchronized_thread_operation extends Counter
{
    public static void main(String[] args)
    {
        Counter r = new Counter();

        //Two threads operating on the same run method.
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();

        /*The final print statement will print the count value at any arbitrary time before the threads have finished
         their  execution and the value will not be the desired output value. This happens because the main is also a
         thread (unbeknownst to us) and it also gets the time to execute without waiting for the other threads to finish
         executing. We say the threads have not joined the main thread before main thread has terminated. To prevent
         this from happening, the thread has to join the main thread before ultimately the main thread terminates.

         The join() method  allows one thread to wait until another thread completes its execution. If t is a Thread
         object whose thread is currently executing, then t.join() will make sure that t is terminated before the next
         instruction is executed by the program.

         */
        try
        {
            System.out.println("Current Thread: "  + Thread.currentThread().getName());
            t1.join(); //WAIT UNTIL THREAD t1 HAS TERMINATED
        }
        catch (InterruptedException e)
        {
           System.out.println("Exception happened at Thread: "  + Thread.currentThread().getName());
        }

        t2.start();

        try
        {
            System.out.println("Current Thread: "  + Thread.currentThread().getName());
            t2.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Exception happened at Thread: "  + Thread.currentThread().getName());
        }
        System.out.println(r.count);

        /* The try-catch blocks are necessary since while waiting for a thread to terminate, the waiting thread may be
        interrupted by other threads and thus raises an InterruptedException:
        The InterruptedException is thrown when a thread is in a blocking or waiting state (such as waiting in a join(),
        sleep(), or wait()) and another thread interrupts it by invoking the interrupt() method on the blocked thread.
        In our case, the InterruptedException can occur if the main thread is waiting in the join() method, and another
        thread interrupts the main thread by invoking interrupt() on it. The interrupt can come from any other thread,
        not necessarily the t1 thread itself.

        To clarify, the join() method is used to wait for a thread to complete its execution. It does not interrupt the
        thread that calls join(). Instead, if the thread waiting in join() (in this case, the main thread) is
        interrupted by another thread, it will throw an InterruptedException.

        In summary, the InterruptedException occurs when a thread is waiting or blocking and another thread interrupts
        it by invoking the interrupt() method. The join() method is not responsible for the interruption itself; it is
        the interrupting thread that causes the exception to be thrown.

        Note that it is only a "may" . That is , it is not 100% certain that the exception will occur for sure. It is
        only for some corner cases where it could potentially arise, the try catch block is required and thus we
        generally enclose the join() method within a try block to be on the safer side, should an InterruptedException
        should arise.

        We can know that the exception didn't arise by checking in the output if our catch block statement has been
        executed.
         */


    }

}
