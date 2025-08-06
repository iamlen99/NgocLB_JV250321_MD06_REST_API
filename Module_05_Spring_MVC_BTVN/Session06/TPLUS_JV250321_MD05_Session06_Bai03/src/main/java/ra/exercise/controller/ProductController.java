package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.exercise.model.entity.Product;
import ra.exercise.model.service.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/product"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getListProduct(Model model) {
        List<Product> productList = productService.GetAllProducts();
        model.addAttribute("productList", productList);
        return "productList";
    }

    @GetMapping("/goToAddPage")
    public String addPage() {
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("image") String imageUrl,
            Model model) {
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        if(productService.AddProduct(product)){
            return "redirect:/";
        }
        model.addAttribute("product", product);
        model.addAttribute("message", "Co loi trong qua trinh them san pham, vui long thu lai");
        return "redirect:/goToAddPage";
    }
}
