package ra.exercise.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.exercise.User;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping
    public String user(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/addUser")
    public String submitForm(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "userForm";
        }
        return "result";
    }
}
