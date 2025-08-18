package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Appointment;
import ra.edu.repository.AppointmentRepository;
import ra.edu.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAllAppointments(int page, int size) {
        return  appointmentRepository.findAll(page, size);
    }

    @Override
    public int getTotalPages(int size) {
        return appointmentRepository.getTotalPages(size);
    }

    @Override
    public Optional<Appointment> findByTime(Long doctorId, LocalDate date, int hour) {
        return appointmentRepository.findByTime(doctorId, date, hour);
    }

    @Override
    public boolean addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public boolean deleteAppointment(Long id) {
        return appointmentRepository.delete(id);
    }
}
