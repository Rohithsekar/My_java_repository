package banking_with_db_connectivity;


import java.sql.*;
import java.util.Scanner;
/*
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
*/


public class BankUser
{
    protected String userName;   //attribute belongs to user object in java, and goes to users table in DBMS
    protected String password;   //attribute belongs to user object in java, and goes to users table in DBMS
    protected long accountNumber;  //attribute belongs to user object in java, but goes to Accounts table in DBMS
    protected Float balance;   //attribute belongs to user object in java, but goes to Accounts table in DBMS

    private static BankUser instance; // Singleton instance
     //all user objects inherit a connection object
    private Connection con;
    private static final String sql_url = "jdbc:mysql://localhost:3306/bank";
    private static final  String sql_username = "root";
    private static final String sql_password = "password";
    private static Statement st = null;
    private static PreparedStatement pst = null;
    private static String result;





    private static String retrieve_query;



    private BankUser()
    {
        //default constructor
        try
        {
            con = DriverManager.getConnection(sql_url,sql_username,sql_password);
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception occurred");
        }

    }
    public static BankUser getInstance()
    {
        if (instance == null) {
            instance = new BankUser();
        }
        return instance;
    }

    public BankUser(String userName, String password, long accountNumber, Float balance) //constructor
    {
        this.userName = userName;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        try
        {
            con = DriverManager.getConnection(sql_url,sql_username,sql_password);
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception occurred");
        }
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



    protected void setBalance(Float balance)
    {
        this.balance = balance;
    }

     */

    protected String getuserName()
    {
        return userName;
    }

    protected String getpassword()
    {
        return password;
    }

    protected Float getBalance()
    {
        return balance;
    }

    protected long getAccountNumber()
    {
        return accountNumber;
    }

    public int retrieve_user_count()
    {
        result = "users_count";
        retrieve_query = "select count(*) users_count from users";
        try
        {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(  retrieve_query);
            rs.next();
            return rs.getInt(result);
        }
        catch(SQLException e)
        {
            System.out.println("SQLException has occurred while creating statement object");
        }

        return -1;
    }

    public boolean isDuplicateAccountNumber(long account_no)
    {
        String result = "IS_DUPLICATE";
        String check_query = "SELECT CASE WHEN EXISTS (" +
                "   SELECT 1" +
                "   FROM accounts" +
                "   WHERE account_number = ?" +
                ") THEN 'true' ELSE 'false' END AS IS_DUPLICATE";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
             PreparedStatement ps = con.prepareStatement(check_query))
        {
            /*
            The `PreparedStatement` is placed inside the `try` statement as part of the
            try-with-resources block. This allows the `PreparedStatement` to be automatically closed when it goes out of
            scope, along with the associated `Connection`.
            The try-with-resources statement is a convenient way to manage resources that implement the `AutoCloseable`
            interface, such as `Connection`, `PreparedStatement`, and `ResultSet`. By placing the resources within the
            parentheses after the `try` keyword, the resources are automatically closed at the end of the block,
            regardless of whether an exception occurs or not.

            In this specific case, the `Connection` and `PreparedStatement` are created and initialized within the
            try-with-resources statement. This ensures that both resources are properly closed and released after they
            are no longer needed, without the need for explicit `close()` method calls.
             */
            ps.setLong(1, account_no);
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    return rs.getBoolean(result);
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException occurred while checking if the account number is duplicate: " + e.getMessage());
        }

        return false;
    }


