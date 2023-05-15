package com.rohithsekar.myOOPSpack;
//interfaces are complete abstract classes in which every method ad class is by default, abstract.
//interface methods are by default abstract and public
//interface attributes are by default abstract, public and final
interface Interface_animal
{
     void animalSound();
    void sleep();
     String color  = "Red";
}

interface Interface_plant
{
    void photosynthesize();
}

class Horse implements Interface_animal
{
    public void animalSound()
    {
        System.out.println("Horse neighs");
    }

    public void sleep()
    {
        System.out.println("Zzz");
    }

}

class Euglena implements Interface_animal, Interface_plant //multiple interfaces
{
    @Override
    public void animalSound()
    {
        System.out.println("Microscopic sounds");
    }

    @Override
    public void sleep()
    {
        System.out.println("Zzz");
    }

    @Override
    public void photosynthesize()
    {
        System.out.println("Produces energy from sun via photosynthesis");
    }
}

class Main3
{
    public static void main(String[] args)
    {
        System.out.println("The colour of the Horse is " + Interface_animal.color);

        //Although we cannot use interfaces to create objects, we can assign(refer) created objects to an interface variable
        Interface_animal horse = new Horse();
        horse.animalSound();
        horse.sleep();

        Euglena euglena = new Euglena();
        euglena.animalSound();
        euglena.sleep();
        euglena.photosynthesize();
    }
}
