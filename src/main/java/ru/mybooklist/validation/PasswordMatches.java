package ru.mybooklist.validation;

import ru.mybooklist.validation.validator.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Daniyar Itegulov
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "{Password.user.dontMatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
