package ra.edu.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.model.entity.Appointment;
import ra.edu.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @Autowired
    private SessionFactory  sessionFactory;

    @Override
    public List<Appointment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Appointment", Appointment.class).list();
    }

    @Override
    public List<Appointment> findAll(int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Appointment", Appointment.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public int getTotalPages(int size) {
        Session session = sessionFactory.getCurrentSession();
        Long count = session.createQuery("select count(*) from Appointment", Long.class).getSingleResult();
        return (int) Math.ceil( (double) count / size);
    }

    @Override
    public Optional<Appointment> findByTime(Long doctorId, LocalDate date, int hour) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Appointment where date = :date and hour = :hour and doctor.id = :doctorId";
        return session.createQuery(hql, Appointment.class)
                .setParameter("date", date).setParameter("hour", hour)
                .setParameter("doctorId", doctorId)
                .uniqueResultOptional();
    }

    @Override
    public boolean save(Appointment appointment) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.persist(appointment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            Session session = sessionFactory.getCurrentSession();
            String  hql = "delete from Appointment where id = :id";
            session.createQuery(hql)
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
