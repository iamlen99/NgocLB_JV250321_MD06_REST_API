package ra.edu.model.service;

import ra.edu.model.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    boolean save(Room room);
    boolean update(Room room, Integer roomId);
    boolean delete(int id);
    Room getRoomById(int id);
    boolean checkRoomPlaced(int id);
}
