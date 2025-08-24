package ra.edu.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ra.edu.service.CustomerService;
import ra.edu.validation.NotExistEmail;
import ra.edu.validation.NotExistUsername;

public class NotExistEmailImpl implements ConstraintValidator<NotExistEmail, String> {
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return customerService.checkValidEmail(value.trim());
    }
}
