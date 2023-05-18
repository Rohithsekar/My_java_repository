package Banking_application;



public class Main
{
    public static int user_count =0;
    public static User[] users = new User[10];
    public static void main(String[] args)
    {
        System.out.println("Welcome to xyz bank");
        //Main_menu.main_menu(); //calling the main_menu method in Main_menu class
        User.user_page();
    }

}
