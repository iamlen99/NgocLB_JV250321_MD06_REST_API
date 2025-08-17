package ra.edu.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.Post;
import ra.edu.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Post> findAll(Long id) {
        String hql = "FROM Post AS p WHERE p.user.id =:id";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Post.class)
                .setParameter("id", id).list();
    }

    @Override
    public Optional<Post> findById(Long id) {
        Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(Post.class, id));
    }

    @Override
    public void save(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void delete(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(post);
    }
}
