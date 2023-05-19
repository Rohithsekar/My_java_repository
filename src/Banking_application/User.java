package Banking_application;

import java.util.*;


public class User extends Main
{
    private String userName;  //attribute
    private String password;   //attribute
    protected long accountNumber;  //attribute
    protected Float balance;  //attribute

    protected String[] log = new String[50]; //attribute

    protected int log_count = 0;

    private static boolean username_found = false;
    private static boolean password_found = false;

    private static long[] account_numbers = new long[10];

    private static int account_numbers_count=0;



    User(String userName, String password, long accountNumber, Float balance) //constructor
    {
        this.userName = userName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    /*
    public void setuserName(String userName) //method
    {
        this.userName = userName;
    }

    public void setPassword(String password)  //method
    {
        this.password = password;
    }

     */

    public void setBalance(Float balance)
    {
        this.balance = balance;
    }

    public String getuserName()
    {
        return userName;
    }

    public String getpassword()
    {
        return password;
    }

    protected Float getBalance()
    {
        return balance;
    }

    public static void user_page()
    {
        System.out.println("Enter 'E' for existing user, 'N' for new user and 'Q' to quit:");
        Scanner input = new Scanner(System.in);
        String x = input.nextLine().trim().toUpperCase(); // Ignore leading/trailing whitespace and convert to uppercase

        if (x.equals("E") || x.equals("N") || x.equals("Q"))
        {
            switch (x)
            {
                case "E":
                    existing_user();
                    break;
                case "N":
                   new_user();
                   break;
                case "Q":
                    System.exit(0);
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
        if(user_count!=10)
        {
        System.out.println("Maximum users can be handled is 10. The count is now " + user_count);
        System.out.print("Enter username:");
        String  new_usrname = scan.nextLine();
        String pwd;
        boolean already_exists;
        if(user_count==0)
        {
            already_exists = false;
        }
        else
        {
            already_exists = check_username(new_usrname);
        }

        if(!(already_exists) && !(new_usrname.trim().isEmpty())) //username should not be duplicate and empty
        {
            System.out.print("Enter password:");
            Scanner scanner = new Scanner(System.in);
            pwd = scanner.nextLine();
            if(!(pwd.trim().isEmpty())) //If password is not empty
            {
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
                        users[user_count]=cur_user;
                        user_count++;

                        User_functions.banking_log(cur_user,initial_sum,'D',true);


                        account_numbers[account_numbers_count]=account_number; //for checking conflicting account numbers and assign unique account number
                        account_numbers_count++;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Invalid input");
                }


            }
            else
            {
                System.out.println("Password field cannot be empty.");
            }
        }
        else
        {
            if(new_usrname.trim().isEmpty())
            {
                System.out.println("Username cannot be empty");
            }

        }
        }
        else
        {
            System.out.println("Maximum user count has been reached. No more users can be added");
        }
    }

    private static void existing_user()
    {
        try
        {
            User user = user_validation();
            banking(user);
        }
        catch(NullPointerException e)
        {
            System.out.println("No such user exist in the record/Invalid input.");
        }
    }

    private static void banking(User user)
    {
        System.out.println("Enter 'D' for Deposit, 'W' for Withdraw , 'T' for Transfer, 'B' for balance, 'S' for statement OR 'A' for account details.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toUpperCase();
        if(input.equals("D") || input.equals("W")|| input.equals("T") || input.equals("B") || input.equals("S") || input.equals("A"))
        {
            switch (input)
            {
                case "A":
                    User_functions.account_details(user);
                    break;
                case "S":
                    User_functions.display_log(user);
                    break;
                case "B":
                    System.out.println("Your balance is " + user.getBalance());
                    break;
                case "D":
                    User_functions.deposit(user);
                    break;
                case "W":
                    User_functions.withdraw(user);
                    break;
                case "T":
                    User_functions.transfer(user);
            }
            System.out.println("Enter C to continue banking or E to quit back to user sign in");
            Scanner scan = new Scanner(System.in);
            String y = scan.nextLine();
            if(y.equals("C") || y.equals("E"))
            {
                switch (y)
                {
                    case "C": banking(user);break;
                    case "E": user_page();
                }
            }
            else
            {
                System.out.println("Invalid input.");
            }

        }
        else
        {
            System.out.println("Invalid input");
        }
    }

    private static User user_validation() throws NullPointerException
    {

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
                    if (user.getuserName().equals(username) && user.getpassword().equals(password))
                    {
                        return user; // Return the user object if username and password match
                    }
                }
            }
            else
            {
                System.out.println("Invalid password");
            }
        }
        else
        {
            System.out.println("Invalid username");
        }
        return null;
    }

    private static boolean check_username(String new_username)
    {
        for(int i=0; i<=user_count-1;i++)
        {
            if(users[i].userName.equals(new_username))
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
        long account_no = (long) random.nextInt(max - min + 1) + min;
        for(long i : account_numbers)
        {
            if(account_no == i)
            {
                random_number();
            }
        }
        return  account_no;
    }

}
