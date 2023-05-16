package com.rohithsekar.myExceptionpack;

public class Exception_handling
{
    //Demonstrating error

    // Java Program to Demonstrate Exception is Thrown
// How the runTime System Searches Call-Stack
// to Find Appropriate Exception Handler




        // Method 1
        // It throws the Exception(ArithmeticException).
        // Appropriate Exception handler is not found
        // within this method.
        static int divideByZero(int a, int b)
        {

            // this statement will cause ArithmeticException
            // (/by zero)
            int i = a / b;

            return i;
        }

        // The runTime System searches the appropriate
        // Exception handler in method also but couldn't have
        // found. So looking forward on the call stack
        static int computeDivision(int a, int b)
        {

            int res = 0;

            // Try block to check for exceptions
            try
            {

                res = divideByZero(a, b);
            }

            // Catch block to handle NumberFormatException
            // exception Doesn't match with
            // ArithmeticException
            catch (NumberFormatException ex)
            {
                // Display message when exception occurs
                System.out.println(
                        "NumberFormatException is occurred");
            }
            return res;
        }

        // Method 2
        // Found appropriate Exception handler.
        // i.e. matching catch block.




    public static void main(String[] args)
    {
        String str = null;
        //System.out.println(str.length());
        /*The above code will result in an error:
        Exception in thread "main" java.lang.NullPointerException
        at com.rohithsekar.myExceptionpack.Exception_handling.main(com.rohithsekar.myExceptionpack.Exception_handling.java:7)
        */
     int a = 1;
     int b = 0;

            // Try block to check for exceptions
            try
            {
                int i = computeDivision(a, b); //method call
            }

            // Catch block to handle ArithmeticException
            // exceptions
    catch (ArithmeticException ex)
    {

        // getMessage() will print description
        // of exception(here / by zero)
        System.out.println(ex.getMessage());
    }



    }
}
