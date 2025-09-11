package ra.edu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.edu.model.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);

    Patient updatePatient(Long id, Patient updatedPatient);

    Page<Patient> getPatients(Pageable pageable);

    Patient getPatientById(Long patientId);

    void deletePatient(Long id);
}
