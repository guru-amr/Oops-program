package oop;
import java.sql.*;
public class ex11 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String user = "root";
        String password = "password";
        String query = "SELECT * FROM Student";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database Successfully!\n");
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("s_id\t|\ts_name\t|\ts_branch");
            System.out.println("--------------------------------------");
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println(
                    rs.getInt("s_id") + "\t|\t" +
                    rs.getString("s_name") + "\t|\t" +
                    rs.getString("s_branch")
                );
            }
            if (!hasData) {
                System.out.println("No results found.");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("SQLSyntaxErrorException: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        }
    }
}
