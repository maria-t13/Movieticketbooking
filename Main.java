import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            AllOperation.con = DBConnection.getConnection();

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n===== MOVIE TICKET BOOKING =====");
                System.out.println("1. Add Movie");
                System.out.println("2. View Movies");
                System.out.println("3. Book Ticket");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        AllOperation.addMovie();
                        break;

                    case 2:
                        AllOperation.viewMovies();
                        break;

                    case 3:
                        AllOperation.bookTicket();
                        break;

                    case 4:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 4);

            sc.close();
            AllOperation.con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
