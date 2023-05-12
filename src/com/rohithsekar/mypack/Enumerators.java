package com.rohithsekar.mypack;

//Enumerators are special class that contain a fixed set of named values in Java. A java enumerator is a class type
//there is no need for instantiation of enum objects. An enum class has most of the capabilities as a regular class , like
//possessing a constructor,instance variables and methods and even implement interfaces. But an enum class cannot be inherited
//(cannot become a parent class) nor can inherit other classes(cannot become a child class)
class Enumerators
{
    enum Weekday
    {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
    }

    public static void main(String[] args)
    {
        Weekday today = Weekday.MONDAY; //no need to instantiate an enum with new
        if(today == Weekday.MONDAY)
        {
            System.out.println("Today is Monday");
        }
        //We cannot override enum constants since they are final

        switch(today)
        {
            case MONDAY:
                System.out.println("Today is Monday");
                break;
            case TUESDAY:
                System.out.println("Today is Tuesday");
                break;
            case WEDNESDAY:
                System.out.println("Today is Wednesday");
                break;
            case THURSDAY:
                System.out.println("Today is Thursday");
                break;
            case FRIDAY:
                System.out.println("Today is Friday");
                break;
            default:
                System.out.println("Today is a weekend");
        }
    }

}

enum Gender
{
    MALE,
    FEMALE,
    TRANSGENDER
}
 class Enumtest
{
    Gender gender;  //Class EnumTest has one attribute/field, and it is of enum type(Gender is an enum)

    public Enumtest(Gender gender) //constructor
    {
        this.gender = gender;
    }

    public void tellItLikeItIs()
    {
        switch(gender)
        {
            case MALE :
                System.out.println("Male gender");
                break;

            case FEMALE:
                System.out.println("Female gender");
                break;

            case TRANSGENDER:
                System.out.println("Transgender");
        }
    }

    public static void main(String[] args)
    {
        Enumtest male = new Enumtest(Gender.MALE);
        male.tellItLikeItIs();
        Enumtest female = new Enumtest(Gender.FEMALE);
        female.tellItLikeItIs();
        Enumtest transgender = new Enumtest(Gender.TRANSGENDER);
        transgender.tellItLikeItIs();
    }
}


enum Planet {

    /*Each enum constant is declared with values for the mass and radius parameters.
    These values are passed to the constructor when the constant is created.
    Java requires that the constants be defined first, prior to any fields or methods.
    Also, when there are fields and methods, the list of enum constants must end with a semicolon.*/
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7); //list of constants ended with a semicolon

    private final double mass;   // in kilograms
    private final double radius; // in meters
    Planet(double mass, double radius)  //constructor
    {
        this.mass = mass;
        this.radius = radius;
    }
    private double mass()
    {
        return mass;
    }
    private double radius()
    {
        return radius;
    }

    // universal gravitational constant  (m3 kg-1 s-2)
    public static final double G = 6.67300E-11;

    double surfaceGravity()
    {
        return G * mass / (radius * radius);
    }
    double surfaceWeight(double otherMass)
    {
        return otherMass * surfaceGravity();
    }
    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.err.println("Usage: java Planet <earth_weight>");
            System.exit(-1);
        }
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight/EARTH.surfaceGravity();
        for (Planet p : Planet.values())
            System.out.printf("Your weight on %s is %f%n",
                    p, p.surfaceWeight(mass));
    }
}