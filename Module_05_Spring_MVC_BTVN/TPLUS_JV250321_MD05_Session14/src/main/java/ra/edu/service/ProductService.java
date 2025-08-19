package ra.edu.service;

import ra.edu.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(Long id);

    Product findProductById(Long id);

    List<Product> findProductByName(String name);
    List<Product> findProductByNameAndCategory(String name, Long categoryId);
}
