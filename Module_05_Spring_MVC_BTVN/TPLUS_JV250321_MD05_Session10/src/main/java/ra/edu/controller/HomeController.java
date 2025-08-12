package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");

        // Kiểm tra login và quyền CUSTOMER
        if (customer == null || !"CUSTOMER".equals(customer.getRole().toString())) {
            model.addAttribute("err", "Bạn cần đăng nhập với quyền CUSTOMER để xem danh sách phòng!");
            model.addAttribute("dtoLogin", new LoginDTO());
            return "login"; // hoặc redirect về trang login
        }

        // Lọc phòng không bị xóa
        List<Room> availableRooms = roomService.findAll()
                .stream()
                .filter(room -> !room.isDelete()) // chỉ lấy phòng isDelete = false
                .collect(Collectors.toList());

        model.addAttribute("rooms", availableRooms);
        return "home";
    }

    @PostMapping("/book/{id}")
    public String bookRoom(@PathVariable("id") Integer id, HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null || !"CUSTOMER".equals(customer.getRole().toString())) {
            model.addAttribute("err", "Bạn cần đăng nhập với quyền CUSTOMER để đặt phòng!");
            return "login";
        }

        Room room = roomService.getRoomById(id);
        if (room.getStatus().toString().equals("PLACED")) {
            model.addAttribute("err", "Phòng đã được đặt!");
            return "redirect:/home";
        }

        room.setStatus(RoomStatus.PLACED);
        roomService.update(room, id);

        model.addAttribute("message", "Đặt phòng thành công!");
        return "redirect:/home";
    }
}
