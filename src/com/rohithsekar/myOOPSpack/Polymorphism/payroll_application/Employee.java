package com.rohithsekar.myOOPSpack.Polymorphism.payroll_application;

/*
                                earnings 				            toString

Employee                        abstract				    firstName lastName
                                                            social security number: SSN


Salaried-Employee		        weeklySalary				firstName lastName
									                        social security number: SSN
									                        weekly salary: weeklySalary


Hourly-Employee			        if (hours <= 40)			firstName lastName
                                wage * hours				social security number: SSN
                                else if (hours > 40)	    hourly wage: wage; hours worked: hours
                                {
                                40 * wage +
                                ( hours - 40 ) *
                                wage * 1.5
                                }



Commission-Employee		       commissionRate *              firstName lastName
				               grossSales				     social security number: SSN
                                                             gross sales: grossSales;
                                                             commission rate: commissionRate



BasePlus-Commission-          (commissionRate * grossSales)  firstName lastName
			                  + baseSalary				     social security number: SSN
                                                             gross sales: grossSales;
                                                             commission rate: commissionRate;
                                                             base salary: baseSalary

 | Polymorphic contract for the Employee hierarchy classes.
 */

public abstract class Employee
{
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    // constructor
    public Employee(String firstName, String lastName, String socialSecurityNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
    // return first name
    public String getFirstName()
    {
        return firstName;
    }
    // return last name
    public String getLastName()
    {
        return lastName;
    }
    // return social security number
    public String getSocialSecurityNumber()
    {
        return socialSecurityNumber;
    }
    // return String representation of Employee object
    @Override
    public String toString()
    {
        return String.format("%s %s%n social security number: %s", getFirstName(), getLastName(), getSocialSecurityNumber());
    }
    // abstract method must be overridden by concrete subclasses
    public abstract double earnings(); // no implementation here, since each employee's earnings are computed differently
} // end abstract class Employee


