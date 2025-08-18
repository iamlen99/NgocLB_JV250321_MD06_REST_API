package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.dto.DoctorDTO;
import ra.edu.model.entity.Doctor;
import ra.edu.repository.DoctorRepository;
import ra.edu.service.DoctorService;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getCurrentDoctors() {
        return doctorRepository.findCurrent();
    }

    @Override
    public Optional<Doctor> findByPhone(String phone) {
        return doctorRepository.findByPhone(phone);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public void addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setFullName(doctorDTO.getFullName());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setContact(doctorDTO.getContact());
        doctor.setPhone(doctorDTO.getPhone());
        doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setFullName(doctorDTO.getFullName());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setContact(doctorDTO.getContact());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setStatus(true);
        doctorRepository.update(doctor);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    @Override
    public void changeStatus(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        doctor.get().setStatus(!doctor.get().getStatus());
        doctorRepository.update(doctor.get());
    }
}
