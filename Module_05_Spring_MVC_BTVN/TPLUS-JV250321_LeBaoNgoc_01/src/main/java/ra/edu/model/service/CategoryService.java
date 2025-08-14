package ra.edu.model.service;

import ra.edu.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories(int page, int pageSize);

    List<Category> getCategoriesByName(String name, int page, int pageSize);

    int getCategoriesTotalPages(int pageSize);

    int getCategoriesTotalPages(String name, int pageSize);

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);

    Optional<Category> getCategoryById(int id);

    Optional<Category> getCategoryByName(String name);
}
