package ra.edu.model.repository;

import ra.edu.model.entity.Booking;

public interface BookingRepository {
    boolean save(Booking booking);
}
