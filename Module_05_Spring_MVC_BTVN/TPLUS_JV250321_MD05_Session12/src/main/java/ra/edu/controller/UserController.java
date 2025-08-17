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
import ra.edu.model.dto.UserDTO;
import ra.edu.model.entity.User;
import ra.edu.service.UserService;
import ra.edu.storage.CloudinaryService;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "ex01/register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "ex02/login";
    }

    @GetMapping("/edit")
    public String showUserProfile(HttpSession session,
                                  Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/userController/login";
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setEmail(currentUser.getEmail());
        userDTO.setPassword(currentUser.getPassword());
        userDTO.setFullName(currentUser.getFullName());
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("imgUrl", currentUser.getAvatar());
        return "ex06/profile";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ex01/register";
        }

        if (userDTO.getImageFile() == null || userDTO.getImageFile().isEmpty()) {
            result.rejectValue("imageFile", "error.avatar", "Avatar không được để trống!");
            return "ex01/register";
        }
        userService.addUser(userDTO);
        redirectAttributes.addFlashAttribute("successMsg", "Đăng ký thành công");
        return "redirect:/userController/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
                        BindingResult result,
                        HttpSession session,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        if (result.hasErrors()) {
            return "ex02/login";
        }
        Optional<User> user = userService.findByEmail(loginDTO.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
            session.setAttribute("currentUser", user.get());
            redirectAttributes.addFlashAttribute("successMsg", "Đăng nhập thành công");
            return "redirect:/postController";
        }
        model.addAttribute("errMsg", "Email hoặc mật khẩu không chính xác!");
        return "ex02/login";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                BindingResult result,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ex01/register";
        }
        userService.updateUser(userDTO, session);
        redirectAttributes.addFlashAttribute("successMsg", "Cập nhật profile thành công");
        return "redirect:/userController/login";
    }
}
