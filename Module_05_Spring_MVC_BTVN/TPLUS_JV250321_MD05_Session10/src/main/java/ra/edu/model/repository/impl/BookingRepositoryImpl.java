package ra.edu.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Booking;
import ra.edu.model.repository.BookingRepository;
import ra.edu.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class BookingRepositoryImpl implements BookingRepository {
    @Override
    public void save(Booking booking) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String sql = "INSERT INTO bookings (room_id, customer_id, check_in_date, check_out_date, guests, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(sql) ;
                preStmt.setInt(1, booking.getRoomId());
                preStmt.setInt(2, booking.getCustomerId());
                preStmt.setString(3, booking.getCheckInDate());
                preStmt.setString(4, booking.getCheckOutDate());
                preStmt.setInt(5, booking.getGuests());
                preStmt.setString(6, booking.getNotes());
                preStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
    }
}
