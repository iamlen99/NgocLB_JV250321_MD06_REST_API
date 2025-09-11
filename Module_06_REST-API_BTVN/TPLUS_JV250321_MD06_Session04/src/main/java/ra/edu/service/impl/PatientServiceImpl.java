package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.BadRequestException;
import ra.edu.model.entity.Patient;
import ra.edu.repository.AppointmentRepository;
import ra.edu.repository.DoctorRepository;
import ra.edu.repository.PatientRepository;
import ra.edu.service.PatientService;

import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Patient with ID " + id + " not found"));

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setDob(updatedPatient.getDob());
        existingPatient.setGender(updatedPatient.getGender());

        return patientRepository.save(existingPatient);
    }

    @Override
    public Page<Patient> getPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient with ID " + patientId + " not found"));
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        boolean hasAppointments = appointmentRepository.existsByPatientId(id);
        if (hasAppointments) {
            throw new BadRequestException("Cannot delete patient because there are appointments related");
        }

        patientRepository.delete(patient);
    }
}
