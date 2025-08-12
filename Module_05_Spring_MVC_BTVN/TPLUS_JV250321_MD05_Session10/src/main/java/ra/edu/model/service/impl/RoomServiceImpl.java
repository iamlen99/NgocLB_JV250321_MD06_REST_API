package ra.edu.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Room;
import ra.edu.model.repository.RoomRepository;
import ra.edu.model.service.RoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public boolean save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public boolean update(Room room, Integer roomId) {
        return roomRepository.update(room, roomId);
    }

    @Override
    public boolean delete(int id) {
        return roomRepository.delete(id);
    }

    @Override
    public Room getRoomById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public boolean checkRoomPlaced(int id) {
        return roomRepository.checkRoomPlaced(id);
    }
}
