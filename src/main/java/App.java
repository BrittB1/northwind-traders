import java.sql.*;

// runs main program. lets UI and Database communicate
public class App {

    private final UserInterface ui;
    private final NorthwindDAO dao;

    public App() {
        this.ui = new UserInterface();
        this.dao = new NorthwindDAO();
    }

    public void run() {
        try {
            dao.connect();
            ui.displaySuccessMessage("Connected! Welcome to the Northwind Database");

            int choice;

            do {
                choice = ui.displayMainMenu();
                System.out.println();

                switch (choice) {
                    case 1 -> displayAllProducts();
                    case 2 -> ui.displayErrorMessage("");
                    case 0 -> ui.displaySuccessMessage("Exiting.. goodbye");
                    default -> ui.displayErrorMessage("Invalid option! Try again..");

                }

                if (choice != 0) {
                    ui.enterToContinue();

                }

            } while (choice != 0);

        } catch (SQLException e) {
            ui.displayErrorMessage("Couldn't connect to database..");
            e.printStackTrace();

        } finally {
            dao.disconnect();
            ui.close();
        }
    }

    private void displayAllProducts() {
        try {
            ResultSet results = dao.getAllProducts();

            ui.displayHeader("All Products");
            ui.displayProductTableHeader();

            int count = 0;

            while (results.next()) {
                int id = results.getInt("ProductID");
                String name = results.getString("ProductName");
                double price = results.getDouble("UnitPrice");
                int stock = results.getInt("UnitsInStock");

                ui.displayProductRow(id, name, price, stock);
                count++;
            }
            ui.displayFooter(count + " products displayed");
            results.close();

        } catch (SQLException e) {
            System.out.println("ERROR! Couldn't display products..");
            e.printStackTrace();
        }
    }

    // program entry point 'front door'
    public static void main(String[] args) {
        App a = new App();
        a.run();
    }
}
