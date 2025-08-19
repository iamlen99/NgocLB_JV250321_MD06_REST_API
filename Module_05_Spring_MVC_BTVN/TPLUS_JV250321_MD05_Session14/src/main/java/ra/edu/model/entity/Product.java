package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productName;
    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (price > 0)")
    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0.01", message = "Giá sản phẩm phải lớn hơn 0")
    private Double price;
    @Column(nullable = false, columnDefinition = "INT CHECK (stock > 0)")
    @NotNull(message = "Số lượng hàng trong kho không được để trống")
    @Min(value = 0, message = "Số lượng hàng trong kho phải lớn hơn hoặc bằng 0")
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
