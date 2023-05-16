package com.rohithsekar.myExceptionpack;

public class Throw
{
    static void fun()
    {
        try
        {
            throw new ArithmeticException("division by zero");
        }
        catch(ArithmeticException e)
        {
            System.out.println("Caught in fun()");
            throw e; //rethrowing exception
        }
    }

    public static void main(String[] args)
    {
        try
        {
            fun();
        }

        catch(ArithmeticException e)
        {
            System.out.println("Caught inside main");
        }
    }
}
