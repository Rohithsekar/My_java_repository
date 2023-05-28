package com.rohithsekar.myOOPSpack.Polymorphism.payroll_application;


/*
This payroll application program is taken from Paul & Harvey Deitel's book:Java How to Program. Early objects
Problem Statement:

A company pays its employees on a weekly basis. The employees are of four types: Salaried
employees are paid a fixed weekly salary regardless of the number of hours worked, hourly
employees are paid by the hour and receive overtime pay (i.e., 1.5 times their hourly
salary rate) for all hours worked in excess of 40 hours, commission employees are paid a
percentage of their sales and base-salaried commission employees receive a base salary plus a
percentage of their sales. For the current pay period, the company has decided to reward
salaried-commission employees by adding 10% to their base salaries. The company
wants you to write an application that performs its payroll calculations polymorphically.
 */

public class Driver
{
    public static void main(String[] args)
    {
// create subclass objects
        SalariedEmployee salariedEmployee =
                new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
        HourlyEmployee hourlyEmployee =
                new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
        CommissionEmployee commissionEmployee =
                new CommissionEmployee(
                        "Sue", "Jones", "333-33-3333", 10000, .06);
        BasePlusCommissionEmployee basePlusCommissionEmployee =
                new BasePlusCommissionEmployee(
                        "Bob", "Lewis", "444-44-4444", 5000, .04, 300);
        System.out.println("Employees processed individually:");
        System.out.printf("%n%s%n%s: $%,.2f%n%n",
                salariedEmployee, "earned", salariedEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n",
                hourlyEmployee, "earned", hourlyEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n",
                commissionEmployee, "earned", commissionEmployee.earnings());
        System.out.printf("%s%n%s: $%,.2f%n%n",
                basePlusCommissionEmployee,
                "earned", basePlusCommissionEmployee.earnings());
// create four-element Employee array
        Employee[] employees = new Employee[4];
// initialize array with Employees
        employees[0] = salariedEmployee;
        employees[1] = hourlyEmployee;
        employees[2] = commissionEmployee;
        employees[3] = basePlusCommissionEmployee;
        System.out.printf("Employees processed polymorphically :%n%n");
// generically process each element in array employees
        for (Employee currentEmployee : employees)
        {
            System.out.println(currentEmployee); // invokes toString
// determine whether element is a BasePlusCommissionEmployee
            if (currentEmployee instanceof BasePlusCommissionEmployee)
            {
// downcast Employee reference to
// BasePlusCommissionEmployee reference
                BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;
                employee.setBaseSalary(1.10 * employee.getBaseSalary());
                System.out.printf(
                        "new base salary with 10%% increase is: $%,.2f%n",
                        employee.getBaseSalary());
            } // end if
            System.out.printf(
                    "earned $%,.2f%n%n", currentEmployee.earnings());
        } // end for
// get type name of each object in employees array
        for (int j = 0; j < employees.length; j++)
            System.out.printf("Employee %d is a %s%n", j,
                    employees[j].getClass().getSimpleName());
    } // end main
} // end class PayrollSystemTest

/*
Creating the Array of Employees
Line 44 declares employees and assigns it an array of four Employee variables. The
array indices are assigned different objects. These assignments are allowed, because a Salaried
Employee is an Employee, an HourlyEmployee is an Employee, a CommissionEmploy-
ee is an Employee and a BasePlusCommissionEmployee is an Employee. Therefore, we can
assign the references of SalariedEmployee, HourlyEmployee, CommissionEmployee and
BasePlusCommissionEmployee objects to superclass Employee variables, even though Employee
is an abstract class.

Polymorphically Processing Employees:
The loop iterate through array employees and invoke methods toString and earnings
with Employee variable currentEmployee, which is assigned the reference to a different
Employee in the array on each iteration. The output illustrates that the specific
methods for each class are indeed invoked. All calls to method toString and earnings are
resolved at execution(run) time, based on the type of the object to which currentEmployee
refers. This process is known as DYNAMIC BINDING RO LATE BINDING. For example, line 54
implicitly invokes method toString of the object to which currentEmployee refers. As a
result of dynamic binding, Java decides which class’s toString method to call at execution
time rather than at compile time. Only the methods of class Employee can be called via an
Employee variable (and Employee, of course, includes the methods of class Object). A superclass
reference can be used to invoke only methods of the superclass—the subclass method implementations are
invoked polymorphically.


Performing Type-Specific Operations on BasePlusCommissionEmployees:
We perform special processing on BasePlusCommissionEmployee objects—as we encounter these objects at
execution time, we increase their base salary by 10%. When processing objects polymorphically, we typically
do not need to worry about the specifics, but to adjust the base salary, we do have to determine the
specific type of Employee object at execution time. Line 56 uses the instanceof operator to determine
whether a particular Employee object’s type is BasePlusCommissionEmployee. The condition in line 56 is
true if the object referenced by currentEmployee is a BasePlusCommissionEmployee. This would also
be true for any object of a BasePlusCommissionEmployee subclass because of the is-a relationship a
subclass has with its superclass. Line 60 downcast currentEmployee from type Employee to type
BasePlusCommissionEmployee—this cast is allowed only if the object has an is-a relationship with
BasePlusCommissionEmployee. The condition at line 56 ensures that this is the case. This cast is required if
we’re to invoke subclass BasePlusCommissionEmployee methods getBaseSalary and setBaseSalary on the current
Employee object—as you’ll see momentarily, attempting to invoke a subclass-only method directly on a
superclass reference is a compilation error.

Common Programming Error 10.3
Assigning a superclass variable to a subclass variable is a compilation error.
Common Programming Error 10.4
When downcasting a reference, a ClassCastException occurs if the referenced object at ex-
ecution time does not have an is-a relationship with the type specified in the cast operator.
If the instanceof expression in line 49 is true, lines 53–60 perform the special pro-
cessing required for the BasePlusCommissionEmployee object. Using BasePlusCommis-
sionEmployee variable employee, line 56 invokes subclass-only methods getBaseSalary
and setBaseSalary to retrieve and update the employee’s base salary with the 10% raise.
 */