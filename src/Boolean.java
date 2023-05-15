public class Boolean
{
    public static void main(String[] args)
    {
        boolean isFishTasty = false;
        boolean isIceCool = true;
        System.out.printf("%s %s", isIceCool ? "true" : "false", isFishTasty ? "true" : "false");
        System.out.print("\n");

        System.out.println(10>6);

        //Booleans are especially useful as gates to execute a specific command in conditional statements
        int votingAge = 18;
        int myAge = 15;
        if(myAge>=votingAge)
        {
            System.out.println("Eligible for voting");
        }
        else
        {
            System.out.println("Ineligible for voting");
        }
    }
}
