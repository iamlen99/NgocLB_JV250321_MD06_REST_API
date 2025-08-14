package ra.edu.model.repository;

import ra.edu.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll(int page, int size);

    List<Category> findAll(String name, int page, int size);

    int getTotalPages(int size);

    int getTotalPage(String name, int size);

    boolean save(Category category);

    Optional<Category> findById(int id);

    boolean update(Category category);

    boolean delete(int id);

    Optional<Category> findByName(String name);
}
