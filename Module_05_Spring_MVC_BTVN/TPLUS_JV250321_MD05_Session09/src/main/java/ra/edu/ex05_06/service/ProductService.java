package ra.edu.ex05_06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ex05_06.entity.CartItems;
import ra.edu.ex05_06.entity.Product;
import ra.edu.ex05_06.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    // Cart Items
    public boolean addCartItems(CartItems cartItems) {
        return productRepository.addCartItems(cartItems);
    }

    public Product getProduct(String name) {
        return productRepository.getProduct(name);
    }

    public List<CartItems> getCartItemsList() {
        return productRepository.getCartItemsList();
    }

}
