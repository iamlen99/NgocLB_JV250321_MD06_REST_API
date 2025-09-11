package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Doctor name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Specialty cannot be blank")
    @Column(nullable = false)
    private String specialty;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^(\\+84|0)[0-9]{9,10}$", message = "Phone number is invalid")
    @Column(nullable = false, unique = true)
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    @Column(nullable = false, unique = true)
    private String email;

    private String avatar;
}
