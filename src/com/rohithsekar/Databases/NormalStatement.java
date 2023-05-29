package com.rohithsekar.Databases;
import java.sql.*;

public class NormalStatement
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
        String n = rs.getString("name");
        System.out.println(n);
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

            /*
            In the example above, the %-4s, %-10s, %-7s, %-12s, and %-6s are format specifiers that add padding to the
            respective columns. The hyphen (-) ensures left alignment within each column, and the number after the %
            specifies the width of each column.
            Adjust the column widths and the format specifiers according to your desired padding and alignment.
             */
        }
        st.close();
        con.close();
    }
}

