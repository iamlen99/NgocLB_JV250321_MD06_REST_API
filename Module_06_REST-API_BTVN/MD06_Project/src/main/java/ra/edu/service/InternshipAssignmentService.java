package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.AssignmentStatus;
import ra.edu.model.entity.InternshipAssignment;
import ra.edu.model.request.InternshipAssignmentRequest;

public interface InternshipAssignmentService {
    Page<InternshipAssignment> getAllInternshipAssignment(int page, int size);

    InternshipAssignment findById(Long id);

    InternshipAssignment createInternshipAssignment(InternshipAssignmentRequest internshipAssignmentRequest);

    InternshipAssignment updateStatusInternshipAssignment(Long id, String status);
}
