package ra.edu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.edu.model.entity.Appointment;
import ra.edu.model.entity.Patient;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment, Long patientId, Long doctorId);
    List<Appointment> getAllAppointments();
}
