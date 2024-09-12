package Database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection connection = null;

    public static java.sql.Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/FileHiderDB?useSSL=false";
                String user = "root";  // Environment variable for the DB user
                String password = "Suyash@20040";  // Environment variable for the DB password
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection Closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
