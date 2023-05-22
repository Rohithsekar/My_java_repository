package com.rohithsekar.myExceptionpack;
import java.util.InputMismatchException;
import java.util.Scanner;
public class try_catch
{
   //Illustrating try catch
    public static void main(String[] args)
    {

        Scanner input=null;

        System.out.println("Enter a number");
      while(true)
        {
            try
            {
                 input = new Scanner(System.in);
                float a = input.nextFloat();
                /*
                the Scanner object attempts to parse the input as the specified type. If the input cannot be
              successfully parsed,  an exception will be thrown, and the invalid input will still be in the input buffer
                */
                break; //if the user input is valid, the command would have proceeded here, adn the loop exits
            }
            catch(InputMismatchException e) //if the input is invalid, the exception object is transferred here
            {
                System.out.print("Invalid input. Enter a number:");
                input.nextLine();
            }

        }


    }
}
