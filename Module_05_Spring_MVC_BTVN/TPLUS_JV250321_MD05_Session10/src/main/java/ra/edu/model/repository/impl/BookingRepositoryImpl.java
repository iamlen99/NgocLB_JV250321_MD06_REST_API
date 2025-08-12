package ra.edu.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Booking;
import ra.edu.model.repository.BookingRepository;
import ra.edu.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class BookingRepositoryImpl implements BookingRepository {
    @Override
    public boolean save(Booking booking) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO bookings (customer_id, room_id, check_in, check_out, total_price, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionDB.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, booking.getCustomerId());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, Date.valueOf(booking.getCheckIn()));
            ps.setDate(4, Date.valueOf(booking.getCheckOut()));
            ps.setDouble(5, booking.getTotalPrice());
            ps.setString(6, booking.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(pstmt, conn);
        }
        return false;
    }
}
