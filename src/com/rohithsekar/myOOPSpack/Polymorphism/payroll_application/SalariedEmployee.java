package com.rohithsekar.myOOPSpack.Polymorphism.payroll_application;

public class SalariedEmployee extends Employee
{

    private double weeklySalary;
    // constructor
    public SalariedEmployee(String firstName, String lastName,
                            String socialSecurityNumber, double weeklySalary)
    {
        super(firstName, lastName, socialSecurityNumber);
        if (weeklySalary < 0.0)
            throw new IllegalArgumentException(
                    "Weekly salary must be >= 0.0");
        this.weeklySalary = weeklySalary;
    }
    // set salary
    public void setWeeklySalary(double weeklySalary)
    {
        if (weeklySalary < 0.0)
            throw new IllegalArgumentException(
                    "Weekly salary must be >= 0.0");
        this.weeklySalary = weeklySalary;
    }
    // return salary
    public double getWeeklySalary()
    {
        return weeklySalary;
    }
    // calculate earnings; override abstract method earnings in Employee
    @Override
    public double earnings()
    {
        return getWeeklySalary();
    }
    // return String representation of SalariedEmployee object

    //Demonstrates code re-usability
    @Override
    public String toString()
    {
        //printing the general features that all employees have in common using the superclass' toString method
        //and only using the class specific methods to print class specific details.
        return String.format("salaried employee: %s%n%s: $%,.2f", super.toString(), "weekly salary", getWeeklySalary());
    }
} // end class SalariedEmployee

