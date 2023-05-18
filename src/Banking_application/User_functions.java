package Banking_application;



import java.util.Scanner;

public class User_functions extends User
{
    User_functions(String userName, String password, long accountNumber, Float balance)
    {
        super(userName, password, accountNumber, balance);
    }

    static void account_details(User user)
    {
        System.out.println("Account number: " + user.accountNumber);
        System.out.println("Username: " + user.getuserName());
        System.out.println("Current balance: " + user.getBalance());
    }

    static void display_log(User user)
    {
        for(int i=user.log_count-1; i>=0;i--)
        {
            System.out.println(user.log[i]);
        }
    }

    static void banking_log(User user, Float amount, Character activity, boolean initial_deposit)
    {
        String log_message;
        if(activity=='D') //deposit log
        {
            if(initial_deposit)
            {
                 log_message = String.format("Initial deposit Rs.%.2f.",amount);
                user.log[user.log_count]=log_message;
                user.log_count++;
            }
            else
            {
                if(user.log_count!=50)
                {
                    log_message = String.format("Credited Rs.%.2f.Closing balance is %.2f" , amount,user.getBalance());
                    user.log[user.log_count]=log_message;
                    user.log_count++;
                }
                else
                {
                    System.out.println("No memory space for storing logs");
                }
            }
        }
        else //withdraw log
        {
            log_message = String.format("Debited Rs.%.2f.Closing balance is %.2f", amount, user.getBalance());
            user.log[user.log_count]=log_message;
            user.log_count++;
        }
    }

    static  void transfer_log(User payer, User payee, float amount)
    {
           String payer_log_message = String.format("Debited Rs.%.2f to account %d. Closing balance:Rs.%.2f",amount,payee.accountNumber,payer.getBalance());
           String payee_log_message = String.format("Credited Rs.%.2f from account %d.Closing balance:Rs.%.2f",amount,payer.accountNumber,payee.getBalance());

           payer.log[payer.log_count]=payer_log_message;
           payee.log[payee.log_count]=payee_log_message;
    }
    static void deposit(User user)
    {
        System.out.print("Enter the amount:");
        Scanner scanner = new Scanner(System.in);
        String amount = scanner.nextLine();
        try
        {
            float deposit_amount = Float.parseFloat(amount);
            if(deposit_amount>0)
            {
                float updated_balance = deposit_amount + user.getBalance();
                user.setBalance(updated_balance);
                System.out.println("Amount deposited successfully.Your closing balance is Rs." + user.getBalance());
                banking_log(user,deposit_amount,'D',false);
            }
            else
            {
                System.out.println("Deposit amount should be greater than zero.");
            }

        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid input");
        }

    }

    static void withdraw(User user)
    {
        System.out.print("Enter the amount:");
        Scanner scanner = new Scanner(System.in);
        String amount = scanner.nextLine();
        try
        {
            float withdraw_amount = Float.parseFloat(amount);
            if(withdraw_amount>0)
            {
                float remaining_bal = user.getBalance() - withdraw_amount;
                if (withdraw_amount < user.getBalance() && remaining_bal >= 500)
                {
                    float updated_balance = user.getBalance()- withdraw_amount;
                    user.setBalance(updated_balance);
                    System.out.println("Amount withdrawn successfully.Your closing balance is Rs." + user.getBalance());
                    banking_log(user,withdraw_amount,'W',false);
                }
                else
                {
                    if (withdraw_amount > user.getBalance())
                    {
                        System.out.println("Insufficient balance.");
                        System.out.println("Your balance is " + user.getBalance());
                    }
                    else
                    {
                        System.out.println("You have withdrawn beyond the allowable limit.Minimum balance should be maintained at minimum 500.00");
                        System.out.println("Your balance is " + user.getBalance());
                    }
                }
            }
            else
            {
                System.out.println("Withdraw amount should be greater than zero.");
            }

        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid input");
        }
    }

    static void transfer(User user)
    {
        System.out.println("Enter beneficiary account:");
        Scanner scanner = new Scanner(System.in);
        String account = scanner.nextLine();
        try
        {
            long account_no = Long.parseLong(account);
            if(account_no>0)
            {
                for(User beneficiary : users)
                {
                    if(beneficiary.accountNumber==account_no)
                    {
                        if(beneficiary.accountNumber== user.accountNumber)
                        {
                            System.out.println("You cannot transfer to your own bank account.");
                        }
                        else
                        {
                            System.out.print("Enter amount:");
                            Scanner scan = new Scanner(System.in);
                            String amount = scan.nextLine();
                            try
                            {
                                float transfer_amount = Float.parseFloat(amount);
                                if(transfer_amount>0)
                                {
                                    float remaining_bal = user.getBalance() - transfer_amount;
                                    if (transfer_amount < user.getBalance() && remaining_bal >= 500)
                                    {
                                        float payer_updated_balance = user.getBalance()- transfer_amount;
                                        user.setBalance(payer_updated_balance);
                                        float beneficiary_updated_balance = beneficiary.getBalance() + transfer_amount;
                                        beneficiary.setBalance(beneficiary_updated_balance);
                                        System.out.println("Transaction success.Your closing balance is Rs." + user.getBalance());
                                        transfer_log(user,beneficiary,transfer_amount);
                                    }
                                    else
                                    {
                                        if (transfer_amount > user.getBalance())
                                        {
                                            System.out.println("Insufficient balance.");
                                            System.out.println("Your balance is " + user.getBalance());
                                        }
                                        else
                                        {
                                            System.out.println("Transfer amount is beyond the allowable limit.Minimum balance should be maintained at minimum 500.00");
                                            System.out.println("Your balance is " + user.getBalance());
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Withdraw amount should be greater than zero.");
                                }
                            }
                            catch(NumberFormatException e)
                            {
                                System.out.println("Invalid input");
                            }
                        }
                    }
                }
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid input");
        }
    }
}
