package ra.edu.service;

import ra.edu.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
}
