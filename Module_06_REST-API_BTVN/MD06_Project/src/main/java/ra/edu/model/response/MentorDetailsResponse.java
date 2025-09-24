package ra.edu.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDetailsResponse {
    private Long mentorId;

    private String fullName;

    private String email;

    private String phone;

    private String department;

    private String academicRank;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
