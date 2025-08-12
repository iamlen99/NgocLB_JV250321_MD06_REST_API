package ra.edu.model.repository;

import ra.edu.model.entity.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> findAll();
    boolean save(Room room);
    boolean update(Room room, Integer roomId);
    boolean delete(int id);
    Room findById(int id);
    boolean checkRoomPlaced(int id);
}
