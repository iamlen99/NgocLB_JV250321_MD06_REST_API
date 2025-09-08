package ra.edu.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.edu.model.dto.ProductListXML;
import ra.edu.model.entity.ProductEx6;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productsEx6")
public class ProductControllerEx06 {
    private final List<ProductEx6> products = Arrays.asList(
            new ProductEx6(1L, "Laptop Dell XPS", 1200.50),
            new ProductEx6(2L, "Laptop MacBook Pro", 2500.00),
            new ProductEx6(3L, "iPhone 15 Pro", 1500.00),
            new ProductEx6(4L, "Samsung Galaxy S23", 1300.00),
            new ProductEx6(5L, "Chuột Logitech MX Master", 99.99)
    );

    @GetMapping
    public List<ProductEx6> getProductsJson() {
        return products;
    }

    @GetMapping(value = "/xml-data", produces = MediaType.APPLICATION_XML_VALUE)
    public ProductListXML getProductsXml() {
        return new ProductListXML(products);
    }
}
