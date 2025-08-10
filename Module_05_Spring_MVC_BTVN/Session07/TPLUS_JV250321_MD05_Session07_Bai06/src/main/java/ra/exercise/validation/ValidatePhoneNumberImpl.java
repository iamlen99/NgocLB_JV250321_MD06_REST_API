package ra.exercise.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidatePhoneNumberImpl implements ConstraintValidator<ValidatePhoneNumber, String> {
    private static final String phoneRegex = "^0\\d{9}$";
    @Override
    public void initialize(ValidatePhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return Pattern.matches(phoneRegex, value.trim());
    }
}
