package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.model.entity.Customer;
import ra.edu.model.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getCustomers(Model model) {
        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("customers", allCustomers);
        return "customerList";
    }
}
