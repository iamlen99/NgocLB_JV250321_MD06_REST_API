package ra.edu.model.repository;

import ra.edu.model.entity.Category;
import ra.edu.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    List<Product> findAll(int page, int size);

    List<Product> findAll(String name, int page, int size);

    int getTotalPages(int size);

    int getTotalPage(String name, int size);

    boolean save(Product product);

    Optional<Product> findById(int id);

    boolean update(Product product);

    boolean delete(int id);

    Optional<Product> findByName(String name);
}
