package ra.exercise.model.ulti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/md5_ss5";
    private static final String username = "root";
    private static final String password = "quxTNL43@";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection( url, username, password);
        } catch (Exception e) {
            System.out.println("Cannot connect to database: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn, Statement statement) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close connection: " + e.getMessage());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close statement: " + e.getMessage());
            }
        }
    }
}
