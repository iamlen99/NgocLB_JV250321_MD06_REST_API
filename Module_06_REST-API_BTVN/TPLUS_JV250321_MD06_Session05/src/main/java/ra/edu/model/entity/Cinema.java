package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "cinemas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cinema name cannot be blank")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Min(value = 1, message = "Capacity must be greater than 0")
    private Integer capacity;

    @ManyToMany(mappedBy = "cinemas", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();
}
