package ra.exercise.model.service;

import ra.exercise.model.dao.impl.CategoryDAOImpl;
import ra.exercise.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    Optional<Category> findById(int id);
}
