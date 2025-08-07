package ra.exercise.model.service;

import ra.exercise.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);

    Optional<Category> getCategory(int id);
}
