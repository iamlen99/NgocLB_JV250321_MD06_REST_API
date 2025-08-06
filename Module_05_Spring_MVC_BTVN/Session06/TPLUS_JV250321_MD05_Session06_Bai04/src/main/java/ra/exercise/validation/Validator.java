package ra.exercise.validation;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {
    public String isValidUsername(String username) {
        if(username == null || username.trim().isEmpty()) {
            return  "Ten nguoi dung khong duoc de trong!";
        }
        return "";
    }

    public String isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if(email == null || email.trim().isEmpty()) {
            return  "Email khong duoc de trong!";
        }

        if(!Pattern.matches(emailRegex, email)) {
            return  "Email khong dung dinh dang!";
        }
        return "";
    }

    public String isValidPhoneNumber (String phone) {
        String phoneRegex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-5]|9[0-9])[0-9]{7}$";
        if(phone == null || phone.trim().isEmpty()) {
            return  "So dien thoai khong duoc de trong!";
        }

        if(!Pattern.matches(phoneRegex, phone)) {
            return  "So dien thoai khong dung dinh dang!";
        }
        return "";
    }
}
