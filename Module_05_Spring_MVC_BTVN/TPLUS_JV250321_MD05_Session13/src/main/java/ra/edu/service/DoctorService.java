package ra.edu.service;

import ra.edu.model.dto.DoctorDTO;
import ra.edu.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    List<Doctor> getCurrentDoctors();
    Optional<Doctor> findByPhone(String phone);
    Optional<Doctor> findById(Long id);
    void addDoctor(DoctorDTO doctorDTO);
    void updateDoctor(DoctorDTO doctorDTO);
    void deleteDoctor(Doctor doctor);
    void changeStatus(Long id);
}
