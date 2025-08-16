package ra.edu.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ra.edu.service.UserService;
import ra.edu.validation.NotExistEmail;

public class NotExistEmailValidation implements ConstraintValidator<NotExistEmail, String> {
    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return userService.findByEmail(value.trim()).isEmpty();
    }
}
