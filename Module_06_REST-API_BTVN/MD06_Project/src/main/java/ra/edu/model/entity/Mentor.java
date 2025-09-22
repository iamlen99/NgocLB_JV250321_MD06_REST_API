package ra.edu.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mentors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mentor {
    @Id
    private Long mentorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "mentor_id")
    private Users user;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false, length = 50)
    private String academicRank;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
