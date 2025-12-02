import java.sql.*;

// Data Access Object: SOC. Separate logic for CRUD operations
public class NorthwindDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/northwind";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yearup";

    private Connection c;

    // throws SQLException: handles database access errors
    public void connect() throws SQLException {
        c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public ResultSet getAllProducts() throws SQLException {
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
        PreparedStatement ps = c.prepareStatement(query);
        return ps.executeQuery();
    }

    public ResultSet getAllCustomers() throws SQLException {
        String query = "SELECT ContactName, CompanyName, City, Country, Phone " +
                "FROM Customers " +
                "ORDER BY Country";
        PreparedStatement ps = c.prepareStatement(query);

        return ps.executeQuery();
    }
    public ResultSet getAllCategories() throws SQLException {
        String query = "SELECT CategoryID, CategoryName " +
                "FROM Categories " +
                "ORDER BY CategoryID";
        PreparedStatement ps = c.prepareStatement(query);

        return ps.executeQuery();
    }
    public ResultSet getProductsByCategory(int categoryId) throws SQLException {
String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock " +
        "FROM Products " +
        "WHERE CategoryID = ? " +
        "ORDER BY ProductID";

PreparedStatement ps = c.prepareStatement(query);
ps.setInt(1,categoryId);
return  ps.executeQuery();
    }

    public void disconnect() {
        if (c != null) {

            try {
                c.close();
            } catch (SQLException e) {
                System.out.println("ERROR! Connection not closed..");
                e.printStackTrace();
            }
        }
    }
}
