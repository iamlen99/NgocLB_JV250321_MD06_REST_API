package ra.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.entity.WebServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/web-services")
public class WebServiceController {
    @GetMapping
    public String index() {
        List<WebServices> list = new ArrayList<>();
        list.add("Soap", "Simple Object Access Protocol");
    }
}
