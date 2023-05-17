package Banking_application;

import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class User_functions extends User
{
    static void deposit(User user)
    {
        System.out.print("Enter the amount:");
        Scanner scanner = new Scanner(System.in);
        String amount = scanner.nextLine();
        try
        {
            float deposit_amount = Float.parseFloat(amount);


        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid input");
        }

    }
}
