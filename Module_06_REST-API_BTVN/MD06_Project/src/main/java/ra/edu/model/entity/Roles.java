package ra.edu.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
