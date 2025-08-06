package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.exercise.config.User;
import ra.exercise.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = {"/", "user"})
public class RegistrationController {
    @Autowired
    private Validator validator;
    public List<User> users = new ArrayList<>();
    @GetMapping("/")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/addUser")
    public String addUser(Model model,
                          @RequestParam("username") String userName,
                          @RequestParam("email") String email,
                          @RequestParam("phoneNumber") String phoneNumber) {
        boolean hasError = false;

        if(!validator.isValidUsername(userName).isEmpty()) {
            model.addAttribute("usernameError", validator.isValidUsername(userName));
            hasError = true;
        }

        if(!validator.isValidEmail(email).isEmpty()) {
            model.addAttribute("emailError", validator.isValidEmail(email));
            hasError = true;
        }

        if(!validator.isValidPhoneNumber(phoneNumber).isEmpty()) {
            model.addAttribute("phoneError", validator.isValidPhoneNumber(phoneNumber));
            hasError = true;
        }

        User user = new User(userName, email, phoneNumber);
        if (hasError) {
            model.addAttribute("user", user);
            return "registration";
        }

        users.add(user);
        model.addAttribute("users", users);
        model.addAttribute("success", "Dang ky thanh cong");
        return "result";
    }
}
