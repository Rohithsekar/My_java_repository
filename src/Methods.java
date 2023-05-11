public class Methods
{
    //A method should be defined inside a class
    //static means that the method belongs to the class in which it is located
    //and not an object of the said class
    //void means, it does not return any value

    static void myMethod()
    {
        System.out.println("Hello world, how are you?");
    }
    static int myRand()
    {
        int min = 1;
        int max = 10;
        int randNum =min+ (int)(java.lang.Math.random()*(max-min+1));
        return randNum;
    }
    static void nameMethod(String name, int age)
    {
        System.out.println(name+age);
    }

    static int mySum(int a, int b)
    {
        return a+b;
    }

    public void printMessage()
    {
        System.out.println("Public/Instance methods can only be accessed by the objects of the class");
    }
    //Driver class
    public static void main(String[] args)
    {
        myMethod(); //static method , no parameters and no return value
        System.out.println(myRand()); //static method, no parameters and returns a value
        nameMethod("John",10); //static method, parameters passed and no return value
        System.out.println(mySum(5,10)); //static method, parameters passed and returns value

        //To call a public method, we first need to create an object, if not already created.
        //We then can invoke a method call on that object

        Methods myObj = new Methods(); //creating an object of class Methods
        myObj.printMessage(); //instance method, a method which can only be called upon an instance of a class

    }

}
