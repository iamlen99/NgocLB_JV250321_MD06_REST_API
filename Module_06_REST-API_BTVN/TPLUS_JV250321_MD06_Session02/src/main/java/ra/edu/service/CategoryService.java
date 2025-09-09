package ra.edu.service;

import ra.edu.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category insertCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
}
