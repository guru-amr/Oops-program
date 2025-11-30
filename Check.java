package oop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Check {
    public static void main(String[] args) {
        // Database details
        String url = "jdbc:mysql://localhost:3306/testc";  // your DB name
        String user = "guru";  // your MySQL username
        String password = "kutty";  // your MySQL password

        // Data to insert
        String name = "Guru Raja";
        int age = 22;
        String department = "Computer Science";

        // SQL query
        String query = "INSERT INTO students (name, age, department) VALUES (?, ?, ?)";

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Step 3: Create PreparedStatement
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);

            // Step 4: Execute update
            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("✅ Data inserted successfully!");
            }

            // Step 5: Close connection
            ps.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database error!");
            e.printStackTrace();
        }
    }
}

