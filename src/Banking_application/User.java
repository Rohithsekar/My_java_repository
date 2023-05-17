package Banking_application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class User extends Main
{
    private String userName;  //attribute
    private String password;   //attribute
    private long accountNumber;  //attribute
    protected Float balance;  //attribute

    private static boolean username_found = false;
    private static boolean password_found = false;

    private static ArrayList<Long> account_numbers = new ArrayList<>();



    User(String userName, String password, long accountNumber, Float balance) //constructor
    {
        this.userName = userName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void setuserName(String userName) //method
    {
        this.userName = userName;
    }

    public void setPassword(String password)  //method
    {
        this.password = password;
    }

    public void setBalance(Float balance)
    {
        this.balance = balance;
    }

    public String getUserName()
    {
        return userName;
    }

    public User getpassword()
    {
        return User;
    }

    public static void user_page()
    {
        System.out.println("Enter 'E' for existing user, 'N' for new user and 'M' for main_menu");
        Scanner input = new Scanner(System.in);
        String x = input.nextLine().trim().toUpperCase(); // Ignore leading/trailing whitespace and convert to uppercase

        if (x.equals("E") || x.equals("N") || x.equals("M"))
        {
            switch (x)
            {
                case "E":
                    existing_user();
                    break;
                    //break;
                case "N":
                   new_user();
                   break;
                case "M":
                    Main_menu.main_menu();
            }
            user_page();
        }
        else
        {
            System.out.println("Invalid input.");
        }
    }

    private static void new_user()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username:");
        String  new_usrname = scan.nextLine();
        boolean already_exists;
        if(users.size()==0)
        {
            already_exists = false;
        }
        else
        {
            already_exists = check_username(new_usrname);
        }

        if(!(already_exists))
        {
            System.out.print("Enter password:");
            Scanner scanner = new Scanner(System.in);
            String pwd = scanner.nextLine();
            long account_number = random_number();
            System.out.println("Enter initial deposit.Minimum should be Rs.2500:");
            Scanner scanner1 = new Scanner(System.in);
            String  initial_deposit = scanner1.nextLine();
            try
            {
                float initial_sum = Float.parseFloat(initial_deposit);
                if(initial_sum<2500)
                {
                    System.out.println("Minimum should be Rs.2500");
                }
                else
                {
                    User cur_user = new User(new_usrname,pwd,account_number,initial_sum);
                    System.out.println("Account successfully created. Your account number is " + cur_user.accountNumber);
                    users.add(cur_user);
                    account_numbers.add(account_number);
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid input");
            }


        }
    }

    private static void existing_user()
    {
        boolean user_exists = user_validation();
        if(user_exists)
        {
            System.out.println("Enter 'D' for Deposit, 'W' for Withdraw , 'T' for Transfer, 'B' for balance, 'S' for statement or 'E' for user_menu");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim().toUpperCase();
            if(input.equals("D") || input.equals("W")|| input.equals("T") || input.equals("B") || input.equals("S") || input.equals("E"))
            {
                switch (input)
                {
                    case "D":
                        User_functions.deposit(this);
                }
            }

        }
    }

    private static User user_validation()
    {
        for(User i : users)
        {
            System.out.println("The user's username is " + i.userName + " and password is " + i.password);
        }
        System.out.print("Enter username:");
        Scanner scan = new Scanner(System.in);
        String username = scan.nextLine();

        for(User j : users)
        {
            if(j.userName.equals(username))
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
            for(User j : users)
            {
                if(j.password.equals(password))
                {
                    password_found = true;
                    break;
                }
            }
            if(password_found)
            {

                // Iterate over the users to find a match
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return user; // Return the user object if username and password match
                    }
                }

                return null;
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

    private static boolean check_username(String new_username)
    {
        for(User i : users)
        {
            if(i.userName.equals(new_username))
            {
                System.out.println("Username already exists.");
                return true;
            }
        }
        return false;
    }

    public static long random_number()
    {

            int min = 1000000; // minimum value of the range
            int max = 9999999; // maximum value of the range

            Random random = new Random();
        Long account_no = (long) random.nextInt(max - min + 1) + min;
        for(Long i : account_numbers)
        {
            if(account_no == i)
            {
                random_number();
            }
        }
        return  account_no;
    }

}
