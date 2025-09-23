package ra.edu.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ra.edu.model.entity.Users;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponse {
    private Long mentorId;

    private String fullName;

    private String department;

    private String academicRank;
}
