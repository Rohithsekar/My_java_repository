public class Constructors
{
    int age;
    String name;
    String gender;

    public Constructors(int age, String name, String gender) //constructor initializes objects with the values passed to it
    {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public static void main(String[] args)
    {
        Constructors person = new Constructors(12,"John","Male"); //class instance creation expression
        System.out.println(person.getName()); //prints the name of the person object created

        //modify the initial attribute's value(state) to some other value
        person.setName("Ben");
        System.out.println(person.getName());

    }
}
