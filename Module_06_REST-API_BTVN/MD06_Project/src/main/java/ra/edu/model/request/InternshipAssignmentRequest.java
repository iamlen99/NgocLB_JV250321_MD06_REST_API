package ra.edu.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipAssignmentRequest {
    @NotNull(message = "phaseId không được để trống")
    private Long phaseId;
    @NotNull(message = "studentId không được để trống")
    private Long studentId;
    @NotNull(message = "mentorId không được để trống")
    private Long mentorId;
    @NotNull(message = "Ngày phân công không được để trống")
    private LocalDate assignedDate;
}
