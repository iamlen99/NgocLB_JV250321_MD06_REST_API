package ra.edu.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.entity.InternshipAssignment;
import ra.edu.model.entity.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResultRequest {
    @NotNull(message = "assignmentId không được để trống")
    private Long assignmentId;

    @NotNull(message = "roundId không được để trống")
    private Long roundId;

    @NotNull(message = "criteriaId không được để trống")
    private Long criteriaId;

    @NotNull(message = "Điểm đạt được cho tiêu chí không được để trống")
    @DecimalMin(value = "0.01", message = "Điểm đạt được cho tiêu chí phải lớn hơn 0")
    private Double score;

    private String comments;

    private LocalDate evaluationDate;
}
