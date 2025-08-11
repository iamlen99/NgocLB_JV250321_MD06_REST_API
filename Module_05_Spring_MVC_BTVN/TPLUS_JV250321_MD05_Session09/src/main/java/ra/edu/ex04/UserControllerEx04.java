package ra.edu.ex04;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userControllerEx04")
public class UserControllerEx04 {
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("user", new UserEx04());
        return "ex04/register-form";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserEx04 user, BindingResult result) {
        if (result.hasErrors()) {
            return "ex04/register-form";
        }
        return "ex04/result";
    }
}
