package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Ngày hẹn không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Ngày hẹn không hợp lệ")
    private LocalDate date;
    @Column(nullable = false)
    @NotNull(message = "Giờ hẹn không được để trống")
    @Min(value = 0, message = "Thời gian hẹn phải từ 0 đến 24h")
    @Max(value = 24, message = "Thời gian hẹn phải từ 0 đến 24h")
    private Integer hour;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
