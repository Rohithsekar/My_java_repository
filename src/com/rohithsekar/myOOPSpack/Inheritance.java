package com.rohithsekar.myOOPSpack;

class Inheritance
{
    protected String brand = "Honda";

    public void changeGear()
    {
        System.out.println("Gear changed");
    }
}

class Car extends Inheritance
{
    private String model = "City";

    public static void main(String[] args)
    {
        Car myCar = new Car();

        // call parent class method
        myCar.changeGear();
        System.out.println(myCar.brand + " " + myCar.model);
    }
}




