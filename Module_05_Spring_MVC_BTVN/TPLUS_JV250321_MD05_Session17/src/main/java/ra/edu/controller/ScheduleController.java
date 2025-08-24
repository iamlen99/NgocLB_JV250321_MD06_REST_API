package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.*;
import ra.edu.service.BusService;
import ra.edu.service.CustomerService;
import ra.edu.service.ScheduleService;
import ra.edu.storage.CloudinaryService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private BusService busService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String showScheduleList(
            Model model,
            HttpSession session
    ) {
        Customer currentCustomer = (Customer) session.getAttribute("currentCustomer");
        if (currentCustomer == null || currentCustomer.getRole().equals(Role.USER)) {
            model.addAttribute("loginDTO", new LoginDTO());
            return "login";
        }

        List<Schedule> schedules = scheduleService.findAll();
        model.addAttribute("schedules", schedules);
        return "/schedule/scheduleList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("buses", busService.findAll());
        return "/schedule/addSchedule";
    }

    @PostMapping("/add")
    public String addSchedule(
            @Valid @ModelAttribute("schedule") Schedule schedule,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "/schedule/addSchedule";
        }

        if (file == null || file.isEmpty()) {
            model.addAttribute("errImg", "Vui lòng chọn ảnh xe!");
            model.addAttribute("buses", busService.findAll());
            return "/schedule/addSchedule";
        }

        try {
            schedule.setImage(cloudinaryService.uploadImage(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<Bus> bus = busService.findById(schedule.getBus().getId());
        if (bus.isPresent()) {
            schedule.setAvailableSeat(bus.get().getCapacity());
            TypeSeat typeSeat = bus.get().getTypeSeat();
            if (typeSeat.equals(TypeSeat.STANDARD)) {
                schedule.setPrice(50000.0);
            } else if (typeSeat.equals(TypeSeat.VIP)) {
                schedule.setPrice(70000.0);
            } else {
                schedule.setPrice(90000.0);
            }
        }
        ;

        Schedule scheduleInserted = scheduleService.save(schedule);
        if (scheduleInserted != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm xe schedule thành công");
            return "redirect:/schedules";
        }
        model.addAttribute("errMsg", "Thêm xe schedule thất bại, vui lòng thử lại");
        model.addAttribute("buses", busService.findAll());
        return "/schedule/addSchedule";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable("id")
            Long id,
            Model model) {
        Optional<Schedule> schedule = scheduleService.findById(id);
        if (schedule.isPresent()) {
            model.addAttribute("schedule", schedule.get());
            model.addAttribute("typeSeats", TypeSeat.values());
            model.addAttribute("buses", busService.findAll());
            return "/schedule/editSchedule";
        }
        model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id xe schedule, xin thử lại");
        return "/schedule/scheduleList";
    }

    @PostMapping("/update")
    public String editSchedule(
            @Valid @ModelAttribute("schedule") Schedule schedule,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "/schedule/editSchedule";
        }

        if (file != null && !file.isEmpty()) {
            try {
                schedule.setImage(cloudinaryService.uploadImage(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Schedule scheduleUpdated = scheduleService.save(schedule);
        if (scheduleUpdated != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa schedule thành công");
            return "redirect:/schedules";
        }
        model.addAttribute("errMsg", "Sửa schedule thất bại, vui lòng thử lại");
        model.addAttribute("buses", busService.findAll());
        return "/schedule/addSchedule";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        if (scheduleService.delete(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa lịch trình thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Xóa lịch trình thành công");
        }
        return "redirect:/schedules";
    }
}
