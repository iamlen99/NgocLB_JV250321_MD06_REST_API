package ra.edu.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.User;
import ra.edu.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User login(String username, String password) {
        String hql = "select u from User u where u.username =:username and u.password = :password";
        try {
            return em.createQuery(hql, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
