package ra.exercise.model.util;

import java.sql.*;

public class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/md05_ss07_db";
    private static final String USER = "root";
    private static final String PASS = "quxTNL43@";

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Co loi khi ket noi voi co so du lieu: " + e.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection connection, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw  new RuntimeException(e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw  new RuntimeException(e);
            }
        }
    }
}
