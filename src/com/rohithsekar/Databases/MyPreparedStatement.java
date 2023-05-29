package com.rohithsekar.Databases;
import java.sql.*;

public class MyPreparedStatement
{
    public static void main(String[] args) throws Exception
    {
        String url ="jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "password";
        String query = "insert into employee_table values(14, 'Steven', 'Male', 'Marketing', 29000)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,username,password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);          //execute update query
        System.out.println(count + " rows affected");
        String query1 = "select * from employee_table";
        ResultSet rs1 = st.executeQuery(query1);
        while(rs1.next())
        {
            int id = rs1.getInt(1);
            String name = rs1.getString(2);
            String gender = rs1.getString(3);
            String department = rs1.getString(4);
            int salary = rs1.getInt(5);
            //Storing the padding format into the output string
            String userData = String.format("%-4s %-10s %-7s %-12s %-6s", id, name, gender, department, salary);
            System.out.println(userData);
        }

        String query2 = "insert into employee_table values(?,?,?,?,?)";
        java.sql.PreparedStatement ps = con.prepareStatement(query2);
        ps.setInt(1,15);
        ps.setString(2,"Lawrence");
        ps.setString(3,"Male");
        ps.setString(4,"Management");
        ps.setInt(5,31000);
        count = ps.executeUpdate();                         //no need to pass the query here.
        System.out.println(count + " rows affected");

        st.close();
        con.close();

    }

}
