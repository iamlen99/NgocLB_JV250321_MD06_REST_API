package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.NotFoundException;
import ra.edu.model.entity.Appointment;
import ra.edu.model.entity.Doctor;
import ra.edu.model.entity.Patient;
import ra.edu.repository.AppointmentRepository;
import ra.edu.repository.DoctorRepository;
import ra.edu.service.AppointmentService;
import ra.edu.service.DoctorService;
import ra.edu.service.PatientService;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientService.getPatientById(patientId);
        Doctor doctor = doctorService.getDoctorById(doctorId);

        appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, appointment.getAppointmentDate())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Doctor already has an appointment at this time.");
                });

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }
}
