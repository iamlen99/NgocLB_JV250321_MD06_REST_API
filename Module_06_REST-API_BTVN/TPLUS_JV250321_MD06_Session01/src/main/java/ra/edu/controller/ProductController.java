package ra.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = Arrays.asList(
            new Product(1L, "Laptop Dell XPS", 1200.50),
            new Product(2L, "Laptop MacBook Pro", 2500.00),
            new Product(3L, "iPhone 15 Pro", 1500.00),
            new Product(4L, "Samsung Galaxy S23", 1300.00),
            new Product(5L, "Chuột Logitech MX Master", 99.99)
    );

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
