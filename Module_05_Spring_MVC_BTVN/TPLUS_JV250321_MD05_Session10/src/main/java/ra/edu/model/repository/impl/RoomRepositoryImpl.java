package ra.edu.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.repository.RoomRepository;
import ra.edu.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public List<Room> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("select * from rooms");
            ResultSet rs = preStmt.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setRoomName(rs.getString("room_name"));
                r.setRoomType(rs.getString("room_type"));
                r.setPrice(rs.getDouble("price"));
                r.setStatus(RoomStatus.valueOf(rs.getString("status")));
                r.setDelete(rs.getBoolean("isDelete"));
                r.setImage(rs.getString("image"));
                rooms.add(r);
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
    }

    @Override
    public boolean save(Room room) {
        Connection conn = null;
        PreparedStatement preStmt = null;

        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("insert into rooms(room_name,room_type,status,isDelete,price,image) values (?,?,?,?,?,?)");
            preStmt.setString(1, room.getRoomName());
            preStmt.setString(2, room.getRoomType());
            preStmt.setString(3, room.getStatus().toString());
            preStmt.setBoolean(4, room.getDelete());
            preStmt.setDouble(5, room.getPrice());
            preStmt.setString(6, room.getImage());
            int i = preStmt.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
        return false;
    }

    @Override
    public boolean update(Room room, Integer roomId) {
        Connection conn = null;
        PreparedStatement preStmt = null;

        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("update rooms set room_name=?,room_type=?,status=?,isDelete=?,price=?,image=? where id=?");
            preStmt.setString(1, room.getRoomName());
            preStmt.setString(2, room.getRoomType());
            preStmt.setString(3, room.getStatus().toString());
            preStmt.setBoolean(4, room.getDelete());
            preStmt.setDouble(5, room.getPrice());
            preStmt.setString(6, room.getImage());
            preStmt.setInt(7, roomId);
            int i = preStmt.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("delete from where id=?");
            preStmt.setInt(1, id);
            int i = preStmt.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
        return false;
    }

    @Override
    public Room findById(int id) {
        Room r = null;
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("select * from rooms where id=?");
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                r = new Room();
                r.setId(rs.getInt("id"));
                r.setRoomName(rs.getString("room_name"));
                r.setRoomType(rs.getString("room_type"));
                r.setPrice(rs.getDouble("price"));
                r.setStatus(RoomStatus.valueOf(rs.getString("status")));
                r.setDelete(rs.getBoolean("isDelete"));
                r.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
        return r;
    }

    @Override
    public boolean checkRoomPlaced(int id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement("select * from rooms where id=? and status = 'PLACED'");
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(preStmt, conn);
        }
    }
}
