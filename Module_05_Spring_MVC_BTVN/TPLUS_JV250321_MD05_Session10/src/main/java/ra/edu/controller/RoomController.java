package ra.edu.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.model.entity.Customer;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.service.RoomService;
import ra.edu.storage.CloudinaryService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String roomList(Model model) {
        List<Room> all = roomService.findAll();
        model.addAttribute("rooms", all);
        return "roomList";
    }

    @GetMapping("/add")
    public String addRoom(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        return "addRoom";
    }

    @PostMapping("/add")
    public String doAddRoom(@Valid @ModelAttribute("room") Room room, BindingResult result, @RequestParam("imgUrl") MultipartFile img, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("room", room);
            return "addRoom";
        }
        //Thuc hien upload
        if (img != null && !img.isEmpty()) {
            try {
                room.setImage(cloudinaryService.uploadImage(img));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        boolean bl = roomService.save(room);
        if (bl) {
            return "redirect:/rooms";
        } else {
            model.addAttribute("err", "Add room failed!");
            model.addAttribute("room", room);
            return "addRoom";
        }
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model, HttpSession session) {
        //kiem tra role co phai la admin
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null || !c.getRole().toString().equals("ADMIN")) {
            model.addAttribute("err", "Bạn không có quyền update!");
            List<Room> all = roomService.findAll();
            model.addAttribute("rooms", all);
            return "roomList";
        }

        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "updateRoom";
    }

    @PostMapping("/update")
    public String doUpdateRoom(@Valid @ModelAttribute("room") Room room, BindingResult result, @RequestParam("imgUrl") MultipartFile img, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("room", room);
            return "addRoom";
        }
        //Thuc hien upload
        if (img != null && !img.isEmpty()) {
            try {
                room.setImage(cloudinaryService.uploadImage(img));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //xu ly lay lai duong dan anh cu:
            Room oldRoom = roomService.getRoomById(room.getId());
            room.setImage(oldRoom.getImage());
        }
        boolean bl = roomService.update(room, room.getId());
        if (bl) {
            return "redirect:/rooms";
        } else {
            model.addAttribute("err", "Update room failed!");
            model.addAttribute("room", room);
            return "updateRoom";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id, Model model, HttpSession session) {
        //kiem tra role co phai la admin
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null || !c.getRole().toString().equals("ADMIN")) {
            model.addAttribute("err", "Bạn không có quyền update!");
            List<Room> all = roomService.findAll();
            model.addAttribute("rooms", all);
            return "roomList";
        }
        //Kiem tra phong dang duoc dat hay trong?
        boolean bl = roomService.checkRoomPlaced(id);
        if (bl) {
            model.addAttribute("message", "Phòng đang được đặt, không thể xóa được!");
        } else {
            boolean blDelete = roomService.delete(id);
            if (blDelete) {
                model.addAttribute("message", "Xóa thành công phòng có id: " + id);
            } else {
                model.addAttribute("err", "Không xóa được phòng có mã: " + id);
            }
        }
        List<Room> all = roomService.findAll();
        model.addAttribute("rooms", all);
        return "roomList";
    }

    @GetMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable("id") Integer id, Model model, HttpSession session) {
        //kiem tra role co phai la admin
        Customer c = (Customer) session.getAttribute("customer");
        if (c == null || !c.getRole().toString().equals("ADMIN")) {
            model.addAttribute("err", "Bạn không có quyền update!");
            List<Room> all = roomService.findAll();
            model.addAttribute("rooms", all);
            return "roomList";
        }
        //Goi service de lay ve room theo id
        Room room = roomService.getRoomById(id);
        if (room.getStatus().toString().equals("PLACED"))
            room.setStatus(RoomStatus.EMPTY);
        else
            room.setStatus(RoomStatus.PLACED);

        //update lai room vao database
        roomService.update(room, id);

        List<Room> all = roomService.findAll();
        model.addAttribute("rooms", all);
        return "roomList";
    }
}
