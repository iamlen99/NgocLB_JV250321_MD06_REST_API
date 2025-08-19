package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.User;
import ra.edu.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO")  LoginDTO loginDTO,
                        BindingResult bindingResult,
                        Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (user == null) {
            model.addAttribute("errMsg", "Tên đăng nhập hoặc mật khẩu không chính xác");
            return "login";
        }
        model.addAttribute("successMsg", "Đăng nhập thành công");
        return "login";
    }
}
