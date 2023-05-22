package com.rohithsekar.Comparable_$_Comparator_illustration;

/*The Comparable interface is available in the java.lang package. Since it provides the sorting facility based on
the natural order of objects, it is more simple and included in the java language package which is implicitly
imported by the java compiler into every java program, and we don't need to explicitly import it.
 */
import java.util.Comparator;
public class Employee implements Comparable<Employee>
{
    private int id;
    private String name;
    private int age;
    private long salary;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public long getSalary()
    {
        return salary;
    }

    public Employee(int id, String name, int age, int salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    //providing the implementation(overriding) for the abstract compareTo method which must be implemented because it
    // is the abstract method which is defined in the comparable interface.

    @Override  //providing the implementation(overriding)
    public int compareTo(Employee emp) //compareTo is the abstract method in comparable interface
    {
        //let's sort the employee based on an id in ascending order
        //returns a negative integer, zero, or a positive integer as this employee id
        //is less than, equal to, or greater than the specified object.
        return (this.id - emp.id);
    }

    /*
    Note :The Employee class does indeed implement the Comparator<Employee> interface implicitly through the static
    nested class(anonymous class) SalaryComparator. Even though the Employee class itself does not explicitly declare
    that it  implements the Comparator interface, the nested(anonymous) class SalaryComparator implements it on behalf
    of the Employee class.

    When you create an instance of the SalaryComparator class and pass it as an argument to the Arrays.sort() method,
    you are effectively using that instance as a Comparator to sort the empArr array based on the specified comparison
    logic.

    The reason why the Employee class does not explicitly declare that it implements the Comparator interface is because
    the implementation of the compare() method is provided by the SalaryComparator class, which is a separate class
    responsible for the comparison logic.(As to why it can't be a separate class(without being inside any other class),
    the answer is that to reduce creating new classes, just for implementing an interface that provides comparison logic.)
    By using a nested class or a separate class to implement the Comparator interface, you can encapsulate the
    comparison logic separately from the main Employee class.

   //Anonymous class.Here, the anonymous class expression is part of the statement that instantiates the
     Comparator object. Since, the anonymous class object reference is assigned to a variable of the comparator
     interface type, the (anonymous)class ensures that it fulfills the contract with Comparator interface(to provide
     an implementation for compare() method.)






     */
    public static Comparator<Employee> SalaryComparator = new Comparator<Employee>() //anonymous class that implements the comparator interface
    {@Override
    public int compare(Employee e1, Employee e2)
    {
            return (int) (e1.getSalary() - e2.getSalary());
    }
    };


    @Override
    //this is required to print the user-friendly information about the Employee
    public String toString()
    {
        return "[id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", salary=" +
                this.salary + "]";
    }

}