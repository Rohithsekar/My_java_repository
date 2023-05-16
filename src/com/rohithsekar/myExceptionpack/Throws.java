package com.rohithsekar.myExceptionpack;

public class Throws
{
    static void divisionresult(int a, int b) throws ArithmeticException
    {
        //b can be zero and thus exception occurs and throws an ArithmeticException object.
        //Note that if an exception occurs, program execution is immediately transferred to appropriate
        //exception handler(the catch statement which can take the thrown object), if there is one. Else,
        //the exception object is handed over to the default exception handler(JVM has it) and program terminates
        //abruptly
        float quotient; //local variable
        try
        {
            quotient = a/b;
            System.out.println("The quotient is " + quotient);
        }
        catch (ArithmeticException e)
        {
            System.out.println("Division by zero is not allowed");
        }
        finally
        {
            System.out.println("Finally statement executed");
        }


    }

    public static void main(String[] args)
    {
        divisionresult(5,0);
    }
}
