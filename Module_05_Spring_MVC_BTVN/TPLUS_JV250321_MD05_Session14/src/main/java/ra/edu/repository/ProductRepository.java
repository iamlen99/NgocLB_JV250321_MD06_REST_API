package ra.edu.repository;

import ra.edu.model.entity.Category;
import ra.edu.model.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByNameAndCategory(String name, Long categoryId);
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(Long id);
    Product findById(Long id);
}
