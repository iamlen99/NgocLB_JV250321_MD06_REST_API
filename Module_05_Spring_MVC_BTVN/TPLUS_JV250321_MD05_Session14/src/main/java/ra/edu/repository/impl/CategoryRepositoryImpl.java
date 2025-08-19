package ra.edu.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.Category;
import ra.edu.repository.CategoryRepository;

import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }

    @Override
    public boolean save(Category category) {
        try{
            em.persist(category);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
