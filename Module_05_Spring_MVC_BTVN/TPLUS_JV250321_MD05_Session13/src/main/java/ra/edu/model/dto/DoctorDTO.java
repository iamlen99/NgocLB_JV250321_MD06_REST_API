package ra.edu.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Long id;
    @NotBlank(message = "Họ tên bác sĩ không được để trống!")
    private String fullName;
    @NotBlank(message = "Chuyên khoa bác sĩ không được để trống!")
    private String specialization;
    @NotBlank(message = "Thông tin liên lạc không được để trống!")
    @Pattern(regexp = "^0[39]\\d{7}$", message = "Số điện thoại không hợp lệ")
    private String contact;
    @NotBlank(message = "Số điện thoại không được để trống!")
    private String phone;
}
