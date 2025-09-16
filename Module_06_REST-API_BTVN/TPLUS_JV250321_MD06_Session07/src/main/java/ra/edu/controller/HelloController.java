package ra.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Security!";
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is a public endpoint!";
    }
}
