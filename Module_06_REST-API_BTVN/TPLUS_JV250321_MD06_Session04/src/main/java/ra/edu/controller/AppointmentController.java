package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Appointment;
import ra.edu.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Appointment>>> getAllAppointments() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list appointments successfully",
                appointmentService.getAllAppointments(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Appointment>> createAppointment(@Valid @RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.createAppointment(
                appointment,
                appointment.getPatient().getId(),
                appointment.getDoctor().getId()
        );

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Created new appointment successfully",
                savedAppointment,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
}
