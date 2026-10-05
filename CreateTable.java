import java.sql.*;

public class CreateTable {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Movie (" +
                "movie_name VARCHAR(100) PRIMARY KEY, " +
                "price DOUBLE)"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Customer (" +
                "customer_id INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "phone VARCHAR(15))"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Booking (" +
                "booking_id INT PRIMARY KEY, " +
                "customer_id INT, " +
                "movie_name VARCHAR(100), " +
                "seat_no VARCHAR(20), " +
                "quantity INT, " +
                "total_price DOUBLE)"
            );

            System.out.println("Tables created successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
