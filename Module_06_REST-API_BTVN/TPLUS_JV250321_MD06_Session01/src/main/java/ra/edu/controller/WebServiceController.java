package ra.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.entity.WebService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/web-services")
public class WebServiceController {
    @GetMapping
    public List<WebService> getWebServices() {
        return Arrays.asList(
                new WebService("REST", "Representational State Transfer"),
                new WebService("SOAP", "Simple Object Access Protocol"),
                new WebService("GraphQL", "Query language for APIs")
        );
    }
}
