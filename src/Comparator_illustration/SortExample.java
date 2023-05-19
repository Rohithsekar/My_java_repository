package Comparator_illustration;
import java.util.Arrays;
import java.util.Comparator;
public class SortExample {

    public static void main(String[] args) {
        Person[] people = {new Person("John", 25), new Person("Alice", 30), new Person("Bob", 20), new Person("Alice", 22)};

        // Sort by age
        Arrays.sort(people, Comparator.comparingInt(Person::getAge));

        System.out.println("Sorted by Age:");
        for (Person person : people)
        {
            System.out.println(person);
        }

        System.out.println();

        // Sort by name
        Arrays.sort(people, Comparator.comparing(Person::getName));

        System.out.println("Sorted by Name:");
        for (Person person : people)
        {
            //Implicitly the person object is converted into string inside println method.(toString method is called, but its default implementation is overridden with
            // custom implementation)
            System.out.println(person);
        }
    }
}







