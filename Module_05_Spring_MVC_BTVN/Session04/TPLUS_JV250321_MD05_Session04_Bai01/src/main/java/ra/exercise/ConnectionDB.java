package ra.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String port = "jdbc:mysql://localhost:3306/";
    private static final String databaseName = "database";
    private static final String username = "root";
    private static final String password = "";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(port + databaseName, username, password);
        } catch (Exception e) {
            System.out.println("Cannot connect to database: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
