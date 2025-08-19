package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.CartItem;
import ra.edu.model.entity.Product;
import ra.edu.service.CategoryService;
import ra.edu.service.CartService;
import ra.edu.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;


    @GetMapping
    public String showCarts(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.calculateTotalAmount());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id,
                            RedirectAttributes redirectAttributes) {
        Product product = productService.findProductById(id);
        if (product != null ) {
            if (product.getStock()>0) {
                cartService.addToCart(product);
                redirectAttributes.addFlashAttribute("successMsg", "Thêm sản phẩm vào giỏ hàng thành công");
            } else{
                redirectAttributes.addFlashAttribute("errMsg", "Sản phẩm này đã hết hàng");
            }
        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        Product product = productService.findProductById(id);
        if (product != null) {
            cartService.removeFromCart(product);
//            product.setStock(product.getStock()+);
            redirectAttributes.addFlashAttribute("successMsg", "Xóa sản phẩm thành công");
        }
        return "redirect:/carts";
    }
}
