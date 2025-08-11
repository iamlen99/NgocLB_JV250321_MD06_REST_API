package ra.edu.ex02.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.ex02.entity.UserEx02;

@Controller
@RequestMapping("/userControllerEx02")
public class UserControllerEx02 {
    private int currentId = 1;
    @GetMapping
    public String user(Model model) {
        UserEx02 user = new UserEx02();
        user.setId(currentId);
        model.addAttribute("id", currentId);
        model.addAttribute("user", user);
        return "ex02/user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") UserEx02 user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", currentId);
            return "ex02/user-form";
        }
        currentId ++;
        return "ex02/result";
    }

}
