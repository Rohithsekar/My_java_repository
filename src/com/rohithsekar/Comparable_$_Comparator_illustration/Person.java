package com.rohithsekar.Comparable_$_Comparator_illustration;


class Person
{
    private String name;
    private int age;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }


    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return name + " (Age: " + age + ")";
    }

    /*
    The `toString()` method in Java is used to provide a string representation of an object.It is defined in the `Object`
    class and can be overridden in custom classes to provide a meaningful and customized string representation of the
    object's state.

    When you pass an object to the `println()` method or any other method that expects a string representation of an
    object, it implicitly calls the `toString()` method of that object to obtain the string representation.

    By default, the `toString()` method in the `Object` class returns a string that consists of the class name,
    followed by the "at" symbol (@), and the object's hash code. This default implementation may not provide meaningful
    information about the object's state.

    However, you can override the `toString()` method in your own class to provide a customized string representation
    that suits your needs. This is often useful when you want to print or display the state of an object, including the
    values of its attributes.


In the above example, the `toString()` method is overridden in the `Person` class to provide a customized string
representation. It returns a string that includes the values of the `name` and `age` attributes of the `Person` object.

When you pass a `Person` object to the `println()` method or any other method that expects a string representation,
it will implicitly call the overridden `toString()` method to obtain the customized string representation.

For example:


Person person = new Person("John", 30);
System.out.println(person);

In this code snippet, the `println()` method implicitly calls the `toString()` method of the `person` object,
resulting in the customized string representation being printed to the console.

So, in summary, you can provide your own implementation of the `toString()` method to customize the string
representation of an object. This is particularly useful when you want to pass the object as an argument to methods
like `println()`, which implicitly rely on the `toString()` method to obtain the string representation.
     */
}