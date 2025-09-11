package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
