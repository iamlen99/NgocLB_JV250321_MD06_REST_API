package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.request.RoomRequest;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.entity.RoomType;
import ra.edu.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Room>>> getAllRooms(
            @RequestParam(name = "type", required = false) RoomType type,
            @RequestParam(name = "status", required = false) RoomStatus status
            ) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Rooms successfully",
                roomService.getAllRooms(type, status),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Room>> createRoom(@Valid @ModelAttribute RoomRequest roomRequest) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Added Room successfully",
                roomService.addRoom(roomRequest),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Room>> updateRoom(
            @Valid @ModelAttribute RoomRequest roomRequest,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Updated Room successfully",
                roomService.updateRoom(id, roomRequest),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
