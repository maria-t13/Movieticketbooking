import java.sql.*;

public class CreateDatabase {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/",
            "root",
            "Maria@13"
        );

        Statement st = con.createStatement();

        st.executeUpdate("CREATE DATABASE IF NOT EXISTS MovieBooking");

        System.out.println("Database created successfully");

        con.close();
    }
}
