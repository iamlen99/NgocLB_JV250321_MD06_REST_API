package ra.edu.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.Doctor;
import ra.edu.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Doctor> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Doctor", Doctor.class).list();
    }

    @Override
    public List<Doctor> findCurrent() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Doctor where status = true", Doctor.class).list();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Doctor.class, id));
    }

    @Override
    public Optional<Doctor> findByPhone(String phone) {
        String hql = "from Doctor d where d.phone = :phone";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Doctor.class)
                .setParameter("phone", phone)
                .uniqueResultOptional();
    }

    @Override
    public void save(Doctor doctor) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(doctor);
    }
}
