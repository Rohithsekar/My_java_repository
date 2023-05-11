public class Sizeof
{
    public static void main(String[] args)
    {
        //Wrapper classes are used to represent primitive data types as Objects and vice versa.
        //In the following, in the argument section, are all Wrapper classes representing their respective primitives
        //the BYTES is a final static field(member) defined in the data types' Wrapper class
        //<ClassName.staticMember> is the syntax
        System.out.println("Sizeof byte type is " + Byte.BYTES);
        System.out.println("Sizeof char type is " + Character.BYTES);
        System.out.println("Sizeof short type is " + Short.BYTES);
        System.out.println("Sizeof int type is " + Integer.BYTES);
        System.out.println("Sizeof long type is " + Long.BYTES);
        System.out.println("Sizeof float type is " + Float.BYTES);
        System.out.println("Sizeof double type is " + Double.BYTES);
        //System.out.println("Sizeof boolean type is " + Boolean.BYTES); boolean type size isn't precisely defined
        //and depends on the implementation of JVM
    }
}
