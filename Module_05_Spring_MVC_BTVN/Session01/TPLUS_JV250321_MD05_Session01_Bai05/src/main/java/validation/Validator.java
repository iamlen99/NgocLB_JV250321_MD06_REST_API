package validation;

import java.util.regex.Pattern;

public class Validator {
    public static String isValidUsername (String username) {
        if (username == null || username.trim().isEmpty()) {
            return "Username is required";
        }
        return null;
    }

    public static String isValidPassword (String password) {
        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,}";
        if (password == null || password.trim().isEmpty()) {
            return "Password is required";
        }
        if (!Pattern.matches(passwordRegex, password)) {
            return "Invalid password";
        }
        return null;
    }

    public static String isValidEmail (String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|vn)$";
        if (email == null || email.trim().isEmpty()) {
            return "Password is required";
        }
        if (!Pattern.matches(emailRegex, email)) {
            return "Invalid email";
        }
        return null;
    }
}
