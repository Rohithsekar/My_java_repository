package Banking_application;

import javax.swing.*;
import java.util.Scanner;

public class Admin extends Main
{
    private String username;
    private String password;

    private static boolean username_found = false;
    private static boolean password_found = false;
    public Admin(String username, String password) //constructor
    {
        this.username = username;
        this.password = password;
    }
    public static void add_admin(Admin new_admin)
    {
        admins.add(new_admin);
    }
    public static boolean admin_validation()
    {
        for(Admin i : admins)
        {
            System.out.println("The admin username is " + i.username + " and password is " + i.password);
        }
        System.out.print("Enter username:");
        Scanner scan = new Scanner(System.in);
        String username = scan.nextLine();



        for(Admin i : admins)
        {
            if(i.username.equals(username))
            {
                username_found = true;
                break;
            }
        }
        if(username_found)
        {
            System.out.print("Enter password:");
            Scanner scan_pwd = new Scanner(System.in);
            String password = scan_pwd.nextLine();
            for(Admin j : admins)
            {
                if(j.password.equals(password))
                {
                    password_found = true;
                }
            }
            if(password_found)
            {
                return true;
            }
            else
            {
                System.out.println("Invalid password");
                return false;
            }
        }
        else
        {
            System.out.println("Invalid username");
            return false;
        }
    }



    static void view_users()
    {
        if(users.size()==0)
        {
            System.out.println("No users added");
        }
    }
}
