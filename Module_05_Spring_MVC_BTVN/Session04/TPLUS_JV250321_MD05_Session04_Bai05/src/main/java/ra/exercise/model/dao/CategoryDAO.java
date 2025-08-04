package ra.exercise.model.dao;

import ra.exercise.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDAO {
    List<Category> findAll();

    boolean save(Category category);

    boolean update(Category category);

    Optional<Category> findById(int id);
}
