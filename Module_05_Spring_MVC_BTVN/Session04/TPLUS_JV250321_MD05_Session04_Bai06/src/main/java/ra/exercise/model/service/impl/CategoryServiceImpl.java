package ra.exercise.model.service.impl;

import ra.exercise.model.dao.CategoryDAO;
import ra.exercise.model.dao.impl.CategoryDAOImpl;
import ra.exercise.model.entity.Category;
import ra.exercise.model.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    public final CategoryDAO categoryDAO;
    public CategoryServiceImpl() {
        categoryDAO = new CategoryDAOImpl();
    }
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryDAO.update(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryDAO.delete(id);
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryDAO.findById(id);
    }
}
