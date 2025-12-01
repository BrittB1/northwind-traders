import java.sql.*;

public class App {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        String query = "SELECT ProductID, ProductName, UnitPrice,UnitsInStock FROM products";

        try {
            Connection c = DriverManager.getConnection(url,username,password);
            System.out.println("Yay! Connected to Northwind Database");

            // old statement, allows for injection
           // Statement s = c.createStatement();
            //ResultSet r = s.executeQuery(query);

            PreparedStatement ps = c.prepareStatement("SELECT ProductID, ProductName,UnitPrice, UnitsInStock FROM Products");
            ResultSet r = ps.executeQuery();

            System.out.println("Northwind Products");
            while (r.next()) {
                int productId = r.getInt("ProductID");
                String productName = r.getString("ProductName");
                double unitPrice = r.getDouble("UnitPrice");
                int unitsInStock = r.getInt("UnitsInStock");

                System.out.println("Product ID: " + productId);
                System.out.println("Name: " + productName);
                System.out.println("Price: $" + unitPrice);
                System.out.println("In Stock: " + unitsInStock);
                System.out.println("_________________________");
            }
            r.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            System.out.println("Couldn't connect..");
            e.printStackTrace();
        }
    }
}
