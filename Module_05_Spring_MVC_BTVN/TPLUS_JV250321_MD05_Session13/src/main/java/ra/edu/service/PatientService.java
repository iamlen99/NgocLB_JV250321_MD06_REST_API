package ra.edu.service;

import ra.edu.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();
    List<Patient> getCurrentPatients();
    Optional<Patient> findPatientById(Long id);
    Optional<Patient> findPatientByPhone(String phone);
    boolean addPatient(Patient patient);
    boolean deletePatient(Patient patient);
    boolean updatePatient(Patient patient);
    void changeStatus(Long id);
}
