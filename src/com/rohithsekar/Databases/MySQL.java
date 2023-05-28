package com.rohithsekar.Databases;
import java.sql.*;

public class MySQL
{
    public static void main(String[] args) throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "password";
        String query = "select name from employee_table where id=1";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,username,password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String name = rs.getString("name");
        System.out.println(name);
        st.close();
        con.close();
    }
}
