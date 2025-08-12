package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Booking;
import ra.edu.model.entity.Room;
import ra.edu.model.service.BookingService;
import ra.edu.model.service.RoomService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/home")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/bookings")
    public String showBookingForm(@RequestParam("roomId") Integer roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            model.addAttribute("error", "Phòng không tồn tại");
            return "roomList"; // trang danh sách phòng (bạn tự tạo hoặc đổi theo project)
        }

        Booking booking = new Booking();
        booking.setRoomId(roomId);
        booking.setCustomerId(1L);

        model.addAttribute("room", room);
        model.addAttribute("booking", booking);
        return "bookRoom";
    }

    @PostMapping("/bookings")
    public String processBooking(@ModelAttribute Booking booking, Model model) {
        System.out.println("POST customerId = " + booking.getCustomerId());
        Room room = roomService.getRoomById(booking.getRoomId());
        if (room == null) {
            model.addAttribute("error", "Phòng không tồn tại");
            model.addAttribute("room", null);
            return "bookRoom";
        }

        if (booking.getCheckIn() == null || booking.getCheckOut() == null) {
            model.addAttribute("room", room);
            model.addAttribute("error", "Vui lòng chọn ngày nhận và trả phòng");
            return "bookRoom";
        }

        if (!booking.getCheckOut().isAfter(booking.getCheckIn())) {
            model.addAttribute("room", room);
            model.addAttribute("error", "Ngày trả phòng phải sau ngày nhận phòng");
            return "bookRoom";
        }

        long days = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
        if (days < 1) {
            model.addAttribute("room", room);
            model.addAttribute("error", "Thời gian thuê phòng phải ít nhất 1 ngày");
            return "bookRoom";
        }

        double totalPrice = days * room.getPrice();
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("PENDING");

        bookingService.saveBooking(booking);

        return "redirect:/home/rooms?success=booking";
    }

    @GetMapping("/rooms")
    public String listRooms(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "roomList";
    }

}