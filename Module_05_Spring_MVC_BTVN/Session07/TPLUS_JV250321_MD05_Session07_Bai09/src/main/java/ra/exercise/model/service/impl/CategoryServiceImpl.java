package ra.exercise.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.model.entity.Category;
import ra.exercise.model.repository.CategoryRepository;
import ra.exercise.model.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryRepository.update(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryRepository.delete(id);
    }

    @Override
    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }
}
