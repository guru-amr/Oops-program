package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchDataExample {
    public static void main(String[] args) {
        // Database details
        String url = "jdbc:mysql://localhost:3306/testc";
        String user = "guru";
        String password = "kutty"; // replace with your MySQL password

        try {
            // Step 1: Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Step 3: Create a statement object
            Statement stmt = conn.createStatement();

            // Step 4: Execute SQL query
            String query = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(query);

            // Step 5: Process the result
            System.out.println("ID | Name | Age | Department");
            System.out.println("--------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String dept = rs.getString("department");

                System.out.println(id + " | " + name + " | " + age + " | " + dept);
            }

            // Step 6: Close everything
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
