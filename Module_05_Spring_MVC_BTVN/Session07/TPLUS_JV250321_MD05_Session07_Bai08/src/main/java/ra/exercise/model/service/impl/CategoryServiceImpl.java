package ra.exercise.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.model.entity.Category;
import ra.exercise.model.repository.CategoryRepository;
import ra.exercise.model.service.CategoryService;

import java.util.List;

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
}
