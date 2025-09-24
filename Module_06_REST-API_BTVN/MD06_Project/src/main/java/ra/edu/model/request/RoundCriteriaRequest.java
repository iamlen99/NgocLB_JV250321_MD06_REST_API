package ra.edu.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.entity.EvaluationCriteria;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundCriteriaRequest {
    @NotNull(message = "Trọng số của tiêu chí trong đợt đánh giá không được để trống")
    @DecimalMin(value = "0.01", message = "Trọng số của tiêu chí trong đợt đánh giá phải lớn hơn 0")
    private Double weight;

    @NotNull(message = "RoundId không được để trống")
    private Long roundId;

    @NotNull(message = "criteriaId không được để trống")
    private Long criteriaId;
}
