package ra.edu.ex07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homeController")
public class HomeController {
    @GetMapping
    public String index(){
        return "ex07/home";
    }
    @GetMapping("/about")
    public String about(){
        return "ex07/about";
    }
    @GetMapping("/contact")
    public String contact(){
        return "ex07/contact";
    }
}
