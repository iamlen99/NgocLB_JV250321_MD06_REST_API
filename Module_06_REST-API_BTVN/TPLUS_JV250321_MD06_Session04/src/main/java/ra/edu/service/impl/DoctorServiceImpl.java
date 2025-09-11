package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.BadRequestException;
import ra.edu.controller_advice.NotFoundException;
import ra.edu.model.entity.Doctor;
import ra.edu.model.entity.Patient;
import ra.edu.repository.AppointmentRepository;
import ra.edu.repository.DoctorRepository;
import ra.edu.repository.PatientRepository;
import ra.edu.service.DoctorService;
import ra.edu.service.PatientService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor with ID " + id + " not found"));
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = getDoctorById(id);

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialty(doctor.getSpecialty());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setAvatar(doctor.getAvatar());

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        boolean hasAppointments = appointmentRepository.existsByDoctorId(id);
        if (hasAppointments) {
            throw new BadRequestException("Cannot delete doctor because there are appointments related");
        }

        doctorRepository.delete(doctor);
    }
}