    public boolean is_New_usrname_duplicate(String new_usrname)
    {
        String result = "duplicate_username_count";
        String  is_new_username_duplicate_query = "SELECT COUNT(*) duplicate_username_count FROM users WHERE username = ?"; //returns zero if no such username found
        try
        {
            PreparedStatement pst = con.prepareStatement(is_new_username_duplicate_query);
            pst.setString(1, new_usrname);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int duplicate_username_count= rs.getInt(result);
            if (duplicate_username_count>0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException has occurred in duplicate username checking method: " + e.getMessage());
        }
        return false;
    }

    public void insert_New_User(BankUser new_user,String transaction_type, float transaction_amount, String transaction_status, String transaction_nature)
    {
            String insert_new_user_info_query = "insert into users(username, password) values(?,?)";
            String insert_accounts_info_query = "insert into accounts values(?,?,?)";
            /*
              create table transactions(id int primary key auto_increment,
        type varchar(10),
        amount decimal(3,2),
        status varchar(10),
        acc_no int,
        constraint external_acc_no foreign key(acc_no) references accounts(account_number),
        direction varchar(4)
          );
          alter table transactions auto_increment=1000;
             */
            String insert_transaction_info_query = "insert into transactions(type,amount,status,acc_no,nature) values(?,?,?,?,?)";
            int user_id ;
            try
            {
                con.setAutoCommit(false);

                //preparing statement for inserting user info into users table
                pst = con.prepareStatement( insert_new_user_info_query,Statement.RETURN_GENERATED_KEYS);
                pst.setString(1,new_user.getuserName());
                pst.setString(2, new_user.getpassword());
                int count1= pst.executeUpdate(); // Execute the query to insert user info

                // Get the last inserted user_id
                ResultSet rs = pst.getGeneratedKeys();

                if (rs.next())
                {
                    user_id = rs.getInt(1);
                }
                else
                {
                    System.out.println("Failed to retrieve user_id");
                    con.rollback();
                    return;
                }

                //preparing statement for inserting account_info info into accounts table
                pst = con.prepareStatement(insert_accounts_info_query);
                pst.setLong(1,new_user.getAccountNumber());
                pst.setFloat(2,new_user.getBalance());
                pst.setInt(3,user_id);
                int count2= pst.executeUpdate();
                //preparing statement for inserting  transaction_info into transactions table
                pst = con.prepareStatement(insert_transaction_info_query);
                pst.setString(1,transaction_type);
                pst.setFloat(2,transaction_amount);
                pst.setString(3,transaction_status);
                pst.setLong(4,new_user.getAccountNumber());
                pst.setString(5,transaction_nature);
                int count3 = pst.executeUpdate();


                if(count1>0 && count2>0 && count3>0)
                {
                    con.commit();
                    con.setAutoCommit(true);
                }
                else
                {
                    con.rollback();
                }


            }
            catch(SQLException e)
            {
                System.out.println("SQL exception while inserting user object info into the tables");
                e.printStackTrace();
            }
        }

        public int validate_existing_user(String userName)
        {
            String validate_existing_user_query = "select user_id from users where username=?";
            try
            {
                pst =con.prepareStatement(validate_existing_user_query);
                pst.setString(1,userName);
                ResultSet rs = pst.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

           return -1; //If it had reached here, there is no user by the entered username.

        }

        public String validate_existing_user_pwd(String pwd)
        {
            String validate_existing_user_pwd_query = "select password from users where password=?";
            try
            {
                pst =con.prepareStatement(validate_existing_user_pwd_query);
                pst.setString(1,pwd);
                ResultSet rs = pst.executeQuery();
                rs.next();
                return rs.getString(1);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return null; //If it had reached here, there is no user by the entered username.
        }

        public void account_details(int user_id)
        {
            String username="null";
            long account_number;
            float balance;
            String retrieve_username = "select username from users where user_id=?";
            String retrieve_account_number_balance = "select account_number,balance from accounts where user_id=?";
            try
            {
                pst= con.prepareStatement(retrieve_username);
                pst.setInt(1,user_id);
                ResultSet rst = pst.executeQuery();
                if(rst.next())
                {
                   username = rst.getString(1);
                }
                else
                {
                    System.out.println("Can't able to retrieve username info");
                }
                pst = con.prepareStatement(retrieve_account_number_balance);
                pst.setInt(1,user_id);
                rst = pst.executeQuery();
                String entry = String.format("%-10s %-15s %-10s","username", "account_number", "balance");
                System.out.println(entry);
                while(rst.next())
                {
                    account_number = rst.getLong(1); //account number
                    balance = rst.getFloat(2); //balance
                    String account_details=String.format("%-10s %-15s %-10s",username,account_number,balance);
                    System.out.println(account_details);
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        public void display_log(int id)
        {
            //System.out.println("The user id is "+id);
            String retrieve_transaction_log = "select * from transactions where acc_no=?";
            long acc_no = retrieve_account_number(id);
            if(acc_no!=-1)
            {
                try
                {
                    pst = con.prepareStatement(retrieve_transaction_log);
                    pst.setLong(1,acc_no);
                    ResultSet rst = pst.executeQuery();
                        String transactions = String.format("%-15s %-20s %-14s %-14s %-20s %-10s", "transaction_id","type","amount","status","acc_no", "nature");
                        System.out.println(transactions);
                        while (rst.next())
                        {
                            int transaction_id = rst.getInt(1);
                            String type = rst.getString(2);
                            float amount =rst.getFloat(3);
                            String status = rst.getString(4);
                            acc_no = rst.getLong(5);
                            String nature = rst.getString(6);
                            String transaction_log = String.format("%-15s %-20s %-14s %-14s %-20s %-10s", transaction_id,type,amount,status,acc_no, nature);
                            System.out.println(transaction_log);
                        }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public static long retrieve_account_number(int id)
        {
            String retrieve_account_no = "select account_number from accounts where user_id=?";
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
                 PreparedStatement ps = con.prepareStatement(retrieve_account_no))
            {
            /*
            The `PreparedStatement` is placed inside the `try` statement as part of the
            try-with-resources block. This allows the `PreparedStatement` to be automatically closed when it goes out of
            scope, along with the associated `Connection`.
            The try-with-resources statement is a convenient way to manage resources that implement the `AutoCloseable`
            interface, such as `Connection`, `PreparedStatement`, and `ResultSet`. By placing the resources within the
            parentheses after the `try` keyword, the resources are automatically closed at the end of the block,
            regardless of whether an exception occurs or not.

            In this specific case, the `Connection` and `PreparedStatement` are created and initialized within the
            try-with-resources statement. This ensures that both resources are properly closed and released after they
            are no longer needed, without the need for explicit `close()` method calls.
             */
                ps.setInt(1,id);
                try (ResultSet rs = ps.executeQuery())
                {
                    if (rs.next())
                    {
                        return rs.getLong(1);
                    }
                    else
                    {
                        System.out.println("Could not retrieve account number for the user");
                    }
                }
            }
            catch (SQLException e)
            {
                System.out.println("SQLException occurred while retrieving the account number is : " + e.getMessage());
            }

            return -1;
        }

        public void view_balance(int id)
        {
            long account_number = retrieve_account_number(id);
            String view_balance_query = "select balance from accounts where account_number=?";
            if(account_number!=-1)
            {
                try
                {
                    pst = con.prepareStatement(view_balance_query);
                    pst.setLong(1,account_number);
                    ResultSet rst = pst.executeQuery();
                    if(rst.next())
                    {
                        System.out.println("Your balance is "+rst.getFloat(1));
                    }
                    else
                    {
                        System.out.println("No balance record found for the user_id and its account");
                    }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public static float retrieve_balance(long account_number)
        {
            String retrieve_balance_query="select balance from accounts where account_number=?";
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
                 PreparedStatement ps = con.prepareStatement(retrieve_balance_query))
            {
                ps.setLong(1,account_number);
                try (ResultSet rs = ps.executeQuery())
                {
                    if (rs.next())
                    {
                        return rs.getFloat(1);
                    }
                    else
                    {
                        System.out.println("Could not retrieve balance from user_id and its account");
                    }
                }
            }
            catch (SQLException e)
            {
                System.out.println("SQLException occurred while retrieving the account balance: " + e.getMessage());
            }

            return -1;
        }

        public void deposit(int id)
        {
            long account_number = retrieve_account_number(id);
            String update_balance_query = "update accounts set balance=? where account_number=?";
            if(account_number!=-1)
            {
                float balance_before = retrieve_balance(account_number);
                if(balance_before!=-1)
                {
                    System.out.print("Enter the amount:");
                    Scanner scanner = new Scanner(System.in);
                    String amount = scanner.nextLine();
                    try
                    {
                        float deposit_amount = Float.parseFloat(amount);
                        if(deposit_amount>0)
                        {
                            float updated_balance = deposit_amount + balance_before;
                            try
                            {
                                pst = con.prepareStatement(update_balance_query);
                                pst.setFloat(1,updated_balance);
                                pst.setLong(2,account_number);
                                int count= pst.executeUpdate();
                                if(count==1)
                                {
                                    //System.out.println("Successfully deposited. Your balance is " + retrieve_balance(account_number));
                                    insert_transaction_info("deposit", deposit_amount, "success", account_number, "credit", updated_balance);
                                }
                                else
                                {
                                    System.out.println("Transaction failed.");
                                    insert_transaction_info("deposit", deposit_amount, "failure", account_number, "credit", updated_balance);
                                }
                            }
                            catch(SQLException e)
                            {
                                e.printStackTrace();
                            }

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

            }
        }

        public void withdraw(int id)
        {
            long account_number = retrieve_account_number(id);
            String update_balance_query = "update accounts set balance=? where account_number=?";
            if (account_number != -1)
            {
                float balance_before = retrieve_balance(account_number);
                if (balance_before != -1)
                {
                    System.out.print("Enter the amount:");
                    Scanner scanner = new Scanner(System.in);
                    String amount = scanner.nextLine();
                    try
                    {
                        float withdraw_amount = Float.parseFloat(amount);
                        if (withdraw_amount > 0)
                        {
                            float remaining_bal = balance_before - withdraw_amount;
                            if (withdraw_amount < balance_before && remaining_bal >= 500)
                            {
                                float updated_balance = balance_before - withdraw_amount;
                                try
                                {
                                    pst = con.prepareStatement(update_balance_query);
                                    pst.setFloat(1, updated_balance);
                                    pst.setLong(2, account_number);
                                    int count = pst.executeUpdate();
                                    if (count == 1)
                                    {
                                        //System.out.println("Successfully withdrawn. Your balance is " + retrieve_balance(account_number));
                                        insert_transaction_info("withdrawal", withdraw_amount, "success", account_number, "debit", updated_balance);
                                    }
                                    else
                                    {
                                        System.out.println("Transaction failed.");
                                        insert_transaction_info("withdrawal", withdraw_amount, "failure", account_number, "debit", updated_balance);
                                    }
                                }
                                catch (SQLException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                            else
                            {
                                System.out.println("Withdraw amount should be greater than zero.");
                            }
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Invalid input");
                    }

                }
            }
        }

        public void transfer(int id)
        {
            long payer_account_number = retrieve_account_number(id);
            float balance_before = retrieve_balance(payer_account_number);
            String update_payer_balance_query = "update accounts set balance=? where account_number=?";
            String update_payee_balance_query = "update accounts set balance=? where account_number=?";
            if (payer_account_number != -1 && balance_before!=-1)
            {
                System.out.println("Enter beneficiary account:");
                Scanner scanner = new Scanner(System.in);
                String account = scanner.nextLine().trim();
                try
                {
                    long payee_account_no = Long.parseLong(account);
                    if (payee_account_no > 0)
                    {
                        //logic for checking if the payer is transferring money to his own account
                        if (payee_account_no == payer_account_number)
                        {
                            System.out.println("You cannot transfer to your own account");
                        }
                        else //check if the entered account number exists within the bank
                        {
                            String payee_acc_no_exists_query = "select exists(select 1 from accounts where account_number=?)";
                            try
                            {
                                pst = con.prepareStatement(payee_acc_no_exists_query);
                                pst.setLong(1, payee_account_no);
                                ResultSet rst = pst.executeQuery();
                                rst.next();
                                if (rst.getBoolean(1))
                                {
                                    float payee_balance_before = retrieve_balance(payee_account_no);

                                    System.out.print("Enter amount:");
                                    Scanner scan = new Scanner(System.in);
                                    String amount = scan.nextLine();
                                    try
                                    {
                                        float transfer_amount = Float.parseFloat(amount);
                                        if (transfer_amount > 0)
                                        {
                                            float payer_remaining_bal = balance_before - transfer_amount;
                                            if (transfer_amount < balance_before && payer_remaining_bal >= 500)
                                            {
                                                float payer_updated_balance = balance_before- transfer_amount;
                                                float payee_updated_balance = payee_balance_before+transfer_amount;
                                                try
                                                {
                                                    con.setAutoCommit(false);
                                                    pst = con.prepareStatement(update_payer_balance_query);
                                                    pst.setFloat(1, payer_updated_balance);
                                                    pst.setLong(2, payer_account_number);
                                                    int count1 = pst.executeUpdate();

                                                    pst = con.prepareStatement(update_payee_balance_query);
                                                    pst.setFloat(1,payee_updated_balance);
                                                    pst.setLong(2,payee_account_no);
                                                    int count2 = pst.executeUpdate();
                                                    if (count1 == 1 && count2 ==1)
                                                    {
                                                        con.commit();
                                                        con.setAutoCommit(true);
                                                       // System.out.println("Transfer successful. Your balance is " + retrieve_balance(payer_account_number));
                                                        insert_transaction_info("transfer", transfer_amount, "success", payer_account_number, "debit", payer_updated_balance);
                                                        insert_transaction_info("transfer", transfer_amount, "success", payee_account_no, "credit", payee_updated_balance);
                                                    }
                                                    else
                                                    {
                                                        con.rollback();
                                                        System.out.println("Transaction failed.");
                                                        insert_transaction_info("transfer", transfer_amount, "failure", payer_account_number, "debit", balance_before);
                                                    }
                                                }
                                                catch (SQLException e)
                                                {
                                                    e.printStackTrace();
                                                }
                                            }
                                            else
                                            {
                                                if (transfer_amount > balance_before)
                                                {
                                                    System.out.println("Insufficient balance.");
                                                    System.out.println("Your balance is " +balance_before);
                                                }
                                                else
                                                {
                                                    System.out.println("Transfer amount is beyond the allowable limit.Minimum balance should be maintained at minimum 500.00");
                                                    System.out.println("Your balance is " + balance_before);
                                                }
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Transfer amount should be greater than zero.");
                                        }
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Invalid input");
                                    }

                                }
                            }
                            catch (SQLException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Invalid input");
                }
            }
            else
            {
                if(payer_account_number==-1)
                {
                  System.out.println("Unable to find the user account");
                }
                else
                {
                    System.out.println("Unable to fetch user balance for processing");
                }

            }
        }



        public void insert_transaction_info(String transaction_type, float transaction_amount, String transaction_status, long acc_no, String transaction_nature, float balance)
        {
            String insert_transaction_info_query = "insert into transactions(type,amount,status,acc_no,nature) values(?,?,?,?,?)";
            try
            {
                pst = con.prepareStatement(insert_transaction_info_query);
                pst.setString(1,transaction_type);
                pst.setFloat(2,transaction_amount);
                pst.setString(3,transaction_status);
                pst.setLong(4,acc_no);
                pst.setString(5,transaction_nature);
                int count = pst.executeUpdate();
                if(count==1)
                {
                    System.out.println("Transaction successful. Your balance is " + balance);
                }
                else
                {
                    System.out.println("Failed to insert transaction info");
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

    public void close_connection()
    {
        try
        {
            if(st!=null)
            {
                st.close();
            }
            if (pst != null)
            {
                pst.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQLException while closing connection: " + e.getMessage());
        }
    }

}






