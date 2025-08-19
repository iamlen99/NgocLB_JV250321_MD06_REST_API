package ra.edu.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ra.edu.model.entity.CartItem;
import ra.edu.model.entity.Product;
import ra.edu.service.CartService;
import ra.edu.service.ProductService;

import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductService productService;

    private final Map<Long, CartItem> cartItemMap = new HashMap<>();

    @Override
    public Collection<CartItem> getCartItems() {
        return cartItemMap.values();
    }

    @Override
    public void addToCart(Product product) {
        Long productId = product.getId();
        if (cartItemMap.containsKey(productId)) {
            CartItem cartItem = cartItemMap.get(productId);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartItem.setId(productId);
            cartItemMap.put(productId, cartItem);
        }
        product.setStock(product.getStock() - 1);
        productService.updateProduct(product);
    }

    @Override
    public void removeFromCart(Product product) {
        CartItem cartItem = cartItemMap.get(product.getId());
        if (cartItem != null) {
            product.setStock(product.getStock() + cartItemMap.get(product.getId()).getQuantity());
            productService.updateProduct(product);
            cartItemMap.remove(product.getId());
        }
    }

    @Override
    public double calculateTotalAmount() {
        return cartItemMap.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    @Override
    public void clearCart() {
        cartItemMap.clear();
    }
}
