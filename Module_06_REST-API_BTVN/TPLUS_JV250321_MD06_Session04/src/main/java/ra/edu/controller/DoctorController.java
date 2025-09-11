package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Doctor;
import ra.edu.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Doctor>>> getAllDoctors() {
        return ResponseEntity.ok(new ApiDataResponse<>(
                true,
                "Get list doctors successfully",
                doctorService.getAllDoctors(),
                null,
                HttpStatus.OK
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Doctor>> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiDataResponse<>(
                true,
                "Get doctor successfully",
                doctorService.getDoctorById(id),
                null,
                HttpStatus.OK
        ));
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Doctor>> createDoctor(@Valid @RequestBody Doctor doctor) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Doctor created successfully",
                doctorService.createDoctor(doctor),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Doctor>> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody Doctor doctor
    ) {
        return ResponseEntity.ok(new ApiDataResponse<>(
                true,
                "Doctor updated successfully",
                doctorService.updateDoctor(id, doctor),
                null,
                HttpStatus.OK
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<String>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiDataResponse<>(
                        true,
                        "Doctor deleted successfully",
                        null,
                        null,
                        HttpStatus.NO_CONTENT
                ));
    }
}
