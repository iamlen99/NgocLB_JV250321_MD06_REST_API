package ra.exercise;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @NotBlank(message = "Tên người dùng không được để trống")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Định dạng email không hợp lệ")
    private String email;

    @Min(value = 1, message = "Vui lòng nhập đánh giá từ 1 đến 5")
    @Max(value = 5, message = "Vui lòng nhập đánh giá từ 1 đến 5")
    private byte rating;

    @Size(max = 200,message = "Bình luận không được quá 200 ký tự")
    private String comment;
}
