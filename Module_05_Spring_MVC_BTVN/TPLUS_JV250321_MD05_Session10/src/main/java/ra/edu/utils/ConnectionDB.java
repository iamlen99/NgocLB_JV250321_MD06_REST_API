package ra.edu.utils;

import java.sql.*;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/md05_ss10";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "quxTNL43@";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Cannot connect to database: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close statement: " + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
