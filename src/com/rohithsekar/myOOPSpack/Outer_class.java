package com.rohithsekar.myOOPSpack;

//Classes can be nested together to make code more readable and maintainable
//To access the inner class, create an object of outer class, and then create an object of inner_class. Exception is
//static inner class as you see below.
class Outer_class
{
    int x = 10;

    class Inner_class //Inner_class is nested inside Outer_class
    {
        int y = 5;
    }
}

class Outerclass2
{
    char a = 'y';
    //by defining inner class as private, we can restrict the access to the inner class. Outer class objects
    //cannot access inner class attributes and methods, if the inner class is declared private.
    private class innerclass2
    {
        int y = 5;
    }
}

class outerclass3
{
    int a = 9;
    static class innerclass3
    {
        int b = 7;
    }
}

//Driver class
class Main1
{
    public static void main(String[] args)
    {
        // regular(without access modifiers) inner classes objects creation needs creating outer class objects first
        Outer_class myOuter = new Outer_class();
        Outer_class.Inner_class myInner = myOuter.new Inner_class();
        System.out.println(myInner.y + myOuter.x);

        //static inner class objects can be created without creating outer class objects
        outerclass3.innerclass3 myInner3 = new outerclass3.innerclass3();
        System.out.println(myInner3.b);
    }
}
