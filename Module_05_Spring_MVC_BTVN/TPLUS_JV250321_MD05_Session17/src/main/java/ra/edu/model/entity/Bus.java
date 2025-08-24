package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Biển số xe không được để trống")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "Sức chứa của xe không được để trống")
    private Integer capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeSeat typeSeat;
}
