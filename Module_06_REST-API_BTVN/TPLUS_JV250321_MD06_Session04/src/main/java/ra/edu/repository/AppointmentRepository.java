package ra.edu.repository;

import jakarta.validation.constraints.Future;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Appointment;
import ra.edu.model.entity.Patient;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Object> findByDoctorIdAndAppointmentDate(Long doctorId, @Future(message = "Appointment date must be in the future") LocalDateTime appointmentDate);

    boolean existsByDoctorId(Long id);

    boolean existsByPatientId(Long id);
}
