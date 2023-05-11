public class Type_casting
{
    public static void main(String[] args)
    {
        byte a = 10;
        short b = a; //widening casting.
        double d1 = 5.678d;
        float f1 = (float) d1; //narrowing casting
        System.out.println(b);
        System.out.println(f1);
    }
}
