package ra.edu.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Category;
import ra.edu.model.repository.CategoryRepository;
import ra.edu.model.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories(int page, int pageSize) {
        return categoryRepository.findAll(page, pageSize);
    }

    @Override
    public List<Category> getCategoriesByName(String name, int page, int pageSize) {
        return categoryRepository.findAll(name, page, pageSize);
    }

    @Override
    public int getCategoriesTotalPages(int pageSize) {
        return categoryRepository.getTotalPages(pageSize);
    }

    @Override
    public int getCategoriesTotalPages(String name, int pageSize) {
        return categoryRepository.getTotalPage(name , pageSize);
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
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
