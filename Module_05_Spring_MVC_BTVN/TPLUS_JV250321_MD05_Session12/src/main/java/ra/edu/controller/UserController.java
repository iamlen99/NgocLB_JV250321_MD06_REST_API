package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.dto.UserDTO;
import ra.edu.model.entity.User;
import ra.edu.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "ex01/register";
    }

    @GetMapping("/auth/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "ex02/login";
    }

    @PostMapping("/auth/register")
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ex01/register";
        }
        userService.addUser(userDTO);
        model.addAttribute("userDTO", new UserDTO());
        return "ex02/login";
    }

    @PostMapping("/auth/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ex02/login";
        }
        Optional<User> user = userService.findByEmail(loginDTO.getEmail());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(loginDTO.getPassword())) {
                return "ex03/postList";
            }
        }
        model.addAttribute("errMsg", "Email hoặc mật khẩu không chính xác!");
        return "ex02/login";
    }
}
