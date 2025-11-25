import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    args[0],
                    args[1]
            );



            String query = """
                SELECT ProductId, ProductName, UnitPrice, UnitsInStock from products
                """;

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Id   Name                                   Price   Stock");
            System.out.println("------------------------------------------------------------");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                int stock = resultSet.getInt(4);
                System.out.printf("%-4d %-35s %8.2f %6d%n", id, name, price, stock);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
