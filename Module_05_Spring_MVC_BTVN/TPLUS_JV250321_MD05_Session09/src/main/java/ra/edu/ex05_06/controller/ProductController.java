package ra.edu.ex05_06.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.edu.ex05_06.entity.CartItems;
import ra.edu.ex05_06.entity.Product;
import ra.edu.ex05_06.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Trang chủ hiển thị danh sách sản phẩm
    @GetMapping
    public String home(@RequestParam(value = "keyword", required = false) String keyword,
                       Model model) {
        List<Product> productList = productService.getProductList();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String lower = keyword.toLowerCase();
            productList = productList.stream()
                    .filter(p -> String.valueOf(p.getId()).contains(lower)
                            || p.getName().toLowerCase().contains(lower))
                    .toList();
        }

        model.addAttribute("productList", productList);
        model.addAttribute("keyword", keyword);
        return "ex05/productList";
    }

    // Thêm sản phẩm vào giỏ hàng
    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int id, HttpSession session) {
        Product product = productService.getProductList()
                .stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (product != null) {
            List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }

            // Kiểm tra sản phẩm đã tồn tại trong giỏ chưa
            CartItems existing = cart.stream()
                    .filter(c -> c.getProduct().getId() == id)
                    .findFirst()
                    .orElse(null);

            if (existing != null) {
                existing.setQuantity(existing.getQuantity() + 1);
            } else {
                cart.add(new CartItems(product, 1));
            }

            session.setAttribute("cart", cart);
        }

        return "redirect:/productController";
    }

    // Xem giỏ hàng
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItems> cart = (List<CartItems>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        model.addAttribute("cart", cart);
        return "ex05/productsCart";
    }
}
