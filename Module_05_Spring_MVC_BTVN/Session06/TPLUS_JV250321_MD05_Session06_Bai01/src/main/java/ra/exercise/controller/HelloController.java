package ra.exercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/hello"})
public class HelloController {
    @GetMapping
    public String hello(Model model) {
        model.addAttribute("message", "Chào mừng đến với ứng dụng của chúng tôi!");
        return "welcome";
    }
}
