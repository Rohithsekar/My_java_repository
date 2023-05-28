package com.rohithsekar.Threads;

class X extends Thread {
  volatile boolean part1done = false;
    public void  run() {
        for (int i = 0; i <3; i++) {
            synchronized(Threads_java.class) {
                System.out.println("hi");
                part1done=true;
                Threads_java.class.notify();  // Notify the other thread
                try {
                    Threads_java.class.wait();  // Wait for the other thread to notify
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Y extends Thread {
    int count =0;
    public void run() {


        for (int i = 0; i <3; i++) {
            synchronized (Threads_java.class) {
                System.out.println("hello");
                count++;
                Threads_java.class.notify();  // Notify the other thread
                try {
                    Threads_java.class.wait();  // Wait for the other thread to notify
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Threads_java {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Threads!...");
        X obj1 = new X();
        Y obj2 = new Y();

        obj1.start();
        obj2.start();

//        synchronized (Threads_java.class) {
//            Threads_java.class.wait();  // Wait for the first thread to start
//        }

        System.out.println("Done");
    }
}