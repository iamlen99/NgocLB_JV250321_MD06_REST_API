package ra.edu.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Booking;
import ra.edu.model.repository.BookingRepository;
import ra.edu.model.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public boolean saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
