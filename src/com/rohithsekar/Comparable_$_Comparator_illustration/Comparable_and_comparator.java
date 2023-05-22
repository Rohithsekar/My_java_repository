package com.rohithsekar.Comparable_$_Comparator_illustration;


import java.util.Arrays;

public class Comparable_and_comparator
{
    public static void main(String[] args)
    {
        //creating a static array of type Employee
        Employee[] employees = {new Employee(1, "John", 21, 25000),
                new Employee(3, "Jake", 23, 20000),
                new Employee(2, "Luke", 29, 30000)
        }; //since it is an expression statement, semicolon should be there

        //sort uses the compareTo method provided in the Employee class, which provides natural order comparison logic

        Arrays.sort(employees);
        for (Employee employee : employees)
        {
            System.out.println(employee);

        }

        //sorting the employees array using the SalaryComparator class object, which has the compare() method necessary
        //for sorting(The SalaryComparator class is an anonymous static nested(inner) class within the Employee class.)

        Arrays.sort(employees,Employee.SalaryComparator);

        System.out.println("Employees list sorted by Salary:\n"+Arrays.toString(employees));


    }
}
