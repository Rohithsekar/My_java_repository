package com.rohithsekar.Databases;
import java.util.Scanner;
import java.sql.*;


public class DAO
{
    public static void main(String[] args)
    {
        System.out.println("Enter C for adding a student, R for retrieve info about student, U for updating student info, D for delete student info");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine().toUpperCase().trim();

            if(s.equals("C") || s.equals("R") || s.equals("U") || s.equals("D"))
            {
                int roll_no;
                String s1;
                Scanner scanner1;
                switch (s)
                {
                    case "C":

                        System.out.print("Enter roll_no of student:");
                        Scanner scanner = new Scanner(System.in);
                        s1 = scanner.nextLine();
                        try
                        {
                            roll_no =Integer.parseInt(s1);
                            System.out.print("Enter name of student:");
                            scanner = new Scanner(System.in);
                            String name = scanner.nextLine();
                            Student student = new Student(roll_no,name);
                            Student.createStudent(student);
                        }
                        catch (IllegalArgumentException e)
                        {
                            System.out.println("Invalid input.");
                        }
                        break;

                    case "R":
                        System.out.print("Enter roll number of student:");
                        scanner1 = new Scanner(System.in);
                        String s2 = scanner1.nextLine();
                        try
                        {
                           roll_no = Integer.parseInt(s2);
                           Student stud =Student.getStudentByRoll_no(roll_no);
                           try
                           {
                               System.out.println(stud.getName());
                           }
                           catch (NullPointerException e)
                           {
                               System.out.println("No student found for the roll number");
                           }
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println("Invalid input");
                        }
                        break;

                    case "U":
                        System.out.print("Enter roll number of student:");
                        scanner1 = new Scanner(System.in);
                        s1 = scanner1.nextLine();
                        try
                        {
                            roll_no = Integer.parseInt(s1);
                            Student stud =Student.getStudentByRoll_no(roll_no);
                            try
                            {
                                System.out.println("Enter the name to be updated with:");
                                scanner1 = new Scanner(System.in);
                                s1 = scanner1.nextLine().trim();
                                if(!s1.isEmpty())
                                {
                                    stud.setName(s1);
                                    Student.updateStudent(stud);
                                }
                                else
                                {
                                    System.out.println("Name cannot be empty");
                                }

                            }
                            catch (NullPointerException e)
                            {
                                System.out.println("No student found for the roll number");
                            }
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println("Invalid input");
                        }
                        break;
                    case "D":
                        System.out.print("Enter roll number of student:");
                        scanner1 = new Scanner(System.in);
                        s1 = scanner1.nextLine();
                        try
                        {
                            roll_no = Integer.parseInt(s1);
                            Student stud =Student.getStudentByRoll_no(roll_no);
                            try
                            {
                                Student.deleteStudent(stud);
                            }
                            catch (NullPointerException e)
                            {
                                System.out.println("No student found for the roll number");
                            }
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println("Invalid input");
                        }

                }
            }
        }


    }



class Student
{
    private  int roll_no ;
    private  String name ;

    public Student()
    {
        //   default constructor
    }


    public Student(int roll_no, String name)   //overriding
    {
        this.roll_no = roll_no;
        this.name = name;
    }

    public int getRoll_no()
    {
        return roll_no;
    }

    public void setRoll_no(int roll_no)
    {
        this.roll_no = roll_no;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    static void createStudent(Student student)                  //creates a student entry in stud_table in students database
    {
        try {
            String url = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password = "password";
            String query = "insert into stud_table values(?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, student.getRoll_no());
            ps.setString(2, student.getName());
            int count = ps.executeUpdate();                         //no need to pass the query here.
            System.out.println(count + " rows affected");
            ps.close();
            con.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not found.");
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception.");
        }
    }

   static  Student getStudentByRoll_no(int roll_no)
    {
        try
        {
            String url = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password = "password";
            String query = "select name from stud_table where roll_no="+roll_no;
            Student s = new Student();
            s.setRoll_no(roll_no);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String n = rs.getString("name");
            s.setName(n);
            rs.close();
            con.close();
            return s;
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not found.");
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception.");
        }
        return null;

    }

    static void updateStudent(Student student)
    {
        try {
            String url = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password = "password";
            String query = "update stud_table set name=? where roll_no=?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, student.name);
            ps.setInt(2, student.roll_no);
            int count = ps.executeUpdate();                         //no need to pass the query here.
            System.out.println(count + " rows affected");
            ps.close();
            con.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not found.");
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception.");
        }
    }

   static void deleteStudent(Student student)
    {
        try {
            String url = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password = "password";
            String query = "delete from stud_table where roll_no=?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, student.roll_no);
            int count = ps.executeUpdate();                         //no need to pass the query here.
            System.out.println(count + " rows affected");
            ps.close();
            con.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not found.");
        }
        catch (SQLException e)
        {
            System.out.println("SQL exception.");
        }

    }

    }


