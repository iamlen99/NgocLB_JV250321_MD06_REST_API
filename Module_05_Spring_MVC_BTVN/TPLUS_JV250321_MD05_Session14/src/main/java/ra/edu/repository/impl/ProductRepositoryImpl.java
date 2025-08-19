package ra.edu.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.Category;
import ra.edu.model.entity.Product;
import ra.edu.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll() {
        return em.createQuery("select c from Product c", Product.class).getResultList();
    }

    @Override
    public boolean save(Product product) {
        try {
            em.persist(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            em.merge(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            String jpql = "delete from Product p where p.id = :id";
            return em.createQuery(jpql).setParameter("id", id).executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product findById(Long id) {
        try {
            return em.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findByName(String name) {
        try {
            String jpql = "select p from Product p where p.productName like :name";
            return em.createQuery(jpql, Product.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Product> findByNameAndCategory(String name, Long categoryId) {
        try {
            String jpql = "select p from Product p where p.category.id = :categoryId and p.productName like :name";
            return em.createQuery(jpql, Product.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
