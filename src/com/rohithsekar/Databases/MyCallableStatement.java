package com.rohithsekar.Databases;
<<<<<<< HEAD
import java.sql.*;

//This class demonstrates simple callable statement with no parameters and no return type
public class MyCallableStatement
{
    private static final String url = "jdbc:mysql://localhost:3306/students";
    private static final  String username = "root";
    private static final String password = "password";
    private static Connection con = null;
    private static CallableStatement cst = null;


    public MyCallableStatement()  //constructor
    {
        try
        {
          con = DriverManager.getConnection(url,username,password);
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception occurred");
        }
    }

    public static void main(String[] args) throws Exception
    {
        MyCallableStatement mcs = new MyCallableStatement();
        mcs. simpleStoredProcedure();  //simple stored procedure with no in and out parameters
        mcs.storedProcedureWithInParameter();
        mcs.storedProcedureWithOutParameter();
        mcs.storedProcedureWithIn_Out_Parameter();


        cst.close();
        con.close();
    }

     void simpleStoredProcedure () throws Exception
     {
         System.out.println("Inside simpleStoredProcedure method");
        //the con.prepareCall method returns a CallableStatement object
        cst = con.prepareCall("{call gettable()}");  //calling the stored procedure using prepareCall method of connection interface
        //We need to use either the executeQuery or executeUpdate method depending on the querying operation carried
        //out in the Stored Procedure. Since the gettable() stored procedure simply prints the table, we use
        //executeQuery in this example
        ResultSet rs = cst.executeQuery();
        String column_header = String.format("%-10s %-6s","roll_no","name");
        System.out.println(column_header);

        while(rs.next())
        {
            int roll_no = rs.getInt(1);
            String name = rs.getString(2);
            String userData = String.format("%-10s %-6s",roll_no,name);
            System.out.println(userData);
        }


    }

    void storedProcedureWithInParameter() throws Exception
    {
        System.out.println("Inside storedProcedureWithInParameter method");
        String department = "Mechanical";
       cst = con.prepareCall("{call getStudentsByDepartment(?)}");
       cst.setString(1,department);
        ResultSet rs = cst.executeQuery();

        String column_header = String.format("%-10s %-6s","roll_no","name");
        System.out.println(column_header);

        while(rs.next())
        {
            int roll_no = rs.getInt(1);
            String name = rs.getString(2);
            String userData = String.format("%-10s %-6s",roll_no,name);
            System.out.println(userData);
        }

    }

    void storedProcedureWithOutParameter() throws Exception
    {
        System.out.println("Inside storedProcedureWithOutParameter method");
        cst = con.prepareCall("{call getStudentCount(?)}");
        cst.registerOutParameter(1,Types.INTEGER);
        cst.executeUpdate();
        System.out.println(cst.getInt(1));
    }

    void storedProcedureWithIn_Out_Parameter() throws Exception
    {
        System.out.println("Inside storedProcedureWithIn_Out_Parameter method");
        int roll_no = 2;
        cst = con.prepareCall("{call getNameByRoll_No(?,?)}");
        cst.setInt(1,roll_no);
        cst.registerOutParameter(2,Types.VARCHAR);
        //we want to use the executeUpdate() to achieve outputting the result to the session variable(a varchar here)
        cst.executeUpdate();
        System.out.println(cst.getString(2));
    }

}

=======

public class MyCallableStatement
{

}
>>>>>>> origin/master
