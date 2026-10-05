import java.sql.*;
import java.util.*;

public class AllOperation {

    static Connection con;

    // 1. Add Movie
    public static void addMovie() throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Movie Name: ");
        String name = sc.nextLine();

        System.out.print("Ticket Price: ");
        double price = sc.nextDouble();

        // Automatically generate Movie ID
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT IFNULL(MAX(movie_id), 0) + 1 FROM Movie"
        );

        rs.next();
        int movieId = rs.getInt(1);

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO Movie (movie_id, movie_name, price) VALUES (?, ?, ?)"
        );

        ps.setInt(1, movieId);
        ps.setString(2, name);
        ps.setDouble(3, price);

        ps.executeUpdate();

        System.out.println("Movie added successfully!");
    }

    // 2. View Movies
    public static void viewMovies() throws Exception {

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT movie_id, movie_name, price FROM Movie"
        );

        System.out.println("\n--- AVAILABLE MOVIES ---");

        while (rs.next()) {

            System.out.println(
                "Movie ID: " + rs.getInt("movie_id") +
                " | Movie: " + rs.getString("movie_name") +
                " | Price: ₹" + rs.getDouble("price")
            );
        }
    }

    // 3. Book Ticket
    public static void bookTicket() throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Booking ID: ");
        int bookingId = sc.nextInt();

        System.out.print("Customer ID: ");
        int customerId = sc.nextInt();

        sc.nextLine();

        System.out.print("Movie Name: ");
        String movieName = sc.nextLine();

        System.out.print("Seat Number: ");
        String seat = sc.nextLine();

        System.out.print("Number of Tickets: ");
        int quantity = sc.nextInt();

        // Find movie price using movie name
        PreparedStatement ps1 = con.prepareStatement(
            "SELECT price FROM Movie WHERE movie_name = ?"
        );

        ps1.setString(1, movieName);

        ResultSet rs = ps1.executeQuery();

        if (rs.next()) {

            double price = rs.getDouble("price");
            double total = price * quantity;

            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO Booking " +
                "(booking_id, customer_id, movie_name, seat_no, quantity, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            ps2.setInt(1, bookingId);
            ps2.setInt(2, customerId);
            ps2.setString(3, movieName);
            ps2.setString(4, seat);
            ps2.setInt(5, quantity);
            ps2.setDouble(6, total);

            ps2.executeUpdate();

            System.out.println("\nTicket booked successfully!");
            System.out.println("Movie: " + movieName);
            System.out.println("Seat: " + seat);
            System.out.println("Tickets: " + quantity);
            System.out.println("Total Price: ₹" + total);

        } else {

            System.out.println("Movie not found!");
        }
    }
}
