package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.exercise.model.entity.Product;
import ra.exercise.model.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = {"/","/product"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String getListProduct(Model model) {
        List<Product> productList = productService.GetAllProducts();
        model.addAttribute("productList", productList);
        return "productList";
    }
}
