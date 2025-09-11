package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Patient;
import ra.edu.service.PatientService;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Patient>> createPatient(@Valid @RequestBody Patient patient) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Added new patient successfully!",
                patientService.createPatient(patient),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Patient>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patient patient
    ) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Updated patient with ID " + id + " successfully!",
                patientService.updatePatient(id, patient),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Patient>>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        String sortField = sort[0];
        Sort.Direction sortDirection = sort.length > 1 && sort[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list patients successfully",
                patientService.getPatients(pageable),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<String>> deleteDoctor(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiDataResponse<>(
                        true,
                        "Patient deleted successfully",
                        null,
                        null,
                        HttpStatus.NO_CONTENT
                ));
    }
}
