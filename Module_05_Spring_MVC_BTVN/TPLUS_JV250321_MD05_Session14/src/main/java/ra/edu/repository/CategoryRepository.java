package ra.edu.repository;

import ra.edu.model.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    boolean save(Category category);
}
