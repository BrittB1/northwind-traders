import java.sql.*;

public class App {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        String query = "SELECT ProductName FROM products";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Yay! Connected to Northwind Database");

            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery(query);

            System.out.println("Northwind Products");
            while (r.next()) {

                String productName = r.getString("ProductName");
                System.out.println(productName);
            }

        } catch (SQLException e) {
            System.out.println("Couldn't connect..");
            e.printStackTrace();
        }
    }
}
