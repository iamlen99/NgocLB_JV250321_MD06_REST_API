package ra.exercise.model.dao;

import ra.exercise.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();

    boolean save(Category category);
}
