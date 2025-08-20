package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.model.dto.UserNameDT0;

@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public String home(Model model) {
        model.addAttribute("userNameDTO", new UserNameDT0());
        return "addUsername";
    }

    @PostMapping("/examList")
    public String showExamList(@Valid @ModelAttribute("userNameDTO") UserNameDT0 userNameDT0,
                               BindingResult bindingResult,
                               HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "addUsername";
        }
        session.setAttribute("userNameDTO", userNameDT0);
        return "redirect:/home";
    }
}
