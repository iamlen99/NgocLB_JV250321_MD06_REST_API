package ra.edu.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationCriteriaRequest {
    @Column(unique = true, nullable = false, length = 200)
    @NotBlank(message = "Tên tiêu chí đánh giá không được để trống")
    private String criterionName;

    private String description;

    @NotNull(message = "Tên tiêu chí đánh giá không được để trống")
    @DecimalMin(value = "0.01", message = "Điểm tối đa cho tiêu chí phải lớn hơn 0")
    @DecimalMax(value = "100.00", message = "Điểm tối đa cho tiêu chí phải nhỏ hơn 100")
    private Double maxScore;
}
