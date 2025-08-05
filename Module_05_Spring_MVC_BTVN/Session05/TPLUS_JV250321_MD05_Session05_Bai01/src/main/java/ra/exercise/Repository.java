package ra.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Optional;

public class Repository {
    public Optional<Customer> getCustomer(String username, String password) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setGender(rs.getString("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setRole(Role.valueOf(rs.getString("role")));
                return Optional.of(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }
}
