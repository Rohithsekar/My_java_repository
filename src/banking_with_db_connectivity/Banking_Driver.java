package banking_with_db_connectivity;


import Banking_application.User;
import Banking_application.User_functions;

import java.util.Random;
import java.util.Scanner;

public class Banking_Driver
{

    public static BankUser obj = BankUser.getInstance();
    public static void main_menu()
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
                    obj.close_connection();
                    System.exit(0);
            }
            main_menu();
        } else
        {
            System.out.println("Invalid input.");
        }
    }

    private static void new_user()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username:");
        String new_usrname = scan.nextLine();
        String pwd;
        boolean already_exists;
        int user_count =obj.retrieve_user_count();
        System.out.println("user count is " + user_count);

        if(user_count==0)
        {
            already_exists = false;
        }
        else
        {
            already_exists = obj.is_New_usrname_duplicate(new_usrname);
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
                        BankUser cur_user = new BankUser(new_usrname,pwd,account_number,initial_sum);
                        String transaction_type = "initial_deposit";
                        String transaction_status = "success";
                        String transaction_nature = "credit";
                            /*To do:
                            1. Add the newly created user to the table. query: insert
                            2. Add the account_no, initial_sum to the accounts table. query: insert
                            3. Add the initial deposit into the Transaction table. query: insert
                            4. Create a new table for this user of the name <user_account_no> Statement
                            */
                        obj.insert_New_User(cur_user,transaction_type,initial_sum,transaction_status,transaction_nature);
                        System.out.println("User successfully added");


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
            else
            {
                System.out.println("Username already exists");
            }

        }
    }

    private static void existing_user()
    {
        System.out.print("Enter username:");
        Scanner scan = new Scanner(System.in);
        String username = scan.nextLine().trim();
        if(!username.isEmpty())  //username should not be empty
        {
            int user_id= obj.validate_existing_user(username);
            if(user_id!=-1)
            {
                System.out.print("Enter password:");
                scan = new Scanner(System.in);
                String pwd= scan.nextLine().trim();
                if(!pwd.isEmpty())
                {
                    String result = obj.validate_existing_user_pwd(pwd);
                    if(result!=null)
                    {
                        banking(user_id);
                    }
                    else
                    {
                        System.out.println("Invalid password");
                    }
                }
                else
                {
                    System.out.println("Password field should not be empty.");
                }
            }
            else
            {
                System.out.println("Username does not exist in the record");
            }
        }
        else
        {
            System.out.println("Username field should not be empty");
        }
    }

    private static void banking(int id)
    {
        System.out.println("Enter 'D' for Deposit, 'W' for Withdraw , 'T' for Transfer, 'B' for balance, 'S' for statement OR 'A' for account details.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toUpperCase();
        if(input.equals("D") || input.equals("W")|| input.equals("T") || input.equals("B") || input.equals("S") || input.equals("A"))
        {
            switch (input)
            {
                case "A":
                    obj.account_details(id);
                    break;
                case "S":
                    obj.display_log(id);
                    break;
                case "B":
                    obj.view_balance(id);
                    break;
                case "D":
                    obj.deposit(id);
                    break;
                case "W":
                    obj.withdraw(id);
                    break;
                case "T":
                    obj.transfer(id);
            }
            System.out.println("Enter C to continue banking or E to quit back to user sign in");
            Scanner scan = new Scanner(System.in);
            String y = scan.nextLine();
            if(y.equals("C") || y.equals("E"))
            {
                switch (y)
                {
                    case "C": banking(id);break;
                    case "E": main_menu();
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


    public static long random_number()
    {

        int min = 1000000; // minimum value of the range
        int max = 9999999; // maximum value of the range
        boolean is_duplicate;

        Random random = new Random();
        long account_no = (long) random.nextInt(max - min + 1) + min;
        is_duplicate= obj.isDuplicateAccountNumber(account_no);

        if(is_duplicate)
        {
            System.out.println("Account number already exists.Generating new random number.");
            random_number();
        }
        return  account_no;
    }
    public static void main(String[] args)
    {
        System.out.println("Welcome to xyz bank");
        //Main_menu.main_menu(); //calling the main_menu method in Main_menu class
        main_menu();
    }
}
