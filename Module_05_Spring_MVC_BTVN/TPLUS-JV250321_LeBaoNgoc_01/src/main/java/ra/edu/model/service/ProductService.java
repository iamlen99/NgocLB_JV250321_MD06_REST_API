package ra.edu.model.service;

import ra.edu.model.entity.Category;
import ra.edu.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts(int page, int pageSize);

    List<Product> getProductsByName(String name, int page, int pageSize);

    int getProductsTotalPages(int pageSize);

    int getProductsTotalPages(String name, int pageSize);

    boolean addProduct (Product product);

    boolean updateProduct (Product product);

    boolean deleteProduct (int id);

    Optional<Product> getProductById(int id);

    Optional<Product> getProductByName(String name);
}
