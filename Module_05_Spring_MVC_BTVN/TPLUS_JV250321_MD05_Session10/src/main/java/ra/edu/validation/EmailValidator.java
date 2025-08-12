package ra.edu.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ra.edu.model.repository.CustomerRepository;

public class EmailValidator implements ConstraintValidator<NotExistEmail, String> {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isBlank()) {
            return true;
        }
        return customerRepository.emailExist(value);
    }
}
