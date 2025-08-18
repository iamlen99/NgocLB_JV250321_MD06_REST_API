package ra.edu.repository;

import ra.edu.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    List<Doctor> findAll();
    List<Doctor> findCurrent();
    Optional<Doctor> findById(Long id);
    Optional<Doctor> findByPhone(String phone);
    void save(Doctor doctor);
    void update(Doctor doctor);
    void delete(Doctor doctor);
}
