package ra.edu.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.Patient;
import ra.edu.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Patient> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Patient", Patient.class).list();
    }

    @Override
    public List<Patient> findCurrent() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Patient where status = true", Patient.class).list();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Patient.class, id));
    }

    @Override
    public Optional<Patient> findByPhone(String phone) {
        String hql = "from Patient p where p.phone = :phone";
        return sessionFactory.getCurrentSession().createQuery(hql, Patient.class)
                .setParameter("phone", phone)
                .uniqueResultOptional();
    }

    @Override
    public boolean save(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(patient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.remove(patient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Patient patient) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.merge(patient);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
