package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.model.dto.LoginDTO;
import ra.edu.model.entity.Customer;
import ra.edu.model.service.CustomerService;

@Controller
@RequestMapping(value = {"/", "/auth"})
public class AuthController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/register"})
    public String registerCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String doRegisterCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("customer", customer);
            return "register";
        }
        if(customerService.emailExist(customer.getEmail())){
            result.rejectValue("email", "", "email exist");
            model.addAttribute("customer", customer);
            return "register";
        }
        boolean bl = customerService.registerCustomer(customer);
        if (bl) {
            switch (customer.getRole().toString()) {
                case "ADMIN":
                    return "home_admin";
                case "CUSTOMER":
                    return "home_customer";
            }
        } else {
            model.addAttribute("err", "Register Customer Failed!");
            model.addAttribute("customer", customer);
            return "register";
        }
        return "";
    }

    @GetMapping(value = {"/", "/login"})
    public String login(Model model) {
        model.addAttribute("dtoLogin", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid @ModelAttribute("dtoLogin") LoginDTO dtoLogin, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("dtoLogin", dtoLogin);
            return "login";
        }
        Customer customer = customerService.loginCustomer(dtoLogin);
        if (customer != null) {
            session.setAttribute("customer", customer);
            switch (customer.getRole().toString()) {
                case "ADMIN":
                    return "home_admin";
                case "CUSTOMER":
                    return "home_customer";
            }
            return "";
        } else {
            model.addAttribute("err", "Login failed!");
            model.addAttribute("dtoLogin", dtoLogin);
            return "login";
        }
    }
}
