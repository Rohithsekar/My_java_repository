package com.rohithsekar.Threads;

/*
  Here a new thread is created in the main thread, and it is started. However, its execution depends on the
  time scheduler. Now there are two threads running concurrently , without any synchronization. If the code for
  main thread waiting(Thread.sleep(1000)) is executed first, CPU time is given to the thread object, and it
  starts executing its run block, in each iteration, checking the flag status. Since its status is set locally
  to false, and does not change (until the halt block gets called), it keeps executing the code in the run
  block(since it is in a loop). However, after the main thread has awakened from the sleep mode, it resumes
  executing the code in the main thread alongside the thread object. Now, in the main thread, the Worker
  class' halt method is called on the worker object. The call goes to the halt method and the flag is set to
  true. However, since the Worker class uses the local state of the variable (the class cache memory), it
  may not get immediately updated and uses the pre-updated flag status to execute the run block. As a result,
  the run block gets executed even after the flag status is set to true via a call from the main thread.
  The updated status will be visible to the thread object, after the main thread has got its CPU time and
  changed the flag status. Now when the thread object get its CPU time, since the status change has happened
  before this, this change will now be visible to the worker class, and the flag status is changed in the
  while loop condition, and it exits.(See "happens before relationship" file in the same package.)
 */

class Worker implements Runnable
{
    private boolean stopFlag = false;

    public void run()
    {
        while (!stopFlag)
        {
            // Perform some work
            System.out.println("Hello world");
        }
        System.out.println("Worker thread stopped.");
    }

    public void halt()
    {
        stopFlag = true;
    }
}
public class Need_For_Volatile
{
    public static void main(String[] args) throws InterruptedException //may throw
    {
        Worker worker = new Worker();
        Thread t = new Thread(worker);
        t.start();

        Thread.sleep(1000); // Wait for 1 second
        worker.halt(); // Request the worker thread to halt
    }
}
