package ra.edu.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ra.edu.service.CustomerService;
import ra.edu.validation.NotExistUsername;

public class NotExistUsernameImpl implements ConstraintValidator<NotExistUsername, String> {
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return customerService.checkValidUsername(value.trim());
    }
}
