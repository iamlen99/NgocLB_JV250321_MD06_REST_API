package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.CustomerDTO;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;
import ra.edu.model.entity.Role;
import ra.edu.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

@Controller
@RequestMapping("/auth")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String showAddForm(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        model.addAttribute("roles", Role.values());
        return "register";
    }

    @PostMapping("/register")
    public String addCustomer(
            @Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO,
            BindingResult result,
            @RequestParam("confirm-password") String confirmPassword,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "register";
        }

        if (!customerDTO.getPassword().equals(confirmPassword)) {
            model.addAttribute("errPass", "Mật khẩu xác nhận không trùng khớp");
            model.addAttribute("roles", Role.values());
            return "register";
        }

        Customer customer = Customer.builder()
                .username(customerDTO.getUsername())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .password(BCrypt.hashpw(customerDTO.getPassword(), BCrypt.gensalt(12)))
                .role(customerDTO.getRole())
                .build();

        Customer savedCustomer = customerService.save(customer);

        if (savedCustomer != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Đăng ký tài khoản thành công");
            return "redirect:/auth/login";
        }
        model.addAttribute("errMsg", "Đăng ký tài khoản thất bại, vui lòng thử lại");
        model.addAttribute("roles", Role.values());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
            BindingResult result,
            HttpSession session,
            Model model
    ) {
        if (result.hasErrors()) {
            return "login";
        }

        if (customerService.login(loginDTO.getUsername(), loginDTO.getPassword()) != null) {
            Customer customer = customerService.findByUsername(loginDTO.getUsername());
            session.setAttribute("currentCustomer", customer);
            if (customer.getRole().equals(Role.ADMIN)) {
                return "redirect:/buses";
            }
            return "redirect:/home";
        }
        model.addAttribute("errMsg", "Thông tin tài khoản hoặc mật khẩu không chính xác");
        return "login";
    }
}
