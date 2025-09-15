package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.request.UserRequest;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Booking;
import ra.edu.model.entity.User;
import ra.edu.service.BookingService;
import ra.edu.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<User>>> getAllUsers() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Users successfully",
                userService.getAllUsers(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<User>> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Added user successfully",
                userService.createUser(userRequest),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<User>> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Found user successfully",
                userService.findUserById(id),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping("/{id}/bookings")
    public ResponseEntity<ApiDataResponse<List<Booking>>> getBookingsByUser(@PathVariable Long id) {
        List<Booking> bookings = bookingService.getBookingsByUserId(id);

        return ResponseEntity.ok(new ApiDataResponse<>(
                true,
                "Retrieved " + bookings.size() + " bookings for user " + id,
                bookings,
                null,
                HttpStatus.OK
        ));
    }
}
