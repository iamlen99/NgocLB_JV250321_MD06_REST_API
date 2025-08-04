package ra.exercise.ulti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String port = "jdbc:mysql://localhost:3306/";
    private static final String databaseName = "md05_ss04";
    private static final String username = "root";
    private static final String password = "quxTNL43@";

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(port + databaseName, username, password);
        } catch (Exception e) {
            System.out.println("Cannot connect to database: " + e.getMessage());
        }
        return null;
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

    public static void closePrepareStatement(PreparedStatement preStmt) {
        if (preStmt != null) {
            try {
                preStmt.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close Prepare Statement: " + e.getMessage());
            }
        }
    }
}
