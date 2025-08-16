package ra.edu.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.User;
import ra.edu.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).list();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .find(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String hql = "from User u where u.email = :email";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("email", email)
                .uniqueResultOptional();
    }

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }

    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
    }
}
