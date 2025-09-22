package ra.edu.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ra.edu.model.entity.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorRequest {
    private Long mentorId;

    private String department;

    private String academicRank;
}
