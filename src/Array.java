public class Array
{
    public static void main(String[] args)
    {
        //Conditional statements, Switch statements, and loops are all identical to C
        //Array declaration
        int[] numbers = {1,2,3,4,5};
        for(int i : numbers)  //for-each loop
        {
            System.out.println(i);
        }
        String[] cars = {"Volkswagen", "Mazda", "Ford", "Fiat"};
        for(String j : cars) //for-each loop
        {
            System.out.println(j);
        }

    }

}
