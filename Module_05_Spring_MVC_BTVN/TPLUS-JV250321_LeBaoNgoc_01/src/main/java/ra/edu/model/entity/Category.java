package ra.edu.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int categoryId;
    @NotBlank(message = "Tên danh mục không được để trống")
    private String categoryName;
    private String description;
    private Boolean status;
}
