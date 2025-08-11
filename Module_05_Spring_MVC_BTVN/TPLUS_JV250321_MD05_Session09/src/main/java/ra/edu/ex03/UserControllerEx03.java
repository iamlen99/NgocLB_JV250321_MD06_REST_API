package ra.edu.ex03;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userControllerEx03")
public class UserControllerEx03 {

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "ex03/login-form";
    }

    @PostMapping("/logging")
    public String logging(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if (user.getUserName().equals("user") && user.getPassword().equals("123456789")) {
            session.setAttribute("loggedInUser", "Le ngoc");
            model.addAttribute("message", "Dang nhap thanh cong");
            return "ex03/result";
        }
        model.addAttribute("error", "Ten tai khoan hoac mat khau khong chinh xac");
        return "ex03/login-form";
    }
}
