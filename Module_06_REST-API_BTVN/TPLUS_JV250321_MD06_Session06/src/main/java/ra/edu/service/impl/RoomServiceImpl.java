package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.model.dto.request.RoomRequest;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.entity.RoomType;
import ra.edu.repository.RoomRepository;
import ra.edu.service.RoomService;
import ra.edu.storage.CloudinaryService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Room> getAllRooms(RoomType type, RoomStatus status) {
        return roomRepository.findAllByTypeAndStatus(type, status);
    }

    @Override
    public Room addRoom(RoomRequest roomRequest) {
        if (roomRepository.existsByRoomNumber(roomRequest.getRoomNumber())) {
            throw new RuntimeException("RoomNumber already exists");
        }

        MultipartFile file = roomRequest.getImageFile();
        String image = null;
        if (file != null && !file.isEmpty()) {
            try {
                image = cloudinaryService.uploadImage(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Room room = Room.builder()
                .roomNumber(roomRequest.getRoomNumber())
                .type(roomRequest.getType())
                .price(roomRequest.getPrice())
                .capacity(roomRequest.getCapacity())
                .status(roomRequest.getStatus())
                .image(image)
                .build();
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, RoomRequest roomRequest) {
        Room existingRoom = getRoomById(id);

        roomRepository.findByRoomNumber(roomRequest.getRoomNumber())
                .ifPresent(foundRoom -> {
                    if(!foundRoom.getId().equals(id)){
                        throw new RuntimeException("Room already exists");
                    }
                });

        MultipartFile file = roomRequest.getImageFile();
        String image = existingRoom.getImage();
        if (file != null && !file.isEmpty()) {
            try {
                image = cloudinaryService.uploadImage(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        existingRoom.setRoomNumber(roomRequest.getRoomNumber());
        existingRoom.setType(roomRequest.getType());
        existingRoom.setPrice(roomRequest.getPrice());
        existingRoom.setCapacity(roomRequest.getCapacity());
        existingRoom.setStatus(roomRequest.getStatus());
        existingRoom.setImage(image);
        return roomRepository.save(existingRoom);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room not found by id: " + id));
    }

    @Override
    public void deleteRoomById(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
}
