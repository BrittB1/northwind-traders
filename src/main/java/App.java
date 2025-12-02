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
                    case 2 -> ui.displayAllCustomers();
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

        try (ResultSet rs = dao.getAllProducts()) {

            ui.displayHeader("All Products");
            ui.displayProductTableHeader();

            int count = 0;

            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                double price = rs.getDouble("UnitPrice");
                int stock = rs.getInt("UnitsInStock");

                ui.displayProductRow(id, name, price, stock);
                count++;
            }
            ui.displayFooter(count + " products displayed");

        } catch (SQLException e) {
            ui.displayErrorMessage("ERROR! Couldn't display products..");
            e.printStackTrace();
        }
    }

    private void displayAllCustomers() {

        try (ResultSet rs = dao.getAllCustomers()) {
            ui.displayHeader("All Customers by Country");
            ui.displayCustomerTableHeader();


            String currentCountry = "";
            int count = 0;

            while (rs.next()) {
                String contactName = rs.getString("ContactName");
                String companyName = rs.getString("CompanyName");
                String city = rs.getString("City");
                String country = rs.getString("Country");
                String phone = rs.getString("Phone");

                if (!country.equals(currentCountry)) {
                    if (!currentCountry.isEmpty())
                        System.out.println();
                }
                currentCountry = country;
            }
            System.out.printf("\"%-25s %-30s %-20s %-15s %-15s%n",
                    "Contact Name", "Company Name", "City", "Country", "Phone" );
            count++;
        }
        ui.displayFooter(count + " customers displayed");

    } catch (SQLException e) {
        ui.displayErrorMessage("ERROR! Couldn't display products..");
        e.printStackTrace();
    }

    // program entry point 'front door'
    public static void main(String[] args) {
        App a = new App();
        a.run();
    }
}
