package Banking_application;

import java.util.Scanner;

public class Main_menu
{
    public static void main_menu()
    {
        System.out.print("Enter U for user and A for Admin:");
        Scanner input = new Scanner(System.in);
        String x = input.nextLine().trim().toUpperCase(); // Ignore leading/trailing whitespace and convert to uppercase

        if (x.equals("U") || x.equals("A"))
        {
            switch (x)
            {
                case "U":
                    User.user_page();
                    break;
                case "A":
                    //goto admin functionality;
                    boolean admin_exists = Admin.admin_validation();
                    if(admin_exists)
                    {
                        Admin.view_users();
                        break;
                    }
                    else
                    {
                        break;
                    }

            }
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }
}
