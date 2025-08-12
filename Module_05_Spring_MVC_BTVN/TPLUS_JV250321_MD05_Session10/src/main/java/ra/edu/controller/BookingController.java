package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.model.entity.Booking;
import ra.edu.model.service.BookingService;

@Controller
@RequestMapping("/home")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/bookings")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "bookRoom";
    }

    @PostMapping("/bookings")
    public String processBooking(@ModelAttribute Booking booking, Model model) {
        if (booking.getGuests() <= 0) {
            model.addAttribute("error", "Số lượng khách phải > 0");
            return "bookRoom";
        }
        bookingService.saveBooking(booking);
        model.addAttribute("message", "Đặt phòng thành công!");
        return "bookingSuccess";
    }
}
