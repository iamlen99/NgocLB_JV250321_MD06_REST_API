package ra.exercise.model.service;

import ra.exercise.model.dao.impl.CategoryDAOImpl;
import ra.exercise.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    boolean addCategory(Category category);
}
