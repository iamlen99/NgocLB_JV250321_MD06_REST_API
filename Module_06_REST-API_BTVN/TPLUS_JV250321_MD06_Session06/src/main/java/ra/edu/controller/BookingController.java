package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.request.BookingRequest;
import ra.edu.model.dto.request.UserRequest;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Booking;
import ra.edu.model.entity.User;
import ra.edu.service.BookingService;
import ra.edu.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Booking>> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.createBooking(bookingRequest);

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Booking created successfully",
                booking,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiDataResponse<Booking>> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(new ApiDataResponse<>(
                true,
                "Booking cancelled successfully",
                booking,
                null,
                HttpStatus.OK
        ));
    }
}
