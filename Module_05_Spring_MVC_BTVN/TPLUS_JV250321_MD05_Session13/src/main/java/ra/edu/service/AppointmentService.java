package ra.edu.service;

import ra.edu.model.entity.Appointment;
import ra.edu.model.entity.Doctor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    List<Appointment> getAllAppointments(int page, int size);
    int getTotalPages(int size);
    Optional<Appointment> findByTime(Long doctorId, LocalDate date, int hour);
    boolean addAppointment(Appointment appointment);
    boolean deleteAppointment(Long id);
}
