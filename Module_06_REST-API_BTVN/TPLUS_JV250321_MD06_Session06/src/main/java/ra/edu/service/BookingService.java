package ra.edu.service;

import ra.edu.model.dto.request.BookingRequest;
import ra.edu.model.dto.request.UserRequest;
import ra.edu.model.entity.Booking;
import ra.edu.model.entity.User;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest request);
    Booking cancelBooking(Long bookingId);
    List<Booking> getBookingsByUserId(Long userId);
}
