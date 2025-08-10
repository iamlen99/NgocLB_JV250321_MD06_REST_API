package ra.exercise.model.service;

import ra.exercise.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    boolean addCategory(Category category);
}
