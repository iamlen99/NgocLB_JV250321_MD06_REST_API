package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Patient;
import ra.edu.repository.PatientRepository;
import ra.edu.service.PatientService;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getCurrentPatients() {
        return patientRepository.findCurrent();
    }

    @Override
    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Optional<Patient> findPatientByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    @Override
    public boolean addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public boolean deletePatient(Patient patient) {
        return patientRepository.delete(patient);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        patient.setStatus(true);
        return patientRepository.update(patient);
    }

    @Override
    public void changeStatus(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        patient.get().setStatus(!patient.get().getStatus());
        patientRepository.update(patient.get());
    }
}
