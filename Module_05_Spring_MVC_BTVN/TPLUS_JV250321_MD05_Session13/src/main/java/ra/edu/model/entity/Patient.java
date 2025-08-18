package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 70, nullable = false)
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;
    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Thông tin liên lạc không được để trống")
    private String contact;
    @Column(length = 20, nullable = false)
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;
    private Boolean status;

    @PrePersist
    public void prePersist() {
        this.status = Boolean.TRUE;
    }
}
