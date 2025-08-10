package ra.exercise.model.repository;

import ra.exercise.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();

    boolean save (Category category);
}
