package ra.edu.service;

import ra.edu.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getAllProductsByCategoryId(Long id);
    List<Product> getAllProductsByName(String name);
    Product getProductById(Long id);
    Product insertProduct(Product product);
    Product updateProduct(Product product);
    void deleteProductById(Long id);
}
