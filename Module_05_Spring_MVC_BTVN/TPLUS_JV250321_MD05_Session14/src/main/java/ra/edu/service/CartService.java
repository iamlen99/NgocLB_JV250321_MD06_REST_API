package ra.edu.service;

import jakarta.servlet.http.HttpSession;
import ra.edu.model.entity.CartItem;
import ra.edu.model.entity.Product;

import java.util.Collection;
import java.util.List;

public interface CartService {
    Collection<CartItem> getCartItems();
    void addToCart(Product product);
    void removeFromCart(Product product);
    double calculateTotalAmount();
    void clearCart();
}
