import java.util.Scanner;

public class AccountTest
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Account myAccount = new Account(null);
        Account account1 = new Account("Colin Monroe");
        Account account2 = new Account("Martin Guptill");

        System.out.printf("The object account1's name is %s\n",account1.getName());
        System.out.printf("the object account2's name is %s\n",account2.getName());

        //display initial value of name
        System.out.printf("Initially, the object myAccount's name is %s\n",myAccount.getName());

        //prompt for a name;
        System.out.println("Enter the name:");
        String theName = input.nextLine();
        myAccount.setName(theName);
        System.out.println();

        System.out.printf("Name stored in object myAccount is %s\n",myAccount.getName());
    }
}
