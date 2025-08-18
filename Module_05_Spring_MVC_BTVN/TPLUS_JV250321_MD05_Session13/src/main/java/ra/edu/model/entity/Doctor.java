package ra.edu.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 70, nullable = false)
    private String fullName;
    @Column(length = 50, nullable = false)
    private String specialization;
    @Column(length = 250, nullable = false)
    private String contact;
    @Column(length = 20, nullable = false, unique = true)
    private String phone;
    private Boolean status;

    @PrePersist
    public void prePersist() {
        this.status = Boolean.TRUE;
    }
}
