package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Category;
import ra.edu.repository.CategoryRepository;
import ra.edu.service.CategoryService;

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
      return  categoryRepository.save(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return false;
    }
}
