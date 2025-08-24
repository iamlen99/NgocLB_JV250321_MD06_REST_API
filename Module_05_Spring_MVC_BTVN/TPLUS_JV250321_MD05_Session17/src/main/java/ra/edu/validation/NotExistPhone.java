package ra.edu.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ra.edu.validation.validator.NotExistPhoneImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {NotExistPhoneImpl.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(NotExistPhone.List.class)
public @interface NotExistPhone {
    String message() default "Số điện thoại đã tồn tại";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @NotExistPhone} constraints on the same element.
     *
     * @see NotExistPhone
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        NotExistPhone[] value();
    }
}
