package ra.exercise.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "userController"})
public class UserController {

    @GetMapping
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute("user")  User user, BindingResult bResult,  Model model) {
        if (bResult.hasErrors()) {
            return "userForm";
        }
        model.addAttribute("user", user);
        return "result";
    }
}