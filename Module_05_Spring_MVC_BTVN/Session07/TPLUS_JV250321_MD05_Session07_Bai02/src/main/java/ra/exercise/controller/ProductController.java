package ra.exercise.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.exercise.Product;

@Controller
@RequestMapping(value = {"/", "productController"})
public class ProductController {

    @GetMapping
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping("addProduct")
    public String addProduct(@Valid @ModelAttribute("product")  Product product, BindingResult bResult,  Model model) {
        if (bResult.hasErrors()) {
            return "productForm";
        }
        model.addAttribute("product", product);
        return "result";
    }
}