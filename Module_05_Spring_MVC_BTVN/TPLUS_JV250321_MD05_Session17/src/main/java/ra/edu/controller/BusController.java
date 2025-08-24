package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Bus;
import ra.edu.model.entity.Customer;
import ra.edu.model.entity.Role;
import ra.edu.model.entity.TypeSeat;
import ra.edu.service.BusService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/buses")
public class BusController {
    @Autowired
    private BusService busService;

    @GetMapping
    public String showBusList(
            Model model,
            HttpSession session
    ) {
        Customer currentCustomer = (Customer) session.getAttribute("currentCustomer");
        if (currentCustomer == null || currentCustomer.getRole().equals(Role.USER)) {
            model.addAttribute("loginDTO", new LoginDTO());
            return "login";
        }

        List<Bus> buses = busService.findAll();
        model.addAttribute("buses", buses);
        return "/bus/busList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        Customer currentCustomer = (Customer) session.getAttribute("currentCustomer");
        if (currentCustomer == null || currentCustomer.getRole().equals(Role.USER)) {
            model.addAttribute("loginDTO", new LoginDTO());
            return "login";
        }
        model.addAttribute("bus", new Bus());
        model.addAttribute("typeSeats", TypeSeat.values());
        return "/bus/addBus";
    }

    @PostMapping("/add")
    public String addBus(
            @Valid @ModelAttribute("bus") Bus bus,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("typeSeats", TypeSeat.values());
            return "/bus/addBus";
        }
        Bus busInserted = busService.save(bus);
        if (busInserted != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm xe bus thành công");
            return "redirect:/buses";
        }
        model.addAttribute("errMsg", "Thêm xe bus thất bại, vui lòng thử lại");
        model.addAttribute("typeSeats", TypeSeat.values());
        return "/bus/addBus";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        if (busService.delete(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa xe bus thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Xóa xe bus thành công");
        }
        return "redirect:/buses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable("id")
            Long id,
            Model model,
            HttpSession session
    ) {
        Customer currentCustomer = (Customer) session.getAttribute("currentCustomer");
        if (currentCustomer == null || currentCustomer.getRole().equals(Role.USER)) {
            model.addAttribute("loginDTO", new LoginDTO());
            return "login";
        }

        Optional<Bus> bus = busService.findById(id);
        if (bus.isPresent()) {
            model.addAttribute("bus", bus.get());
            model.addAttribute("typeSeats", TypeSeat.values());
            return "/bus/editBus";
        }
        model.addAttribute("errMsg", "Có lỗi trong quá trình lấy id xe bus, xin thử lại");
        return "/bus/busList";
    }

    @PostMapping("/update")
    public String editBus(
            @Valid @ModelAttribute("bus") Bus bus,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("typeSeats", TypeSeat.values());
            return "/bus/editBus";
        }
        Bus busUpdated = busService.save(bus);
        if (busUpdated != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa thông tin xe bus thành công");
            return "redirect:/buses";
        }
        model.addAttribute("errMsg", "Sửa thông tin xe bus thất bại, vui lòng thử lại");
        model.addAttribute("typeSeats", TypeSeat.values());
        return "/bus/addBus";
    }
}
