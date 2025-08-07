package ra.exercise.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Category {
    private int id;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = 50, message = "Độ dài tối đa là 50 ký tự")
    private String categoryName;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    private boolean status;
    public Category() {
    }

    public Category(int id, String categoryName, String description, boolean status) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
