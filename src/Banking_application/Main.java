package Banking_application;

import java.util.ArrayList;

public class Main
{
    public static  ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static void main(String[] args)
    {
        System.out.println("Welcome to xyz bank");
        Admin admin1 = new Admin("a","a"); //creating an admin with username and password
        Admin.add_admin(admin1);
        Main_menu.main_menu(); //calling the main_menu method in Main_menu class
    }

}
