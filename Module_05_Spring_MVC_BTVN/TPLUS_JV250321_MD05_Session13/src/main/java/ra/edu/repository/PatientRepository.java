package ra.edu.repository;

import ra.edu.model.entity.Doctor;
import ra.edu.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    List<Patient> findAll();
    List<Patient> findCurrent();
    Optional<Patient> findById(Long id);
    Optional<Patient> findByPhone(String phone);
    boolean save(Patient patient);
    boolean delete(Patient patient);
    boolean update(Patient patient);
}
