package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Tên dịch vụ không được để trống")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Mô tả dịch vụ không được để trống")
    private String description;
    @Column(nullable = false)
    @NotNull(message = "Giá dịch vụ không được để trống")
    @Min(value = 0, message = "Giá dịch vụ phải lớn hơn 0")
    private Double price;
    @Column(nullable = false)
    @NotNull(message = "Thời gian dịch vụ không được để trống")
    @Min(value = 1, message = "Thời gian dịch vụ tối thiểu là 1")
    private Integer duration;
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;
}
