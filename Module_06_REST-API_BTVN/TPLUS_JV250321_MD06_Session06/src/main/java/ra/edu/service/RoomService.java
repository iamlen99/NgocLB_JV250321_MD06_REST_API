package ra.edu.service;

import ra.edu.model.dto.request.RoomRequest;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.entity.RoomType;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms(RoomType type, RoomStatus status);
    Room addRoom(RoomRequest roomRequest);
    Room updateRoom(Long id, RoomRequest roomRequest);
    Room getRoomById(Long id);
    void deleteRoomById(Long id);
}
