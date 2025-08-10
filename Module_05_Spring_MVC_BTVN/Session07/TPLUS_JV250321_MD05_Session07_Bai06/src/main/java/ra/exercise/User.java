package ra.exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ra.exercise.validation.ValidatePhoneNumber;

public class User {
    @NotBlank(message = "Số điện thoại không được để trống")
    @ValidatePhoneNumber
    private String phoneNumber;

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
