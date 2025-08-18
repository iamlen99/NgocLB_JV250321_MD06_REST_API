package ra.edu.repository;

import ra.edu.model.entity.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    List<Appointment> findAll();
    List<Appointment> findAll(int page, int size);
    int getTotalPages(int size);
    Optional<Appointment> findByTime(Long doctorId, LocalDate date,int hour);
    boolean save(Appointment appointment);
    boolean delete(Long id);
}
