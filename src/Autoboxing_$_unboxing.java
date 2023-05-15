public class Autoboxing_$_unboxing
{
    public static void main(String[] args)
    {
        Integer i = 10; //autoboxing int value into a variable of Integer Class
        System.out.println("Value of i is:" + i);
        int i1 = i; //unboxing of Integer wrapper class  variable i
        System.out.println("Value of i1 i:" + i1);

        Character a = 'j'; //autoboxing of char value into a variable of Character Class
        System.out.println("value in the autoboxed Character class variable is:" + a);
        char a1 = a; //unboxing of Character wrapper class variable a
        System.out.println("Unboxed value in the Character class variable is:" + a1);
    }
}
